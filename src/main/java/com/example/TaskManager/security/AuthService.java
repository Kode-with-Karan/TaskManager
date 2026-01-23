package com.example.TaskManager.security;

import com.example.TaskManager.dto.LoginRequestDTO;
import com.example.TaskManager.dto.LoginResponseDTO;
import com.example.TaskManager.dto.SignupResponseDTO;
import com.example.TaskManager.entity.User;
import com.example.TaskManager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDTO(token, user.getId());


    }

    public SignupResponseDTO signup(LoginRequestDTO signupRequestDTO){
        User user = (User) userRepository.findByUsername(signupRequestDTO.getUsername()).orElse(null);

        if(user != null ) throw new IllegalArgumentException("User already Exists");

        user = userRepository.save(User.builder()
                        .username(signupRequestDTO.getUsername())
                        .password(passwordEncoder.encode(signupRequestDTO.getPassword()))
                        .build()
        );

        return new SignupResponseDTO(user.getId(), user.getUsername());


    }
}
