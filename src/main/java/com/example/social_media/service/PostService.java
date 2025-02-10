package com.example.social_media.service;

import com.example.social_media.model.Post;
import com.example.social_media.dto.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.example.social_media.dto.PostResponse;
import com.example.social_media.model.Comment;
import com.example.social_media.model.User;
import com.example.social_media.dto.repository.CommentRepository;
import com.example.social_media.dto.repository.LikeRepository;
import com.example.social_media.dto.repository.UserRepository;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository; // 新增 CommentRepository 來刪除留言

    @Autowired
    private LikeRepository likeRepository;

    // 定義圖片上傳目錄
    private static final String UPLOAD_DIR = "C:\\Users\\ivan\\Desktop\\專案圖片";

    public Post createPost(Long userID, String content, MultipartFile image) throws IOException {
        String imagePath = null;

        // 處理圖片上傳
        if (image != null && !image.isEmpty()) {
            imagePath = saveImage(image);
        }

        // 創建貼文
        Post post = new Post();
        // 不能直接用 setUserId() 因為 Post 實體沒有這個方法
        // Post 實體只有 setUser() 方法來設置關聯的 User 物件
        Optional<User> user = userRepository.findById(userID);
        if (user.isPresent()) {
            post.setUser(user.get()); // 調用 Post.java 中的 setUser() 方法
        }
        post.setContent(content); // 調用 Post.java 中的 setContent() 方法
        post.setImage(imagePath); // 調用 Post.java 中的 setImage() 方法
        // createdAt 在 Post 類中已經有預設值 new Date()，不需要手動設置

        return postRepository.save(post);
    }





    private String saveImage(MultipartFile image) throws IOException {
        // 確保上傳目錄存在
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 獲取圖片名稱並保存
        String imageName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        Path imagePath = uploadPath.resolve(imageName);
        Files.copy(image.getInputStream(), imagePath);

        // 返回相對路徑
        return "/images/" + imageName;  // 修改這裡，返回相對路徑
    }

    /**
     * 取得所有發文（包含作者資訊、留言、按讚數）
     */
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();// 從 PostRepository 取得所有發文
        return posts.stream()
                .map(post -> {
                    int likeCount = likeRepository.countByPostPostID(post.getPostID());
                    // 是計算這篇發文的按讚數

                    List<Comment> commentList = commentRepository.findByPostPostID(post.getPostID()); // 取得留言
                    // 是查詢這篇發文的所有留言

                    return new PostResponse(post, likeCount, commentList);
                    // 把 （發文）（按讚數）（留言）打包成 PostResponse，讓 API 回應完整資訊！
                })
                .collect(Collectors.toList());
    }

    /**
     * 取得特定用戶的發文
     * 
     * @param userID 用戶 ID
     * @return 該用戶的發文列表
     */
    public List<Post> getPostsByUser(Long userID) {
        return postRepository.findByUserUserID(userID);
    }

    /**
     * 更新發文（僅限發文者編輯）
     * 
     * @param userID     當前用戶 ID
     * @param postID     要更新的發文 ID
     * @param newContent 新的發文內容
     * @param newImage   新的圖片 URL（可以為 null）
     * @return 回傳 "SUCCESS"（更新成功）、"NOT_FOUND"（發文不存在）、"FORBIDDEN"（無權編輯）
     */
    public String updatePost(Long userID, Long postID, String newContent, String newImage) {
        Optional<Post> postOpt = postRepository.findById(postID);

        if (postOpt.isEmpty()) {
            return "NOT_FOUND"; // 發文不存在
        }

        Post post = postOpt.get();

        if (!post.getUser().getUserID().equals(userID)) {
            return "FORBIDDEN"; // 用戶無權編輯此發文
        }

        // 更新發文內容
        post.setContent(newContent);
        post.setImage(newImage);
        postRepository.save(post); // 儲存更新後的發文

        return "SUCCESS";
    }

    /**
     * 刪除發文（僅限發文者刪除）
     * @param userID 當前用戶 ID
     * @param postID 要刪除的發文 ID
     * @return "SUCCESS"（刪除成功）、"NOT_FOUND"（發文不存在）、"FORBIDDEN"（無權刪除）
     */

//    @Transactional // 確保發文與留言刪除是同一個 Transaction
//    public String deletePost(Long userID, Long postID) {
//        Optional<Post> postOpt = postRepository.findById(postID);
//
//        if (postOpt.isEmpty()) {
//            return "NOT_FOUND"; // 發文不存在
//        }
//
//        Post post = postOpt.get();
//        if (!post.getUser().getUserID().equals(userID)) {
//            return "FORBIDDEN"; // 用戶無權刪除此發文
//        }
//
//        // 先刪除該發文的所有留言
//        commentRepository.deleteByPostPostID(postID);
//
//        // // 故意丟出錯誤，模擬異常
//        // if (true) throw new RuntimeException("模擬錯誤，測試回滾！");
//
//        // 驗證通過後刪除發文
//        postRepository.deleteById(postID);
//        return "SUCCESS";
//    }
//    
//    @Service
   

    @Transactional // 加入 Transaction，確保資料一致性
        public String deletePost(Long userID, Long postID) {
            try {
                postRepository.deletePost(postID, userID);
                return "SUCCESS";
            } catch (Exception e) {
                return "FORBIDDEN"; // 無權刪除或發生錯誤
            }
        }
  


    /**
     * 新增發文
     * 
     * @param userID  發文者的 ID
     * @param content 發文內容
     * @param image   可選的圖片 URL
     * @return 創建成功的 Post 物件，若失敗則返回 null
     */

    // 防止防止 XSS 跨站腳本攻擊

    public String sanitizeInput(String input) {
        return HtmlUtils.htmlEscape(input); // 轉換 HTML 特殊字符，防止 XSS
        // 將 <script> 轉成 &lt;script&gt;，防止 JavaScript 被執行。
    }

    public Post createPost(Long userID, String content, String image) {
        Optional<User> userOpt = userRepository.findById(userID);
        if (userOpt.isPresent()) {
            Post post = new Post();
            post.setUser(userOpt.get());
            post.setContent(sanitizeInput(content)); // 過濾 XSS
            post.setImage(image);
            return postRepository.save(post);
        }
        return null;
    }
}
