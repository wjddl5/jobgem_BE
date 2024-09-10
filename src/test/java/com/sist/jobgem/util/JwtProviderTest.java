package com.sist.jobgem.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtProviderTest {
    @Autowired
    private JwtProvider jwtProvider;

    @Test
    void testGetAccessToken() {
        // given
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("name", "kyb");

        // when
        String accessToken = jwtProvider.getAccessToken(map);
        System.out.println("accessToken = " + accessToken);

        // then
        assertNotNull(accessToken);
    }
}
