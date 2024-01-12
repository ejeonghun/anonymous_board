package com.example.board.reply.controller;

import com.example.board.post.dto.PostDto;
import com.example.board.reply.dto.ReplyDto;
import com.example.board.reply.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "reply", description = "댓글 API")
@RequiredArgsConstructor
@RestController
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Operation(summary = "댓글 목록 조회", description = "게시글id를 입력받아 해당 게시글의 댓글들을 조회합니다.")
    @GetMapping("/api/reply/{postid}")
    @ResponseBody
    public List<ReplyDto> getReplys(@PathVariable("postid") Integer postid) {
        return replyService.getReplys(postid);
    }

    @Operation(summary = "댓글 등록", description = "게시글id와 내용(content)을 입력하여 해당 게시글의 댓글을 신규 등록합니다.")
    @PostMapping("/api/reply/create/{postid}")
    public ReplyDto createReply(@PathVariable("postid") Integer postid, @RequestBody ReplyDto requestData) {
        ReplyDto response = replyService.createReply(postid ,requestData);
        return response;
    }

    @Operation(summary = "댓글 삭제", description = "댓글 id와 password를 입력받아 password 검증 절차를 거친 뒤 해당 댓글을 삭제합니다.")
    @GetMapping("/api/reply/delete/{replyid}")
    public String deleteReply(@PathVariable("replyid") Integer replyid, @RequestParam("password") String password) {
        String res = replyService.deleteReply(replyid, password);
        return res;
    }
}
