package com.example.social_media.repository;

import com.example.social_media.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostPostID(Long postID); // 查詢某篇發文的所有留言
    @Procedure(name = "UpdateComment")
    void updateComment(@Param("p_commentID") Long commentID, 
                       @Param("p_userID") Long userID, 
                       @Param("p_newContent") String newContent);
    void deleteByPostPostID(Long postID); // 刪除某篇發文的所有留言
}


    
   
