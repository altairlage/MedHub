package com.medhub.notifier.enums;

public enum SubjectEnum {
    SCHEDULE_APPOINTMENT("Consulta Marcada"),
    CHANGE_APPOINTMENT("Consulta alterada"),
    CANCEL_APPOINTMENT("Consulta cancelada");

    private final String subject;

    SubjectEnum(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
