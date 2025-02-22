package luanvan.com.course_registration_system.dto.request;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocKyRequest {
    @Id
    String maHocKy; // Primary Key
    int namHoc;
    String tenHocKy;
    LocalDate thoiGianBatDau;
    LocalDate thoiGianKetThuc;
}
