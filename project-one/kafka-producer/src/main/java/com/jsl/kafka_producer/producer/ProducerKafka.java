package com.jsl.kafka_producer.producer;

import com.jsl.model.StudentPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerKafka {
    private final NewTopic topic;
    private final KafkaTemplate<String, StudentPayload> kafkaTemplate;

    public void sendMessage(StudentPayload studentPayload) {
        Message<StudentPayload> message = MessageBuilder.withPayload(studentPayload)
                .setHeader(KafkaHeaders.TOPIC, topic.name()).build();
        log.info("Sending message to {} :: {}", topic.name(), message.getPayload());
        kafkaTemplate.send(message);
    }
}
