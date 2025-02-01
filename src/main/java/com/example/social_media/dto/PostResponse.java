package com.example.social_media.dto;

import com.example.social_media.model.Post;
import com.example.social_media.model.Comment;
import java.util.List;
import java.util.stream.Collectors;

public class PostResponse {
    private Long postID;
    private String content;
    private String image;
    private String authorName;
    private Long authorID;
    private int likes;
    private List<CommentResponse> comments;

    // 建構子：將 Post 轉換為 PostResponse
    public PostResponse(Post post, int likes, List<Comment> commentList) {
        this.postID = post.getPostID();
        this.content = post.getContent();
        this.image = post.getImage();
        this.authorName = post.getUser().getUserName();
        this.authorID = post.getUser().getUserID();
        this.likes = likes;
        this.comments = commentList.stream()
            .map(CommentResponse::new)
            .collect(Collectors.toList());
    }

    // Getters
    public Long getPostID() { return postID; }
    public String getContent() { return content; }
    public String getImage() { return image; }
    public String getAuthorName() { return authorName; }
    public Long getAuthorID() { return authorID; }
    public int getLikes() { return likes; }
    public List<CommentResponse> getComments() { return comments; }
}
