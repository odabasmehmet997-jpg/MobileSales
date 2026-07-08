package com.sun.mail.smtp;

import javax.mail.Address;
import javax.mail.SendFailedException;
import javax.mail.internet.InternetAddress;

public class SMTPSendFailedException extends SendFailedException {
    private static final long serialVersionUID = 8049122628728932894L;
    protected InternetAddress addr;
    protected String cmd;
    protected int rc;

    public SMTPSendFailedException(final String str, final int i2, final String str2, final Exception exc, final Address[] addressArr, final Address[] addressArr2, final Address[] addressArr3) {
        super(str2, exc, addressArr, addressArr2, addressArr3);
        cmd = str;
        rc = i2;
    }

    public String getCommand() {
        return cmd;
    }

    public int getReturnCode() {
        return rc;
    }
}
