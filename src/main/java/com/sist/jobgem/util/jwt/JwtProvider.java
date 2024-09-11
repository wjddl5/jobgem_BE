package com.sist.jobgem.util.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

    private final int ACCESS_TOKEN_EXPIRES_IN = 60*60;
    private final int REFRESH_TOKEN_EXPIRES_IN = 60*60*24*100;

    @Value("${custom.jwt.secretKey}")
    private String SECRET_KEY;

    @Value("${custom.jwt.subject}")
    private String SUBJECT;

    private SecretKey secretKey;

    public SecretKey getSecretKey() {
        if(secretKey == null) {
            String encoding = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
            secretKey = Keys.hmacShaKeyFor(encoding.getBytes());
        }

        return secretKey;
    }

    private String genToken(Map<String, Object> map, int seconds) {
        long now = new Date().getTime();
        Date accessTokenExpiresIn = new Date(now + 1000L * seconds);

        JwtBuilder jwtBuilder = Jwts.builder()
                .subject(SUBJECT)
                .expiration(accessTokenExpiresIn);

        Set<String> keys = map.keySet();
        Iterator<String> it = keys.iterator();

        while(it.hasNext()) {
            String key = it.next();
            Object value = map.get(key);
            jwtBuilder.claim(key, value);
        }

        return jwtBuilder.signWith(getSecretKey()).compact();
    }

    public String getAccessToken(AccessTokenClaims claims) {
        return genToken(claims.toMap(), ACCESS_TOKEN_EXPIRES_IN);
    }

    public String getRefreshToken(RefreshTokenClaims claims) {
        return genToken(claims.toMap(), REFRESH_TOKEN_EXPIRES_IN);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
