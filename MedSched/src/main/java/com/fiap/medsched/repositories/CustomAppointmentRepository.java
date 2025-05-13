package com.fiap.medsched.repositories;

import com.fiap.medsched.dtos.CreateUpdateAppointmentRequest;
import com.fiap.medsched.dtos.CreateUpdateAppointmentResponse;
import com.fiap.medsched.models.AppointmentModel;

import java.util.List;

public interface CustomAppointmentRepository {
    CreateUpdateAppointmentResponse createAppointment(CreateUpdateAppointmentRequest request);
    CreateUpdateAppointmentResponse updateAppointment(CreateUpdateAppointmentRequest request);
    CreateUpdateAppointmentResponse cancelAppointment(Long id);
    List<AppointmentModel> getAllAppointments();
    List<AppointmentModel> getAppointmentsByDoctorId(Long id);
    List<AppointmentModel> getAppointmentsByPatientId(Long id);
    AppointmentModel getAppointmentById(Long id);
}
