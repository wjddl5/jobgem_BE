package com.sist.jobgem.util.jwt;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessTokenClaims {
    
    private int usIdx;
    private int idx;
    private String email;
    private String name;
    private String img;
    private int role;

    Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("USIDX", usIdx);
        map.put("ROLE", role);
        map.put("IDX", idx);
        map.put("EMAIL", email);
        map.put("NAME", name);
        map.put("IMG", img);

        return map;
    }
}
