package com.jsl.kafka_consumer.services;

import lombok.Getter;

@Getter
public enum EmailTemplate {
    COURSE("course");

    private final String name;

    EmailTemplate(String name) {
        this.name = name;
    }
}
