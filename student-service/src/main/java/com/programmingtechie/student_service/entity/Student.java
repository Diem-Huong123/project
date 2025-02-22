package com.programmingtechie.student_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;

@Entity
@Table(name = "students") // Đặt tên bảng rõ ràng
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
    @Id
    String mssv; // MSSV là khóa chính

    @Column(nullable = false)
    String fullName; // Họ tên sinh viên

    String email; // Email sinh viên

    LocalDate dob; // Ngày sinh

    String gender; // Giới tính

    @Column(nullable = false)
    String maLop; // Mã lớp

    @Column(nullable = false)
    String maNganh; // Mã ngành học

    @Column(nullable = false)
    String khoaHoc; // Khóa học (VD: K65, K66, ...)
}
