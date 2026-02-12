package com.example.todoapp.jpa.repository;

import com.example.todoapp.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByEditDateDesc();
}
