package com.example.hw12springcore.user.response;

import com.example.hw12springcore.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private LocalDate lastUpdatedDate;
    private LocalDate createdDate;
    private Set<Role> roles = new HashSet<>();
}
