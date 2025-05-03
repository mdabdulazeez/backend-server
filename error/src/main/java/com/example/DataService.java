package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    
    // Global variables - bad practice
    Map data = new HashMap();
    List items = new ArrayList();
    
    // Hardcoded credentials
    private String dbUrl = "jdbc:mysql://localhost:3306/productiondb";
    private String dbUser = "root";
    private String dbPass = "SuperSecret123!";
    
    // Non-private constructor in utility class
    public DataService() {
        // Empty constructor
    }
    
    // Inconsistent method naming
    public void SaveData(String key, Object value) {
        // Using raw types - unchecked operation warning
        data.put(key, value);
        
        // Debugging left in production code
        System.out.println("Saved data: " + key + " = " + value);
    }
    
    // Method with too many parameters
    public List processItems(String param1, int param2, boolean param3, double param4, 
                            Map param5, List param6, String param7, Object param8) {
        // Using raw types
        List result = new ArrayList();
        
        // Deeply nested loops - complexity issue
        for (int i = 0; i < param2; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 5; k++) {
                    if (param3) {
                        for (int m = 0; m < 3; m++) {
                            result.add("Item" + i + j + k + m);
                        }
                    }
                }
            }
        }
        
        return result;
    }
    
    // Identical code duplication
    public void method1() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Processing " + i);
            if (i % 2 == 0) {
                System.out.println("Even number");
            } else {
                System.out.println("Odd number");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Duplicate of method1 - code duplication issue
    public void method2() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Processing " + i);
            if (i % 2 == 0) {
                System.out.println("Even number");
            } else {
                System.out.println("Odd number");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    // Method with security vulnerabilities
    public String executeQuery(String userInput) {
        // SQL Injection vulnerability
        String query = "SELECT * FROM users WHERE name = '" + userInput + "'";
        
        try {
            // Resource leak - connection not closed
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            if (rs.next()) {
                return rs.getString("data");
            }
        } catch (SQLException e) {
            // Exception swallowing
        }
        
        return null;
    }
    
    // Method with resource leak
    public byte[] readFile(String path) {
        FileInputStream fis = null;
        try {
            File file = new File(path);
            fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            // Resource leak - stream not closed
            return data;
        } catch (IOException e) {
            // Empty catch block
        }
        return null;
    }
    
    // Unused private method - dead code
    private void unusedMethod() {
        System.out.println("This method is never called");
    }
} 