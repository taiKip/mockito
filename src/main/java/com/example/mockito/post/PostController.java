package com.example.mockito.post;

import com.example.mockito.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
public class PostController {
    private final PostService postService;
    @PostMapping
    public ResponseEntity<Post> createPost(PostRequestDto postRequestDto) throws UserNotFoundException {
        return ResponseEntity.ok(postService.createPost(postRequestDto));
    }
@GetMapping("{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) throws PostNotFoundException {
        return ResponseEntity.ok(postService.getPost(postId));
}

}
