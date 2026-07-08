package okhttp3.internal.platform;

import androidx.annotation.NonNull;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.internal.Util;
import okhttp3.internal.platform.android.AndroidLog;
import okhttp3.internal.tls.BasicCertificateChainCleaner;
import okhttp3.internal.tls.BasicTrustRootIndex;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.TrustRootIndex;
import okio.Buffer;

import javax.net.ssl.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static kotlin.jvm.internal.Intrinsics.*;

public class Platform {
    public static final Companion Companion;
    public static final int INFO = 4;
    public static final int WARN = 5;
    private static final Logger logger;
    private static volatile Platform platform;
    static {
        Companion companion = new Companion(null);
        Companion = companion;
        platform = companion.findPlatform();
        logger = Logger.getLogger(OkHttpClient.class.getName());
    }
    public static Platform get() {
        return Companion.get();
    }
    public static void logdefault(Platform platform2, String str, int i2, Throwable th, int i3, Object obj) {
        if (null != obj) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: log");
        }
        if (0 != (i3 & 2)) {
            i2 = 4;
        }
        if (0 != (i3 & 4)) {
            th = null;
        }
        platform2.log(str, i2, th);
    }
    public void afterHandshake(SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
    }
    public void configureTlsExtensions(SSLSocket sslSocket, String str, List<Protocol> protocols) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        Intrinsics.checkNotNullParameter(protocols, "protocols");
    }
    public String getSelectedProtocol(SSLSocket sslSocket) {
        Intrinsics.checkNotNullParameter(sslSocket, "sslSocket");
        return null;
    }
    public boolean isCleartextTrafficPermitted(String hostname) {
        Intrinsics.checkNotNullParameter(hostname, "hostname");
        return true;
    }
    public final String getPrefix() {
        return "OkHttp";
    }
    public SSLContext newSSLContext() throws NoSuchAlgorithmException {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        checkNotNullExpressionValue(sSLContext, "getInstance(\"TLS\")");
        return sSLContext;
    }
    public X509TrustManager platformTrustManager() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        checkNotNull(trustManagers);
        if (1 == trustManagers.length) {
            TrustManager trustManager = trustManagers[0];
            if (trustManager instanceof X509TrustManager && null != trustManager) {
                return (X509TrustManager) trustManager;
            }
        }
        String string = Arrays.toString(trustManagers);
        checkNotNullExpressionValue(string, "toString(this)");
        throw new IllegalStateException(stringPlus("Unexpected default trust managers: ", string));
    }
    public X509TrustManager trustManager(SSLSocketFactory sslSocketFactory) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        Intrinsics.checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
        try {
            Class<?> sslContextClass = Class.forName("sun.security.ssl.SSLContextImpl");
            checkNotNullExpressionValue(sslContextClass, "sslContextClass");
            Object fieldOrNull = Util.readFieldOrNull(sslSocketFactory, sslContextClass, "context");
            if (null == fieldOrNull) {
                return null;
            }
            return Util.readFieldOrNull(fieldOrNull, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (RuntimeException e2) {
            if (areEqual(e2.getClass().getName(), "java.lang.reflect.InaccessibleObjectException")) {
                return null;
            }
            throw e2;
        }
    }
    public void connectSocket(Socket socket, InetSocketAddress address, int i2) throws IOException {
        Intrinsics.checkNotNullParameter(socket, "socket");
        Intrinsics.checkNotNullParameter(address, "address");
        socket.connect(address, i2);
    }
    public void log(String message, int i2, Throwable th) {
        Intrinsics.checkNotNullParameter(message, "message");
        logger.log(5 == i2 ? Level.WARNING : Level.INFO, message, th);
    }
    public Object getStackTraceForCloseable(String closer) {
        Intrinsics.checkNotNullParameter(closer, "closer");
        if (logger.isLoggable(Level.FINE)) {
            return new Throwable(closer);
        }
        return null;
    }
    public void logCloseableLeak(String message, Object obj) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (null == obj) {
            message = stringPlus(message, " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);");
        }
        log(message, 5, (Throwable) obj);
    }
    public CertificateChainCleaner buildCertificateChainCleaner(X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        return new BasicCertificateChainCleaner(buildTrustRootIndex(trustManager));
    }
    public TrustRootIndex buildTrustRootIndex(X509TrustManager trustManager) {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        X509Certificate[] acceptedIssuers = trustManager.getAcceptedIssuers();
        checkNotNullExpressionValue(acceptedIssuers, "trustManager.acceptedIssuers");
        return new BasicTrustRootIndex(Arrays.copyOf(acceptedIssuers, acceptedIssuers.length));
    }
    public SSLSocketFactory newSslSocketFactory(X509TrustManager trustManager) throws KeyManagementException {
        Intrinsics.checkNotNullParameter(trustManager, "trustManager");
        try {
            SSLContext sSLContextNewSSLContext = newSSLContext();
            sSLContextNewSSLContext.init(null, new TrustManager[]{trustManager}, null);
            SSLSocketFactory socketFactory = sSLContextNewSSLContext.getSocketFactory();
            checkNotNullExpressionValue(socketFactory, "newSSLContext().apply {\n\u2026ll)\n      }.socketFactory");
            return socketFactory;
        } catch (GeneralSecurityException e2) {
            throw new AssertionError(stringPlus("No System TLS: ", e2), e2);
        }
    }
    public String toString() {
        String simpleName = getClass().getSimpleName();
        checkNotNullExpressionValue(simpleName, "javaClass.simpleName");
        return simpleName;
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static void resetForTestsdefault(Companion companion, Platform platform, int i2, Object obj) {
            if (0 != (i2 & 1)) {
                platform = companion.findPlatform();
            }
            companion.resetForTests(platform);
        }

        public Platform get() {
            return Platform.platform;
        }

        public void resetForTests(Platform platform) {
            Intrinsics.checkNotNullParameter(platform, "platform");
            Platform.platform = platform;
        }

        public boolean isAndroid() {
            return areEqual("Dalvik", System.getProperty("java.vm.name"));
        }

        private boolean isConscryptPreferred() {
            return areEqual("Conscrypt", Security.getProviders()[0].getName());
        }

        private boolean isOpenJSSEPreferred() {
            return areEqual("OpenJSSE", Security.getProviders()[0].getName());
        }

        private boolean isBouncyCastlePreferred() {
            return areEqual("BC", Security.getProviders()[0].getName());
        }

        private Platform findPlatform() {
            if (isAndroid()) {
                return findAndroidPlatform();
            }
            return findJvmPlatform();
        }

        private Platform findAndroidPlatform() {
            AndroidLog.INSTANCE.enable();
            Platform platformBuildIfSupported = Android10Platform.Companion.buildIfSupported();
            if (null != platformBuildIfSupported) {
                return platformBuildIfSupported;
            }
            Platform platformBuildIfSupported2 = AndroidPlatform.Companion.buildIfSupported();
            checkNotNull(platformBuildIfSupported2);
            return platformBuildIfSupported2;
        }

        private Platform findJvmPlatform() {
            OpenJSSEPlatform openJSSEPlatformBuildIfSupported;
            BouncyCastlePlatform bouncyCastlePlatformBuildIfSupported;
            ConscryptPlatform conscryptPlatformBuildIfSupported;
            if (isConscryptPreferred() && null != (conscryptPlatformBuildIfSupported = ConscryptPlatform.Companion.buildIfSupported())) {
                return conscryptPlatformBuildIfSupported;
            }
            if (isBouncyCastlePreferred() && null != (bouncyCastlePlatformBuildIfSupported = BouncyCastlePlatform.Companion.buildIfSupported())) {
                return bouncyCastlePlatformBuildIfSupported;
            }
            if (isOpenJSSEPreferred() && null != (openJSSEPlatformBuildIfSupported = OpenJSSEPlatform.Companion.buildIfSupported())) {
                return openJSSEPlatformBuildIfSupported;
            }
            Jdk9Platform jdk9PlatformBuildIfSupported = Jdk9Platform.Companion.buildIfSupported();
            if (null != jdk9PlatformBuildIfSupported) {
                return jdk9PlatformBuildIfSupported;
            }
            Platform platformBuildIfSupported = Jdk8WithJettyBootPlatform.Companion.buildIfSupported();
            return null != platformBuildIfSupported ? platformBuildIfSupported : new Platform();
        }

        public byte[] concatLengthPrefixed(List<Protocol> protocols) {
            Intrinsics.checkNotNullParameter(protocols, "protocols");
            Buffer buffer = new Buffer();
            for (String str : alpnProtocolNames(protocols)) {
                buffer.writeByte(str.length());
                buffer.writeUtf8(str);
            }
            return buffer.readByteArray();
        }

        public List<String> alpnProtocolNames(List<Protocol> protocols) {
            Intrinsics.checkNotNullParameter(protocols, "protocols");
            ArrayList arrayList = new ArrayList();
            for (Object obj : protocols) {
                if (Protocol.HTTP_1_0 != obj) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(it.next().toString());
            }
            return arrayList2;
        }
    }
}
