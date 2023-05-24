package com.example.movierama.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotEmpty(message = "Email should not be empty")
    @Size(max = 255, message = "Max 255 chars accepted")
    @Email
    private String email;

    @NotEmpty(message = "Name should not be empty")
    @Size(max = 255, message = "Max 255 chars accepted")
    private String name;

    @NotEmpty(message = "Password should not be empty")
    @Size(max = 255, message = "Max 255 chars accepted")
    private String password;


}
