package luanvan.com.course_registration_system.controller;

import luanvan.com.course_registration_system.dto.request.KeHoachHocTapRequest;
import luanvan.com.course_registration_system.dto.response.KeHoachHocTapResponse;
import luanvan.com.course_registration_system.service.KeHoachHocTapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/kehoachhoctap")
public class KeHoachHocTapController {

    @Autowired
    private KeHoachHocTapService keHoachHocTapService;

    @PostMapping
    public ResponseEntity<KeHoachHocTapResponse> createKeHoachHocTap(@RequestBody KeHoachHocTapRequest request) {
        return ResponseEntity.ok(keHoachHocTapService.createKeHoachHocTap(request));
    }
    @PutMapping("/{id}/add-hocphan/{maHocPhan}")
    public ResponseEntity<KeHoachHocTapResponse> addHocPhanToKeHoach(
            @PathVariable Long id,
            @PathVariable String maHocPhan) {
        return ResponseEntity.ok(keHoachHocTapService.addHocPhanToKeHoach(id, maHocPhan));
    }
    @PutMapping("/{maSinhVien}/hoc-ky/{maHocKy}/add-hocphan/{maHocPhan}")
    public ResponseEntity<KeHoachHocTapResponse> addHocPhanToKeHoach1(
            @PathVariable String maSinhVien,
            @PathVariable String maHocKy,
            @PathVariable String maHocPhan) {
        return ResponseEntity.ok(keHoachHocTapService.addHocPhanToKeHoach1(maSinhVien, maHocKy, maHocPhan));
    }

    //xóa mahocphan theo hocki theo mssv
    @DeleteMapping("/{maSinhVien}/hoc-ky/{maHocKy}/remove-hocphan/{maHocPhan}")
    public ResponseEntity<?> removeHocPhanFromKeHoach(
            @PathVariable String maSinhVien,
            @PathVariable String maHocKy,
            @PathVariable String maHocPhan) {
        keHoachHocTapService.removeHocPhanFromKeHoach(maSinhVien, maHocKy, maHocPhan);
        return ResponseEntity.ok().build();
    }



    @GetMapping("/{maSinhVien}")
    public ResponseEntity<List<KeHoachHocTapResponse>> getKeHoachByMaSinhVien(@PathVariable String maSinhVien) {
        return ResponseEntity.ok(keHoachHocTapService.getKeHoachByMaSinhVien(maSinhVien));
    }

    @GetMapping("/chi-tiet/{maSinhVien}")
    public ResponseEntity<List<KeHoachHocTapResponse>> getKeHoachChiTiet(@PathVariable String maSinhVien) {
        return ResponseEntity.ok(keHoachHocTapService.getKeHoachHocTapChiTietByMaSinhVien(maSinhVien));
    }

    @GetMapping("/{maSinhVien}/{maHocKy}")
    public ResponseEntity<KeHoachHocTapResponse> getKeHoachHocTapByMaSinhVienAndMaHocKy(
            @PathVariable String maSinhVien,
            @PathVariable String maHocKy) {
        KeHoachHocTapResponse response = keHoachHocTapService.getKeHoachHocTapByMaSinhVienAndMaHocKy(maSinhVien, maHocKy);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/chi-tiet/{maSinhVien}")
//    public ResponseEntity<List<KeHoachHocTapResponse>> getKeHoachChiTiet(@PathVariable String maSinhVien) {
//        return ResponseEntity.ok(keHoachHocTapService.getKeHoachHocTapChiTietByMaSinhVien(maSinhVien));
//    }

//
//    @PutMapping("/{id}")
//    public ResponseEntity<KeHoachHocTapResponse> updateKeHoachHocTap(@PathVariable Long id, @RequestBody KeHoachHocTapRequest request) {
//        return ResponseEntity.ok(keHoachHocTapService.updateKeHoachHocTap(id, request));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteKeHoachHocTap(@PathVariable Long id) {
//        keHoachHocTapService.deleteKeHoachHocTap(id);
//        return ResponseEntity.ok("Xóa thành công!");
//    }
//
//    public KeHoachHocTapController(KeHoachHocTapService keHoachHocTapService) {
//        this.keHoachHocTapService = keHoachHocTapService;
//    }
//
//    @GetMapping("/{id}/hocphans")
//    public ResponseEntity<List<HocPhanResponse>> getHocPhansByKeHoachId(@PathVariable Long id) {
//        List<HocPhanResponse> hocPhans = keHoachHocTapService.getHocPhanDetails(id);
//        return ResponseEntity.ok(hocPhans);
//    }

}
