package com.programmingtechie.controller;

import com.programmingtechie.dto.AuthResponse;
import com.programmingtechie.dto.LoginRequest;
import com.programmingtechie.dto.SignupRequest;
import com.programmingtechie.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    //Đăng ký tài khoản mới
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok("Tạo tài khoản thành công!");
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.authenticate(request);
    }

    @GetMapping("/verify")
    public String verifyToken() {
        return "Token hợp lệ!";
    }
}
