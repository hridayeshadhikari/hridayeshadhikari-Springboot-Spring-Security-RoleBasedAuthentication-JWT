package com.jwtauth.Controller;

import com.jwtauth.DTO.JwtAuthResponse;
import com.jwtauth.DTO.RefreshTokenReq;
import com.jwtauth.DTO.UserLoginDto;
import com.jwtauth.DTO.UserSignupDto;
import com.jwtauth.Entity.User;
import com.jwtauth.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody UserSignupDto userSignupDto){
        return ResponseEntity.ok(authService.signup(userSignupDto));

    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> loginUser(@RequestBody UserLoginDto userLoginDto){
        return ResponseEntity.ok(authService.signin(userLoginDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refresh(@RequestBody RefreshTokenReq refreshTokenReq){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenReq));
    }
}
