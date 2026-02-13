package com.example.todoapp.jpa.controller;

import com.example.todoapp.jpa.dto.*;
import com.example.todoapp.jpa.entity.User;
import com.example.todoapp.jpa.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    //로그인
    @PostMapping("/signin")
    public ResponseEntity<Void> signin(
            @Valid @RequestBody SigninRequest request, HttpSession session
    ){
        SessionUser sessionUser = userService.signin(request);
        session.setAttribute("signinsUser", sessionUser);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
    // 사용자 생성 (회원가입)
    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        CreateUserResponse result = userService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 사용자 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable Long id) {
        GetUserResponse result = userService.getOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 사용자 전체 조회 (추가됨)
    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getAllUsers() {
        List<GetUserResponse> result = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 사용자 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(
            @PathVariable Long id,
            @RequestBody UpdateUserRequest request
    ) {
        UpdateUserResponse result = userService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 사용자 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}