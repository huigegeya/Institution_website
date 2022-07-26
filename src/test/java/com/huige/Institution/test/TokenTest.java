package com.huige.Institution.test;

import com.huige.Institution.service.impl.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
public class TokenTest {
    @Autowired
    private TokenService tokenService;

    @Test
    public void testToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("uuid", UUID.randomUUID().toString());
        String jwtToken = tokenService.createToken(claims);
        System.out.println(jwtToken);
    }


}
