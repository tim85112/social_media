package com.example.social_media.service;

import com.example.social_media.model.User;
import com.example.social_media.repository.UserRepository;
import com.example.social_media.util.PasswordUtil;
import com.example.social_media.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 註冊用戶：保存用戶資料到資料庫。
     * @param user 包含用戶資訊的對象
     * @return 成功返回 true，失敗返回 false
     */
    public boolean registerUser(User user) {
    	if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        try {
        	
        	// 防止 XSS 攻擊，清理使用者名稱
            user.setUserName(sanitizeInput(user.getUserName()));
            
            
            // 密碼加鹽與雜湊處理
            String salt = PasswordUtil.generateSalt();
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword(), salt);

            // 設定加鹽和雜湊後的密碼
            user.setPassword(hashedPassword);
            user.setSalt(salt);

            // 保存用戶到資料庫
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 驗證用戶身份並生成 JWT。
     * @param phoneNumber 用戶輸入的手機號碼
     * @param password 用戶輸入的密碼
     * @return 驗證成功返回 JWT，否則返回 null
     */
    public String authenticate(String phoneNumber, String password) {
        // 從資料庫中查找用戶
    	if (!phoneNumber.matches("^[0-9]{10}$")) {//正則表達式去過濾不對的格式
            throw new IllegalArgumentException("Invalid phone number format");
        }
        Optional<User> userOpt = userRepository.findByPhoneNumber(phoneNumber);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // 驗證密碼是否正確（需加鹽與雜湊比對）
            if (PasswordUtil.verifyPassword(password, user.getPassword(), user.getSalt())) {
                // 驗證成功，生成 JWT
                return JwtUtil.generateToken(user.getUserID()); // 使用靜態方法生成 JWT
            }
        }
        return null; // 驗證失敗
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
    
}





