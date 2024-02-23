package com.example.mockito.user;

import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class UserDtoMapper implements Function<User,UserResponseDto> {
    @Override
    public UserResponseDto apply(User user) {
        return new UserResponseDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }
}
