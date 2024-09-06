package com.jsl.kafka_producer.services.repository;

import com.jsl.kafka_producer.services.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    Student findByStudentNumber(String studentNumber);
}
