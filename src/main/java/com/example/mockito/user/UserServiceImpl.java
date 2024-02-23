package com.example.mockito.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        User newuser = User.builder()
                .email(userRequestDto.email())
                .firstName(userRequestDto.firstName())
                .lastName(userRequestDto.lastName()).build();
      User  user =   userRepository.save(newuser);
return  new UserResponseDto(user.getId(),user.getFirstName(),user.getLastName(),user.getEmail());
    }

    @Override
    public List<UserResponseDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDtoMapper)
                .collect(Collectors.toList());
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public void deleteUserById(Long id) {

        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("User Not Found");
        }
        return user.get();
    }
}