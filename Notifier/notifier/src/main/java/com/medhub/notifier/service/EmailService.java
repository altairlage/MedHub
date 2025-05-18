package com.medhub.notifier.service;

import com.medhub.notifier.dto.SendScheduleNotification;
import com.medhub.notifier.enums.AppointmentStatus;
import com.medhub.notifier.enums.SubjectEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(SendScheduleNotification scheduleNotification) {
        String subject = getSubject(scheduleNotification.status());
        String body = buildBody(scheduleNotification);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(scheduleNotification.patientEmail());
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }

    private String getSubject(AppointmentStatus tipo) {
        return switch (tipo) {
            case CREATED -> SubjectEnum.SCHEDULE_APPOINTMENT.getSubject();
            case EDITED -> SubjectEnum.CHANGE_APPOINTMENT.getSubject();
            case CANCELLED -> SubjectEnum.CANCEL_APPOINTMENT.getSubject();
        };
    }

    private String buildBody(SendScheduleNotification scheduleNotification) {
        return String.format("""
                        Olá %s,
                        
                        Sua consulta foi %s para o dia %s, com o Medico %s
                        
                        Obrigado!
                        """,
                scheduleNotification.patientName(),
                scheduleNotification.status().getAppointmentStatus(),
                scheduleNotification.appointmentDateTime(),
                scheduleNotification.doctorName());
    }
}

