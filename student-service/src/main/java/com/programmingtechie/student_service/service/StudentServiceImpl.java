package com.programmingtechie.student_service.service;

import com.programmingtechie.student_service.dto.request.StudentRequest;
import com.programmingtechie.student_service.dto.response.StudentResponse;
import com.programmingtechie.student_service.entity.Student;
import com.programmingtechie.student_service.exception.StudentAlreadyExistsException;
import com.programmingtechie.student_service.exception.StudentNotFoundException;
import com.programmingtechie.student_service.reponsitory.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<StudentResponse> getAllStudents(){
        return studentRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public StudentResponse getStudentByMssv(String mssv) {
        Student student = studentRepository.findById(mssv)
                .orElseThrow(() -> new StudentNotFoundException("Không tìm thấy sinh viên có MSSV: " + mssv));
        return convertToResponse(student);
    }


    @Override
    public StudentResponse createStudent(StudentRequest request) {
        if (studentRepository.existsById(request.getMssv())) {
            throw new StudentAlreadyExistsException("MSSV đã tồn tại: " + request.getMssv());
        }

        Student student = convertToEntity(request);
        studentRepository.save(student);

        return convertToResponse(student);
    }

    @Override
    public StudentResponse updateStudent(String mssv, StudentRequest request) {
        Student student = studentRepository.findById(mssv)
                .orElseThrow(() -> new StudentNotFoundException("Không tìm thấy sinh viên có MSSV: " + mssv));

        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        student.setDob(request.getDob());
        student.setGender(request.getGender());
        student.setMaLop(request.getMaLop());
        student.setMaLop(request.getMaNganh());
        student.setMaLop(request.getKhoaHoc());
        studentRepository.save(student);
        return convertToResponse(student);
    }


    private Student convertToEntity(StudentRequest request) {
        return new Student(
                request.getMssv(),
                request.getFullName(),
                request.getEmail(),
                request.getDob(),
                request.getGender(),
                request.getMaLop(),
                request.getMaNganh(),
                request.getKhoaHoc()
        );
    }

    private StudentResponse convertToResponse(Student student) {
        return new StudentResponse(
                student.getMssv(),
                student.getFullName(),
                student.getEmail(),
                student.getDob(),
                student.getGender(),
                student.getMaLop(),
                student.getMaNganh(),
                student.getKhoaHoc()
        );
    }
    @Override
    public void deleteStudent(String mssv) {
        Student student = studentRepository.findById(mssv)
                .orElseThrow(() -> new StudentNotFoundException("Không tìm thấy sinh viên có MSSV: " + mssv));

        studentRepository.delete(student);
    }


}
