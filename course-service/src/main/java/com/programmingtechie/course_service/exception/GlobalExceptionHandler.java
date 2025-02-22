package com.programmingtechie.course_service.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<Map<String, Object>> handleAppException(AppException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", ex.getErrorCode().getStatus().value());
        errorResponse.put("error", ex.getErrorCode().name());
        errorResponse.put("message", ex.getMessage());

        return ResponseEntity.status(ex.getErrorCode().getStatus()).body(errorResponse);
    }
}
