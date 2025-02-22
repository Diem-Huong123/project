package com.programmingtechie.course_service.repository;

import com.programmingtechie.course_service.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> findByMaHocPhanIn(List<String> maHocPhan);
}
