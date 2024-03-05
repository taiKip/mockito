package com.example.mockito.user;

import jakarta.validation.constraints.*;
import utils.AppConstants;

public record UserRequestDto(@NotNull(message = "firstname should not be null") @NotEmpty String firstName,
                             @NotNull(message = "lastname should not be null") @NotEmpty String lastName,

                               @Email(message = "Provide a valid email address") String email) {
}
