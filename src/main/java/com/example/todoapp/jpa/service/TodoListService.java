package com.example.todoapp.jpa.service;

import com.example.todoapp.jpa.dto.*;
import com.example.todoapp.jpa.entity.TodoList;
import com.example.todoapp.jpa.repository.TodoListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class TodoListService {
    private final TodoListRepository todoListRepository;
    //저장
    @Transactional
    public CreateTodoListResponse save(CreateTodoListRequest request) {
        TodoList todoList = new TodoList(
                request.getName(),
                request.getContent(),
                request.getAuthor(),
                request.getCreateDate(),
                request.getPassword()
        );
        TodoList savedTodoList = todoListRepository.save(todoList);

        return new CreateTodoListResponse(
                savedTodoList.getId(),
                savedTodoList.getName(),
                savedTodoList.getContent(),
                savedTodoList.getAuthor(),
                savedTodoList.getCreateDate(),
                savedTodoList.getEditDate()
        );
    }
    //단건 조회
    @Transactional(readOnly = true)
    public GetOneTodoResponse getOne(Long id) {
        TodoList todoList = todoListRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        return new GetOneTodoResponse(
                todoList.getId(),
                todoList.getName(),
                todoList.getContent(),
                todoList.getAuthor(),
                todoList.getCreateDate(),
                todoList.getEditDate()
        );
    }
    //전체조회
    @Transactional(readOnly = true)
    public List<GetOneTodoResponse> getAll(String author) {
        List<TodoList> all;
        if(author != null && !author.isEmpty()) {
            all = todoListRepository.findByAuthorOrderByEditDateDesc(author);
        }else {
            all = todoListRepository.findAllByOrderByEditDateDesc();
        }

        List<GetOneTodoResponse> dtos = new ArrayList<>();
        for(TodoList todoList : all) {
            GetOneTodoResponse dto = new GetOneTodoResponse(
                    todoList.getId(),
                    todoList.getName(),
                    todoList.getContent(),
                    todoList.getAuthor(),
                    todoList.getCreateDate(),
                    todoList.getEditDate()
            );
            dtos.add(dto);
        }
        return dtos;
    }
    //수정
    @Transactional
    public UpdateTodoResponse update(Long id, UpdateTodoRequest request) {
        TodoList todoList = todoListRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        if(!todoList.getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        todoList.update(
                request.getName(),
                request.getAuthor()
        );

        return new UpdateTodoResponse(
                todoList.getId(),
                todoList.getName(),
                todoList.getContent(),
                todoList.getAuthor(),
                todoList.getCreateDate(),
                todoList.getEditDate()

        );
    }
    //삭제
    @Transactional
    public void delete(Long id, DeleteTodoRequest request) {
        TodoList todoList = todoListRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        if(!todoList.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        todoListRepository.deleteById(id);
    }
}
