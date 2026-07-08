package com.sun.mail.smtp;

import java.io.InputStream;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class SMTPMessage extends MimeMessage {
    public static final int NOTIFY_DELAY = 4;
    public static final int NOTIFY_FAILURE = 2;
    public static final int NOTIFY_NEVER = -1;
    public static final int NOTIFY_SUCCESS = 1;
    public static final int RETURN_FULL = 1;
    public static final int RETURN_HDRS = 2;
    private static final String[] returnOptionString = {null, "FULL", "HDRS"};
    private boolean allow8bitMIME;
    private String envelopeFrom;
    private String extension;
    private int notifyOptions;
    private int returnOption;
    private boolean sendPartial;
    private String submitter;

    public SMTPMessage(Session session) {
        super(session);
        this.notifyOptions = 0;
        this.returnOption = 0;
        this.sendPartial = false;
        this.allow8bitMIME = false;
        this.submitter = null;
        this.extension = null;
    }

    public SMTPMessage(Session session, InputStream inputStream) throws MessagingException {
        super(session, inputStream);
        this.notifyOptions = 0;
        this.returnOption = 0;
        this.sendPartial = false;
        this.allow8bitMIME = false;
        this.submitter = null;
        this.extension = null;
    }

    public SMTPMessage(MimeMessage mimeMessage) throws MessagingException {
        try {
            mimeMessage.super(this);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        this.notifyOptions = 0;
        this.returnOption = 0;
        this.sendPartial = false;
        this.allow8bitMIME = false;
        this.submitter = null;
        this.extension = null;
    }

    public void setEnvelopeFrom(String str) {
        this.envelopeFrom = str;
    }

    public String getEnvelopeFrom() {
        return this.envelopeFrom;
    }

    public void setNotifyOptions(int i2) {
        if (i2 < -1 || i2 >= 8) {
            throw new IllegalArgumentException("Bad return option");
        }
        this.notifyOptions = i2;
    }

    public int getNotifyOptions() {
        return this.notifyOptions;
    }

    String getDSNNotify() {
        int i2 = this.notifyOptions;
        if (i2 == 0) {
            return null;
        }
        if (i2 == -1) {
            return "NEVER";
        }
        StringBuffer stringBuffer = new StringBuffer();
        if ((this.notifyOptions & 1) != 0) {
            stringBuffer.append("SUCCESS");
        }
        if ((this.notifyOptions & 2) != 0) {
            if (stringBuffer.length() != 0) {
                stringBuffer.append(',');
            }
            stringBuffer.append("FAILURE");
        }
        if ((this.notifyOptions & 4) != 0) {
            if (stringBuffer.length() != 0) {
                stringBuffer.append(',');
            }
            stringBuffer.append("DELAY");
        }
        return stringBuffer.toString();
    }

    public void setReturnOption(int i2) {
        if (i2 < 0 || i2 > 2) {
            throw new IllegalArgumentException("Bad return option");
        }
        this.returnOption = i2;
    }

    public int getReturnOption() {
        return this.returnOption;
    }

    String getDSNRet() {
        return returnOptionString[this.returnOption];
    }

    public void setAllow8bitMIME(boolean z) {
        this.allow8bitMIME = z;
    }

    public boolean getAllow8bitMIME() {
        return this.allow8bitMIME;
    }

    public void setSendPartial(boolean z) {
        this.sendPartial = z;
    }

    public boolean getSendPartial() {
        return this.sendPartial;
    }

    public String getSubmitter() {
        return this.submitter;
    }

    public void setSubmitter(String str) {
        this.submitter = str;
    }

    public String getMailExtension() {
        return this.extension;
    }

    public void setMailExtension(String str) {
        this.extension = str;
    }
}
