package okhttp3.internal.platform;

import android.annotation.SuppressLint;
import android.os.Build;
import android.security.NetworkSecurityPolicy;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import okhttp3.internal.platform.android.*;
import okhttp3.internal.tls.CertificateChainCleaner;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class Android10Platform extends Platform {
    public static final Companion Companion = new Companion(null);
    private static final boolean isSupported;

    static {
        isSupported = Platform.Companion.isAndroid() && 29 <= Build.VERSION.SDK_INT;
    }

    private final List<SocketAdapter> socketAdapters;

    public Android10Platform() {
        final List listListOfNotNull = CollectionsKt.listOfNotNull(Android10SocketAdapter.Companion.buildIfSupported(), new DeferredSocketAdapter(AndroidSocketAdapter.Companion.getPlayProviderFactory()), new DeferredSocketAdapter(ConscryptSocketAdapter.Companion.getFactory()), new DeferredSocketAdapter(BouncyCastleSocketAdapter.Companion.getFactory()));
        final ArrayList arrayList = new ArrayList();
        for (final Object obj : listListOfNotNull) {
            if (((SocketAdapter) obj).isSupported()) {
                arrayList.add(obj);
            }
        }
        socketAdapters = arrayList;
    }

    public X509TrustManager trustManager(final SSLSocketFactory sslSocketFactory) {
        Object next;
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        final Iterator<SocketAdapter> it = socketAdapters.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((SocketAdapter) next).matchesSocketFactory(sslSocketFactory)) {
                break;
            }
        }
        final SocketAdapter socketAdapter = (SocketAdapter) next;
        if (null == socketAdapter) {
            return null;
        }
        return socketAdapter.trustManager(sslSocketFactory);
    }
    public void configureTlsExtensions(final SSLSocket sslSocket, final String str, final List<Protocol> protocols) {
        Object next;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
        final Iterator<SocketAdapter> it = socketAdapters.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (((SocketAdapter) next).matchesSocket(sslSocket)) {
                    break;
                }
            }
        }
        final SocketAdapter socketAdapter = (SocketAdapter) next;
        if (null == socketAdapter) {
            return;
        }
        socketAdapter.configureTlsExtensions(sslSocket, str, protocols);
    }
    public String getSelectedProtocol(final SSLSocket sslSocket) {
        Object next;
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        final Iterator<SocketAdapter> it = socketAdapters.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((SocketAdapter) next).matchesSocket(sslSocket)) {
                break;
            }
        }
        final SocketAdapter socketAdapter = (SocketAdapter) next;
        if (null == socketAdapter) {
            return null;
        }
        return socketAdapter.getSelectedProtocol(sslSocket);
    }

    @Override // okhttp3.internal.platform.Platform
    @SuppressLint("NewApi")
    public boolean isCleartextTrafficPermitted(final String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(hostname);
    }

    @Override // okhttp3.internal.platform.Platform
    public CertificateChainCleaner buildCertificateChainCleaner(final X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        final AndroidCertificateChainCleaner androidCertificateChainCleanerBuildIfSupported = AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager);
        return null == androidCertificateChainCleanerBuildIfSupported ? super.buildCertificateChainCleaner(trustManager) : androidCertificateChainCleanerBuildIfSupported;
    }

    /* compiled from: Android10Platform.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public boolean isSupported() {
            return isSupported;
        }

        public Platform buildIfSupported() {
            if (this.isSupported()) {
                return new Android10Platform();
            }
            return null;
        }
    }
}
