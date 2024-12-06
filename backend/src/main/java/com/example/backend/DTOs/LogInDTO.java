package com.example.backend.DTOs;

import com.example.backend.Enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LogInDTO {
    Integer id;
    Role role;
}
