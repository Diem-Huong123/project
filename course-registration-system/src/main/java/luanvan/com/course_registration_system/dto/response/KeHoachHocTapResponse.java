package luanvan.com.course_registration_system.dto.response;

import lombok.*;
import luanvan.com.course_registration_system.entity.KeHoachHocTap;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KeHoachHocTapResponse {
    private Long id;
    private String maSinhVien;
    private String maHocKy;

    private List<HocPhanResponse> maHocPhans;

}
