package com.example.social_media.controller;

import com.example.social_media.dto.LoginRequest;
import com.example.social_media.dto.LoginResponse;
import com.example.social_media.model.User;
import com.example.social_media.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 註冊 API：接收用戶註冊資料並保存到資料庫中。
     * @param user 用戶的註冊資訊
     * @return 成功或失敗訊息
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        boolean isRegistered = userService.registerUser(user);
        if (isRegistered) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed");
        }
    }

    /**
     * 登入 API：接收用戶輸入的手機號碼和密碼，調用服務層驗證身份。
     * @param loginRequest 包含手機號碼和密碼的請求對象
     * @return 驗證成功時返回 JWT，否則返回錯誤訊息
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // 呼叫服務層進行身份驗證
        String token = userService.authenticate(loginRequest.getPhoneNumber(), loginRequest.getPassword());
        
        if (token != null) {
            // 驗證成功，返回 JWT
            return ResponseEntity.ok(new LoginResponse(token));
        } else {
            // 驗證失敗，返回 401 錯誤
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid phone number or password");
        }
    }
}
