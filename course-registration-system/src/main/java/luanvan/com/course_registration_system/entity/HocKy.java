package luanvan.com.course_registration_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

@Entity
@Table(name = "hoc_ky")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HocKy {
    @Id
    String maHocKy; // Primary Key
    int namHoc;
    String tenHocKy;
    LocalDate thoiGianBatDau;
    LocalDate thoiGianKetThuc;
}
