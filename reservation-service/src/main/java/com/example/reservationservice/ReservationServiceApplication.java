package com.example.reservationservice;

import com.example.reservationservice.email.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservationServiceApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

    @Autowired
    private MailService emailService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending Email...");
        emailService.sendEmail("nagcloudlab@gmail.com","hello123","this test email");
        System.out.println("Done");
    }
}
