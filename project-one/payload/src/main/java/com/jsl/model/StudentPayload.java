package com.jsl.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentPayload {
    private String studentNumber;
    private String firstname;
    private String lastname;
    private String email;
    private List<CoursePayload> courses;
}
