package com.sun.mail.smtp;

import javax.mail.SendFailedException;
import javax.mail.internet.InternetAddress;

public class SMTPAddressFailedException extends SendFailedException {
    private static final long serialVersionUID = 804831199768630097L;
    protected InternetAddress addr;
    protected String cmd;
    protected int rc;
    public SMTPAddressFailedException(final InternetAddress internetAddress, final String str, final int i2, final String str2) {
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
