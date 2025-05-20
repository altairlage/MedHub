package com.fiap.medsched.models;

import com.fiap.medsched.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentModel {
    private Long id;

    private UserModel patient;

    private UserModel doctor;

    private AppointmentStatus status;

    private LocalDateTime appointmentDate;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;
}
