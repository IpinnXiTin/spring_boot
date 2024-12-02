package com.ipinxitin.identity_service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import jakarta.xml.bind.DatatypeConverter;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class IdentityServiceApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(IdentityServiceApplicationTests.class);

    @Test
    void hash() throws NoSuchAlgorithmException {
        String password = "123456";

        // MD5 hashing
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String md5Hash = DatatypeConverter.printHexBinary(digest);
        log.info("MD5 round 1: {}", md5Hash);

        // MD5 hashing round 2
        md.update(password.getBytes());
        digest = md.digest();
        md5Hash = DatatypeConverter.printHexBinary(digest);
        log.info("MD5 round 2: {}", md5Hash);

        // BCrypt hashing
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        log.info("BCrypt round 1: {}", passwordEncoder.encode(password));
        log.info("BCrypt round 2: {}", passwordEncoder.encode(password));
    }
}
