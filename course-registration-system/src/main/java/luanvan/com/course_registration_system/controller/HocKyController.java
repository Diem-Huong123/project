package luanvan.com.course_registration_system.controller;

import luanvan.com.course_registration_system.dto.request.HocKyRequest;
import luanvan.com.course_registration_system.dto.response.HocKyResponse;
import luanvan.com.course_registration_system.dto.response.KeHoachHocTapResponse;
import luanvan.com.course_registration_system.service.HocKyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hocky")
@RequiredArgsConstructor
public class HocKyController {

    private final HocKyService hocKyService;

    @PostMapping
    public ResponseEntity<HocKyResponse> createHocKy(@RequestBody HocKyRequest request) {
        return ResponseEntity.ok(hocKyService.saveHocKy(request));
    }

    @GetMapping
    public ResponseEntity<List<HocKyResponse>> getAllHocKys() {
        return ResponseEntity.ok(hocKyService.getAllHocKys());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HocKyResponse> getHocKyById(@PathVariable String id) {
        return ResponseEntity.ok(hocKyService.getHocKyById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHocKy(@PathVariable String id) {
        hocKyService.deleteHocKy(id);
        return ResponseEntity.ok("Xóa học kỳ thành công!");
    }

}
