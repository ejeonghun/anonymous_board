package com.example.board.post.entity;

import com.example.board.reply.entity.ReplyEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@Setter
@Table(name="post")
@NoArgsConstructor // 생성자에서 하나도 없이 생성할 수 있다.
@Builder
@AllArgsConstructor // 값이 다 있어도 생성자를 생성할 수 있다.
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="post_id")
    private Integer postId; // 게시글 ID

    @Column(length = 100)
    private String title; // 게시글 제목

    @Column
    private String content; // 게시글 본문

    @Column(length = 40)
    private String password; // 게시글 비밀번호

    @CreationTimestamp
    @Column(name="createdAt")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReplyEntity> replies;
}
