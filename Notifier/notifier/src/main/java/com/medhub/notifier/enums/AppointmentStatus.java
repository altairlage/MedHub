package com.medhub.notifier.enums;

public enum AppointmentStatus {
    CREATED("MARCADA"),
    EDITED("ALTERADA"),
    CANCELLED("CANCELADA");

    private final String appointmentStatus;

    AppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }
}
