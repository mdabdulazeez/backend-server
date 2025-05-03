package com.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

@RestController
public class UserController {

    @Autowired
    private DataService dataService;

    // Hardcoded map - mutable public static field
    public static Map<String, User> USERS = new HashMap<>();
    
    // Static initializer with complex logic
    static {
        USERS.put("user1", new User("user1", "John Doe", 30));
        USERS.put("user2", new User("user2", "Jane Smith", 25));
        USERS.put("user3", new User("user3", "Bob Johnson", 40));
        
        // Unnecessary complex initialization
        for (int i = 4; i <= 10; i++) {
            USERS.put("user" + i, new User("user" + i, "Generated User " + i, 20 + i));
        }
    }
    
    // XSS vulnerability
    @GetMapping("/user/{id}")
    public String getUserDetails(@PathVariable String id) {
        User user = USERS.get(id);
        if (user != null) {
            // XSS vulnerability - directly returning user input
            return "<div>User: " + user.getName() + "</div>";
        }
        return "User not found";
    }
    
    // Method with too many local variables
    @PostMapping("/user/register")
    public String registerUser(@RequestBody Map<String, Object> userData) {
        // Too many local variables
        String userId = (String) userData.get("id");
        String name = (String) userData.get("name");
        Integer age = (Integer) userData.get("age");
        String email = (String) userData.get("email");
        String phone = (String) userData.get("phone");
        String address = (String) userData.get("address");
        String city = (String) userData.get("city");
        String country = (String) userData.get("country");
        String zipCode = (String) userData.get("zipCode");
        
        // Debug info
        System.out.println("Registering user: " + userId);
        
        // Unused variables
        String registrationDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        boolean isActive = true;
        
        // Direct object reference vulnerability
        USERS.put(userId, new User(userId, name, age));
        
        // File operation with path traversal vulnerability
        try {
            File userFile = new File("users/" + userId + ".txt");
            FileOutputStream fos = new FileOutputStream(userFile);
            fos.write(("User: " + name + ", Age: " + age).getBytes());
            // Resource leak - stream not closed
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return "User registered: " + userId;
    }
    
    // Method with security vulnerabilities and unnecessary complexity
    @GetMapping("/admin/users")
    public List<User> getAllUsers(@RequestParam(required = false) String role) {
        // No authentication check for admin endpoint
        
        List<User> userList = new ArrayList<>(USERS.values());
        
        // Unnecessary complexity
        if (role != null) {
            if (role.equals("admin")) {
                // Do nothing, return all users
            } else if (role.equals("manager")) {
                // Do nothing, return all users
            } else if (role.equals("user")) {
                // Do nothing, return all users
            } else {
                // Do nothing, return all users
            }
        }
        
        // Debugging
        System.out.println("Returning " + userList.size() + " users");
        
        return userList;
    }
    
    // Unsafe reflection usage
    @GetMapping("/execute")
    public String executeMethod(@RequestParam String className, @RequestParam String methodName) {
        try {
            // Using reflection dangerously
            Class<?> clazz = Class.forName(className);
            Object instance = clazz.newInstance();
            Method method = clazz.getMethod(methodName);
            Object result = method.invoke(instance);
            return "Executed: " + result;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
    
    // Inner class with public fields - encapsulation issue
    public static class User {
        public String id;
        public String name;
        public int age;
        
        public User(String id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }
        
        public String getName() {
            return name;
        }
    }
} 