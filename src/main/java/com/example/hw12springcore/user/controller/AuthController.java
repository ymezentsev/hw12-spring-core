package com.example.hw12springcore.user.controller;

import com.example.hw12springcore.user.exception.UserAlreadyExistException;
import com.example.hw12springcore.user.request.UserRequest;
import com.example.hw12springcore.user.response.JwtResponse;
import com.example.hw12springcore.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.loginUser(userRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest) throws UserAlreadyExistException {
        userService.registerUser(userRequest.getUsername(), userRequest.getPassword());
        return ResponseEntity.accepted().build();
    }
}
