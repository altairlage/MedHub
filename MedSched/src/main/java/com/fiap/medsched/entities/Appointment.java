package com.fiap.medsched.entities;

import com.fiap.medsched.enums.AppointmentStatus;
import com.fiap.medsched.models.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Appointment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Users patient;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Users doctor;

    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate lastUpdatedAt;

    public Appointment(UserModel patient, UserModel doctor, LocalDate appointmentDate, AppointmentStatus status, LocalDate createdAt, LocalDate lastUpdatedAt) {
        this.patient = new Users(patient.getName(), patient.getSurname(), patient.getEmail(), patient.getUserType());
        this.doctor = new Users(doctor.getName(), doctor.getSurname(), doctor.getEmail(), doctor.getUserType());
        this.appointmentDate = appointmentDate;
        this.status = status;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
