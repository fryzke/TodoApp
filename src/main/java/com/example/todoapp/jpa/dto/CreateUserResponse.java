package com.example.todoapp.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CreateUserResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final String password;
    private final LocalDateTime createDate;
    private final LocalDateTime editDate;
}