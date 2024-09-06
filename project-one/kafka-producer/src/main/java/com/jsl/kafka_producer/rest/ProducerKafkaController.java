package com.jsl.kafka_producer.rest;

import com.jsl.kafka_producer.producer.ProducerKafka;
import com.jsl.kafka_producer.services.models.Student;
import com.jsl.kafka_producer.services.repository.service.repo.StudentService;
import com.jsl.model.StudentPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("student")
public class ProducerKafkaController {
    private final ProducerKafka producerKafka;
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> sendPayload(
            @RequestBody StudentPayload studentPayload
            ) {
        producerKafka.sendMessage(studentPayload);
        return ResponseEntity.ok().body(studentService.saveStudentCourses(studentPayload));
    }


}
