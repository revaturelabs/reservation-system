package com.example.reservationservice;

import com.example.reservationservice.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootApplication
public class ReservationServiceApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }


    @Autowired
    private EmailService emailService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending Email...");
        emailService.sendEmail();
        System.out.println("Done");
    }
}
