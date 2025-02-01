package com.example.social_media.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;  // 自動生成的唯一 ID

    @Column(nullable = false, length = 100)
    private String userName;  // 使用者名稱

    @Column(name = "PhoneNumber", nullable = false, unique = true, length = 15)
    private String phoneNumber;  // 手機號碼

    @Column(nullable = false, unique = true, length = 255)
    private String email;  // 電子郵件

    @Column(nullable = false, length = 255)
    private String password;  // 密碼

    @Column(length = 255)
    private String salt;  // 密碼鹽值

    @Column(length = 255)
    private String coverImage;  // 封面圖片（選填）

    @Column(columnDefinition = "TEXT")
    private String biography;  // 使用者簡介（選填）

    // Getters and Setters
    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
