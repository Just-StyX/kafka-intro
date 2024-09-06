package com.jsl.kafka_producer.services.repository;

import com.jsl.kafka_producer.services.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}
