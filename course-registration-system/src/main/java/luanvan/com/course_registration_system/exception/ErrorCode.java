package luanvan.com.course_registration_system.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    HOC_KY_NOT_FOUND("Không tìm thấy học kỳ"),
    DUPLICATE_HOC_KY("Học kỳ đã tồn tại"),
    INVALID_DATA("Dữ liệu không hợp lệ"),
    SINH_VIEN_NOT_FOUND("Không tìm thấy sinh viên"),
    KE_HOACH_NOT_FOUND("Không tìm thấy kế hoạch học tập"),
    HOC_PHAN_NOT_FOUND("Không tìm thấy học phần");


    private final String message;
}
