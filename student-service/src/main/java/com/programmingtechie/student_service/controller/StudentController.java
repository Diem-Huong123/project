package com.programmingtechie.student_service.controller;

import com.programmingtechie.student_service.dto.request.StudentRequest;
import com.programmingtechie.student_service.dto.response.StudentResponse;
import com.programmingtechie.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // Lấy danh sách tất cả sinh viên
    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // Lấy thông tin sinh viên theo MSSV
    @GetMapping("/{mssv}")
    public ResponseEntity<StudentResponse> getStudentByMssv(@PathVariable String mssv) {
        return ResponseEntity.ok(studentService.getStudentByMssv(mssv));
    }

    // Thêm sinh viên mới
    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.createStudent(request));
    }

    // Cập nhật thông tin sinh viên
    @PutMapping("/{mssv}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable String mssv, @RequestBody StudentRequest request) {
        return ResponseEntity.ok(studentService.updateStudent(mssv, request));
    }

    //Xóa sinh viên theo MSSV
    @DeleteMapping("/{mssv}")
    public ResponseEntity<String> deleteStudent(@PathVariable String mssv) {
        studentService.deleteStudent(mssv);
        return ResponseEntity.ok("Đã xóa sinh viên có MSSV: " + mssv);
    }
}
