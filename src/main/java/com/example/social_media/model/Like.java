package com.example.social_media.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity // 告訴 JPA 這是一個實體（對應資料庫中的表）
@Table(name = "Likes", uniqueConstraints = @UniqueConstraint(columnNames = {"postID", "userID"}))
//每個用戶只能對同一篇文章按一次讚（UNIQUE(postID, userID)）。

public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeID;

   
    @ManyToOne // 多對一：多個按讚記錄對應同一篇發文
    @JoinColumn(name = "postID", nullable = false)
    //👉 Likes     表的 postID 是 外鍵，連接 Post 表。
    private Post post;

    // 當刪除一個用戶時，用戶的按讚記錄也會刪除（@ManyToOne 關聯）。
    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt = new Date();

    
    
    
    
    // Getters & Setters
    public Long getLikeID() { return likeID; }
    public void setLikeID(Long likeID) { this.likeID = likeID; }

    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Date getCreatedAt() { return createdAt; }
}
