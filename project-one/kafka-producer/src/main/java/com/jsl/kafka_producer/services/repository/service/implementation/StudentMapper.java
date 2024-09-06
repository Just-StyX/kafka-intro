package com.jsl.kafka_producer.services.repository.service.implementation;

import com.jsl.kafka_producer.services.models.Course;
import com.jsl.kafka_producer.services.models.Student;
import com.jsl.model.StudentPayload;

public class StudentMapper {
    public static Student toStudent(StudentPayload studentPayload) {
        return Student.builder()
                .studentNumber(studentPayload.getStudentNumber())
                .firstname(studentPayload.getFirstname())
                .lastname(studentPayload.getLastname())
                .email(studentPayload.getEmail())
                .courses(studentPayload.getCourses().stream().map(
                        coursePayload -> Course.builder()
                                .courseTitle(coursePayload.getCourseTitle())
                                .courseName(coursePayload.getCourseName())
                                .creditHours(coursePayload.getCreditHours()).build()
                ).toList())
                .build();
    }
}
