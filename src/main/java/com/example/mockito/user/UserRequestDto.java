package com.example.mockito.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDto(String firstName, String lastName,
                             @NotNull(message = "email is required")
                             @Email(message = "email is not valid") String email) {
}
