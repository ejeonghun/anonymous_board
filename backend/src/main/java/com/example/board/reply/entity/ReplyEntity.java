package com.example.board.reply.entity;

import com.example.board.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Getter
@Entity
@Setter
@Table(name="replies")
@NoArgsConstructor // 생성자에서 하나도 없이 생성할 수 있다.
@Builder
@AllArgsConstructor // 값이 다 있어도 생성자를 생성할 수 있다.
public class ReplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer replyId; // 댓글ID - PRIMARY KEY

    @Column
    private String replyContent; // 댓글 본문

    @Column
    private String password; // 댓글 비밀번호

    @CreationTimestamp
    @Column(name="replyCreatedAt")
    private LocalDateTime replyCreatedAt; // 댓글 생성 날짜

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity post;

}


