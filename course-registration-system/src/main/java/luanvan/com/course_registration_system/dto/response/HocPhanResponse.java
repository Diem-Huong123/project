package luanvan.com.course_registration_system.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HocPhanResponse {
    private String maHocPhan;
    private String tenHocPhan;
    private int soTinChi;
}
