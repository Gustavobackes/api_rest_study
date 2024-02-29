package com.email.emailservice.services;

import com.email.emailservice.domain.entities.EmailEntity;
import com.email.emailservice.domain.enums.StatusEmail;
import com.email.emailservice.services.util.EmailUtil;
import com.email.emailservice.services.util.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Properties;


@Slf4j
@Service
public class EmailService {

    @Value("${mail.username}")
    private String userName;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.host}")
    private String host;

    private static final String SMTP = "smtp";


    public EmailEntity sendEmail(EmailEntity emailEntity) {
        emailEntity.setSendDateEmail(LocalDateTime.now());
        Properties props = EmailUtil.getEmailProperties(host, port);
        Session session = UserUtil.getUserSession(props, userName, password);
        try {
            Transport transport = session.getTransport(SMTP);
            transport.connect(host, port, userName, password);

            MimeMessage message = getMimeMessage(emailEntity, session);

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            log.info("Email sent successfully: {}", emailEntity);
        } catch (MessagingException e) {
            log.error(String.format("Error sending e-mail: %s", e.getMessage()));
            emailEntity.setStatusEmail(StatusEmail.ERROR);
        }
        emailEntity.setStatusEmail(StatusEmail.SENT);
        return emailEntity;
    }

    private static MimeMessage getMimeMessage(EmailEntity emailEntity, Session session) throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailEntity.getEmailFrom()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailEntity.getEmailTo()));
        message.setSubject(emailEntity.getSubject());
        message.setText(emailEntity.getText());
        return message;
    }

}
