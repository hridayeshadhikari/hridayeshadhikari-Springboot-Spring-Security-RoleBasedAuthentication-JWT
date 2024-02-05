package com.jwtauth.Service.Impl;

import com.jwtauth.DTO.JwtAuthResponse;
import com.jwtauth.DTO.RefreshTokenReq;
import com.jwtauth.DTO.UserLoginDto;
import com.jwtauth.DTO.UserSignupDto;
import com.jwtauth.Entity.Role;
import com.jwtauth.Entity.User;
import com.jwtauth.Repository.UserRepository;
import com.jwtauth.Service.AuthService;
import com.jwtauth.Service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public User signup(UserSignupDto userSignupDto){
        User user=new User();
        user.setFirstName(userSignupDto.getFirstName());
        user.setLastName(userSignupDto.getLastName());
        user.setEmail(userSignupDto.getEmail());
        user.setPassword(passwordEncoder.encode(userSignupDto.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
    public JwtAuthResponse signin(UserLoginDto userLoginDto){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(),userLoginDto.getPassword()));
        var user =userRepository.findByEmail(userLoginDto.getEmail()).orElseThrow(()->new IllegalArgumentException("invalid email or password"));
        var jwt=jwtService.generateToken(user);
        var refreshToken=jwtService.generateRefreshToken(new HashMap<>(),user);
        JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
        jwtAuthResponse.setToken(jwt);
        jwtAuthResponse.setRefreshToken(refreshToken);
        return jwtAuthResponse;
    }
    public JwtAuthResponse refreshToken(RefreshTokenReq refreshTokenReq){
        String userEmail=jwtService.extractUserName(refreshTokenReq.getToken());
        User user =userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenReq.getToken(),user)){
            var jwt=jwtService.generateToken(user);
            JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
            jwtAuthResponse.setToken(jwt);
            jwtAuthResponse.setRefreshToken(refreshTokenReq.getToken());
            return jwtAuthResponse;
        }
        return null;
    }
}
