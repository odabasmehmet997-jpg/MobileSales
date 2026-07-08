package com.sun.mail.smtp;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

public class SMTPAddressSucceededException extends MessagingException {
    private static final long serialVersionUID = -1168335848623096749L;
    protected InternetAddress addr;
    protected String cmd;
    protected int rc;
    public SMTPAddressSucceededException(final InternetAddress internetAddress, final String str, final int i2, final String str2) {
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
