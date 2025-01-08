package com.gestion.demogestioncafetaria.exception;

import jakarta.mail.MessagingException;

public class EmailNotSendException extends MessagingException {
    public EmailNotSendException(String message){
        super(message);
    }
}
