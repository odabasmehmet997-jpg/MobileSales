package sun.mail.iap;

public class CommandFailedException extends ProtocolException {
    private static final long serialVersionUID = 793932807880443631L;

    public CommandFailedException() {
    }

    public CommandFailedException(final String str) {
        super(str);
    }

    public CommandFailedException(final Response response) {
        super(response);
    }
}
