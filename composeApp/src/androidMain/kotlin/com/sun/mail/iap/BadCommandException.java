package sun.mail.iap;

import com.google.android.gms.common.api.Response;

import java.net.ProtocolException;

public class BadCommandException extends ProtocolException {
    private static final long serialVersionUID = 5769722539397237515L;
    public BadCommandException() {
    }
    public BadCommandException(final String str) {
        super(str);
    }
    public BadCommandException(final Response response) {
        super(response.toString());
    }
}
