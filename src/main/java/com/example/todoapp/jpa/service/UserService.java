package com.example.todoapp.jpa.service;

import com.example.todoapp.jpa.dto.*;
import com.example.todoapp.jpa.entity.User;
import com.example.todoapp.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 유저 생성
    @Transactional
    public CreateUserResponse save(CreateUserRequest request) {
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        User savedUser = userRepository.save(user);

        return new CreateUserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPassword(),
                savedUser.getCreateDate(),
                savedUser.getEditDate()
        );
    }

    // 유저 단건 조회
    @Transactional(readOnly = true)
    public GetUserResponse getOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 사용자입니다.")
        );

        return new GetUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreateDate(),
                user.getEditDate()
        );
    }

    // 유저 전체 조회
    @Transactional(readOnly = true)
    public List<GetUserResponse> getAll() {
        List<User> users = userRepository.findAllByOrderByEditDateDesc();

        return users.stream()
                .map(user -> new GetUserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getCreateDate(),
                        user.getEditDate()
                ))
                .collect(Collectors.toList());
    }

    // 유저 정보 수정
    @Transactional
    public UpdateUserResponse update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 사용자입니다.")
        );

        user.update(request.getName());

        return new UpdateUserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreateDate(),
                user.getEditDate()
        );
    }

    // 유저 삭제
    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("존재하지 않는 사용자입니다.");
        }
        userRepository.deleteById(id);
    }
}