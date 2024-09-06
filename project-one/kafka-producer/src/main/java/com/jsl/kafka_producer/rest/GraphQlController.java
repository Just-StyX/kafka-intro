package com.jsl.kafka_producer.rest;

import com.jsl.kafka_producer.services.models.Student;
import com.jsl.kafka_producer.services.repository.service.repo.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class GraphQlController {

    private final StudentService studentService;

    @QueryMapping
    public Mono<Student> findStudentByNumber(@Argument String number) {
        return Mono.just(studentService.findByStudentNumber(number));
    }
}
