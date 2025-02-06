package com.example.social_media.service;

import com.example.social_media.model.Comment;
import com.example.social_media.model.Post;
import com.example.social_media.model.User;
import com.example.social_media.repository.CommentRepository;
import com.example.social_media.repository.PostRepository;
import com.example.social_media.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;
    
    // 新增留言
    public Comment addComment(Long userID, Long postID, String content) {
        Optional<User> userOpt = userRepository.findById(userID);
        Optional<Post> postOpt = postRepository.findById(postID);

        if (userOpt.isPresent() && postOpt.isPresent()) {
            Comment comment = new Comment();
            comment.setUser(userOpt.get());
            comment.setPost(postOpt.get());
            comment.setContent(sanitizeInput(content));
            return commentRepository.save(comment);
        }

        return null; // 發文或用戶不存在
    }
    
    /**
     * 清理用戶輸入，防止 XSS 攻擊
     * @param input 用戶輸入的文字
     * @return 清理後的安全文字
     */
    private String sanitizeInput(String input) {
        if (input == null) {
            return null;
        }
        return input
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#x27;")
                .replaceAll("&", "&amp;");
        }

    // 列出所有留言（針對某篇發文）
    public List<Comment> getCommentsByPost(Long postID) {
        return commentRepository.findByPostPostID(postID);
    }

    // 刪除留言（僅限留言者刪除）
    public String deleteComment(Long userID, Long commentID) {
        Optional<Comment> commentOpt = commentRepository.findById(commentID);

        if (commentOpt.isEmpty()) {
            return "NOT_FOUND";
        }

        Comment comment = commentOpt.get();
        if (!comment.getUser().getUserID().equals(userID)) {
            return "FORBIDDEN";
        }

        commentRepository.deleteById(commentID);
        return "SUCCESS";
    }
    
//    // 更新留言
//    public String updateComment(Long userID, Long commentID, String newContent) {
//        Optional<Comment> optionalComment = commentRepository.findById(commentID);
//
//        if (!optionalComment.isPresent()) {
//            return "NOT_FOUND"; // 找不到留言
//        }
//
//        Comment comment = optionalComment.get();
//        
//        // 確保只有原留言作者可以編輯
//        if (!comment.getUser().getUserID().equals(userID)) {  // 修正這裡
//            return "FORBIDDEN"; // 無權限編輯
//        }
//
//        comment.setContent(newContent);  // 更新內容
//        commentRepository.save(comment); // 儲存更新
//        return "SUCCESS"; // 回傳成功狀態
//    }
    
 // 更新留言，改用 Stored Procedure
    public String updateComment(Long userID, Long commentID, String newContent) {
        try {
            commentRepository.updateComment(commentID, userID, newContent);
            return "SUCCESS";
        } catch (Exception e) {
            return "FORBIDDEN"; // 可能是權限不足
        }
    }
}
