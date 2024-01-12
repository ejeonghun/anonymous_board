package com.example.board.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private String password;
    private LocalDateTime created_at;

}
