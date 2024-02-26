package com.example.mockito.user;

import com.example.mockito.error.DuplicateException;
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
    public UserResponseDto createUser(UserRequestDto userRequestDto) throws DuplicateException {
       Optional< User> userDb = userRepository.findByEmail(userRequestDto.email());
       if(userDb.isPresent()){
           throw new DuplicateException("Email already taken");
       }
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
    public String deleteUserById(Long id) throws UserNotFoundException {
Optional<User> userdb =  userRepository.findById(id);
if(userdb.isEmpty()){
    throw new UserNotFoundException();
}
        userRepository.deleteById(id);
        return String.format("User with %d id has been deleted",id);
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
