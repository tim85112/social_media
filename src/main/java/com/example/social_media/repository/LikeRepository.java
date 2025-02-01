package com.example.social_media.repository;

import com.example.social_media.model.Like;
import com.example.social_media.model.Post;
import com.example.social_media.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
	
    int countByPostPostID(Long postID); // 計算某篇發文的按讚數

    Optional<Like> findByUserAndPost(User user, Post post); // 查找用戶是否已按讚

    void deleteByUserAndPost(User user, Post post); // 取消按讚
}
