package com.example.todoapp.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SigninRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
