package com.example.TaskManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SignupResponseDTO {

    private Long id;
    private String username;
}
