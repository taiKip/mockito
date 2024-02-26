package com.example.mockito.user;

import com.example.mockito.error.DuplicateException;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto) throws DuplicateException;

    List<UserResponseDto> getUsers();

    User getUserByEmail(String email);

    String deleteUserById(Long id) throws UserNotFoundException;

    User getUserById(Long id) throws UserNotFoundException;
}
