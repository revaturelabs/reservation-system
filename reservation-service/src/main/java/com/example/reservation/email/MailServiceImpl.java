package com.example.reservation.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Component
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender emailSender;


    public void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("nagcloudlab@gmail.com");
        msg.setTo("nagcloudlab@gmail.com");

        msg.setSubject("Reservation System - Test Email");
        msg.setText("Hello World \n from Reservation System");

        emailSender.send(msg);

    }

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@example.com");
        System.out.println(to);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }






    public void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = emailSender.createMimeMessage();
        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);

        helper.setTo("to_@email");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        // hard coded a file path
        //FileSystemResource file = new FileSystemResource(new File("path/android.png"));

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        emailSender.send(msg);

    }

}