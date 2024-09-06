package com.jsl.kafka_consumer.consumer;

import com.jsl.kafka_consumer.services.CourseRegistrationEmailService;
import com.jsl.kafka_consumer.services.EmailTemplate;
import com.jsl.model.StudentPayload;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerKafka {
    private final CourseRegistrationEmailService courseRegistrationEmailService;

    @KafkaListener(topics = "wikimedia-stream", groupId = "student")
    public void receivePayload(StudentPayload studentPayload) throws MessagingException {
        courseRegistrationEmailService.sendCourseRegistrationEmail(
                studentPayload.getEmail(),
                studentPayload.getLastname(),
                EmailTemplate.COURSE,
                studentPayload, "Course Registration"
        );
    }
}
