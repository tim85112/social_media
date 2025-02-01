package com.example.social_media.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class TestController {

    private final DataSource dataSource;
   
    //建立空參
    public TestController(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    //
    @GetMapping("/test")
    public String testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return "資料庫連線成功：" + connection.getMetaData().getDatabaseProductName();
        } catch (Exception e) {
            return "資料庫連線失敗：" + e.getMessage();
        }
    }
}
