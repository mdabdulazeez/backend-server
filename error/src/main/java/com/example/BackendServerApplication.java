package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class BackendServerApplication {
    
    // Hardcoded credentials - security vulnerability
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "Password123!";
    
    // Unused variables - dead code
    private static String unusedString = "This is never used";
    private static int unusedInt = 42;
    
    // Public static mutable field - security issue
    public static ArrayList<String> SENSITIVE_DATA = new ArrayList<>();

    public static void main(String[] args) {
        // Duplicate code block 1
        for (int i = 0; i < 10; i++) {
            System.out.println("Processing item " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Empty catch block - bad practice
        try {
            File file = new File("nonexistent.txt");
            if (file.exists()) {
                System.out.println("File exists");
            }
        } catch (Exception e) {
            // Empty catch block
        }
        
        // Duplicate code block 2
        for (int i = 0; i < 10; i++) {
            System.out.println("Processing item " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        // Unclosed resource - resource leak
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Connection is not closed
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Finally launch the app
        SpringApplication.run(BackendServerApplication.class, args);
    }
    
    // Unnecessary complexity - cognitive complexity issue
    public static boolean isComplexCondition(int a, int b, int c, String s, boolean flag) {
        if (a > 0) {
            if (b > 0) {
                if (c > 0) {
                    if (s != null && s.length() > 0) {
                        if (flag) {
                            return true;
                        } else {
                            return false;
                        }
                    } else {
                        return a > b ? true : false;
                    }
                } else {
                    return c < a && b != c ? true : a == b;
                }
            } else {
                return a * b > c || s == null;
            }
        }
        return false;
    }
} 