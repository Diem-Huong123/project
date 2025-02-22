package com.programmingtechie.course_service.service.Impl;

import com.programmingtechie.course_service.dto.request.CourseRequest;
import com.programmingtechie.course_service.dto.response.CourseResponse;
import com.programmingtechie.course_service.entity.Course;
import com.programmingtechie.course_service.exception.AppException;
import com.programmingtechie.course_service.exception.ErrorCode;
import com.programmingtechie.course_service.repository.CourseRepository;
import com.programmingtechie.course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseResponse> getAllCourses() {
        List<Course> courses = courseRepository.findAll(); // Trả về List<Course>

        // Chuyển đổi List<Course> thành List<CourseResponse>
        List<CourseResponse> courseResponses = courses.stream()
                .map(course -> new CourseResponse(
                        course.getMaHocPhan(),
                        course.getTenHocPhan(),
                        course.getSoTinChi(),
                        course.getMoTa()
                ))
                .collect(Collectors.toList());

        return courseResponses;
    }


    @Override
    public CourseResponse getCourseById(String maHocPhan) {
        Course course = courseRepository.findById(maHocPhan)
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));
        return new CourseResponse(course.getMaHocPhan(), course.getTenHocPhan(), course.getSoTinChi(), course.getMoTa());
    }

    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {
        if (courseRepository.existsById(courseRequest.getMaHocPhan())) {
            throw new AppException(ErrorCode.COURSE_ALREADY_EXISTS);
        }

        Course course = new Course(
                courseRequest.getMaHocPhan(),
                courseRequest.getTenHocPhan(),
                courseRequest.getSoTinChi(),
                courseRequest.getMoTa());

        courseRepository.save(course);
        return new CourseResponse(course.getMaHocPhan(), course.getTenHocPhan(), course.getSoTinChi(), course.getMoTa());
    }

    @Override
    public CourseResponse updateCourse(String maHocPhan, CourseRequest courseRequest) {
        Course course = courseRepository.findById(maHocPhan)
                .orElseThrow(() -> new AppException(ErrorCode.COURSE_NOT_FOUND));

        course.setTenHocPhan(courseRequest.getTenHocPhan());
        course.setSoTinChi(courseRequest.getSoTinChi());
        course.setMoTa(courseRequest.getMoTa());

        courseRepository.save(course);
        return new CourseResponse(course.getMaHocPhan(), course.getTenHocPhan(), course.getSoTinChi(), course.getMoTa());
    }

    @Override
    public void deleteCourse(String maHocPhan) {
        if (!courseRepository.existsById(maHocPhan)) {
            throw new AppException(ErrorCode.COURSE_NOT_FOUND);
        }
        courseRepository.deleteById(maHocPhan);
    }

    public List<CourseResponse> getCoursesByIds(List<String> ids) {
        List<Course> courses = courseRepository.findByMaHocPhanIn(ids);
        return courses.stream().map(course ->
                        new CourseResponse(course.getMaHocPhan(), course.getTenHocPhan(), course.getSoTinChi(), course.getMoTa()))
                .collect(Collectors.toList());
    }


}
