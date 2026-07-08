package javax.mail.internet;

public class AddressException extends ParseException {
    private static final long serialVersionUID = 9134583443539323120L;
    protected int pos;
    protected String ref;
    public AddressException() {
        this.ref = null;
        this.pos = -1;
    }
    public AddressException(String str) {
        super(str);
        this.ref = null;
        this.pos = -1;
    }
    public AddressException(String str, String str2) {
        super(str);
        this.pos = -1;
        this.ref = str2;
    }
    public AddressException(String str, String str2, int i2) {
        super(str);
        this.ref = str2;
        this.pos = i2;
    }
    public String getRef() {
        return this.ref;
    }
    public int getPos() {
        return this.pos;
    }
    public String toString() {
        String messagingException = super.toString();
        if (this.ref == null) {
            return messagingException;
        }
        String stringBuffer2 = messagingException +
                " in string ``" +
                this.ref +
                "''";
        if (this.pos < 0) {
            return stringBuffer2;
        }
        final String stringBuffer3 = stringBuffer2 +
                " at position " +
                this.pos;
        return stringBuffer3;
    }
}
