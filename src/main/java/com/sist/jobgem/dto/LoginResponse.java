package com.sist.jobgem.dto;

import lombok.Getter;

@Getter
public class LoginResponse {
    
    private UserDto user;
    private String msg;

    public LoginResponse(UserDto user, String msg) {
        this.user = user;
        this.msg = msg;
    }
}
