package javax.mail.internet;

import com.sun.mail.util.FolderClosedIOException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownServiceException;
import javax.activation.DataSource;
import javax.mail.FolderClosedException;
import javax.mail.MessageAware;
import javax.mail.MessageContext;
import javax.mail.MessagingException;

public class MimePartDataSource implements DataSource, MessageAware {
    private MessageContext context;
    protected MimePart part;
    public MimePartDataSource(MimePart mimePart) {
        this.part = mimePart;
    }
    public InputStream getInputStream() throws IOException {
        InputStream inputStream;
        try {
            MimePart mimePart = this.part;
            if (mimePart instanceof MimeBodyPart) {
                inputStream = ((MimeBodyPart) mimePart).getContentStream();
            } else if (mimePart instanceof MimeMessage) {
                inputStream = ((MimeMessage) mimePart).getContentStream();
            } else {
                throw new MessagingException("Unknown part");
            }
            MimePart mimePart2 = this.part;
            String restrictEncoding = MimeBodyPart.restrictEncoding(mimePart2, mimePart2.getEncoding());
            if (null != restrictEncoding) {
                return MimeUtility.decode(inputStream, restrictEncoding);
            }
            return inputStream;
        } catch (FolderClosedException e2) {
            throw new FolderClosedIOException(e2.getFolder(), e2.getMessage());
        } catch (MessagingException e3) {
            throw new IOException(e3.getMessage());
        }
    }
    public OutputStream getOutputStream() throws IOException {
        throw new UnknownServiceException("Writing not supported");
    }
    public String getContentType() {
        try {
            return this.part.getContentType();
        } catch (MessagingException unused) {
            return "application/octet-stream";
        }
    }
    public String getName() {
        try {
            MimePart mimePart = this.part;
            if (mimePart instanceof MimeBodyPart) {
                return mimePart.getFileName();
            }
            return "";
        } catch (MessagingException unused) {
            return "";
        }
    }
    public synchronized MessageContext getMessageContext() {
        try {
            if (null == context) {
                this.context = new MessageContext(this.part);
            }
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return this.context;
    }
}
