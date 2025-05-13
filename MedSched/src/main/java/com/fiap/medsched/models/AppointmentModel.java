package com.fiap.medsched.models;

import com.fiap.medsched.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentModel {
    private Long id;

    private UserModel patient;

    private UserModel doctor;

    private AppointmentStatus status;

    private LocalDate appointmentDate;

    private LocalDate createdAt;

    private LocalDate lastUpdatedAt;

    public AppointmentModel(UserModel patient, UserModel doctor, AppointmentStatus status, LocalDate appointmentDate, LocalDate createdAt, LocalDate lastUpdatedAt) {
        this.patient = patient;
        this.doctor = doctor;
        this.status = status;
        this.appointmentDate = appointmentDate;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
