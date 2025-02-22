package com.programmingtechie.exception;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    USER_EXISTED(HttpStatus.BAD_REQUEST, "MSSV đã tồn tại."),
    USER_NOT_EXISTED(HttpStatus.NOT_FOUND, "MSSV không tồn tại."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Mật khẩu không đúng."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Lỗi hệ thống.");

    private final HttpStatus statusCode;
    private final String message;

    ErrorCode(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
