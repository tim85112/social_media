package com.example.social_media.dto;

//用於封裝登入請求資料
public class LoginRequest {
 private String phoneNumber;// 必須與前端字段名稱對應
 private String password;   // 必須與前端字段名稱對應

 // Getter 和 Setter
 public String getPhoneNumber() {
     return phoneNumber;
 }

 public void setPhoneNumber(String phoneNumber) {
     this.phoneNumber = phoneNumber;
 }

 public String getPassword() {
     return password;
 }

 public void setPassword(String password) {
     this.password = password;
 }
}