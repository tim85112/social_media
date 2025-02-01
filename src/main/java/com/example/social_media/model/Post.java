package com.example.social_media.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Post") // 對應資料庫的 Post 表
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postID; // 自動遞增的發文 ID

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user; // 關聯到 User 表

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 發文內容

    @Column(length = 255)
    private String image; // 圖片（選填）

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt = new Date(); // 發文時間

    // Getters 和 Setters
    public Long getPostID() { return postID; }
    public void setPostID(Long postID) { this.postID = postID; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public Date getCreatedAt() { return createdAt; }
}
