package com.gestion.demogestioncafetaria.notification;

import com.gestion.demogestioncafetaria.exception.SomethingWrongException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.username}")
    private String fromEmail;
    private final JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String text, List<String> list) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        if (list != null && !list.isEmpty()) {
            message.setCc(list.toArray(new String[0]));
        }

        mailSender.send(message);
    }

    public void forgotMail(String to, String subject, String resetLink) throws SomethingWrongException {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            String htmlMasg = "<p><b>Your Login details for Cafe Management System</b><br><b>Email:</b>" + to
                    + "<br><b><br><a href=\""+resetLink+"\">Click here to creer new password </a><p>";
            message.setContent(htmlMasg, "text/html");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new SomethingWrongException("An error occurred while sending the email: " + e.getMessage());
        }
    }

}
