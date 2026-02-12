package com.example.todoapp.jpa.service;

import com.example.todoapp.jpa.dto.*;
import com.example.todoapp.jpa.entity.TodoList;
import com.example.todoapp.jpa.repository.TodoListRepository;
import com.example.todoapp.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoListService {
    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    // 저장
    @Transactional
    public CreateTodoResponse save(CreateTodoRequest request) {
        User user = userRepository.findById(request.getAuthorId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        TodoList todoList = new TodoList(
                request.getName(),
                request.getContent(),
                user, // String이 아닌 User 객체
                request.getPassword()
        );

        TodoList savedTodoList = todoListRepository.save(todoList);

        return new CreateTodoResponse(
                savedTodoList.getId(),
                savedTodoList.getName(),
                savedTodoList.getContent(),
                savedTodoList.getAuthor().getName(), // User 객체에서 이름만 추출
                savedTodoList.getCreateDate(),
                savedTodoList.getEditDate()
        );
    }

    // 단건 조회
    @Transactional(readOnly = true)
    public GetOneTodoResponse getOne(Long id) {
        TodoList todoList = todoListRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        return new GetOneTodoResponse(
                todoList.getId(),
                todoList.getName(),
                todoList.getContent(),
                todoList.getAuthor().getName(), // 작성자 이름 반환
                todoList.getCreateDate(),
                todoList.getEditDate()
        );
    }

    // 전체 조회 (작성자 ID 기준 필터링 추가 가능)
    @Transactional(readOnly = true)
    public List<GetOneTodoResponse> getAll(Long authorId) {
        List<TodoList> all;

        if (authorId != null) {
            all = todoListRepository.findByAuthor_IdOrderByEditDateDesc(authorId);
        } else {
            all = todoListRepository.findAllByOrderByEditDateDesc();
        }

        return all.stream()
                .map(todo -> new GetOneTodoResponse(
                        todo.getId(),
                        todo.getName(),
                        todo.getContent(),
                        todo.getAuthor().getName(), // 작성자 이름 추출
                        todo.getCreateDate(),
                        todo.getEditDate()
                ))
                .collect(Collectors.toList());
    }
    // 수정
    @Transactional
    public UpdateTodoResponse update(Long id, UpdateTodoRequest request) {
        TodoList todoList = todoListRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        if (!todoList.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        // TodoList 엔티티의 update 메서드 호출 (name과 content 수정)
        todoList.update(
                request.getName()
        );

        return new UpdateTodoResponse(
                todoList.getId(),
                todoList.getName(),
                todoList.getContent(),
                todoList.getAuthor().getName(),
                todoList.getCreateDate(),
                todoList.getEditDate()
        );
    }

    // 삭제
    @Transactional
    public void delete(Long id, DeleteTodoRequest request) {
        TodoList todoList = todoListRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("없는 일정입니다.")
        );

        if (!todoList.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        todoListRepository.delete(todoList); // deleteById 대신 객체 삭제 권장
    }
}