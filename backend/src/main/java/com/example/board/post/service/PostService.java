package com.example.board.post.service;

import com.example.board.post.dto.PostDto;
import com.example.board.post.entity.PostEntity;
import com.example.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    private PostDto entityToDto(PostEntity entity) {
        return PostDto.builder()
                .postId(entity.getPostId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .password(entity.getPassword())
                .created_at(entity.getCreatedAt())
                .build();
    }

    private PostEntity toEntity(PostDto dto) {
        return PostEntity.builder()
                .postId(dto.getPostId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdAt(dto.getCreated_at())
                .build();
    }

    public List<PostDto> getPosts() {
        List<PostDto> posts = new ArrayList<>();

        postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")).forEach(post -> posts.add(entityToDto(post)));
        return posts;
    }

    public PostDto writePost(String title,String content, String password) {
        PostEntity t = PostEntity.builder()
                    .title(title)
                    .content(content)
                    .password(password)
                    .build();
        PostEntity savedPost = postRepository.save(t);
        return entityToDto(savedPost);
    }


    public String deletePost(Integer postId, String password) {
        Optional<PostEntity> data = postRepository.findById(postId);
        if (data.isPresent()) {
            PostEntity entity = data.get();
            if (entity.getPassword().equals(password)) {
                postRepository.deleteById(postId);
            } else {
                return "비밀번호가 틀립니다.";
            }
        } else {return "게시글이 존재하지 않음";}
        return "성공";
    }

    public String updatePost(PostDto dto) {
        Optional<PostEntity> data = postRepository.findById(dto.getPostId());
        if (data.isPresent()) {
            if(dto.getPassword().equals(data.get().getPassword())) {
                PostEntity t = PostEntity.builder()
                        .postId(dto.getPostId())
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .password(dto.getPassword())
                        .createdAt(LocalDateTime.now())
                        .build();
                postRepository.save(t);
                System.out.println("postId:"+dto.getPostId()+"게시글이 수정되었음");
                return "수정 완료";
            } else {
                System.out.println("postId:" + dto.getPostId() +"의 비밀번호가 틀림");
                return "비밀번호가 틀림."; } // 비밀번호가 틀림
        } else {
            return "게시글이 존재 하지 않음"; // 게시글이 존재하지 않음
        }
    }
}
