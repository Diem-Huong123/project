package luanvan.com.course_registration_system.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class KeHoachHocTapRequest {
    @NotBlank(message = "Mã sinh viên không được để trống")
    private String maSinhVien;

    @NotBlank(message = "Mã học kỳ không được để trống")
    private String maHocKy;

    @NotNull(message = "Danh sách học phần không được để trống")
    private List<String> maHocPhans;
}
