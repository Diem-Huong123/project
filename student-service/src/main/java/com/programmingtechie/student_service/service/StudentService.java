package com.programmingtechie.student_service.service;

import com.programmingtechie.student_service.dto.request.StudentRequest;
import com.programmingtechie.student_service.dto.response.StudentResponse;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentByMssv(String mssv);
    StudentResponse createStudent(StudentRequest request);
    StudentResponse updateStudent(String mssv, StudentRequest request);
    void deleteStudent(String mssv);
}

