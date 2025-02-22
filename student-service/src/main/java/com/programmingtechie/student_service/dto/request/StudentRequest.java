package com.programmingtechie.student_service.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequest {
    String mssv;
    String fullName;
    String email;
    LocalDate dob;
    String gender;
    String maLop; // Mã lớp
    String maNganh; // Mã ngành học

    String khoaHoc; // Khóa học (VD: K65, K66, ...)

    
}
