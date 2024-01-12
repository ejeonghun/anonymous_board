package com.example.board.post.repository;

import com.example.board.post.dto.PostDto;
import com.example.board.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {

}
