package okhttp3.internal.platform;

import android.os.Build;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Protocol;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.areEqual;

public class Jdk9Platform extends Platform {
    public static final Companion Companion = new Companion(null);
    private static final boolean isAvailable;
    static {
        String property = System.getProperty("java.specification.version");
        Integer intOrNull = null != property ? StringsKt.toIntOrNull(property) : null;
        boolean z = false;
        if (null != intOrNull) {
            if (9 <= intOrNull) {
                z = true;
            }
        } else {
            try {
                SSLSocket.class.getMethod("getApplicationProtocol", (Class<?>) null);
                z = true;
            } catch (NoSuchMethodException ignored) {
            }
        }
        isAvailable = z;
    }
    public void configureTlsExtensions(SSLSocket sslSocket, String str, List<Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        SSLParameters sSLParameters = sslSocket.getSSLParameters();
        Object[] array = Platform.Companion.alpnProtocolNames(protocols).toArray(new String[0]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            sSLParameters.setApplicationProtocols((String[]) array);
        }
        sslSocket.setSSLParameters(sSLParameters);
    }
    public String getSelectedProtocol(SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        try {
            String applicationProtocol = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                applicationProtocol = sslSocket.getApplicationProtocol();
            }
            if (null == applicationProtocol || areEqual(applicationProtocol, "")) {
                return null;
            }
            return applicationProtocol;
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }
    public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public boolean isAvailable() {
            return Jdk9Platform.isAvailable;
        }

        public Jdk9Platform buildIfSupported() {
            if (isAvailable()) {
                return new Jdk9Platform();
            }
            return null;
        }
    }
}
