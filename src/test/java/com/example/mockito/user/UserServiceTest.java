package com.example.mockito.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @BeforeEach
    void setUp() {
        User user  = User
                .builder()
                .id(2L)
                .firstName("vic")
                .lastName("tarus")
                .email("test@123.com")
                .build();
        Mockito.when(userRepository.findByEmailIgnoreCase("test@123.com"))
                .thenReturn(Optional.ofNullable(user));
        Mockito.when(userRepository.findById(2L))
                .thenReturn(Optional.ofNullable(user));
    }
    @Test
    public void whenValidEmail_thenUserShouldFound(){
String email = "test@123.com";
User foundUser =
        userService.getUserByEmail(email);
assertEquals(email,foundUser.getEmail());
    }

    @Test
    @DisplayName("Get data based on valid id")
    public void whenValidId_thenUserShouldFound() throws UserNotFoundException {
      Long userId = 2L  ;
      User foundUser =
              userService.getUserById(userId);
      assertEquals(userId,foundUser.getId());
    }

}