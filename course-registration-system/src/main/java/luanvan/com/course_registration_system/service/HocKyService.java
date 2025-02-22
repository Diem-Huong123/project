package luanvan.com.course_registration_system.service;

import luanvan.com.course_registration_system.dto.request.HocKyRequest;
import luanvan.com.course_registration_system.dto.response.HocKyResponse;
import luanvan.com.course_registration_system.entity.HocKy;
import luanvan.com.course_registration_system.exception.AppException;
import luanvan.com.course_registration_system.exception.ErrorCode;
import luanvan.com.course_registration_system.repository.HocKyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HocKyService {
    private final HocKyRepository hocKyRepository;

    // 🔹 Tạo học kỳ
    public HocKyResponse saveHocKy(HocKyRequest request) {
        if (hocKyRepository.existsById(request.getMaHocKy())) {
            throw new AppException(ErrorCode.DUPLICATE_HOC_KY);
        }

        HocKy hocKy = new HocKy();
        hocKy.setMaHocKy(request.getMaHocKy());
        hocKy.setNamHoc(request.getNamHoc());
        hocKy.setTenHocKy(request.getTenHocKy());
        hocKy.setThoiGianBatDau(request.getThoiGianBatDau());
        hocKy.setThoiGianKetThuc(request.getThoiGianKetThuc());

        HocKy savedHocKy = hocKyRepository.save(hocKy);
        return convertToResponse(savedHocKy);
    }

    // 🔹 Lấy tất cả học kỳ
    public List<HocKyResponse> getAllHocKys() {
        List<HocKy> hocKys = hocKyRepository.findAll();
        return hocKys.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // 🔹 Lấy học kỳ theo ID
    public HocKyResponse getHocKyById(String id) {
        HocKy hocKy = hocKyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.HOC_KY_NOT_FOUND));

        return convertToResponse(hocKy);
    }

    // 🔹 Xóa học kỳ theo ID
    public void deleteHocKy(String id) {
        HocKy hocKy = hocKyRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.HOC_KY_NOT_FOUND));

        hocKyRepository.delete(hocKy);
    }

    // 🔹 Chuyển đổi Entity → Response DTO
    private HocKyResponse convertToResponse(HocKy hocKy) {
        return new HocKyResponse(
                hocKy.getMaHocKy(),
                hocKy.getNamHoc(),
                hocKy.getTenHocKy(),
                hocKy.getThoiGianBatDau(),
                hocKy.getThoiGianKetThuc()
        );
    }
}
