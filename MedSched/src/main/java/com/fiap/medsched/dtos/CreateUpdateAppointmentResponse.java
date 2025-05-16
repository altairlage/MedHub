package com.fiap.medsched.dtos;

import com.fiap.medsched.entities.Appointment;
import com.fiap.medsched.enums.AppointmentStatus;
import com.fiap.medsched.models.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SchemaMapping("CreateUpdateAppointmentResponse")
public class CreateUpdateAppointmentResponse {
    private Long id;
    private UserModel patient;
    private UserModel doctor;
    private String appointmentDate;
    private String appointmentHour;
    private AppointmentStatus appointmentStatus;

    public CreateUpdateAppointmentResponse(Appointment appointment) {
        UserModel patient = new UserModel(appointment.getPatient());
        UserModel doctor = new UserModel(appointment.getDoctor());


        setId(appointment.getId());
        setDoctor(patient);
        setPatient(doctor);
        setAppointmentDate(appointment.getAppointmentDate().toString());
        setAppointmentHour(appointment.getAppointmentHour());
        setAppointmentStatus(appointment.getStatus());
    }
}
