package com.programmingtechie.course_service.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
        COURSE_NOT_FOUND(HttpStatus.NOT_FOUND, "KHÔNG TÌM THẤY HỌC PHẦN"),
        COURSE_ALREADY_EXISTS(HttpStatus.CONFLICT, "HỌC PHẦN ĐÃ TỒN TẠI"),
        INVALID_REQUEST(HttpStatus.BAD_REQUEST, "YÊU CẦU KHÔNG HỢP LỆ"),
        INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "LỖI HỆ THỐNG");

        private final HttpStatus status;
        private final String message;

        ErrorCode(HttpStatus status, String message) {
            this.status = status;
            this.message = message;
        }
}
