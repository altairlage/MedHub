package com.fiap.medsched.producer;

import com.fiap.medsched.dtos.SendScheduleNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerApplication {

    private final Logger log = LoggerFactory.getLogger(KafkaProducerApplication.class);

    @Autowired
    private KafkaTemplate<String, SendScheduleNotification> kafkaTemplate;

    public void sendKafkaMessage(SendScheduleNotification scheduleNotification) {
         kafkaTemplate.send("schedule-notification", scheduleNotification);
         log.info("Schedule sent: {}", scheduleNotification);
    }
}
