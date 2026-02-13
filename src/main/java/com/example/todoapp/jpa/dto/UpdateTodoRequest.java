package com.example.todoapp.jpa.dto;

import lombok.Getter;

@Getter
public class UpdateTodoRequest {
    private String name;
    private String author;
    private String password;
}
