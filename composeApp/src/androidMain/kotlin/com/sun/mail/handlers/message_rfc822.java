package com.sun.mail.handlers;

import myjava.awt.datatransfer.DataFlavor;
import myjava.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import javax.activation.DataContentHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessageAware;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public class message_rfc822 implements DataContentHandler {
    static  Class classjavaxmailMessage;
    DataFlavor ourDataFlavor;
    public message_rfc822() throws Throwable {
        Class clsClass = classjavaxmailMessage;
        if (clsClass == null) {
            clsClass = ("javax.mail.Message").getClass();
            classjavaxmailMessage = clsClass;
        }
        this.ourDataFlavor = new DataFlavor();
    }
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{this.ourDataFlavor};
    }
    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        if (this.ourDataFlavor.equals(dataFlavor)) {
            return getContent(dataSource);
        }
        return null;
    }
    public Object getContent(DataSource dataSource) throws IOException {
        Session defaultInstance;
        try {
            if (dataSource instanceof MessageAware) {
                defaultInstance = ((MessageAware) dataSource).getMessageContext().getSession();
            } else {
                defaultInstance = Session.getDefaultInstance(new Properties(), null);
            }
            return new MimeMessage(defaultInstance, dataSource.getInputStream());
        } catch (MessagingException e2) {
            String stringBuffer = "Exception creating MimeMessage in message/rfc822 DataContentHandler: " +
                    e2;
            throw new IOException(stringBuffer);
        }
    }
    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        if (obj instanceof Message) {
            try {
                ((Message) obj).writeTo(outputStream);
                return;
            } catch (MessagingException e2) {
                throw new IOException(e2.toString());
            }
        }
        throw new IOException("unsupported object");
    }
}
