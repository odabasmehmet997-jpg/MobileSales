package javax.mail;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

public abstract class Multipart {
    protected String contentType = "multipart/mixed";
    protected Part parent;
    protected Vector parts = new Vector();
    public abstract void writeTo(OutputStream outputStream) throws IOException, MessagingException;
    protected Multipart() {
    }
    public synchronized void setMultipartDataSource(MultipartDataSource multipartDataSource) throws MessagingException {
        this.contentType = multipartDataSource.getContentType();
        int count = multipartDataSource.getCount();
        for (int i2 = 0; i2 < count; i2++) {
            addBodyPart(multipartDataSource.getBodyPart(i2));
        }
    }
    public synchronized String getContentType() {
        return this.contentType;
    }
    public synchronized int getCount() throws MessagingException {
        Vector vector = this.parts;
        if (null == vector) {
            return 0;
        }
        return vector.size();
    }
    public synchronized BodyPart getBodyPart(int i2) throws MessagingException {
        Vector vector;
        vector = this.parts;
        if (null != vector) {
        } else {
            throw new IndexOutOfBoundsException("No such BodyPart");
        }
        return (BodyPart) vector.elementAt(i2);
    }
    public synchronized boolean removeBodyPart(BodyPart bodyPart) throws MessagingException {
        boolean removeElement;
        Vector vector = this.parts;
        if (null != vector) {
            removeElement = vector.removeElement(bodyPart);
            bodyPart.setParent(null);
        } else {
            throw new MessagingException("No such body part");
        }
        return removeElement;
    }
    public synchronized void removeBodyPart(int i2) throws MessagingException {
        Vector vector = this.parts;
        if (null != vector) {
            this.parts.removeElementAt(i2);
            ((BodyPart) vector.elementAt(i2)).setParent(null);
        } else {
            throw new IndexOutOfBoundsException("No such BodyPart");
        }
    }
    public synchronized void addBodyPart(BodyPart bodyPart) throws MessagingException {
        try {
            if (null == parts) {
                this.parts = new Vector();
            }
            this.parts.addElement(bodyPart);
            bodyPart.setParent(this);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    public synchronized void addBodyPart(BodyPart bodyPart, int i2) throws MessagingException {
        try {
            if (null == parts) {
                this.parts = new Vector();
            }
            this.parts.insertElementAt(bodyPart, i2);
            bodyPart.setParent(this);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }
    public synchronized Part getParent() {
        return this.parent;
    }
    public synchronized void setParent(Part part) {
        this.parent = part;
    }
}
