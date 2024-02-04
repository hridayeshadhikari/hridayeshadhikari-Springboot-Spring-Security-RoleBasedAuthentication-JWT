package com.jwtauth.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupDto {
    private String name;

    private String email_id;

    private String password;

    private String role;
}
