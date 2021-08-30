package com.example.reservationservice.email;

import javax.mail.MessagingException;
import java.io.IOException;

public interface MailService {

    public void sendEmail();
    public void sendEmail(String to, String subject, String text);
    public void sendEmailWithAttachment() throws MessagingException, IOException;

}
