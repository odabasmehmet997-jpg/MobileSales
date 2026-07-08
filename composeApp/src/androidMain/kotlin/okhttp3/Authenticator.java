package okhttp3;

import java.io.IOException;
import kotlin.jvm.internal.Intrinsics;

public interface Authenticator {
    Companion Companion = Authenticator.Companion.INSTANCE;
    Authenticator NONE = new Companion.AuthenticatorNone();
    java.net.Authenticator JAVA_NET_AUTHENTICATOR = new okhttp3.internal.authenticator.JavaNetAuthenticator();
    Request authenticate(Route route, Response response) throws IOException;
    final class Companion {
        static final Companion INSTANCE = new Companion();
        private static final class AuthenticatorNone implements Authenticator {
            public Request authenticate(Route route, Response response) {
                Intrinsics.checkNotNullParameter(response, "response");
                return null;
            }
        }
        private Companion() {
        }
    }
}
