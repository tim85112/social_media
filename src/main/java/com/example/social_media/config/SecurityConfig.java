package com.example.social_media.config;

import com.example.social_media.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 啟用方法級別的安全性檢查
public class SecurityConfig {
 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // 關閉 CSRF，因為我們使用 JWT
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 不使用 session
            .and()
            .authorizeHttpRequests() // 新版的 Spring Security 使用 authorizeHttpRequests
                .requestMatchers("/user/login", "/user/register","/test","/").permitAll() // 登入和註冊和測試不需要驗證
                .requestMatchers("/posts/create","/likes/**").authenticated() // 允許已登入使用者發文
                .anyRequest().authenticated() // 其他 API 都需要驗證
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 添加 JWT 驗證過濾器

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}






