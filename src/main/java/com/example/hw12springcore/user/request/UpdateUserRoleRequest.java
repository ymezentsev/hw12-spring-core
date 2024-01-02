package com.example.hw12springcore.user.request;

import com.example.hw12springcore.user.entity.ERole;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UpdateUserRoleRequest {

    @NotEmpty
    Set<ERole> roles = new HashSet<>();
}
