package com.example.social_media.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    /**
     * 生成隨機鹽值
     * @return 隨機生成的鹽值（Base64 編碼）
     */
    public static String generateSalt() {
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * 將密碼加鹽並雜湊
     * @param password 原始密碼
     * @param salt 隨機鹽值
     * @return 雜湊後的密碼（Base64 編碼）
     */
    public static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String saltedPassword = salt + password;
            byte[] hashedBytes = md.digest(saltedPassword.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * 驗證密碼是否正確
     * @param inputPassword 用戶輸入的密碼
     * @param storedPassword 資料庫中存儲的雜湊密碼
     * @param salt 資料庫中存儲的鹽值
     * @return 如果密碼匹配則返回 true，否則返回 false
     */
    public static boolean verifyPassword(String inputPassword, String storedPassword, String salt) {
        String hashedInput = hashPassword(inputPassword, salt);
        return hashedInput.equals(storedPassword);
    }
}
