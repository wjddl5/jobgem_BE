package com.sist.jobgem.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sist.jobgem.util.jwt.AccessTokenClaims;
import com.sist.jobgem.util.jwt.JwtProvider;

@SpringBootTest
public class JwtProviderTest {
    @Autowired
    private JwtProvider jwtProvider;

    @Test
    void testGetAccessToken() {
        // given
        AccessTokenClaims claims = new AccessTokenClaims("ROLE_USER", "kyb");

        // when
        String accessToken = jwtProvider.getAccessToken(claims);
        System.out.println("accessToken = " + accessToken);

        // then
        assertNotNull(accessToken);
    }
}
