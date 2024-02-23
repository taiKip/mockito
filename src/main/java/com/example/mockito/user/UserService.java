package com.example.mockito.user;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getUsers();

    User getUserByEmail(String email);

    void deleteUserById(Long id);

    User getUserById(Long id) throws UserNotFoundException;
}
