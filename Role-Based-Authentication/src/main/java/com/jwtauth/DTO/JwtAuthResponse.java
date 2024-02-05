package com.jwtauth.DTO;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;
    private String refreshToken;
}
