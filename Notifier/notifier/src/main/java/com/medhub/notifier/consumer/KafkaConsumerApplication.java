package com.medhub.notifier.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.medhub.notifier.dto.ScheduleNotification;
import com.medhub.notifier.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerApplication {
    private final Logger log = LoggerFactory.getLogger(KafkaConsumerApplication.class);
    private final EmailService emailService;

    public KafkaConsumerApplication(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "schedule-notification", groupId = "schedule_notification_group")
    public void consumeScheduleNotification(ScheduleNotification notification, Acknowledgment ack) {
        log.info("Schedule Notification consumed: {}", notification);

        try {
//          Envio de email para paciente
            emailService.sendEmail(notification);
            log.info("Email enviado.");

            ack.acknowledge();
            log.info("Offset commitado.");
        } catch (Exception e) {
            log.error("Erro ao processar notificação: {}", e.getMessage(), e);
        } finally {
            log.info("Processo finalizado.");
        }
    }
}
