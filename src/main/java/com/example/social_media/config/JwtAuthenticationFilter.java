package com.example.social_media.config;

import com.example.social_media.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * JWT 驗證過濾器，負責解析請求中的 JWT，並驗證其有效性。
 * 如果驗證成功，則將用戶資訊存入 Spring Security 上下文，讓後續請求可以識別用戶身份。
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * 過濾請求，提取 JWT，並驗證用戶身份。
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // 從請求的 Header 取得 Authorization 欄位
        String authHeader = request.getHeader("Authorization");

        // 確保 Authorization 標頭存在且格式正確（以 "Bearer " 開頭）
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // 取得 JWT（去除 "Bearer " 前綴）
            String token = authHeader.substring(7);
            System.out.println("Extracted Token: " + token); // 確保 Token 正確獲取

            // 驗證 JWT 是否有效
            if (JwtUtil.validateToken(token)) {
                // 從 Token 提取用戶 ID
                Long userId = JwtUtil.extractUserId(token);
                System.out.println("Extracted User ID: " + userId); // 確保 JWT 解析的 userId 正確

                // 使用 Spring Security 的 UserDetails 來建立用戶信息
                UserDetails userDetails = User.withUsername(userId.toString()).password("").authorities("USER").build();

                // 建立 Spring Security 驗證對象
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 將用戶身份存入 Spring Security 上下文
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("Token validation failed!"); // JWT 驗證失敗
            }
        } else {
            System.out.println("Authorization header missing or incorrect!"); // 未提供 Token 或格式錯誤
        }

        // 繼續執行請求
        filterChain.doFilter(request, response);
    }
}
