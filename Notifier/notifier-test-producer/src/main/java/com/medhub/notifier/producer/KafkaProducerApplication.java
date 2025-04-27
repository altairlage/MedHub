package com.medhub.notifier.producer;

import com.medhub.notifier.dto.ScheduleNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class KafkaProducerApplication implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(KafkaProducerApplication.class);

    @Autowired
    private KafkaTemplate<String, ScheduleNotification> kafkaTemplate;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            ScheduleNotification schedule = new ScheduleNotification(Long.valueOf(i), "create", "Milin Pau", "Inbarajan Selvarajan", LocalDateTime.now());
            kafkaTemplate.send("schedule-notification", schedule);
            log.info("Schedule sent: {}", schedule);
        }
    }
}
