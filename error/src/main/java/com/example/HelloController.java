package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Date;
import java.util.Random;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
public class HelloController {

    // Magic numbers - code smell
    private static final int TIMEOUT = 5000;
    private static final int MAX_RETRIES = 3;
    
    // Non-final static field - security issue
    public static String API_KEY = "1234567890abcdef";
    
    @GetMapping("/hello")
    public String sayHello() {
        // Debugging left in code - info leak
        System.out.println("sayHello method called");
        
        // Returning hardcoded value
        return "Hello, World!";
    }
    
    // SQL Injection vulnerability
    @GetMapping("/user")
    public String getUserById(@RequestParam String id) {
        String query = "SELECT * FROM users WHERE id = " + id;
        
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb", "admin", "password");
            Statement stmt = conn.createStatement();
            // Execute the query - SQL injection risk
            stmt.execute(query);
            // Resources not properly closed
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return "User data for ID: " + id;
    }
    
    // Method too complex - high cyclomatic complexity
    @PostMapping("/process")
    public String processData(@RequestBody String data) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        
        // Long method with high complexity
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (Character.isDigit(c)) {
                result.append(c);
            } else if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    result.append(Character.toLowerCase(c));
                } else {
                    result.append(Character.toUpperCase(c));
                }
            } else if (c == ' ') {
                result.append('_');
            } else if (c == '.') {
                result.append(',');
            } else if (c == '!') {
                result.append('?');
            } else {
                result.append(c);
            }
            
            // Unnecessary random operations
            if (random.nextBoolean()) {
                result.append(random.nextInt(10));
            }
        }
        
        // File operation with path traversal vulnerability
        try {
            File file = new File("logs/" + data.substring(0, 5) + ".log");
            PrintWriter writer = new PrintWriter(new FileWriter(file, true));
            writer.println("Processed: " + data);
            // Resource leak - writer not closed
        } catch (IOException e) {
            // Swallowed exception - bad practice
        }
        
        return result.toString();
    }
    
    // Duplicate method - code duplication
    @GetMapping("/hello2")
    public String sayHelloAgain() {
        // Debugging left in code - info leak
        System.out.println("sayHello method called");
        
        // Returning hardcoded value 
        return "Hello, World!";
    }
} 