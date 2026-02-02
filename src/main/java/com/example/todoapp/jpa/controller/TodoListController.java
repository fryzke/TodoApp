package com.example.todoapp.jpa.controller;

import com.example.todoapp.jpa.dto.*;
import com.example.todoapp.jpa.service.TodoListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TodoListController {
    private final TodoListService todoListService;

    @PostMapping("/todos")
    public ResponseEntity<CreateTodoListResponse> createTodo(@RequestBody CreateTodoListRequest request) {
        CreateTodoListResponse result = todoListService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/todos/{id}")
    public  ResponseEntity<GetOneTodoResponse> getOneTodo(@PathVariable Long id) {
        GetOneTodoResponse result = todoListService.getOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/todos/{author}")
    public ResponseEntity<List<GetOneTodoResponse>> getAllTodos(@PathVariable String author) {
        List<GetOneTodoResponse> result = todoListService.getAll(author);
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<UpdateTodoResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateTodoRequest request
    ) {
        UpdateTodoResponse result = todoListService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id,
            @RequestBody DeleteTodoRequest request) {
        todoListService.delete(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
