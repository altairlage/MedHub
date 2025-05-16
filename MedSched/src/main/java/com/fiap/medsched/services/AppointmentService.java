package com.fiap.medsched.services;

import com.fiap.medsched.dtos.CreateUpdateAppointmentRequest;
import com.fiap.medsched.dtos.CreateUpdateAppointmentResponse;
import com.fiap.medsched.exceptions.MedException;
import com.fiap.medsched.models.AppointmentModel;
import com.fiap.medsched.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public CreateUpdateAppointmentResponse createAppointment(CreateUpdateAppointmentRequest request) {
        verifyAppointmentDateAndHour(request.getAppointmentDate(), request.getAppointmentHour());
        return appointmentRepository.createAppointment(request);
    }

    public CreateUpdateAppointmentResponse updateAppointment(CreateUpdateAppointmentRequest request) {
        return appointmentRepository.updateAppointment(request);
    }

    public List<AppointmentModel> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    public AppointmentModel getAppointmentById(Long id) {
        return appointmentRepository.getAppointmentById(id);
    }

    public CreateUpdateAppointmentResponse cancelAppointment(Long id) {
        return appointmentRepository.cancelAppointment(id);
    }

    public List<AppointmentModel> getAppointmentByDoctorId(Long id) {
        return appointmentRepository.getAppointmentsByDoctorId(id);
    }

    public List<AppointmentModel> getAppointmentByPatientId(Long id) {
        return appointmentRepository.getAppointmentsByPatientId(id);
    }

    public void verifyAppointmentDateAndHour(String appointmentDate, String appointmentHour) {
        Pattern datePattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
        Pattern hourPattern = Pattern.compile("^(\\d{2})[Hh](\\d{2})$");
        Matcher dateMatcher = datePattern.matcher(appointmentDate);
        Matcher hourMatcher = hourPattern.matcher(appointmentHour);

        if (!dateMatcher.matches()) {
            throw new MedException("The appointment date field should be in the format DD/MM/YYYY");
        }

        if (!hourMatcher.matches()) {
            throw new MedException("The appointment hour field should be in the format 99H99");
        }
    }
}
