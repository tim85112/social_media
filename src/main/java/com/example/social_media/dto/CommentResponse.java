package com.example.social_media.dto;

import com.example.social_media.model.Comment;

public class CommentResponse {
    private Long commentID;
    private String user;
    private String content;

    // 建構子
    public CommentResponse(Comment comment) {
        this.commentID = comment.getCommentID();
        this.user = comment.getUser().getUserName();
        this.content = comment.getContent();
    }

    // Getters
    public Long getCommentID() { return commentID; }
    public String getUser() { return user; }
    public String getContent() { return content; }
}
