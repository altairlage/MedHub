package com.fiap.medsched.entities;

import com.fiap.medsched.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Users patient;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Users doctor;

    private LocalDateTime appointmentDate;

    private AppointmentStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime lastUpdatedAt;

    public Appointment(Users patient, Users doctor, LocalDateTime appointmentDate, AppointmentStatus status, LocalDateTime createdAt, LocalDateTime lastUpdatedAt) {
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
