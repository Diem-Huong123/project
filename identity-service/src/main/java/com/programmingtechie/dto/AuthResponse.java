package com.programmingtechie.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@Setter
@Getter
public class AuthResponse {
    private String accessToken;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    // Getter & Setter
}
