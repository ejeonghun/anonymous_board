package com.example.board.reply.service;

import com.example.board.post.dto.PostDto;
import com.example.board.post.entity.PostEntity;
import com.example.board.reply.dto.ReplyDto;
import com.example.board.reply.entity.ReplyEntity;
import com.example.board.reply.repository.ReplyRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    private ReplyDto entityToDto(ReplyEntity entity) {
        return ReplyDto.builder()
                .replyId(entity.getReplyId())
                .postId(entity.getPost().getPostId())
                .replyContent(entity.getReplyContent())
                .password(entity.getPassword())
                .replyCreatedAt(entity.getReplyCreatedAt())
                .build();
    }

    private ReplyEntity toEntity(ReplyDto dto) {
        return ReplyEntity.builder()
                .replyId(dto.getReplyId())
                .replyContent(dto.getReplyContent())
                .password(dto.getPassword())
                .replyCreatedAt(dto.getReplyCreatedAt())
                .post(PostEntity.builder().postId(dto.getPostId()).build()) // PostEntity 객체 생성 및 postId 설정
                .build();
    }

    public List<ReplyDto> getReplys(Integer postId) {
        ArrayList<ReplyDto> replies = new ArrayList<>();
        replyRepository.findAllByPost_PostId(postId).forEach(reply -> replies.add(entityToDto(reply)));
        return replies;
    }


    public ReplyDto createReply(Integer post_id ,ReplyDto dto) {
        dto.setPostId(post_id); // 입력받은 게시글 id 지정
        ReplyEntity result = replyRepository.save(toEntity(dto));
        return entityToDto(result);
    }

    public String deleteReply(Integer reply_id, String password) {
        Optional<ReplyEntity> reply = replyRepository.findById(reply_id);
        if (reply.isPresent()) {
            ReplyEntity entity = reply.get();
            if (entity.getPassword().equals(password)) {
                replyRepository.deleteById(reply_id);
            } else {
                return "비밀번호가 틀립니다.";
            }
        } else {return "댓글이 존재하지 않음";}
        return "삭제 성공";
    }
}
