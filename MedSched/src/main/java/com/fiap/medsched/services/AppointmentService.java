package com.fiap.medsched.services;

import com.fiap.medsched.dtos.CreateUpdateAppointmentRequest;
import com.fiap.medsched.dtos.CreateUpdateAppointmentResponse;
import com.fiap.medsched.dtos.SendScheduleNotification;
import com.fiap.medsched.enums.AppointmentStatus;
import com.fiap.medsched.models.AppointmentModel;
import com.fiap.medsched.producer.KafkaProducerApplication;
import com.fiap.medsched.repositories.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserService userService;
    private final KafkaProducerApplication kafkaProducer;

//    Essa variavel sera utilizada para gerar um id para as mensagens do kafka
    private final AtomicLong idKafka = new AtomicLong(1);
//    Esse metodo incrementa o id, e bem simples e fica em memoria
    public long generateId(){return idKafka.getAndIncrement();}

    public CreateUpdateAppointmentResponse createAppointment(CreateUpdateAppointmentRequest request) {
        CreateUpdateAppointmentResponse response = appointmentRepository.createAppointment(request);

        SendScheduleNotification notification = new SendScheduleNotification(
                generateId(),
                AppointmentStatus.CREATED,
                response.getPatient().getName(),
                response.getPatient().getEmail(),
                response.getDoctor().getName(),
                response.getAppointmentDate()
        );

        kafkaProducer.sendKafkaMessage(notification);

        return response;
    }

    public CreateUpdateAppointmentResponse updateAppointment(CreateUpdateAppointmentRequest request) {
        CreateUpdateAppointmentResponse response = appointmentRepository.updateAppointment(request);

        SendScheduleNotification notification = new SendScheduleNotification(
                generateId(),
                AppointmentStatus.EDITED,
                response.getPatient().getName(),
                response.getPatient().getEmail(),
                response.getDoctor().getName(),
                response.getAppointmentDate()
        );

        kafkaProducer.sendKafkaMessage(notification);

        return response;
    }

    public List<AppointmentModel> getAllAppointments() {
        return appointmentRepository.getAllAppointments();
    }

    public AppointmentModel getAppointmentById(Long id) {
        return appointmentRepository.getAppointmentById(id);
    }

    public CreateUpdateAppointmentResponse cancelAppointment(Long id) {
        CreateUpdateAppointmentResponse response = appointmentRepository.cancelAppointment(id);

        SendScheduleNotification notification = new SendScheduleNotification(
                generateId(),
                AppointmentStatus.CANCELLED,
                response.getPatient().getName(),
                response.getPatient().getEmail(),
                response.getDoctor().getName(),
                response.getAppointmentDate()
        );

        kafkaProducer.sendKafkaMessage(notification);

        return response;
    }

    public List<AppointmentModel> getAppointmentByDoctorId(Long id) {
        return appointmentRepository.getAppointmentsByDoctorId(id);
    }

    public List<AppointmentModel> getAppointmentByPatientId(Long id) {
        return appointmentRepository.getAppointmentsByPatientId(id);
    }
}
