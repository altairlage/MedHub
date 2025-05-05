package com.medhub.notifier.consumer;

import com.medhub.notifier.dto.ScheduleNotification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerApplication {
    private final Logger log = LoggerFactory.getLogger(KafkaConsumerApplication.class);

    @KafkaListener(topics = "schedule-notification", groupId = "schedule_notification_group")
    public void consumeScheduleNotification(ScheduleNotification scheduleNotification, Acknowledgment ack) {
        try{
            log.info("Schedule Notification consumed: {}", scheduleNotification);

            ack.acknowledge();
        } catch (Exception e){

        }
    }
}
