package com.medhub.notifier.service;

import com.medhub.notifier.dto.ScheduleNotification;
import com.medhub.notifier.enums.EventTypeEnum;
import com.medhub.notifier.enums.SubjectEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(ScheduleNotification scheduleNotification) {
        String subject = getSubject(scheduleNotification.tipoDeEvento());
        String body = buildBody(scheduleNotification);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(scheduleNotification.emailPaciente());
        message.setSubject(subject);
        message.setText(body);

        javaMailSender.send(message);
    }

    private String getSubject(EventTypeEnum tipo) {
        return switch (tipo) {
            case SCHEDULE -> SubjectEnum.SCHEDULE_APPOINTMENT.getSubject();
            case CHANGE -> SubjectEnum.CHANGE_APPOINTMENT.getSubject();
            case CANCEL -> SubjectEnum.CANCEL_APPOINTMENT.getSubject();
        };
    }

    private String buildBody(ScheduleNotification scheduleNotification) {
        String dateFormat = scheduleNotification.dataHora().format(DateTimeFormatter.ofPattern("'o dia' dd/MM/yyyy 'as' HH:mm"));

        return String.format("""
                        Olá %s,
                        
                        Sua consulta foi %s para %s, com o Medico %s
                        
                        Obrigado!
                        """,
                scheduleNotification.nomePaciente(),
                scheduleNotification.tipoDeEvento().getStatus(),
                dateFormat,
                scheduleNotification.nomeMedico());
    }
}

