package javax.mail;

public class MessagingException extends Exception {
    private static final long serialVersionUID = -7569192289819959253L;
    private Exception next;
    public MessagingException() {
        initCause(null);
    }
    public MessagingException(String str) {
        super(str);
        initCause(null);
    }
    public MessagingException(String str, Exception exc) {
        super(str);
        this.next = exc;
        initCause(null);
    }
    public synchronized Exception getNextException() {
        return this.next;
    }
    public synchronized Throwable getCause() {
        return this.next;
    }
    public synchronized boolean setNextException(Exception exc) {
        Exception exc2 = this;
        while ((exc2 instanceof MessagingException) && null != ((MessagingException) exc2).next) {
            try {
                exc2 = ((MessagingException) exc2).next;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (!(exc2 instanceof MessagingException)) {
            return false;
        }
        ((MessagingException) exc2).next = exc;
        return true;
    }
    public synchronized String toString() {
        String obj = super.toString();
        Exception exc = this.next;
        if (null == exc) {
            return obj;
        }
        if (null == obj) {
            obj = "";
        }
        StringBuffer stringBuffer = new StringBuffer(obj);
        while (null != exc) {
            stringBuffer.append(";\n  nested exception is:\n\t");
            if (exc instanceof MessagingException messagingException) {
                stringBuffer.append(messagingException.superToString());
                exc = messagingException.next;
            } else {
                stringBuffer.append(exc);
                exc = null;
            }
        }
        return stringBuffer.toString();
    }
    private String superToString() {
        return super.toString();
    }
}
