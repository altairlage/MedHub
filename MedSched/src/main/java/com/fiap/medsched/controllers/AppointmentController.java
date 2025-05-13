package com.fiap.medsched.controllers;

import com.fiap.medsched.dtos.CreateUpdateAppointmentRequest;
import com.fiap.medsched.dtos.CreateUpdateAppointmentResponse;
import com.fiap.medsched.models.AppointmentModel;
import com.fiap.medsched.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@Controller
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @PostMapping("/create")
    @MutationMapping
    public CreateUpdateAppointmentResponse createAppointment(@RequestBody CreateUpdateAppointmentRequest request) {
        return appointmentService.createAppointment(request);
    }

    @PostMapping("/update")
    @MutationMapping
    public CreateUpdateAppointmentResponse updateAppointment(@RequestBody CreateUpdateAppointmentRequest request) {
        return appointmentService.updateAppointment(request);
    }

    @PostMapping("{id}/cancel")
    @MutationMapping
    public CreateUpdateAppointmentResponse cancelAppointment(@PathVariable long id) {
        return appointmentService.cancelAppointment(id);
    }

    @GetMapping
    @QueryMapping
    public List<AppointmentModel> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    @QueryMapping
    public AppointmentModel getAppointmentById(@PathVariable long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/patient/{id}")
    @QueryMapping
    public List<AppointmentModel> getAppointmentsByPatientId(@PathVariable long id) {
        return appointmentService.getAppointmentsByPatientId(id);
    }

    @GetMapping("/doctor/{id}")
    @QueryMapping
    public List<AppointmentModel> getAppointmentsByDoctorId(@PathVariable long id) {
        return appointmentService.getAppointmentsByDoctorId(id);
    }

    @GetMapping("/{date}")
    @QueryMapping
    public List<AppointmentModel> getAppointmentsByDate(@PathVariable String date) {
        return appointmentService.getAppointmentsByDate(date);
    }
}
