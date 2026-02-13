package com.example.todoapp.jpa.repository;

import com.example.todoapp.jpa.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByEditDateDesc();
    Optional<User> findByEmail(@NotBlank String email);
}
