package com.sist.jobgem.enums;

public enum LoginStatusEnum {
    
    LOGIN_SUCCESS("로그인에 성공하였습니다."),
    LOGIN_WRONG_EMAIL("이메일이 존재하지 않습니다."),
    LOGIN_WRONG_PW("비밀번호를 확인해주세요.");

    private String message;

    LoginStatusEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
