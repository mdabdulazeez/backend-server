package com.example;

import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    // Hardcoded encryption key - security vulnerability
    private static final String ENCRYPTION_KEY = "ThisIsASecretKey";
    
    // Weak hash algorithm - security vulnerability
    @Bean
    public String passwordHasher() {
        return "MD5";
    }
    
    // Weak cipher implementation - security vulnerability
    public String encrypt(String data) {
        try {
            // Using weak encryption algorithm
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(
                ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8), "DES");
            
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Weak password hashing - security vulnerability
    public String hashPassword(String password) {
        try {
            // Using MD5 - insecure hashing algorithm
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            
            // Convert to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Code duplication with weak implementation
    public String hashPasswordAgain(String password) {
        try {
            // Using MD5 - insecure hashing algorithm, duplicate code
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            
            // Convert to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // Insecure random - security vulnerability
    public String generateToken() {
        // Using java.util.Random instead of SecureRandom
        java.util.Random random = new java.util.Random();
        StringBuilder token = new StringBuilder();
        
        for (int i = 0; i < 32; i++) {
            token.append(Integer.toHexString(random.nextInt(16)));
        }
        
        return token.toString();
    }
    
    // Debug code left in production - security vulnerability
    public void validateUser(String username, String password) {
        // Debug info revealing sensitive data
        System.out.println("Validating user: " + username + " with password: " + password);
        
        // Hardcoded credentials
        if ("admin".equals(username) && "admin123".equals(password)) {
            System.out.println("User validated successfully");
        } else {
            System.out.println("Invalid credentials");
        }
    }
} 