package com.fiap.medsched.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateAppointmentRequest {
    private long id;
    private long patientId;
    private long doctorId;
    private String appointmentDate;
}
