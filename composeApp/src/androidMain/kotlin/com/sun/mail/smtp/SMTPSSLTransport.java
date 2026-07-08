package com.sun.mail.smtp;

import javax.mail.Session;
import javax.mail.URLName;

public class SMTPSSLTransport extends SMTPTransport {
    public SMTPSSLTransport(final Session session, final URLName uRLName) {
        super(session, uRLName, "smtps", true);
    }
}
