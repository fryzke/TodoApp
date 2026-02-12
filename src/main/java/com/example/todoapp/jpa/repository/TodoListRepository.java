package com.example.todoapp.jpa.repository;

import com.example.todoapp.jpa.entity.TodoList;
import com.example.todoapp.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    List<TodoList> findByAuthor_IdOrderByEditDateDesc(Long authorId);
    List<TodoList> findAllByOrderByEditDateDesc();
}
