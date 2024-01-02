package com.example.hw12springcore.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @NotBlank
    @Size(min = 3, max = 50)
    private String oldUsername;

    @NotBlank
    @Size(min = 3, max = 100)
    private String oldPassword;

    @NotBlank
    @Size(min = 3, max = 50)
    private String newUsername;

    @NotBlank
    @Size(min = 3, max = 100)
    private String newPassword;
}
