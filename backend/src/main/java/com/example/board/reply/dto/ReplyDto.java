package com.example.board.reply.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class ReplyDto {
    private Integer replyId;
    private Integer postId;
    private String replyContent;
    private String password;
    private LocalDateTime replyCreatedAt;

    public void setPostId(Integer postid) {
        this.postId = postid;
    }
}
