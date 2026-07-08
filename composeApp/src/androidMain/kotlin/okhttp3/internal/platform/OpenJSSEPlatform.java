package okhttp3.internal.platform;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import org.openjsse.net.ssl.OpenJSSE;

import javax.net.ssl.*;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.Arrays;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.*;

public final class OpenJSSEPlatform extends Platform {
    public static final Companion Companion;
    private static final boolean isSupported;
    static {
        final Companion companion = new Companion(null);
        Companion = companion;
        boolean z = false;
        try {
            Class.forName("org.openjsse.net.ssl.OpenJSSE", false, companion.getClass().getClassLoader());
            z = true;
        } catch (final ClassNotFoundException unused) {
            throw new RuntimeException(unused);
        }
        isSupported = z;
    }
    private final Provider provider;
    public OpenJSSEPlatform(final DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
    private OpenJSSEPlatform() {
        provider = new OpenJSSE();
    }
    public SSLContext newSSLContext() throws NoSuchAlgorithmException {
        final SSLContext sSLContext = SSLContext.getInstance("TLSv1.3", provider);
        checkNotNullExpressionValue(sSLContext, "getInstance(\"TLSv1.3\", provider)");
        return sSLContext;
    }
    public X509TrustManager platformTrustManager() throws NoSuchAlgorithmException, KeyStoreException {
        final TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm(), provider);
        trustManagerFactory.init((KeyStore) null);
        final TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        checkNotNull(trustManagers);
        if (1 == trustManagers.length) {
            final TrustManager trustManager = trustManagers[0];
            if (trustManager instanceof X509TrustManager) {
                if (null != trustManager) {
                    return (X509TrustManager) trustManager;
                }
                throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
            }
        }
        final String string = Arrays.toString(trustManagers);
        checkNotNullExpressionValue(string, "toString(this)");
        throw new IllegalStateException(stringPlus("Unexpected default trust managers: ", string));
    }
    public X509TrustManager trustManager(final SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported with OpenJSSE");
    }
    public void configureTlsExtensions(final SSLSocket sslSocket, final String str, final List<Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        super.configureTlsExtensions(sslSocket, str, protocols);
    }
    public String getSelectedProtocol(final SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        return super.getSelectedProtocol(sslSocket);
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
        public  boolean isSupported() {
            return isSupported;
        }
        public OpenJSSEPlatform buildIfSupported() {
            final DefaultConstructorMarker defaultConstructorMarker = null;
            if (this.isSupported()) {
                return new OpenJSSEPlatform(defaultConstructorMarker);
            }
            return null;
        }
    }
}
