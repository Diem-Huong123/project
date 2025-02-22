package com.programmingtechie.course_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseRequest {

    @NotBlank(message = "Mã học phần không được để trống")
    @Size(max = 20, message = "Mã học phần không được quá 20 ký tự")
    String maHocPhan;

    @NotBlank(message = "Tên học phần không được để trống")
    String tenHocPhan;

    @NotBlank(message = "Số tín chỉ không được để trống")
    String soTinChi;

    String moTa;
}
