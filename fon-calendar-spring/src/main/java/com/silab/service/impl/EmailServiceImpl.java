package com.silab.service.impl;

import com.silab.service.EmailService;
import java.io.File;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andjela
 */
@Service
public class EmailServiceImpl implements EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
    
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    
    @Override
    public void sendMail(String to, String subject, String body) {
        logger.debug("Sendim email on subject {} to {}", subject, to);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    
    @Override
    public void sendPreConfiguredMail(String message) {
        logger.debug("Sending preconfigured email with message {}", message);
        SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
        mailMessage.setText(message);
        mailSender.send(mailMessage);
    }
    
    @Override
    public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setFrom(new InternetAddress("test@gmail.com"));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(body);
                
                FileSystemResource file = new FileSystemResource(new File(fileToAttach));
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.addAttachment("logo.jpg", file);
            }
        };
        
        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            logger.error(ex.getMessage());
        }
    }
}
