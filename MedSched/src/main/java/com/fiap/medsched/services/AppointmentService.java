package com.fiap.medsched.services;

import com.fiap.medsched.dtos.CreateUpdateAppointmentRequest;
import com.fiap.medsched.dtos.CreateUpdateAppointmentResponse;
import com.fiap.medsched.enums.AppointmentStatus;
import com.fiap.medsched.models.AppointmentModel;
import com.fiap.medsched.models.UserModel;
import com.fiap.medsched.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserService userService;

    public CreateUpdateAppointmentResponse createAppointment(CreateUpdateAppointmentRequest request) {
        UserModel patient = userService.getUserById(request.getPatientId());
        UserModel doctor = userService.getUserById(request.getDoctorId());

        String[] appointmentDateSplit = request.getAppointmentDate().split("/");
        int appointmentDay = Integer.parseInt(appointmentDateSplit[0]);
        int appointmentMonth = Integer.parseInt(appointmentDateSplit[1]);
        int appointmentYear = Integer.parseInt(appointmentDateSplit[2]);

        AppointmentModel appointmentModel = new AppointmentModel(patient, doctor, AppointmentStatus.CREATED,
                LocalDate.of(appointmentYear, appointmentMonth, appointmentDay), LocalDate.now(), LocalDate.now());

        return appointmentRepository.createAppointment(appointmentModel);
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
}
