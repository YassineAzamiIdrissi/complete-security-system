package com.security.robust.api.security.system.Email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public void sendEmail(String to,
                          String username,
                          EmailTemplate template,
                          String activationCode,
                          String confirmationLink,
                          String subject
    ) throws MessagingException {
        String templateName;
        if (template == null) {
            templateName = "activate_account";
        } else {
            templateName = template.getName();
        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper
                (message, MULTIPART_MODE_MIXED, UTF_8.name());
        Map<String, Object> props = new HashMap<>();
        props.put("confirmationUrl", confirmationLink);
        props.put("username", username);
        props.put("activation_code", activationCode);

        Context context = new Context();
        context.setVariables(props);

        messageHelper.setFrom("yassineazamiidrissi@gmail.com");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);

        String templateSource = templateEngine.process(templateName, context);
        messageHelper.setText(templateSource, true);
        mailSender.send(message);
    }
}
