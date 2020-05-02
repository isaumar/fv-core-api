package com.bw.foodvendor.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequestDto {

    @NotBlank
    @Email(message="Email is invalid!")
    private String email;

    @NotBlank(message="Password is required!")
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
