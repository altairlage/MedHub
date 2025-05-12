package com.medhub.notifier.enums;

public enum EventTypeEnum {
    SCHEDULE("MARCADA"),
    CHANGE("ALTERADA"),
    CANCEL("CANCELADA");

    private String status;

    EventTypeEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
