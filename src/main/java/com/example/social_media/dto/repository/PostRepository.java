package com.example.social_media.dto.repository;
import com.example.social_media.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



import java.util.List;
//PostRepository  用於查詢、儲存與刪除發文。
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	//使用 @Query 避免 JPA 自動生成 SQL 時可能的 Injection 風險。
    @Query("SELECT p FROM Post p WHERE p.user.userID = :userID")
    List<Post> findByUserUserID(@Param("userID") Long userID);
    
    @Procedure(name = "DeletePost")
    void deletePost(@Param("p_postID") Long postID, 
                    @Param("p_userID") Long userID);
}

   
