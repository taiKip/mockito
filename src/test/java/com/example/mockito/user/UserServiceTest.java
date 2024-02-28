package com.example.mockito.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceTest {
    @Autowired
private UserService userService;
    @BeforeEach
    void setUp() {
    }
    @Test
    public void whenValidEmail_thenUserShouldFound(){
String email = "test@123.com";
User foundUser =
        userService.getUserByEmail(email);
assertEquals(email,foundUser.getEmail());
    }
}