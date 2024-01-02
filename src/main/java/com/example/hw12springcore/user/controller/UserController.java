package com.example.hw12springcore.user.controller;

import com.example.hw12springcore.config.jwt.UserDetailsImpl;
import com.example.hw12springcore.user.exception.UserAlreadyExistException;
import com.example.hw12springcore.user.exception.UserIncorrectPasswordException;
import com.example.hw12springcore.user.exception.UserNotFoundException;
import com.example.hw12springcore.user.request.UpdateUserRequest;
import com.example.hw12springcore.user.request.UpdateUserRoleRequest;
import com.example.hw12springcore.user.response.UserResponse;
import com.example.hw12springcore.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest)
            throws UserNotFoundException, UserAlreadyExistException, UserIncorrectPasswordException {
        SecurityContext context = SecurityContextHolder.getContext();
        UserDetailsImpl authentication = (UserDetailsImpl) context.getAuthentication().getPrincipal();

        return ResponseEntity.ok(userService.updateUser(authentication.getId(), updateUserRequest));
    }

    @PutMapping("/update/roles")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('SUPER_ADMIN')")
    public ResponseEntity<UserResponse> updateUserRole(@Valid @RequestBody UpdateUserRoleRequest updateUserRoleRequest)
            throws UserNotFoundException {
        SecurityContext context = SecurityContextHolder.getContext();
        UserDetailsImpl authentication = (UserDetailsImpl) context.getAuthentication().getPrincipal();

        return ResponseEntity.ok(userService.updateUserRoles(authentication.getId(), updateUserRoleRequest.getRoles()));
    }
}
