package com.example.social_media.controller;
import com.example.social_media.model.Post;
import com.example.social_media.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import com.example.social_media.dto.PostResponse;
import com.example.social_media.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;
    
    /**
     * 編輯發文（僅限發文者）
     * @param postID 要編輯的發文 ID
     * @param requestBody 包含新的發文內容和圖片
     * @param authentication 當前用戶的身份
     * @return 更新結果
     */
    @PutMapping("/{postID}")
    public ResponseEntity<String> updatePost(
            @PathVariable Long postID,
            @RequestBody Map<String, String> requestBody,
            Authentication authentication) {

        Long userID = Long.parseLong(authentication.getName()); // 取得當前用戶 ID
        String newContent = requestBody.get("content"); // 新的發文內容
        String newImage = requestBody.get("image"); // 新的圖片 URL

        String result = postService.updatePost(userID, postID, newContent, newImage);

        switch (result) {
            case "NOT_FOUND":
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("發文不存在");
            case "FORBIDDEN":
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("無權編輯該發文");
            case "SUCCESS":
                return ResponseEntity.ok("發文更新成功");
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("未知錯誤");
        }
    }
    /**
     * 新增發文
     */
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody Post post, Authentication authentication) {
        Long userID = Long.parseLong(authentication.getName()); // 取得當前登入者的 ID
        Post newPost = postService.createPost(userID, post.getContent(), post.getImage());
        return (newPost != null) ? ResponseEntity.ok(newPost) : ResponseEntity.badRequest().body("發文失敗");
    }

    /**
     * 取得所有發文（包含作者資訊、留言、按讚數）
     */
    @GetMapping("/all")
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    /**
     * 取得特定使用者的發文
     */
    @GetMapping("/user/{userID}")
    public ResponseEntity<List<Post>> getPostsByUser(@PathVariable Long userID) {
        return ResponseEntity.ok(postService.getPostsByUser(userID));
    }

    /**
     * 刪除發文
     */
    @DeleteMapping("/{postID}")
    public ResponseEntity<String> deletePost(@PathVariable Long postID, Authentication authentication) {
        Long userID = Long.parseLong(authentication.getName()); // 獲取當前用戶的 ID
        
        String result = postService.deletePost(userID, postID);
        
        switch (result) {
            case "NOT_FOUND":
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("發文不存在");
            case "FORBIDDEN":
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("無權刪除該發文");
            case "SUCCESS":
                return ResponseEntity.ok("刪除成功");
            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("未知錯誤");
        }
    }

}
