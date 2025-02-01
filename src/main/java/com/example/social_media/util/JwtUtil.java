package com.example.social_media.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // 固定的 Secret Key，請確保這個密鑰足夠長（至少 256 位元）
    private static final String SECRET = "YourSecureFixedSecretKey1234567890ABCDEFGHIJKLMNOPQRSTUV";
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // JWT 的有效時間 (1 小時)
    private static final long EXPIRATION_TIME = 3600000; // 毫秒

    /**
     * 生成 JWT
     * @param userId 用戶 ID
     * @return 生成的 JWT 字串
     */
    public static String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // 設定主題為用戶 ID
                .setIssuedAt(new Date())            // 簽發時間
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 過期時間
                .signWith(SECRET_KEY)              // 使用密鑰簽名
                .compact();
    }

    /**
     * 驗證 JWT 是否有效
     * @param token JWT 字串
     * @return 如果有效返回 true，否則返回 false
     */
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
            return true; // 驗證通過
        } catch (JwtException e) {
            return false; // 驗證失敗
        }
    }

    /**
     * 從 JWT 中提取用戶 ID
     * @param token JWT 字串
     * @return 提取的用戶 ID
     */
    public static Long extractUserId(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.valueOf(claims.getSubject());
    }
}
