package com.programmingtechie.student_service.dto.response;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponse {
    String mssv;
    String fullName;
    String email;
    LocalDate dob;
    String gender;
    String maLop; // Mã lớp
    String maNganh; // Mã ngành học
    String khoaHoc;

}
