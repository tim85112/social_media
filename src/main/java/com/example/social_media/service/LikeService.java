package com.example.social_media.service;

import com.example.social_media.model.Like;
import com.example.social_media.model.Post;
import com.example.social_media.model.User;
import com.example.social_media.dto.repository.LikeRepository;
import com.example.social_media.dto.repository.PostRepository;
import com.example.social_media.dto.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 按讚或取消按讚
     */
    @Transactional
    public String toggleLike(Long userID, Long postID) {
        Optional<User> userOpt = userRepository.findById(userID);
        Optional<Post> postOpt = postRepository.findById(postID);

        if (userOpt.isEmpty() || postOpt.isEmpty()) {
            return "NOT_FOUND"; // 用戶或發文不存在
        }

        User user = userOpt.get();
        Post post = postOpt.get();

        Optional<Like> existingLike = likeRepository.findByUserAndPost(user, post);
        if (existingLike.isPresent()) {
            likeRepository.deleteByUserAndPost(user, post); // 取消按讚
            return "UNLIKED"; // 已取消按讚
        } else {
            Like like = new Like();
            like.setUser(user);
            like.setPost(post);
            likeRepository.save(like);
            return "LIKED"; // 已成功按讚
        }
    }

    /**
     * 取得發文的按讚數
     */
    public int getLikeCount(Long postID) {
        return likeRepository.countByPostPostID(postID);
    }
}
