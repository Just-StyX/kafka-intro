package com.jsl.kafka_consumer.services;

import com.jsl.model.StudentPayload;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CourseRegistrationEmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine springTemplateEngine;

    public void sendCourseRegistrationEmail(String receiver,
                                String username,
                                EmailTemplate emailTemplate,
                                StudentPayload studentPayload, String subject) throws MessagingException {
        String templateName;
        if (emailTemplate == null) {
            templateName = "confirm_email";
        } else {
            templateName = emailTemplate.getName();
        }
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", username);
        properties.put("registration", studentPayload);

        Context context = new Context();
        context.setVariables(properties);
        helper.setFrom("contact@jsl-group.com");
        helper.setTo(receiver);
        helper.setSubject(subject);

        String template = springTemplateEngine.process(templateName, context);
        helper.setText(template, true);
        javaMailSender.send(mimeMessage);
    }
}
