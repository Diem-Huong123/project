package com.programmingtechie.course_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @Column(nullable = false, unique = true, length = 20)
    String maHocPhan;  // Mã học phần (Khóa chính)

    @Column(nullable = false, length = 100)
    String tenHocPhan;  // Tên học phần

    @Column(nullable = false, length = 5)
    String soTinChi;  // Số tín chỉ

    @Column(length = 500)
    String moTa;  // Mô tả học phần
}
