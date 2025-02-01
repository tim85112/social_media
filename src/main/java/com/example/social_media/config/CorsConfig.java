package com.example.social_media.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 允許所有 API
                        .allowedOrigins("http://localhost:3000","http://localhost:8081") // 允許的前端網址
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允許的 HTTP 方法
                        .allowedHeaders("*") // 允許的 Header
                        .allowCredentials(true); // 允許攜帶 Cookie
            }
        };
    }
}
