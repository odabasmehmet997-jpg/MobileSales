package com.sun.mail.smtp;

import javax.mail.SendFailedException;
import javax.mail.internet.InternetAddress;

public class SMTPSenderFailedException extends SendFailedException {
    private static final long serialVersionUID = 514540454964476947L;
    protected InternetAddress addr;
    protected String cmd;
    protected int rc;

    public SMTPSenderFailedException(final InternetAddress internetAddress, final String str, final int i2, final String str2) {
        super(str2);
        addr = internetAddress;
        cmd = str;
        rc = i2;
    }

    public InternetAddress getAddress() {
        return addr;
    }

    public String getCommand() {
        return cmd;
    }

    public int getReturnCode() {
        return rc;
    }
}
