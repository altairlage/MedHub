package com.fiap.medsched.repositories.implementations;

import com.fiap.medsched.dtos.CreateUpdateAppointmentRequest;
import com.fiap.medsched.dtos.CreateUpdateAppointmentResponse;
import com.fiap.medsched.entities.Appointment;
import com.fiap.medsched.entities.Users;
import com.fiap.medsched.enums.AppointmentStatus;
import com.fiap.medsched.models.AppointmentModel;
import com.fiap.medsched.models.UserModel;
import com.fiap.medsched.repositories.CustomAppointmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomAppointmentRepositoryImpl implements CustomAppointmentRepository {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public CreateUpdateAppointmentResponse createAppointment(AppointmentModel model) {
        Appointment appointment = new Appointment(model.getPatient(), model.getDoctor(), model.getAppointmentDate(),
                model.getStatus(), model.getCreatedAt(), model.getLastUpdatedAt());

        entityManager.persist(appointment);
        return new CreateUpdateAppointmentResponse(appointment);
    }

    @Override
    @Transactional
    public CreateUpdateAppointmentResponse updateAppointment(CreateUpdateAppointmentRequest request) {
        Appointment appointment = entityManager.find(Appointment.class, request.getId());

        if (appointment == null) {
            throw new RuntimeException("Appointment not found");
        }

        appointment.setPatient(entityManager.find(Users.class, request.getPatientId()));
        appointment.setDoctor(entityManager.find(Users.class, request.getDoctorId()));
        appointment.setAppointmentDate(LocalDate.parse(request.getAppointmentDate()));
        appointment.setStatus(AppointmentStatus.EDITED);
        appointment.setLastUpdatedAt(LocalDate.now());

        entityManager.merge(appointment);

        return new CreateUpdateAppointmentResponse(appointment);
    }

    @Override
    @Transactional
    public List<AppointmentModel> getAllAppointments(){
        List<Appointment> appointmentList = entityManager.createQuery("select a from Appointment a", Appointment.class).getResultList();
        List<AppointmentModel> appointmentModelList = new ArrayList<>();

        for (Appointment appointment : appointmentList) {
            UserModel doctor = new UserModel(appointment.getDoctor().getName(), appointment.getDoctor().getSurname(), appointment.getDoctor().getUserType());
            UserModel patient = new UserModel(appointment.getPatient().getName(), appointment.getPatient().getSurname(), appointment.getPatient().getUserType());

            appointmentModelList.add(new AppointmentModel(patient, doctor, appointment.getStatus(), appointment.getAppointmentDate(),
                    appointment.getCreatedAt(),appointment.getLastUpdatedAt()));
        }

        return appointmentModelList;
    }

    @Override
    @Transactional
    public AppointmentModel getAppointmentById(Long id){
        Appointment appointment = entityManager.find(Appointment.class, id);

        if (appointment == null) {
            throw new RuntimeException("Appointment not found");
        }

        UserModel doctor = new UserModel(appointment.getDoctor().getName(), appointment.getDoctor().getSurname(), appointment.getDoctor().getUserType());
        UserModel patient = new UserModel(appointment.getPatient().getName(), appointment.getPatient().getSurname(), appointment.getPatient().getUserType());

        return new AppointmentModel(patient, doctor, appointment.getStatus(), appointment.getAppointmentDate(),
                appointment.getCreatedAt(),appointment.getLastUpdatedAt());
    }

    @Override
    @Transactional
    public CreateUpdateAppointmentResponse cancelAppointment(Long id) {
        Appointment appointment = entityManager.find(Appointment.class, id);

        if (appointment == null) {
            throw new RuntimeException("Appointment not found");
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointment.setLastUpdatedAt(LocalDate.now());
        entityManager.merge(appointment);

        UserModel doctor = new UserModel(appointment.getDoctor().getName(), appointment.getDoctor().getSurname(), appointment.getDoctor().getUserType());
        UserModel patient = new UserModel(appointment.getPatient().getName(), appointment.getPatient().getSurname(), appointment.getPatient().getUserType());

        AppointmentModel appointmentModel = new AppointmentModel(patient, doctor, appointment.getStatus(), appointment.getAppointmentDate(),
                appointment.getCreatedAt(),appointment.getLastUpdatedAt());

        return new CreateUpdateAppointmentResponse(appointment);
    }

    @Override
    public List<AppointmentModel> getAppointmentsByDoctorId(@PathVariable Long id) {
        Query query = entityManager.createQuery("select a from Appointment a where a.doctor.id = :doctor_id", Appointment.class);
        query.setParameter("doctor_id", id);
        List<Appointment> appointmentList = query.getResultList();
        List<AppointmentModel> appointmentModelList = new ArrayList<>();

        for (Appointment appointment : appointmentList) {
            UserModel doctor = new UserModel(appointment.getDoctor().getName(), appointment.getDoctor().getSurname(), appointment.getDoctor().getUserType());
            UserModel patient = new UserModel(appointment.getPatient().getName(), appointment.getPatient().getSurname(), appointment.getPatient().getUserType());

            appointmentModelList.add(new AppointmentModel(patient, doctor, appointment.getStatus(), appointment.getAppointmentDate(),
                    appointment.getCreatedAt(),appointment.getLastUpdatedAt()));
        }

        return appointmentModelList;
    }

    @Override
    public List<AppointmentModel> getAppointmentsByPatientId(@PathVariable Long id) {
        Query query = entityManager.createQuery("select a from Appointment a where a.patient.id = :patient_id", Appointment.class);
        query.setParameter("patient_id", id);
        List<Appointment> appointmentList = query.getResultList();
        List<AppointmentModel> appointmentModelList = new ArrayList<>();

        for (Appointment appointment : appointmentList) {
            UserModel doctor = new UserModel(appointment.getDoctor().getName(), appointment.getDoctor().getSurname(), appointment.getDoctor().getUserType());
            UserModel patient = new UserModel(appointment.getPatient().getName(), appointment.getPatient().getSurname(), appointment.getPatient().getUserType());

            appointmentModelList.add(new AppointmentModel(patient, doctor, appointment.getStatus(), appointment.getAppointmentDate(),
                    appointment.getCreatedAt(),appointment.getLastUpdatedAt()));
        }

        return appointmentModelList;
    }
}
