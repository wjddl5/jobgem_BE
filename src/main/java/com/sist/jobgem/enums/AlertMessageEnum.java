package com.sist.jobgem.enums;

public enum AlertMessageEnum {

    ALERT_OFFER("success"),
    ALERT_CHAT("email_not_found");

    private String message;

    AlertMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}