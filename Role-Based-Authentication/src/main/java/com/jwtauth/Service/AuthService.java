package com.jwtauth.Service;

import com.jwtauth.DTO.JwtAuthResponse;
import com.jwtauth.DTO.UserLoginDto;
import com.jwtauth.DTO.UserSignupDto;
import com.jwtauth.Entity.User;

public interface AuthService {
    User signup(UserSignupDto userSignupDto);
    JwtAuthResponse signin(UserLoginDto userLoginDto);
}
