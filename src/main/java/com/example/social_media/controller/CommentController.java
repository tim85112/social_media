package com.example.social_media.controller;

import com.example.social_media.model.Comment;
import com.example.social_media.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 新增留言
    @PostMapping("/{postID}")
    public ResponseEntity<?> addComment(@PathVariable Long postID, @RequestBody Comment comment, Authentication authentication) {
        Long userID = Long.parseLong(authentication.getName()); // 當前用戶 ID
        Comment newComment = commentService.addComment(userID, postID, comment.getContent());
        return (newComment != null) ? ResponseEntity.ok(newComment) : ResponseEntity.badRequest().body("新增留言失敗");
    }

    // 查詢某篇發文的所有留言
    @GetMapping("/{postID}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postID) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postID));
    }

    // 刪除留言
    @DeleteMapping("/{commentID}")
    public ResponseEntity<String> deleteComment(@PathVariable Long commentID, Authentication authentication) {
        Long userID = Long.parseLong(authentication.getName());
        String result = commentService.deleteComment(userID, commentID);

        switch (result) {
            case "NOT_FOUND":
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("留言不存在");
            case "FORBIDDEN":
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("無權刪除該留言");
            case "SUCCESS":
                return ResponseEntity.ok("留言刪除成功");
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("未知錯誤");
        }
    }
}
