package com.example.todoapp.jpa.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateTodoResponse {
    private final Long id;
    private final String name;
    private final String content;
    private final String author;
    private final LocalDateTime createDate;
    private final LocalDateTime editDate;


    public UpdateTodoResponse(Long id, String name, String content, String author, LocalDateTime createDate, LocalDateTime editDate) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.author = author;
        this.createDate = createDate;
        this.editDate = editDate;
    }
}
