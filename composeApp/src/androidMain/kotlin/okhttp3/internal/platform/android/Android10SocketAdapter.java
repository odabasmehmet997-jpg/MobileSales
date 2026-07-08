package okhttp3.internal.platform.android;


import android.net.ssl.SSLSockets;
import android.os.Build;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import okhttp3.internal.platform.Platform;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.areEqual;

public final class Android10SocketAdapter implements SocketAdapter {
    public static final Companion Companion = new Companion(null);

    public boolean matchesSocketFactory(final SSLSocketFactory sSLSocketFactory) {
        return SocketAdapter.DefaultImpls.matchesSocketFactory(this, sSLSocketFactory);
    }
    public X509TrustManager trustManager(final SSLSocketFactory sSLSocketFactory) {
        return SocketAdapter.DefaultImpls.trustManager(this, sSLSocketFactory);
    }
    public boolean matchesSocket(final SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        return SSLSockets.isSupportedSocket(sslSocket);
    }
    public boolean isSupported() {
        return Android10SocketAdapter.Companion.isSupported();
    }
    public String getSelectedProtocol(final SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        final String applicationProtocol = sslSocket.getApplicationProtocol();
        if (null == applicationProtocol || areEqual(applicationProtocol, "")) {
            return null;
        }
        return applicationProtocol;
    }
    public void configureTlsExtensions(final SSLSocket sslSocket, final String str, final List<Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        try {
            SSLSockets.setUseSessionTickets(sslSocket, true);
            final SSLParameters sSLParameters = sslSocket.getSSLParameters();
            final Object[] array = Platform.Companion.alpnProtocolNames(protocols).toArray(new String[0]);
            if (null == array) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            sSLParameters.setApplicationProtocols((String[]) array);
            sslSocket.setSSLParameters(sSLParameters);
        } catch (final IllegalArgumentException e2) {
            try {
                throw new IOException("Android internal error", e2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }

        public SocketAdapter buildIfSupported() {
            if (this.isSupported()) {
                return new Android10SocketAdapter();
            }
            return null;
        }
        public boolean isSupported() {
            return Platform.Companion.isAndroid() && 29 <= Build.VERSION.SDK_INT;
        }
    }
}
