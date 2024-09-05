package com.jsl.kafka_producer.rest;

import com.jsl.kafka_producer.producer.ProducerKafka;
import com.jsl.model.StudentPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class ProducerKafkaController {
    private final ProducerKafka producerKafka;

    @PostMapping
    public ResponseEntity<Void> sendPayload(
            @RequestBody StudentPayload studentPayload
            ) {
        producerKafka.sendMessage(studentPayload);
        return ResponseEntity.ok().build();
    }
}
