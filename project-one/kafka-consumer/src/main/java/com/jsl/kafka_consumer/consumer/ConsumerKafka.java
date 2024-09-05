package com.jsl.kafka_consumer.consumer;

import com.jsl.model.StudentPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerKafka {
    @KafkaListener(topics = "wikimedia-stream", groupId = "student")
    public void receivePayload(StudentPayload studentPayload) {
        log.info("Payload received :: {}", studentPayload);
    }
}
