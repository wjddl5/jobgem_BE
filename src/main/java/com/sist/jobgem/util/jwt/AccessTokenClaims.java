package com.sist.jobgem.util.jwt;

import java.util.HashMap;
import java.util.Map;

public class AccessTokenClaims {
    
    private String role;
    private String uid;

    public AccessTokenClaims(String role, String uid) {
        this.role = role;
        this.uid = uid;
    }

    Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("ROLE", role);
        map.put("UID", uid);
        return map;
    }
    
}
