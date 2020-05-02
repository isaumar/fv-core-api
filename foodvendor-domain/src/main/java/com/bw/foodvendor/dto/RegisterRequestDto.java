package com.bw.foodvendor.dto;

import com.bw.security.Unique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegisterRequestDto {
    @NotBlank(message="Email is required!")
    @Email(message="Email is invalid")
    @Size(max=100,message="Maximum email length is 100")
    @Unique(table="portal_user",column="email",message="Email has been registered!")
    private String email;

    @NotBlank(message="Phone Number is required!")
    @Pattern(regexp = "^[0]\\d{10}$",
            message = "Invalid Phone number")
    @Size(max=15,message="Maximum email length is 15")
    @Unique(table="portal_user",column="phone_number",message="Phone number has been registered!")
    private String phoneNumber;

    @NotBlank(message="Password is required!")
    @Size(min=6,message="Minimum password length is 6")
    private String password;

    @NotBlank(message="First name is required!")
    @Size(min=1,max=30,message="Name length should be in range 3 and 30")
    @Pattern(
            regexp="^[a-z][a-z-\\s']+",
            flags={Pattern.Flag.CASE_INSENSITIVE},
            message="Name can only contain alphabets, space and '"
    )
    private String firstName;

    @NotBlank(message="Last name is required!")
    @Size(min=1,max=30,message="Name length should be in range 3 and 30")
    @Pattern(
            regexp="^[a-z][a-z-\\s']+",
            flags={Pattern.Flag.CASE_INSENSITIVE},
            message="Name can only contain alphabets, space and '"
    )
    private String lastName;

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
