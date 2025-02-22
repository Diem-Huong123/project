package com.programmingtechie.student_service.reponsitory;

import com.programmingtechie.student_service.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}
