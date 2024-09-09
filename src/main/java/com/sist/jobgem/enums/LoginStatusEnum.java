package com.sist.jobgem.enums;

public enum LoginStatusEnum {
    
    LOGIN_SUCCESS("success"),
    LOGIN_WRONG_EMAIL("email_not_found"),
    LOGIN_WRONG_PW("password_not_match");

    private String message;

    LoginStatusEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}