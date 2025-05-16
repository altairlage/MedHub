package com.fiap.medsched.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.medsched.enums.AppointmentStatus;

import java.time.LocalDateTime;

public record SendScheduleNotification(
        @JsonProperty("id") Long id,
        @JsonProperty("status") AppointmentStatus status,
        @JsonProperty("patientName") String patientName,
        @JsonProperty("patientEmail") String patientEmail,
        @JsonProperty("doctorName") String doctorName,
        @JsonProperty("appointmentDateTime") String appointmentDateTime
) {
}
