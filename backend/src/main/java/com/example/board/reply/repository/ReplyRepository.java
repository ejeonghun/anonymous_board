package com.example.board.reply.repository;

import com.example.board.reply.entity.ReplyEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<ReplyEntity, Integer> {
    public List<ReplyEntity> findAllByPost_PostId(Integer postId);
}