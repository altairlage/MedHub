package com.fiap.medsched.repositories.implementations;

import com.fiap.medsched.dtos.CreateUpdateAppointmentRequest;
import com.fiap.medsched.dtos.CreateUpdateAppointmentResponse;
import com.fiap.medsched.entities.Appointment;
import com.fiap.medsched.entities.Users;
import com.fiap.medsched.enums.AppointmentStatus;
import com.fiap.medsched.exceptions.MedException;
import com.fiap.medsched.models.AppointmentModel;
import com.fiap.medsched.models.UserModel;
import com.fiap.medsched.repositories.CustomAppointmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomAppointmentRepositoryImpl implements CustomAppointmentRepository {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public CreateUpdateAppointmentResponse createAppointment(CreateUpdateAppointmentRequest request) {
        Users patient = entityManager.find(Users.class, request.getPatientId());

        if (patient == null) {
            throw new MedException("Patient not found");
        }

        Users doctor = entityManager.find(Users.class, request.getDoctorId());

        if (doctor == null) {
            throw new MedException("Doctor not found");
        }

        String[] appointmentDateSplit = request.getAppointmentDate().split("/");
        String[] appointmentHourSplit = request.getAppointmentHour().split("[Hh]");
        int appointmentDay = Integer.parseInt(appointmentDateSplit[0]);
        int appointmentMonth = Integer.parseInt(appointmentDateSplit[1]);
        int appointmentYear = Integer.parseInt(appointmentDateSplit[2]);
        int appointmentHour = Integer.parseInt(appointmentHourSplit[0]);
        int appointmentMinute = Integer.parseInt(appointmentHourSplit[1]);

        LocalDateTime appointmentDateTime = LocalDateTime.of(appointmentYear, appointmentMonth, appointmentDay, appointmentHour, appointmentMinute);

        Appointment appointment = new Appointment(patient, doctor, appointmentDateTime, AppointmentStatus.CREATED, LocalDateTime.now(), LocalDateTime.now());

        entityManager.persist(appointment);
        return new CreateUpdateAppointmentResponse(appointment);
    }

    @Override
    @Transactional
    public CreateUpdateAppointmentResponse updateAppointment(CreateUpdateAppointmentRequest request) {
        Appointment appointment = entityManager.find(Appointment.class, request.getId());

        if (appointment == null) {
            throw new MedException("Appointment not found");
        }

        if (appointment.getStatus() == AppointmentStatus.CANCELLED){
            throw new MedException("Appointment cancelled");
        }

        String[] appointmentDateSplit = request.getAppointmentDate().split("/");
        String[] appointmentHourSplit = request.getAppointmentHour().split("[Hh]");
        int appointmentDay = Integer.parseInt(appointmentDateSplit[0]);
        int appointmentMonth = Integer.parseInt(appointmentDateSplit[1]);
        int appointmentYear = Integer.parseInt(appointmentDateSplit[2]);
        int appointmentHour = Integer.parseInt(appointmentHourSplit[0]);
        int appointmentMinute = Integer.parseInt(appointmentHourSplit[1]);

        LocalDateTime appointmentDateTime = LocalDateTime.of(appointmentYear, appointmentMonth, appointmentDay, appointmentHour, appointmentMinute);

        appointment.setPatient(entityManager.find(Users.class, request.getPatientId()));
        appointment.setDoctor(entityManager.find(Users.class, request.getDoctorId()));
        appointment.setAppointmentDate(appointmentDateTime);
        appointment.setStatus(AppointmentStatus.EDITED);
        appointment.setLastUpdatedAt(LocalDateTime.now());

        entityManager.merge(appointment);

        return new CreateUpdateAppointmentResponse(appointment);
    }

    @Override
    @Transactional
    public List<AppointmentModel> getAllAppointments(){
        List<Appointment> appointmentList = entityManager.createQuery("select a from Appointment a", Appointment.class).getResultList();
        List<AppointmentModel> appointmentModelList = new ArrayList<>();

        for (Appointment appointment : appointmentList) {
            UserModel doctor = new UserModel(appointment.getDoctor());
            UserModel patient = new UserModel(appointment.getPatient());

            appointmentModelList.add(new AppointmentModel(appointment.getId(), patient, doctor, appointment.getStatus(), appointment.getAppointmentDate(),
                    appointment.getCreatedAt(),appointment.getLastUpdatedAt()));
        }

        return appointmentModelList;
    }

    @Override
    @Transactional
    public AppointmentModel getAppointmentById(Long id){
        Appointment appointment = entityManager.find(Appointment.class, id);

        if (appointment == null) {
            throw new MedException("Appointment not found");
        }

        UserModel doctor = new UserModel(appointment.getDoctor());
        UserModel patient = new UserModel(appointment.getPatient());

        return new AppointmentModel(appointment.getId(), patient, doctor, appointment.getStatus(), appointment.getAppointmentDate(),
                appointment.getCreatedAt(),appointment.getLastUpdatedAt());
    }

    @Override
    @Transactional
    public CreateUpdateAppointmentResponse cancelAppointment(Long id) {
        Appointment appointment = entityManager.find(Appointment.class, id);

        if (appointment == null) {
            throw new MedException("Appointment not found");
        }

        if(appointment.getStatus() == AppointmentStatus.CANCELLED) {
            throw new MedException("Appointment already cancelled");
        }

        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointment.setLastUpdatedAt(LocalDateTime.now());
        entityManager.merge(appointment);

        return new CreateUpdateAppointmentResponse(appointment);
    }

    @Override
    public List<AppointmentModel> getAppointmentsByDoctorId(Long id) {
        Query query = entityManager.createQuery("select a from Appointment a where a.doctor.id = :doctor_id", Appointment.class);
        query.setParameter("doctor_id", id);
        List<Appointment> appointmentList = query.getResultList();
        List<AppointmentModel> appointmentModelList = new ArrayList<>();

        for (Appointment appointment : appointmentList) {
            UserModel doctor = new UserModel(appointment.getDoctor());
            UserModel patient = new UserModel(appointment.getPatient());

            appointmentModelList.add(new AppointmentModel(appointment.getId(), patient, doctor, appointment.getStatus(), appointment.getAppointmentDate(),
                    appointment.getCreatedAt(),appointment.getLastUpdatedAt()));
        }

        return appointmentModelList;
    }

    @Override
    public List<AppointmentModel> getAppointmentsByPatientId(Long id) {
        Query query = entityManager.createQuery("select a from Appointment a where a.patient.id = :patient_id", Appointment.class);
        query.setParameter("patient_id", id);
        List<Appointment> appointmentList = query.getResultList();
        List<AppointmentModel> appointmentModelList = new ArrayList<>();

        for (Appointment appointment : appointmentList) {
            UserModel doctor = new UserModel(appointment.getDoctor());
            UserModel patient = new UserModel(appointment.getPatient());

            appointmentModelList.add(new AppointmentModel(appointment.getId(), patient, doctor, appointment.getStatus(), appointment.getAppointmentDate(),
                    appointment.getCreatedAt(),appointment.getLastUpdatedAt()));
        }

        return appointmentModelList;
    }

    @Override
    public List<AppointmentModel> getAllAppointmentsByAppointmentDate(LocalDateTime appointmentDateStart, LocalDateTime appointmentDateEnd) {
        Query query = entityManager.createQuery("select a from Appointment a where a.appointmentDate BETWEEN :start AND :end", Appointment.class);
        query.setParameter("start", appointmentDateStart);
        query.setParameter("end", appointmentDateEnd);
        List<Appointment> appointmentList = query.getResultList() != null ? query.getResultList() : new ArrayList<>();
        List<AppointmentModel> appointmentModelList = new ArrayList<>();

        for (Appointment appointment : appointmentList) {
            UserModel doctor = new UserModel(appointment.getDoctor());
            UserModel patient = new UserModel(appointment.getPatient());

            appointmentModelList.add(new AppointmentModel(appointment.getId(), patient, doctor, appointment.getStatus(), appointment.getAppointmentDate(),
                    appointment.getCreatedAt(),appointment.getLastUpdatedAt()));
        }

        return appointmentModelList;
    }
}
