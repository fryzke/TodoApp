package com.example.todoapp.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessionUser {
    private final Long id;
    private final String name;
    private final String email;
}
