package com.example.social_media.dto;


//用於封裝登入成功後的回應
public class LoginResponse {
 private String token;

 public LoginResponse(String token) {
     this.token = token;
 }

 public String getToken() {
     return token;
 }

 public void setToken(String token) {
     this.token = token;
 }
}