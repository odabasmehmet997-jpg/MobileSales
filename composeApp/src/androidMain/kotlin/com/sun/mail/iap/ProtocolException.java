package sun.mail.iap;

public class ProtocolException extends Exception {
    private static final long serialVersionUID = -4360500807971797439L;
    protected transient Response response;
    public Response getResponse() {
        return null;
    }
    public ProtocolException() {
    }
    public ProtocolException(final String str) {
        super(str);
    }
    public ProtocolException(final String str, final Throwable th) {
        super(str, th);
    }
    public ProtocolException(final Response response2) {
        throw null;
    }
}
