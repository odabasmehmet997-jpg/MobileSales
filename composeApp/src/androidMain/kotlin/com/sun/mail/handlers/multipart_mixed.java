package com.sun.mail.handlers;

import myjava.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.io.OutputStream;
import javax.activation.DataContentHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;

public class multipart_mixed implements DataContentHandler {
    static  Class classjavaxmailinternetMimeMultipart;
    private final DataFlavor myDF;

    public multipart_mixed() throws Throwable {
        Class clsClass = classjavaxmailinternetMimeMultipart;
        if (clsClass == null) {
            clsClass = ("javax.mail.internet.MimeMultipart").getClass();
            classjavaxmailinternetMimeMultipart = clsClass;
        }
        this.myDF = new DataFlavor();
    }

    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{this.myDF};
    }

    public Object getTransferData(DataFlavor dataFlavor, DataSource dataSource) throws IOException {
        if (this.myDF.equals(dataFlavor)) {
            return getContent(dataSource);
        }
        return null;
    }
    public Object getContent(DataSource dataSource) throws IOException {
        try {
            return new MimeMultipart(dataSource);
        } catch (MessagingException e2) {
            IOException iOException = new IOException("Exception while constructing MimeMultipart", e2);
            throw iOException;
        }
    }

    public void writeTo(Object obj, String str, OutputStream outputStream) throws IOException {
        if (obj instanceof MimeMultipart) {
            try {
                ((MimeMultipart) obj).writeTo(outputStream);
            } catch (MessagingException e2) {
                throw new IOException(e2.toString());
            }
        }
    }
}
