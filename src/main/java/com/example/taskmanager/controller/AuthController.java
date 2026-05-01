package com.example.taskmanager.controller;

import com.example.taskmanager.dto.AuthRequest;
import com.example.taskmanager.security.JwtUtils;
import com.example.taskmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        userService.register(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(Map.of("message", "Пользователь зарегистрирован"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()));
        String token = jwtUtils.generateToken(request.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }
}