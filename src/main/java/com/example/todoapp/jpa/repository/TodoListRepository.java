package com.example.todoapp.jpa.repository;

import com.example.todoapp.jpa.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    List<TodoList> findByAuthorOrderByEditDateDesc(String author);
    List<TodoList> findAllByOrderByEditDateDesc();
}
