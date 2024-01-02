package com.example.hw12springcore.user.service;

import com.example.hw12springcore.user.entity.User;
import com.example.hw12springcore.user.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .lastUpdatedDate(user.getLastUpdatedDate())
                .createdDate(user.getCreatedDate())
                .roles(user.getRoles())
                .build();
    }
}
