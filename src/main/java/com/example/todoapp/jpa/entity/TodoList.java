package com.example.todoapp.jpa.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)

public class TodoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String author;

    @Column(length = 10, nullable = false)
    private String password;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime editDate;

    public TodoList(String name, String content, String author, LocalDateTime createDate, String password) {
        this.name = name;
        this.content = content;
        this.author = author;
        this.createDate = createDate;
        this.editDate = createDate;
        this.password = password;
    }

    public void update (String name, String author) {
        this.name = name;
        this.author = author;
    }
}
