package com.example.board.post.controller;

import com.example.board.post.dto.PostDto;
import com.example.board.post.service.PostService;;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "post", description = "게시물 API")
@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Operation(summary = "게시물 목록 조회", description = "모든 게시글을 리스트로 반환합니다.")
    @GetMapping("/api/board/posts")
    public List<PostDto> getPosts(){
        return postService.getPosts();
    }

    @Operation(summary = "게시물 등록", description = "제목(title)과 내용(content)을 이용하여 게시물을 신규 등록합니다.")
    @PostMapping("/api/board/create")
    public PostDto create_post(@RequestBody PostDto requestData) { // JSON 형태로 title, content, password 에 대한 내용을 받는다
        return postService.writePost(requestData.getTitle(), requestData.getContent(), requestData.getPassword());
    }

    @Operation(summary = "게시물 삭제", description = "게시글id와 password를 입력받아 password 검증 절차를 거친 뒤 게시글을 삭제합니다.")
    @GetMapping("/api/board/delete/{PostId}")
    public String delete_post(@PathVariable("PostId") int PostId, @RequestParam(name="password", required = true) String password) {
        return postService.deletePost(PostId, password);
    }

    @Operation(summary = "게시물 수정", description = "password를 입력받고 password 검증 절차를 거친 뒤 게시글을 수정합니다.")
    @PostMapping("/api/board/update")
    public String update_post(@RequestBody PostDto requestData) { // JSON 형태로 title, content, password 에 대한 내용을 받는다
        return postService.updatePost(requestData);
    }
}
