package com.example.social_media.config;

import com.example.social_media.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .cors() // 添加這行
        .and()
            .csrf().disable() // 關閉 CSRF，因為我們使用 JWT
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 不使用 session
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/user/login", "/user/register", "/test", "/").permitAll() // 註冊、登入、測試 API 允許訪問
            .requestMatchers("/posts/all").permitAll() // ✅ 允許所有人訪問所有貼文與留言
            .requestMatchers("/posts/create", "/posts/**").authenticated() // ❗ 其他貼文操作需要登入
            .anyRequest().authenticated() // 其他 API 需要驗證
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 添加 JWT 驗證過濾器

        return http.build();
    }
    
    
    
    
    
}




