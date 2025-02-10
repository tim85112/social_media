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
        if (user == null) {
            System.out.println("❌ User 對象為 null，請檢查請求數據！");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("請求數據錯誤");
        }
        System.out.println("✅ 接收到的 User：");
        System.out.println("userName: " + user.getUserName());
        System.out.println("phoneNumber: " + user.getPhoneNumber());
        System.out.println("email: " + user.getEmail());
        System.out.println("password: " + user.getPassword());
        System.out.println("biography: " + user.getBiography());

        try {
            boolean isRegistered = userService.registerUser(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("伺服器發生錯誤");
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
    @GetMapping("/login")
    public String loginPage() {
        return "This is the login endpoint. Please use POST method with proper credentials.";
    }
}
