package com.programmingtechie.course_service.controller;

import com.programmingtechie.course_service.dto.request.CourseRequest;
import com.programmingtechie.course_service.dto.response.CourseResponse;
import com.programmingtechie.course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")  // Thêm prefix URL để rõ ràng hơn
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{maHocPhan}") //lay tt hp theo mahp
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable String maHocPhan) {
        return ResponseEntity.ok(courseService.getCourseById(maHocPhan));
    }

    @PostMapping //them hp moi
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
        return ResponseEntity.ok(courseService.createCourse(courseRequest));
    }

    @PutMapping("/{maHocPhan}") //capnhat hp theo mahp
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable String maHocPhan, @RequestBody CourseRequest courseRequest) {
        return ResponseEntity.ok(courseService.updateCourse(maHocPhan, courseRequest));
    }

    @DeleteMapping("/{maHocPhan}") //xoa hp theo mahp
    public ResponseEntity<String> deleteCourse(@PathVariable String maHocPhan) {
        courseService.deleteCourse(maHocPhan);
        return ResponseEntity.ok("Học phần đã được xóa thành công!");
    }

    @GetMapping("/filter")
    public ResponseEntity<List<CourseResponse>> getCoursesByIds(@RequestParam List<String> ids) {
        return ResponseEntity.ok(courseService.getCoursesByIds(ids));
    }

}
