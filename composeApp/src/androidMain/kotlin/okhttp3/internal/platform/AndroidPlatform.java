package okhttp3.internal.platform;

import android.os.Build;
import android.security.NetworkSecurityPolicy;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;
import okhttp3.internal.platform.android.*;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.areEqual;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class AndroidPlatform extends Platform {
    public static final Companion Companion = new Companion(null);
    private static final boolean isSupported;

    static {
        boolean z = Platform.Companion.isAndroid() && 30 > Build.VERSION.SDK_INT;
        isSupported = z;
    }

    private final CloseGuard closeGuard;
    private final List<SocketAdapter> socketAdapters;

    public AndroidPlatform() {
        final List listListOfNotNull = CollectionsKt.listOfNotNull(StandardAndroidSocketAdapter.Companion.buildIfSupporteddefault(StandardAndroidSocketAdapter.Companion, null, 1, null), new DeferredSocketAdapter(AndroidSocketAdapter.Companion.getPlayProviderFactory()), new DeferredSocketAdapter(ConscryptSocketAdapter.Companion.getFactory()), new DeferredSocketAdapter(BouncyCastleSocketAdapter.Companion.getFactory()));
        final ArrayList arrayList = new ArrayList();
        for (final Object obj : listListOfNotNull) {
            if (((SocketAdapter) obj).isSupported()) {
                arrayList.add(obj);
            }
        }
        socketAdapters = arrayList;
        try {
            closeGuard = CloseGuard.Companion.get();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void connectSocket(final Socket socket, final InetSocketAddress address, final int i2) throws IOException {
        Intrinsics.checkNotNullParameter(socket, "socket");
        Intrinsics.checkNotNullParameter(address, "address");
        socket.connect(address, i2);
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
    public Object getStackTraceForCloseable(final String closer) {
        Intrinsics.checkNotNullParameter(closer, "closer");
        try {
            return closeGuard.createAndOpen(closer);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    public void logCloseableLeak(final String message, final Object obj) {
        Intrinsics.checkNotNullParameter(message, "message");
        try {
            if (closeGuard.warnIfOpen(obj)) {
                return;
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        logdefault(this, message, 5, null, 4, null);
    }
    public boolean isCleartextTrafficPermitted(final String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(hostname);
    }
    public CertificateChainCleaner buildCertificateChainCleaner(final X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        final AndroidCertificateChainCleaner androidCertificateChainCleanerBuildIfSupported = AndroidCertificateChainCleaner.Companion.buildIfSupported(trustManager);
        return null == androidCertificateChainCleanerBuildIfSupported ? super.buildCertificateChainCleaner(trustManager) : androidCertificateChainCleanerBuildIfSupported;
    }
    public TrustRootIndex buildTrustRootIndex(final X509TrustManager trustManager) throws SecurityException {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        try {
            final Method method = trustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            method.setAccessible(true);
            checkNotNullExpressionValue(method, "method");
            return new CustomTrustRootIndex(trustManager, method);
        } catch (final NoSuchMethodException unused) {
            return super.buildTrustRootIndex(trustManager);
        }
    }

    /* compiled from: AndroidPlatform.kt */
    public static final class CustomTrustRootIndex implements TrustRootIndex {
        private final Method findByIssuerAndSignatureMethod;
        private final X509TrustManager trustManager;

        public CustomTrustRootIndex(final X509TrustManager trustManager, final Method findByIssuerAndSignatureMethod) {
            Intrinsics.checkNotNullParameter(trustManager, "trustManager");
            Intrinsics.checkNotNullParameter(findByIssuerAndSignatureMethod, "findByIssuerAndSignatureMethod");
            this.trustManager = trustManager;
            this.findByIssuerAndSignatureMethod = findByIssuerAndSignatureMethod;
        }

        public static CustomTrustRootIndex copydefault(final CustomTrustRootIndex customTrustRootIndex, X509TrustManager x509TrustManager, Method method, final int i2, final Object obj) {
            if (0 != (i2 & 1)) {
                x509TrustManager = customTrustRootIndex.trustManager;
            }
            if (0 != (i2 & 2)) {
                method = customTrustRootIndex.findByIssuerAndSignatureMethod;
            }
            return customTrustRootIndex.copy(x509TrustManager, method);
        }

        private X509TrustManager component1() {
            return trustManager;
        }

        private Method component2() {
            return findByIssuerAndSignatureMethod;
        }

        public CustomTrustRootIndex copy(final X509TrustManager trustManager, final Method findByIssuerAndSignatureMethod) {
            Intrinsics.checkNotNullParameter(trustManager, "trustManager");
            Intrinsics.checkNotNullParameter(findByIssuerAndSignatureMethod, "findByIssuerAndSignatureMethod");
            return new CustomTrustRootIndex(trustManager, findByIssuerAndSignatureMethod);
        }

        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CustomTrustRootIndex customTrustRootIndex)) {
                return false;
            }
            return areEqual(trustManager, customTrustRootIndex.trustManager) && areEqual(findByIssuerAndSignatureMethod, customTrustRootIndex.findByIssuerAndSignatureMethod);
        }

        public int hashCode() {
            return (trustManager.hashCode() * 31) + findByIssuerAndSignatureMethod.hashCode();
        }

        public String toString() {
            return "CustomTrustRootIndex(trustManager=" + trustManager + ", findByIssuerAndSignatureMethod=" + findByIssuerAndSignatureMethod + ')';
        }
        public X509Certificate findByIssuerAndSignature(final X509Certificate cert) throws IllegalArgumentException {
            Intrinsics.checkNotNullParameter(cert, "cert");
            try {
                final Object objInvoke = findByIssuerAndSignatureMethod.invoke(trustManager, cert);
                if (null == objInvoke) {
                    throw new NullPointerException("null cannot be cast to non-null type java.security.cert.TrustAnchor");
                }
                return ((TrustAnchor) objInvoke).getTrustedCert();
            } catch (final IllegalAccessException e2) {
                throw new AssertionError("unable to get issues and signature", e2);
            } catch (final InvocationTargetException unused) {
                return null;
            }
        }
    }

    /* compiled from: AndroidPlatform.kt */
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
                return new AndroidPlatform();
            }
            return null;
        }
    }
}
