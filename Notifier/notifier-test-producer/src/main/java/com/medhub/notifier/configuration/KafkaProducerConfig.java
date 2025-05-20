package com.medhub.notifier.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.medhub.notifier.dto.ScheduleNotification;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaProducerConfig {
    // Faz usar o nome do serviço definido no docker-compose.yml e que 
    // localhost:9092 só será usado em ambiente local fora do container.
    @Value("${SPRING_KAFKA_BOOTSTRAP_SERVERS:localhost:9092}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 10_000);
        return props;
    }

    @Bean
    public ProducerFactory<String, ScheduleNotification> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, ScheduleNotification> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // Configs do consumer:

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // Para consumir todas as mensagens da fila, nao somente as mensagens criadas apos a criação do consumer
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // grupo de consumo
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "schedule_notification_group");

        return props;
    }

    @Bean
    public ConsumerFactory<String, ScheduleNotification> consumerFactory() {
        JsonDeserializer<ScheduleNotification> deserializer = new JsonDeserializer<>(ScheduleNotification.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(false);

        return new DefaultKafkaConsumerFactory<>(
                consumerConfigs(),
                new StringDeserializer(),
                deserializer
        );

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ScheduleNotification> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ScheduleNotification> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        // Para informar corretamente ao kafka que as mensagens foram processadas
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }

}
