package com.example.mockito.user;

import jakarta.validation.constraints.*;
import utils.AppConstants;

public record UserRequestDto(String firstName, String lastName,
                             @NotNull(message = "email is required")
                            @NotNull @NotEmpty @Pattern(regexp = AppConstants.EMAIL_REGEX) @Email(message = "email is not valid") String email) {
}
