package com.example.todoapp.jpa.dto;

import lombok.Getter;

@Getter
public class CreateTodoRequest {
    private String name;
    private String content;
    private Long authorId;
    private String password;
}
