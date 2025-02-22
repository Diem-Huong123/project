package com.programmingtechie.course_service.service;

import com.programmingtechie.course_service.dto.request.CourseRequest;
import com.programmingtechie.course_service.dto.response.CourseResponse;
import com.programmingtechie.course_service.entity.Course;

import java.util.List;

public interface CourseService {
    List<CourseResponse> getAllCourses();
    CourseResponse getCourseById(String maHocPhan);
    CourseResponse createCourse(CourseRequest courseRequest);
    CourseResponse updateCourse(String maHocPhan, CourseRequest courseRequest);
    void deleteCourse(String maHocPhan);
    List<CourseResponse> getCoursesByIds(List<String> ids);
}
