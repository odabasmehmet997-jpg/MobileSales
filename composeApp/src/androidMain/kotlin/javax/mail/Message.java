package javax.mail;

import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.Date;
import javax.mail.search.SearchTerm;

public abstract class Message implements Part {
    protected boolean expunged;
    protected Folder folder;
    protected int msgnum;
    protected Session session;
    public abstract void addFrom(Address[] addressArr) throws MessagingException;
    public abstract void addRecipients(RecipientType recipientType, Address[] addressArr) throws MessagingException;
    public abstract Flags getFlags() throws MessagingException;
    public abstract Address[] getFrom() throws MessagingException;
    public abstract Date getReceivedDate() throws MessagingException;
    public abstract Address[] getRecipients(RecipientType recipientType) throws MessagingException;
    public abstract Date getSentDate() throws MessagingException;
    public abstract String getSubject() throws MessagingException;
    public abstract Message reply(boolean z) throws MessagingException;
    public abstract void saveChanges() throws MessagingException;
    public abstract void setFlags(Flags flags, boolean z) throws MessagingException;
    public abstract void setFrom() throws MessagingException;
    public abstract void setFrom(Address address) throws MessagingException;
    public abstract void setRecipients(RecipientType recipientType, Address[] addressArr) throws MessagingException;
    public abstract void setSentDate(Date date) throws MessagingException;
    public abstract void setSubject(String str) throws MessagingException;
    protected Message() {
        this.msgnum = 0;
        this.expunged = false;
        this.folder = null;
        this.session = null;
    }
    protected Message(Folder folder2, int i2) {
        this.expunged = false;
        this.session = null;
        this.folder = folder2;
        this.msgnum = i2;
        this.session = folder2.store.session;
    }
    protected Message(Session session2) {
        this.msgnum = 0;
        this.expunged = false;
        this.folder = null;
        this.session = session2;
    }
    public static class RecipientType implements Serializable {
        public static final RecipientType BCC = new RecipientType("Bcc");
        public static final RecipientType CC = new RecipientType("Cc");
        public static final RecipientType TO = new RecipientType("To");
        private static final long serialVersionUID = -7479791750606340008L;
        protected String type;

        protected RecipientType(String str) {
            this.type = str;
        }

        
        public Object readResolve() throws ObjectStreamException {
            if ("To".equals(type)) {
                return TO;
            }
            if ("Cc".equals(type)) {
                return CC;
            }
            if ("Bcc".equals(type)) {
                return BCC;
            }
            final String stringBuffer = "Attempt to resolve unknown RecipientType: " +
                    this.type;
            throw new InvalidObjectException(stringBuffer);
        }

        public String toString() {
            return this.type;
        }
    }
    public Address[] getAllRecipients() throws MessagingException {
        int i2;
        Address[] recipients = getRecipients(RecipientType.TO);
        Address[] recipients2 = getRecipients(RecipientType.CC);
        Address[] recipients3 = getRecipients(RecipientType.BCC);
        if (null == recipients2 && null == recipients3) {
            return recipients;
        }
        Address[] addressArr = new Address[((null != recipients ? recipients.length : 0) + (null != recipients2 ? recipients2.length : 0) + (null != recipients3 ? recipients3.length : 0))];
        if (null != recipients) {
            System.arraycopy(recipients, 0, addressArr, 0, recipients.length);
            i2 = recipients.length;
        } else {
            i2 = 0;
        }
        if (null != recipients2) {
            System.arraycopy(recipients2, 0, addressArr, i2, recipients2.length);
            i2 += recipients2.length;
        }
        if (null != recipients3) {
            System.arraycopy(recipients3, 0, addressArr, i2, recipients3.length);
        }
        return addressArr;
    }
    public void setRecipient(RecipientType recipientType, Address address) throws MessagingException {
        setRecipients(recipientType, new Address[]{address});
    }
    public void addRecipient(RecipientType recipientType, Address address) throws MessagingException {
        addRecipients(recipientType, new Address[]{address});
    }
    public Address[] getReplyTo() throws MessagingException {
        return getFrom();
    }
    public void setReplyTo(Address[] addressArr) throws MessagingException {
        throw new MethodNotSupportedException("setReplyTo not supported");
    }
    public boolean isSet(Flags.Flag flag) throws MessagingException {
        return getFlags().contains(flag);
    }
    public void setFlag(Flags.Flag flag, boolean z) throws MessagingException {
        setFlags(new Flags(flag), z);
    }
    public int getMessageNumber() {
        return this.msgnum;
    }
    public void setMessageNumber(int i2) {
        this.msgnum = i2;
    }
    public Folder getFolder() {
        return this.folder;
    }
    public boolean isExpunged() {
        return this.expunged;
    }
    public void setExpunged(boolean z) {
        this.expunged = z;
    }
    public boolean match(SearchTerm searchTerm) throws MessagingException {
        return searchTerm.match(this);
    }
}
