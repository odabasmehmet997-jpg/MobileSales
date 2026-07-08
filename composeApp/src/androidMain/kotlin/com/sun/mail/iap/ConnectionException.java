package sun.mail.iap;

public class ConnectionException extends ProtocolException {
    private static final long serialVersionUID = 5749739604257464727L;
    private transient Protocol p;

    public Protocol getProtocol() {
        return null;
    }

    public ConnectionException() {
    }

    public ConnectionException(final String str) {
        super(str);
    }

    public ConnectionException(final Protocol protocol, final Response response) {
        super(response);
    }
}
