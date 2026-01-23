package com.example.TaskManager.controller;

import com.example.TaskManager.dto.LoginRequestDTO;
import com.example.TaskManager.dto.LoginResponseDTO;
import com.example.TaskManager.dto.SignupResponseDTO;
import com.example.TaskManager.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody LoginRequestDTO signupRequestDTO){
        return ResponseEntity.ok(authService.signup(signupRequestDTO));
    }
}
