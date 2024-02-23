package com.example.mockito.comment;

import com.example.mockito.post.Post;
import com.example.mockito.user.User;
import jakarta.persistence.*;
import lombok.*;
import entity.BaseEntity;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue
    private  Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
