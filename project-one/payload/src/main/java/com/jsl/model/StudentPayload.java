package com.jsl.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StudentPayload {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
}
