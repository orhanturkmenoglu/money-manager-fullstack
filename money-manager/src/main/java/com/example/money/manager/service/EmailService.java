package com.example.money.manager.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendEmail(String to,String subject,String body){
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            mailSender.send(message);
        }catch (Exception exception){
            throw  new RuntimeException("Error sending email: " + exception.getMessage());
        }
    }
    public static String buildActivationEmailBody(String userName, String activationLink) {
        return """
            <html>
                <body style="font-family: Arial, sans-serif; line-height: 1.6; color: #333;">
                    <h2 style="color: #2E86C1;">Welcome to Money Manager!</h2>
                    <p>Hi %s,</p>
                    <p>Thank you for creating your account. To activate your account, please click the link below:</p>
                    
                    <p><a href="%s" style="color: #2E86C1;">%s</a></p>
                    
                    <hr/>
                    <p style="font-size: 12px; color: #777;">
                        This email was sent by Money Manager. Please do not share your account information with others.
                    </p>
                </body>
            </html>
            """.formatted(userName, activationLink, activationLink);
    }

}
