package com.sun.mail.smtp;

import javax.mail.MessagingException;

public interface SaslAuthenticator {
    boolean authenticate(String[] mechanisms, String host, String user, String password, String protocol) throws MessagingException;
}
