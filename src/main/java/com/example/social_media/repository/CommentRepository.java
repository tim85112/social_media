package com.example.social_media.repository;

import com.example.social_media.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostPostID(Long postID); // 查詢某篇發文的所有留言
    
    void deleteByPostPostID(Long postID); // 刪除某篇發文的所有留言
}

