package com.thoughtworks.twu.service;


public interface MailServiceInterface {

    void send(String toEmail, String fromEmail, String offerTitle);
}
