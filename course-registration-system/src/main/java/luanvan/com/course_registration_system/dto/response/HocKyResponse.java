package luanvan.com.course_registration_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class HocKyResponse {
    private String maHocKy;
    private int namHoc;
    private String tenHocKy;
    private LocalDate thoiGianBatDau;
    private LocalDate thoiGianKetThuc;
}
