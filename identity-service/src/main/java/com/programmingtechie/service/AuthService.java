package com.programmingtechie.service;
import com.programmingtechie.dto.AuthResponse;
import com.programmingtechie.dto.LoginRequest;
import com.programmingtechie.dto.SignupRequest;
import com.programmingtechie.entity.StudentAccount;
import com.programmingtechie.exception.AppException;
import com.programmingtechie.exception.ErrorCode;
import com.programmingtechie.repository.StudentAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
public class AuthService {

    private final StudentAccountRepository studentAccountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public AuthService(StudentAccountRepository studentAccountRepository, PasswordEncoder passwordEncoder, JwtEncoder jwtEncoder) {
        this.studentAccountRepository = studentAccountRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtEncoder = jwtEncoder;
    }

    public AuthResponse authenticate(LoginRequest request) {
        StudentAccount student = studentAccountRepository.findByMssv(request.getMssv())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        // Tạo claims cho JWT
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(student.getMssv())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(3600)) // 1 ngày
//                .claim("roles", "STUDENT") // Gán quyền
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new AuthResponse(token);
    }

    @Transactional
    public void signup(SignupRequest request) {
        // Kiểm tra MSSV đã tồn tại chưa
        if (studentAccountRepository.existsByMssv(request.getMssv())) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }

        // Mã hóa mật khẩu
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Tạo tài khoản mới (Entity)
        StudentAccount newStudent = StudentAccount.builder()
                .mssv(request.getMssv())
                .password(encodedPassword)
                .email(request.getEmail())
                .build();

        // Lưu vào database
        studentAccountRepository.save(newStudent);
    }

}
