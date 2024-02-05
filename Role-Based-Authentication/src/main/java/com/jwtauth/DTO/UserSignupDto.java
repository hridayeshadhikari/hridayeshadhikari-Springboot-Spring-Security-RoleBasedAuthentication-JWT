package com.jwtauth.DTO;

import lombok.Data;

@Data
public class UserSignupDto {
    private String firstName;

    private String lastName;
    private String email;

    private String password;
}
