package com.jsl.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CoursePayload {
    private String courseName;
    private String courseTitle;
    private int creditHours;
}
