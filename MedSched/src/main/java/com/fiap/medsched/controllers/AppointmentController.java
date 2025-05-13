package com.fiap.medsched.controllers;

import com.fiap.medsched.dtos.CreateUpdateAppointmentRequest;
import com.fiap.medsched.dtos.CreateUpdateAppointmentResponse;
import com.fiap.medsched.models.AppointmentModel;
import com.fiap.medsched.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
//@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @MutationMapping
    public CreateUpdateAppointmentResponse createAppointment(@Argument CreateUpdateAppointmentRequest request) {
        return appointmentService.createAppointment(request);
    }

    @MutationMapping
    public CreateUpdateAppointmentResponse updateAppointment(@Argument CreateUpdateAppointmentRequest request) {
        return appointmentService.updateAppointment(request);
    }

    @MutationMapping
    public CreateUpdateAppointmentResponse cancelAppointment(@Argument Long appointmentId) {
        return appointmentService.cancelAppointment(appointmentId);
    }

    @QueryMapping
    public List<AppointmentModel> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @QueryMapping
    public AppointmentModel getAppointmentById(@Argument Long appointmentId) {
        return appointmentService.getAppointmentById(appointmentId);
    }

    @QueryMapping
    public List<AppointmentModel> getAllAppointmentByPatientId(@Argument Long patientId) {
        return appointmentService.getAppointmentByPatientId(patientId);
    }

    @QueryMapping
    public List<AppointmentModel> getAllAppointmentByDoctorId(@Argument Long doctorId) {
        return appointmentService.getAppointmentByDoctorId(doctorId);
    }
}
