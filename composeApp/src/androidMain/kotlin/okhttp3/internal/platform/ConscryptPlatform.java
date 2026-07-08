package okhttp3.internal.platform;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import org.conscrypt.Conscrypt;
import org.conscrypt.ConscryptHostnameVerifier;

import javax.net.ssl.*;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.*;

public final class ConscryptPlatform extends Platform {
    public static final Companion Companion;
    private static final boolean isSupported;

    static {
        final Companion companion = new Companion(null);
        Companion = companion;
        boolean z = false;
        try {
            Class.forName("org.conscrypt.ConscryptVersion", false, companion.getClass().getClassLoader());
            if (Conscrypt.isAvailable()) {
                if (companion.atLeastVersion(2, 1, 0)) {
                    z = true;
                }
            }
        } catch (final ClassNotFoundException | NoClassDefFoundError unused) {
        }
        isSupported = z;
    }

    private final Provider provider;

    public ConscryptPlatform(final DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ConscryptPlatform() {
        final Provider providerNewProvider = Conscrypt.newProvider();
        checkNotNullExpressionValue(providerNewProvider, "newProvider()");
        provider = providerNewProvider;
    }
    public X509TrustManager trustManager(final SSLSocketFactory sslSocketFactory) {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        return null;
    }
    public SSLContext newSSLContext() throws NoSuchAlgorithmException {
        final SSLContext sSLContext = SSLContext.getInstance("TLS", provider);
        checkNotNullExpressionValue(sSLContext, "getInstance(\"TLS\", provider)");
        return sSLContext;
    }
    public X509TrustManager platformTrustManager() throws NoSuchAlgorithmException, KeyStoreException {
        final TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        final TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        checkNotNull(trustManagers);
        if (1 == trustManagers.length) {
            final TrustManager trustManager = trustManagers[0];
            if (trustManager instanceof X509TrustManager x509TrustManager) {
                if (null != trustManager) {
                    Conscrypt.setHostnameVerifier(x509TrustManager, DisabledHostnameVerifier.INSTANCE);
                    return x509TrustManager;
                }
                throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
            }
        }
        final String string = Arrays.toString(trustManagers);
        checkNotNullExpressionValue(string, "toString(this)");
        throw new IllegalStateException(stringPlus("Unexpected default trust managers: ", string));
    }
    public void configureTlsExtensions(final SSLSocket sslSocket, final String str, final List<Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        if (Conscrypt.isConscrypt(sslSocket)) {
            Conscrypt.setUseSessionTickets(sslSocket, true);
            final Object[] array = Platform.Companion.alpnProtocolNames(protocols).toArray(new String[0]);
            if (null == array) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            Conscrypt.setApplicationProtocols(sslSocket, (String[]) array);
            return;
        }
        super.configureTlsExtensions(sslSocket, str, protocols);
    }

    
    public String getSelectedProtocol(final SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        if (Conscrypt.isConscrypt(sslSocket)) {
            return Conscrypt.getApplicationProtocol(sslSocket);
        }
        return super.getSelectedProtocol(sslSocket);
    }
    public SSLSocketFactory newSslSocketFactory(final X509TrustManager trustManager) throws KeyManagementException {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        final SSLContext sSLContextNewSSLContext;
        try {
            sSLContextNewSSLContext = this.newSSLContext();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        sSLContextNewSSLContext.init(null, new TrustManager[]{trustManager}, null);
        final SSLSocketFactory socketFactory = sSLContextNewSSLContext.getSocketFactory();
        checkNotNullExpressionValue(socketFactory, "newSSLContext().apply {\n\u2026null)\n    }.socketFactory");
        return socketFactory;
    }

    public static final class DisabledHostnameVerifier implements ConscryptHostnameVerifier {
        public static final DisabledHostnameVerifier INSTANCE = new DisabledHostnameVerifier();

        private DisabledHostnameVerifier() {
        }

        public boolean verify(final String str, final SSLSession sSLSession) {
            return true;
        }

        public boolean verify(final X509Certificate[] x509CertificateArr, final String str, final SSLSession sSLSession) {
            return true;
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static boolean atLeastVersiondefault(final Companion companion, final int i2, int i3, int i4, final int i5, final Object obj) {
            if (0 != (i5 & 2)) {
                i3 = 0;
            }
            if (0 != (i5 & 4)) {
                i4 = 0;
            }
            return companion.atLeastVersion(i2, i3, i4);
        }
        public boolean isSupported() {
            return isSupported;
        }
        public ConscryptPlatform buildIfSupported() {
            final DefaultConstructorMarker defaultConstructorMarker = null;
            if (this.isSupported()) {
                return new ConscryptPlatform(defaultConstructorMarker);
            }
            return null;
        }
        public boolean atLeastVersion(final int i2, final int i3, final int i4) {
            final Conscrypt.Version version = Conscrypt.version();
            return version.major() != i2 ? version.major() > i2 : version.minor() != i3 ? version.minor() > i3 : version.patch() >= i4;
        }
    }
}
