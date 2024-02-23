package com.example.mockito.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userDto){
        return  ResponseEntity.ok(userService.createUser(userDto));
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }
    @GetMapping(path = "search")
    public ResponseEntity<User> getUserByEmail(@RequestParam(value = "email",required = false)String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
    @GetMapping(path = "{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id")Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserById(id));
    }
@DeleteMapping(path = "{id}")
    public void deleteById(@PathVariable("id")Long id){
        userService.deleteUserById(id);
}
}
