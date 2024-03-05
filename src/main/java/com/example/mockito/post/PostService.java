package com.example.mockito.post;

import com.example.mockito.user.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    Post createPost(PostRequestDto postRequestDto) throws UserNotFoundException;

    PostResponseDto getPost(Long postId) throws PostNotFoundException;
}
