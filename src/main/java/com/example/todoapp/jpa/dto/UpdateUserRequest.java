package com.example.todoapp.jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUserRequest {
    @NotBlank(message = "수정할 이름은 필수입니다.")
    @Size(max = 30, message = "이름은 30자 이내여야 합니다.")
    private String name;

    public UpdateUserRequest(String name) {
        this.name = name;
    }
}
