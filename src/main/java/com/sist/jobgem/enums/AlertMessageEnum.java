package com.sist.jobgem.enums;

public enum AlertMessageEnum {

    ALERT_OFFER("입사제안이 도착했습니다."),
    ALERT_CHAT("메시지가 도착했습니다.");

    private String message;

    AlertMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}