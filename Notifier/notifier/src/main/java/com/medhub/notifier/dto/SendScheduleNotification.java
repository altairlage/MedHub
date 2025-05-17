package com.medhub.notifier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.medhub.notifier.enums.AppointmentStatus;

public record SendScheduleNotification(
        @JsonProperty("id") Long id,
        @JsonProperty("status") AppointmentStatus status,
        @JsonProperty("patientName") String patientName,
        @JsonProperty("patientEmail") String patientEmail,
        @JsonProperty("doctorName") String doctorName,
        @JsonProperty("appointmentDateTime")
//        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
        String appointmentDateTime
) {}
