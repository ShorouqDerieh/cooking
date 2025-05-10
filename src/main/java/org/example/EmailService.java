package org.example;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailService {
public void sendEmail( String to, String subject, String body) {
    String username = "s12217236@stu.najah.edu";
    final String password = "pwpr ovjy umyu xafu";
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
    try{
        // بناء الرسالة
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(to)//مستقبل
        );
        message.setSubject(subject);
        message.setText(body);

        // إرسال الرسالة
        Transport.send(message);

        System.out.println("Email Sent Successfully to: "+to+" !");

    } catch (
            MessagingException e) {
        e.printStackTrace();
    }
}
}
