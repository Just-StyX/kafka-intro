package com.jsl.kafka_producer.services.repository.service.implementation;

import com.jsl.kafka_producer.services.models.Student;
import com.jsl.kafka_producer.services.repository.StudentRepository;
import com.jsl.kafka_producer.services.repository.service.repo.StudentService;
import com.jsl.model.StudentPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.jsl.kafka_producer.services.repository.service.implementation.StudentMapper.toStudent;

@Service
@RequiredArgsConstructor
public class StudentServiceRepository implements StudentService{

    private final StudentRepository studentRepository;

    @Override
    public Student saveStudentCourses(StudentPayload studentPayload) {
        return studentRepository.save(toStudent(studentPayload));
    }

    @Override
    public Student findByStudentNumber(String studentNumber) {
        return studentRepository.findByStudentNumber(studentNumber);
    }
}
