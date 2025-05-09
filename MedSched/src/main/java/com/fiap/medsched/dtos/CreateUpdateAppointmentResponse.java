package com.fiap.medsched.dtos;

import com.fiap.medsched.entities.Appointment;
import com.fiap.medsched.enums.AppointmentStatus;
import com.fiap.medsched.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUpdateAppointmentResponse {
    private long id;
    private UserModel patient;
    private UserModel doctor;
    private String appointmentDate;
    private AppointmentStatus appointmentStatus;

    public CreateUpdateAppointmentResponse(Appointment appointment) {
        UserModel patient = new UserModel(appointment.getPatient().getName(), appointment.getPatient().getSurname(), appointment.getPatient().getUserType());
        UserModel doctor = new UserModel(appointment.getDoctor().getName(), appointment.getDoctor().getSurname(), appointment.getDoctor().getUserType());


        setId(appointment.getId());
        setDoctor(patient);
        setPatient(doctor);
        setAppointmentDate(appointment.getAppointmentDate().toString());
        setAppointmentStatus(appointment.getStatus());
    }
}
