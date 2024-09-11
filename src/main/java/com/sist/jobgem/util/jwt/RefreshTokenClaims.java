package com.sist.jobgem.util.jwt;

import java.util.HashMap;
import java.util.Map;

public class RefreshTokenClaims {
    private String refresh;

    public RefreshTokenClaims(String refresh) {
        this.refresh = refresh;
    }

    Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("refresh", refresh);
        return map;
    }
}
