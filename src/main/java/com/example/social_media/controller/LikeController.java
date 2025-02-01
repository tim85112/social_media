package com.example.social_media.controller;

import com.example.social_media.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    /**
     * 按讚或取消按讚
     */
    @PostMapping("/{postID}")
    public ResponseEntity<String> toggleLike(@PathVariable Long postID, Authentication authentication) {
    	if (authentication == null) {
            System.out.println("❌ Authentication 為 null，請確認 JWT Token 是否正確傳遞！");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("未授權");
        }
    	
    	
    	Long userID = Long.parseLong(authentication.getName());
        String result = likeService.toggleLike(userID, postID);

        switch (result) {
            case "LIKED":
                return ResponseEntity.ok("按讚成功");
            case "UNLIKED":
                return ResponseEntity.ok("取消按讚成功");
            case "NOT_FOUND":
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("發文或用戶不存在");
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("未知錯誤");
        }
    }

    /**
     * 取得某篇發文的按讚數
     */
    @GetMapping("/{postID}")
    public ResponseEntity<Integer> getLikeCount(@PathVariable Long postID) {
        int likeCount = likeService.getLikeCount(postID);
        return ResponseEntity.ok(likeCount);
    }
}
