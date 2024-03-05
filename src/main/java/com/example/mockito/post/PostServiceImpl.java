package com.example.mockito.post;

import com.example.mockito.user.User;
import com.example.mockito.user.UserNotFoundException;
import com.example.mockito.user.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final PostRepository postRepository;
    private  final UserRepository userRepository;
    @Override
    public Post createPost(PostRequestDto postRequestDto) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(postRequestDto.userId());
        if(user.isEmpty()){
            throw new UserNotFoundException("User with id " + postRequestDto.userId() + " does not exist");
        }
        Post newPost = Post
                .builder()
                .title(postRequestDto.title())
                .content(postRequestDto.content())
                .user(user.get())
                .build();
        return  postRepository.save(newPost);
    }

    @Override
    public PostResponseDto getPost(Long postId) throws PostNotFoundException {
        Optional<Post> postDb = postRepository.findById(postId);
        if(postDb.isEmpty()){
            throw new PostNotFoundException("Post with id:" + postId + " does not exist");
        }
        Post foundPost = postDb.get();
        return new PostResponseDto(foundPost.getUser().getId(),foundPost.getTitle(),foundPost.getContent());
    }

}
