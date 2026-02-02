package com.example.todoapp.jpa.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateTodoListRequest {
    private String name;
    private String content;
    private String author;
    private LocalDateTime createDate;
    private LocalDateTime editDate;
    private String password;
}
