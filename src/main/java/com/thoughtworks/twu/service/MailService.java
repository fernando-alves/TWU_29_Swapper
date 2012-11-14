package com.thoughtworks.twu.service;


import com.sun.mail.smtp.SMTPTransport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@Transactional
public class MailService implements MailServiceInterface {
    @Override
    public void send(String toEmail, String fromUser, String offerTitle) {
        String host = "smtp.gmail.com";
        final String userName = "twufeedmycat@gmail.com";
        final String password = "@twu292012";

        Properties props = System.getProperties();
        props.put("mail.smtps.host", host);
        props.put("mail.smtp.user", userName);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "465");
        props.put("mail.smtps.auth", "true");

        String to = toEmail + "@thoughtworks.com";

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });

        MimeMessage message = new MimeMessage(session);

        try {
            String from = fromUser + "@thoughtworks.com";
            message.setFrom(new InternetAddress("offers@feedmycat.com", "FeedMyCat"));
            Address[] addresses = { new InternetAddress(from) };
            message.setReplyTo(addresses);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("I am interested in your offer.");
            message.setText("Hi " + toEmail + ":\n\n" + "I am interested in the offer for \"" + offerTitle + "\" you made at FeedMyCat. I would like to talk to you about it.\n"+"\n"+"Cheers\n"+fromUser, "utf-8");

            SMTPTransport transport = (SMTPTransport) session.getTransport("smtps");
            transport.connect(host, userName, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



    }
}
