package com.silab.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

/**
 *
 * @author Andjela
 */
@Configuration
public class EmailConfig {

    @Bean
    public SimpleMailMessage emailTemplate() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("homersimpson794@gmail.com");
        message.setSubject("Fon calendar");
        message.setText("Test");
        return message;
    }
}
