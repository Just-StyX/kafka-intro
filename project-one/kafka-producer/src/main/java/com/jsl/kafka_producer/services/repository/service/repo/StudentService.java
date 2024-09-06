package com.jsl.kafka_producer.services.repository.service.repo;

import com.jsl.kafka_producer.services.models.Student;
import com.jsl.model.StudentPayload;

public interface StudentService {
    Student saveStudentCourses(StudentPayload studentPayload);
    Student findByStudentNumber(String studentNumber);
}
