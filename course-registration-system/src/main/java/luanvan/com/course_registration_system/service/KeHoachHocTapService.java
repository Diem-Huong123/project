package luanvan.com.course_registration_system.service;

import jakarta.transaction.Transactional;
import luanvan.com.course_registration_system.dto.request.KeHoachHocTapRequest;
import luanvan.com.course_registration_system.dto.response.HocPhanResponse;
import luanvan.com.course_registration_system.dto.response.KeHoachHocTapResponse;
import luanvan.com.course_registration_system.entity.HocKy;
import luanvan.com.course_registration_system.entity.KeHoachHocTap;
import luanvan.com.course_registration_system.exception.AppException;
import luanvan.com.course_registration_system.exception.ErrorCode;
import luanvan.com.course_registration_system.repository.HocKyRepository;
import luanvan.com.course_registration_system.repository.KeHoachHocTapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeHoachHocTapService {

    @Autowired
    private KeHoachHocTapRepository keHoachHocTapRepository;

    @Autowired
    private HocKyRepository hocKyRepository;

    @Autowired
    private CourseServiceClient courseServiceClient;

    /**
     * Chuyển đổi entity sang DTO response
     */
    private KeHoachHocTapResponse convertToResponse(KeHoachHocTap keHoachHocTap) {
        List<HocPhanResponse> hocPhans = keHoachHocTap.getMaHocPhans().stream()
                .map(courseServiceClient::getHocPhanByMaHocPhan) // Gọi course-service lấy thông tin học phần
                .collect(Collectors.toList());

        return new KeHoachHocTapResponse(
                keHoachHocTap.getId(),
                keHoachHocTap.getMaSinhVien(),
                keHoachHocTap.getHocKy().getMaHocKy(),
                hocPhans
        );
    }

    /**
     * API: Lấy danh sách kế hoạch học tập chi tiết theo MSSV
     */
    public List<KeHoachHocTapResponse> getKeHoachHocTapChiTietByMaSinhVien(String maSinhVien) {
        List<KeHoachHocTap> danhSachKeHoach = keHoachHocTapRepository.findByMaSinhVien(maSinhVien);
        return danhSachKeHoach.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    /**
     * API: Thêm học phần vào kế hoạch học tập
     */
    public KeHoachHocTapResponse addHocPhanToKeHoach1(String maSinhVien, String maHocKy, String maHocPhan) {
        // Tìm kế hoạch học tập theo mã sinh viên và mã học kỳ
        KeHoachHocTap keHoachHocTap = keHoachHocTapRepository
                .findByMaSinhVienAndHocKy_MaHocKy(maSinhVien, maHocKy)
                .orElseThrow(() -> new AppException(ErrorCode.KE_HOACH_NOT_FOUND));

        // Tìm học phần
        HocPhanResponse hocPhan = courseServiceClient.getHocPhanByMaHocPhan(maHocPhan);

        if (hocPhan == null) {
            throw new AppException(ErrorCode.HOC_PHAN_NOT_FOUND);
        }

        // Thêm học phần vào kế hoạch
        if (!keHoachHocTap.getMaHocPhans().contains(maHocPhan)) {
            keHoachHocTap.getMaHocPhans().add(maHocPhan);
            keHoachHocTap = keHoachHocTapRepository.save(keHoachHocTap);
        }

        // Chuyển đổi sang DTO và trả về
        return convertToResponse(keHoachHocTap);
    }


    @Transactional
    public KeHoachHocTapResponse addHocPhanToKeHoach(Long id, String maHocPhan) {
        // Lấy kế hoạch học tập từ database
        KeHoachHocTap keHoachHocTap = keHoachHocTapRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.KE_HOACH_NOT_FOUND));

        // Gọi course-service để kiểm tra mã học phần có tồn tại không
        HocPhanResponse hocPhan = courseServiceClient.getHocPhanByMaHocPhan(maHocPhan);
        if (hocPhan == null) {
            throw new AppException(ErrorCode.HOC_PHAN_NOT_FOUND);
        }

        // Thêm mã học phần vào danh sách (nếu chưa có)
        if (!keHoachHocTap.getMaHocPhans().contains(maHocPhan)) {
            keHoachHocTap.getMaHocPhans().add(maHocPhan);
            keHoachHocTap = keHoachHocTapRepository.save(keHoachHocTap);
        }

        return convertToResponse(keHoachHocTap);
    }

    /**
     * API: Tạo kế hoạch học tập mới
     */
    @Transactional
    public KeHoachHocTapResponse createKeHoachHocTap(KeHoachHocTapRequest request) {
        // Kiểm tra học kỳ có tồn tại không
        HocKy hocKy = hocKyRepository.findByMaHocKy(request.getMaHocKy())
                .orElseThrow(() -> new AppException(ErrorCode.HOC_KY_NOT_FOUND));

        // Tạo kế hoạch học tập mới, KHÔNG bắt buộc phải có mã học phần
        KeHoachHocTap keHoachHocTap = new KeHoachHocTap();
        keHoachHocTap.setMaSinhVien(request.getMaSinhVien());
        keHoachHocTap.setHocKy(hocKy);
        keHoachHocTap.setMaHocPhans(List.of()); // Ban đầu chưa có mã học phần nào

        // Lưu vào database
        keHoachHocTap = keHoachHocTapRepository.save(keHoachHocTap);

        return convertToResponse(keHoachHocTap);
    }

    /**
     * API: Lấy danh sách kế hoạch học tập theo MSSV
     */
    public List<KeHoachHocTapResponse> getKeHoachByMaSinhVien(String maSinhVien) {
        List<KeHoachHocTap> danhSachKeHoach = keHoachHocTapRepository.findByMaSinhVien(maSinhVien);
        return danhSachKeHoach.stream().map(this::convertToResponse).collect(Collectors.toList());
    }

    @Transactional
    public KeHoachHocTapResponse removeHocPhanFromKeHoach(String maSinhVien, String maHocKy, String maHocPhan) {
        // Tìm kế hoạch học tập của sinh viên trong học kỳ cụ thể
        KeHoachHocTap keHoachHocTap = keHoachHocTapRepository
                .findByMaSinhVienAndHocKy_MaHocKy(maSinhVien, maHocKy)
                .orElseThrow(() -> new AppException(ErrorCode.KE_HOACH_NOT_FOUND));

        // Kiểm tra xem học phần có tồn tại không
        if (!keHoachHocTap.getMaHocPhans().contains(maHocPhan)) {
            throw new AppException(ErrorCode.HOC_PHAN_NOT_FOUND);
        }

        // Xóa học phần khỏi danh sách
        keHoachHocTap.getMaHocPhans().remove(maHocPhan);

        // Lưu thay đổi vào database
        keHoachHocTap = keHoachHocTapRepository.save(keHoachHocTap);

        return convertToResponse(keHoachHocTap);
    }


}
