package com.silab.service;

/**
 *
 * @author Andjela
 */
public interface EmailService {
    
    public void sendMail(String to, String subject, String body);
    
    public void sendPreConfiguredMail(String message);
    
    public void sendMailWithAttachment(String to, String subject, String body, String fileToAttach);
    
}
