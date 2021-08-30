package com.example.reservationservice.email;

public interface EmailService {
    public void sendEmail(String to, String subject, String text);
    public void sendEmail();
}
