package com.example.usermanager.main;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "123456";
        String newPassword = bCryptPasswordEncoder.encode(rawPassword);
        System.out.println("" + newPassword);
        //$2a$10$rjGXQPbvTm0XRcE6T86wiuzQmLH9lSAkcQNRs/U97GJuw2j0o3E4G
    }
}
