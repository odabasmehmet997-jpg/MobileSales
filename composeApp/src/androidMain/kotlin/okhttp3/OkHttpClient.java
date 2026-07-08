package okhttp3;

import com.proje.mobilesales.R;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RouteDatabase;
import okhttp3.internal.ws.RealWebSocket;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.proxy.NullProxySelector;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import java.net.Proxy;
import java.net.ProxySelector;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static kotlin.jvm.internal.Intrinsics.*;

public class OkHttpClient extends Call.Factory implements Cloneable, WebSocket.Factory {
    public static final Companion Companion = new Companion(null);
    private static final List<Protocol> DEFAULT_PROTOCOLS = Util.immutableListOf(Protocol.HTTP_2, Protocol.HTTP_1_1);
    private static final List<ConnectionSpec> DEFAULT_CONNECTION_SPECS = Util.immutableListOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT);
    private final Authenticator authenticator;
    private final Cache cache;
    private final int callTimeoutMillis;
    private CertificateChainCleaner certificateChainCleaner;
    private CertificatePinner certificatePinner;
    private final int connectTimeoutMillis;
    private final ConnectionPool connectionPool;
    private final List<ConnectionSpec> connectionSpecs;
    private final CookieJar cookieJar;
    private final Dispatcher dispatcher;
    private final Dns dns;
    private final EventListener.Factory eventListenerFactory;
    private final boolean followRedirects;
    private final boolean followSslRedirects;
    private final HostnameVerifier hostnameVerifier;
    private final List<Interceptor> interceptors;
    private final long minWebSocketMessageToCompress;
    private final List<Interceptor> networkInterceptors;
    private final int pingIntervalMillis;
    private final List<Protocol> protocols;
    private final Proxy proxy;
    private final Authenticator proxyAuthenticator;
    private final ProxySelector proxySelector;
    private final int readTimeoutMillis;
    private final boolean retryOnConnectionFailure;
    private final RouteDatabase routeDatabase;
    private final SocketFactory socketFactory;
    private SSLSocketFactory sslSocketFactoryOrNull;
    private final int writeTimeoutMillis;
    private X509TrustManager x509TrustManager;

    public OkHttpClient(final Builder builder) throws NoSuchAlgorithmException, KeyStoreException {
        ProxySelector proxySelectorokhttp;
        checkNotNullParameter(builder, "builder");
        dispatcher = builder.getDispatcherokhttp();
        connectionPool = builder.getConnectionPoolokhttp();
        interceptors = Util.toImmutableList(builder.getInterceptorsokhttp());
        networkInterceptors = Util.toImmutableList(builder.getNetworkInterceptorsokhttp());
        eventListenerFactory = builder.getEventListenerFactoryokhttp();
        retryOnConnectionFailure = builder.getRetryOnConnectionFailureokhttp();
        authenticator = builder.getAuthenticatorokhttp();
        followRedirects = builder.getFollowRedirectsokhttp();
        followSslRedirects = builder.getFollowSslRedirectsokhttp();
        cookieJar = builder.getCookieJarokhttp();
        cache = builder.getCacheokhttp();
        dns = builder.getDnsokhttp();
        proxy = builder.getProxyokhttp();
        if (null != builder.getProxyokhttp()) {
            proxySelectorokhttp = NullProxySelector.INSTANCE;
        } else {
            proxySelectorokhttp = builder.getProxySelectorokhttp();
            proxySelectorokhttp = null == proxySelectorokhttp ? ProxySelector.getDefault() : proxySelectorokhttp;
            if (null == proxySelectorokhttp) {
                proxySelectorokhttp = NullProxySelector.INSTANCE;
            }
        }
        proxySelector = proxySelectorokhttp;
        proxyAuthenticator = builder.getProxyAuthenticatorokhttp();
        socketFactory = builder.getSocketFactoryokhttp();
        final List<ConnectionSpec> connectionSpecsokhttp = builder.getConnectionSpecsokhttp();
        connectionSpecs = connectionSpecsokhttp;
        protocols = builder.getProtocolsokhttp();
        hostnameVerifier = builder.getHostnameVerifierokhttp();
        callTimeoutMillis = builder.getCallTimeoutokhttp();
        connectTimeoutMillis = builder.getConnectTimeoutokhttp();
        readTimeoutMillis = builder.getReadTimeoutokhttp();
        writeTimeoutMillis = builder.getWriteTimeoutokhttp();
        pingIntervalMillis = builder.getPingIntervalokhttp();
        minWebSocketMessageToCompress = builder.getMinWebSocketMessageToCompressokhttp();
        final RouteDatabase routeDatabaseokhttp = builder.getRouteDatabaseokhttp();
        routeDatabase = null == routeDatabaseokhttp ? new RouteDatabase() : routeDatabaseokhttp;
        if (null != connectionSpecsokhttp && connectionSpecsokhttp.isEmpty()) {
            sslSocketFactoryOrNull = null;
            certificateChainCleaner = null;
            x509TrustManager = null;
            certificatePinner = CertificatePinner.DEFAULT;
        } else {
            final Iterator<ConnectionSpec> it = connectionSpecsokhttp.iterator();
            while (it.hasNext()) {
                if (it.next().isTls()) {
                    if (null != builder.getSslSocketFactoryOrNullokhttp()) {
                        sslSocketFactoryOrNull = builder.getSslSocketFactoryOrNullokhttp();
                        final CertificateChainCleaner certificateChainCleanerokhttp = builder.getCertificateChainCleanerokhttp();
                        checkNotNull(certificateChainCleanerokhttp);
                        certificateChainCleaner = certificateChainCleanerokhttp;
                        final X509TrustManager x509TrustManagerOrNullokhttp = builder.getX509TrustManagerOrNullokhttp();
                        checkNotNull(x509TrustManagerOrNullokhttp);
                        x509TrustManager = x509TrustManagerOrNullokhttp;
                        final CertificatePinner certificatePinnerokhttp = builder.getCertificatePinnerokhttp();
                        checkNotNull(certificateChainCleanerokhttp);
                        certificatePinner = certificatePinnerokhttp.withCertificateChainCleanerokhttp(certificateChainCleanerokhttp);
                    } else {
                        final Platform.Companion companion = Platform.Companion;
                        final X509TrustManager x509TrustManagerPlatformTrustManager = companion.get().platformTrustManager();
                        x509TrustManager = x509TrustManagerPlatformTrustManager;
                        final Platform platform = companion.get();
                        checkNotNull(x509TrustManagerPlatformTrustManager);
                        try {
                            sslSocketFactoryOrNull = platform.newSslSocketFactory(x509TrustManagerPlatformTrustManager);
                        } catch (KeyManagementException e) {
                            throw new RuntimeException(e);
                        }
                        final CertificateChainCleaner.Companion companion2 = CertificateChainCleaner.Companion;
                        checkNotNull(x509TrustManagerPlatformTrustManager);
                        final CertificateChainCleaner certificateChainCleaner = companion2.get(x509TrustManagerPlatformTrustManager);
                        this.certificateChainCleaner = certificateChainCleaner;
                        final CertificatePinner certificatePinnerokhttp2 = builder.getCertificatePinnerokhttp();
                        checkNotNull(certificateChainCleaner);
                        certificatePinner = certificatePinnerokhttp2.withCertificateChainCleanerokhttp(certificateChainCleaner);
                    }
                }
            }
            sslSocketFactoryOrNull = null;
            certificateChainCleaner = null;
            x509TrustManager = null;
            certificatePinner = CertificatePinner.DEFAULT;
        }
        this.verifyClientState();
    }
    public OkHttpClient(final Authenticator authenticator, final Cache cache, final int callTimeoutMillis, final int connectTimeoutMillis, final ConnectionPool connectionPool, final List<ConnectionSpec> connectionSpecs, final CookieJar cookieJar, final Dispatcher dispatcher, final Dns dns, final EventListener.Factory eventListenerFactory, final boolean followRedirects, final boolean followSslRedirects, final HostnameVerifier hostnameVerifier, final List<Interceptor> interceptors, final long minWebSocketMessageToCompress, final List<Interceptor> networkInterceptors, final int pingIntervalMillis, final List<Protocol> protocols, final Proxy proxy, final Authenticator proxyAuthenticator, final ProxySelector proxySelector, final int readTimeoutMillis, final boolean retryOnConnectionFailure, final RouteDatabase routeDatabase, final SocketFactory socketFactory, final int writeTimeoutMillis) {
        this.authenticator = authenticator;
        this.cache = cache;
        this.callTimeoutMillis = callTimeoutMillis;
        this.connectTimeoutMillis = connectTimeoutMillis;
        this.connectionPool = connectionPool;
        this.connectionSpecs = connectionSpecs;
        this.cookieJar = cookieJar;
        this.dispatcher = dispatcher;
        this.dns = dns;
        this.eventListenerFactory = eventListenerFactory;
        this.followRedirects = followRedirects;
        this.followSslRedirects = followSslRedirects;
        this.hostnameVerifier = hostnameVerifier;
        this.interceptors = interceptors;
        this.minWebSocketMessageToCompress = minWebSocketMessageToCompress;
        this.networkInterceptors = networkInterceptors;
        this.pingIntervalMillis = pingIntervalMillis;
        this.protocols = protocols;
        this.proxy = proxy;
        this.proxyAuthenticator = proxyAuthenticator;
        this.proxySelector = proxySelector;
        this.readTimeoutMillis = readTimeoutMillis;
        this.retryOnConnectionFailure = retryOnConnectionFailure;
        this.routeDatabase = routeDatabase;
        this.socketFactory = socketFactory;
        this.writeTimeoutMillis = writeTimeoutMillis;
        new Builder();
    }
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
    public final Dispatcher dispatcher() {
        return dispatcher;
    }
    public final ConnectionPool connectionPool() {
        return connectionPool;
    }
    public final List<Interceptor> interceptors() {
        return interceptors;
    }
    public final List<Interceptor> networkInterceptors() {
        return networkInterceptors;
    }
    public final EventListener.Factory eventListenerFactory() {
        return eventListenerFactory;
    }
    public final boolean retryOnConnectionFailure() {
        return retryOnConnectionFailure;
    }
    public final Authenticator authenticator() {
        return authenticator;
    }
    public final boolean followRedirects() {
        return followRedirects;
    }
    public final boolean followSslRedirects() {
        return followSslRedirects;
    }
    public final CookieJar cookieJar() {
        return cookieJar;
    }
    public final Cache cache() {
        return cache;
    }
    public final Dns dns() {
        return dns;
    }
    public final Proxy proxy() {
        return proxy;
    }
    public final ProxySelector proxySelector() {
        return proxySelector;
    }
    public final Authenticator proxyAuthenticator() {
        return proxyAuthenticator;
    }
    public final SocketFactory socketFactory() {
        return socketFactory;
    }
    public final SSLSocketFactory sslSocketFactory() {
        final SSLSocketFactory sSLSocketFactory = sslSocketFactoryOrNull;
        if (null != sSLSocketFactory) {
            return sSLSocketFactory;
        }
        throw new IllegalStateException("CLEARTEXT-only client");
    }
    public final X509TrustManager x509TrustManager() {
        return x509TrustManager;
    }
    public final List<ConnectionSpec> connectionSpecs() {
        return connectionSpecs;
    }
    public final List<Protocol> protocols() {
        return protocols;
    }
    public final HostnameVerifier hostnameVerifier() {
        return hostnameVerifier;
    }
    public final CertificatePinner certificatePinner() {
        return certificatePinner;
    }
    public final CertificateChainCleaner certificateChainCleaner() {
        return certificateChainCleaner;
    }
    public final int callTimeoutMillis() {
        return callTimeoutMillis;
    }
    public final int connectTimeoutMillis() {
        return connectTimeoutMillis;
    }
    public final int readTimeoutMillis() {
        return readTimeoutMillis;
    }
    public final int writeTimeoutMillis() {
        return writeTimeoutMillis;
    }
    public final int pingIntervalMillis() {
        return pingIntervalMillis;
    }
    public final long minWebSocketMessageToCompress() {
        return minWebSocketMessageToCompress;
    }
    public final RouteDatabase getRouteDatabase() {
        return routeDatabase;
    }
    private final void verifyClientState() {
        if (interceptors.contains(null)) {
            throw new IllegalStateException(stringPlus("Null interceptor: ", this.interceptors()));
        }
        if (networkInterceptors.contains(null)) {
            throw new IllegalStateException(stringPlus("Null network interceptor: ", this.networkInterceptors()));
        }
        final List<ConnectionSpec> list = connectionSpecs;
        if (null == list || list.isEmpty()) {
            final Iterator<ConnectionSpec> it = list.iterator();
            while (it.hasNext()) {
                final ConnectionSpec spec = it.next();
                if (spec.isTls()) {
                    if (null == sslSocketFactoryOrNull) {
                        throw new IllegalStateException("sslSocketFactory == null");
                    }
                    if (null == certificateChainCleaner) {
                        throw new IllegalStateException("certificateChainCleaner == null");
                    }
                    if (null == x509TrustManager) {
                        throw new IllegalStateException("x509TrustManager == null");
                    }
                    return;
                }
            }
        }
        if (null != sslSocketFactoryOrNull) {
            throw new IllegalStateException("Check failed.");
        }
        if (null != certificateChainCleaner) {
            throw new IllegalStateException("Check failed.");
        }
        if (null != x509TrustManager) {
            throw new IllegalStateException("Check failed.");
        }
        if (!areEqual(certificatePinner, CertificatePinner.DEFAULT)) {
            throw new IllegalStateException("Check failed.");
        }
    }
    public RealCall newCall(final Request request) {
        checkNotNullParameter(request, "request");
        return new RealCall(this, request, false);
    }
    public WebSocket newWebSocket(final Request request, final WebSocketListener listener) {
        checkNotNullParameter(request, "request");
        checkNotNullParameter(listener, "listener");
        final RealWebSocket realWebSocket = new RealWebSocket(TaskRunner.INSTANCE, request, listener, new Random(), pingIntervalMillis, null, minWebSocketMessageToCompress);
        realWebSocket.connect(this);
        return realWebSocket;
    }
    public Builder newBuilder() {
        return new Builder(this);
    }
    public final Dispatcher m1807deprecated_dispatcher() {
        return dispatcher;
    }
    public final ConnectionPool m1804deprecated_connectionPool() {
        return connectionPool;
    }
    public final List<Interceptor> m1813deprecated_interceptors() {
        return interceptors;
    }
    public final List<Interceptor> m1814deprecated_networkInterceptors() {
        return networkInterceptors;
    }
    public final EventListener.Factory m1809deprecated_eventListenerFactory() {
        return eventListenerFactory;
    }
    public final boolean m1821deprecated_retryOnConnectionFailure() {
        return retryOnConnectionFailure;
    }
    public final Authenticator m1799deprecated_authenticator() {
        return authenticator;
    }
    public final boolean m1810deprecated_followRedirects() {
        return followRedirects;
    }
    public final boolean m1811deprecated_followSslRedirects() {
        return followSslRedirects;
    }
    public final CookieJar m1806deprecated_cookieJar() {
        return cookieJar;
    }
    public final Cache m1800deprecated_cache() {
        return cache;
    }
    public final Dns m1808deprecated_dns() {
        return dns;
    }
    public final Proxy m1817deprecated_proxy() {
        return proxy;
    }
    public final ProxySelector m1819deprecated_proxySelector() {
        return proxySelector;
    }
    public final Authenticator m1818deprecated_proxyAuthenticator() {
        return proxyAuthenticator;
    }
    public final SocketFactory m1822deprecated_socketFactory() {
        return socketFactory;
    }
    public final SSLSocketFactory m1823deprecated_sslSocketFactory() {
        return this.sslSocketFactory();
    }
    public final List<ConnectionSpec> m1805deprecated_connectionSpecs() {
        return connectionSpecs;
    }
    public final List<Protocol> m1816deprecated_protocols() {
        return protocols;
    }
    public final HostnameVerifier m1812deprecated_hostnameVerifier() {
        return hostnameVerifier;
    }
    public final CertificatePinner m1802deprecated_certificatePinner() {
        return certificatePinner;
    }
    public final int m1801deprecated_callTimeoutMillis() {
        return callTimeoutMillis;
    }
    public final int m1803deprecated_connectTimeoutMillis() {
        return connectTimeoutMillis;
    }
    public final int m1820deprecated_readTimeoutMillis() {
        return readTimeoutMillis;
    }
    public final int m1824deprecated_writeTimeoutMillis() {
        return writeTimeoutMillis;
    }
    public final int m1815deprecated_pingIntervalMillis() {
        return pingIntervalMillis;
    }
    public static final class Builder {
        private final List<Interceptor> interceptors;
        private final List<Interceptor> networkInterceptors;
      private final Object call = null;
      private Authenticator authenticator;
        private Cache cache;
        private int callTimeout;
        private CertificateChainCleaner certificateChainCleaner;
        private CertificatePinner certificatePinner;
        private int connectTimeout;
        private ConnectionPool connectionPool;
        private List<ConnectionSpec> connectionSpecs;
        private CookieJar cookieJar;
        private Dispatcher dispatcher;
        private Dns dns;
        private EventListener.Factory eventListenerFactory;
        private boolean followRedirects;
        private boolean followSslRedirects;
        private HostnameVerifier hostnameVerifier;
        private long minWebSocketMessageToCompress;
        private int pingInterval;
        private List<Protocol> protocols;
        private Proxy proxy;
        private Authenticator proxyAuthenticator;
        private ProxySelector proxySelector;
        private int readTimeout;
        private boolean retryOnConnectionFailure;
        private RouteDatabase routeDatabase;
        private SocketFactory socketFactory;
        private SSLSocketFactory sslSocketFactoryOrNull;
        private int writeTimeout;
        private X509TrustManager x509TrustManagerOrNull;
        public Builder() {
            dispatcher = new Dispatcher();
            connectionPool = new ConnectionPool();
            interceptors = new ArrayList();
            networkInterceptors = new ArrayList();
            eventListenerFactory = Util.asFactory(EventListener.NONE, call);
            retryOnConnectionFailure = true;
            final Authenticator authenticator = Authenticator.NONE;
            this.authenticator = authenticator;
            followRedirects = true;
            followSslRedirects = true;
            cookieJar = CookieJar.NO_COOKIES;
            dns = Dns.SYSTEM;
            proxyAuthenticator = authenticator;
            final SocketFactory socketFactory = SocketFactory.getDefault();
            checkNotNullExpressionValue(socketFactory, "getDefault()");
            this.socketFactory = socketFactory;
            final Companion companion = Companion;
            connectionSpecs = companion.getDEFAULT_CONNECTION_SPECSokhttp();
            protocols = companion.getDEFAULT_PROTOCOLSokhttp();
            hostnameVerifier = OkHostnameVerifier.INSTANCE;
            certificatePinner = CertificatePinner.DEFAULT;
            connectTimeout = 10000;
            readTimeout = 10000;
            writeTimeout = 10000;
            minWebSocketMessageToCompress = RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
        }
        public Builder(final OkHttpClient okHttpClient) {
            this();
            checkNotNullParameter(okHttpClient, "okHttpClient");
            dispatcher = okHttpClient.dispatcher();
            connectionPool = okHttpClient.connectionPool();
            CollectionsKt.addAll(interceptors, okHttpClient.interceptors());
            CollectionsKt.addAll(networkInterceptors, okHttpClient.networkInterceptors());
            eventListenerFactory = okHttpClient.eventListenerFactory();
            retryOnConnectionFailure = okHttpClient.retryOnConnectionFailure();
            authenticator = okHttpClient.authenticator();
            followRedirects = okHttpClient.followRedirects();
            followSslRedirects = okHttpClient.followSslRedirects();
            cookieJar = okHttpClient.cookieJar();
            cache = okHttpClient.cache();
            dns = okHttpClient.dns();
            proxy = okHttpClient.proxy();
            proxySelector = okHttpClient.proxySelector();
            proxyAuthenticator = okHttpClient.proxyAuthenticator();
            socketFactory = okHttpClient.socketFactory();
            sslSocketFactoryOrNull = okHttpClient.sslSocketFactoryOrNull;
            x509TrustManagerOrNull = okHttpClient.x509TrustManager();
            connectionSpecs = okHttpClient.connectionSpecs();
            protocols = okHttpClient.protocols();
            hostnameVerifier = okHttpClient.hostnameVerifier();
            certificatePinner = okHttpClient.certificatePinner();
            certificateChainCleaner = okHttpClient.certificateChainCleaner();
            callTimeout = okHttpClient.callTimeoutMillis();
            connectTimeout = okHttpClient.connectTimeoutMillis();
            readTimeout = okHttpClient.readTimeoutMillis();
            writeTimeout = okHttpClient.writeTimeoutMillis();
            pingInterval = okHttpClient.pingIntervalMillis();
            minWebSocketMessageToCompress = okHttpClient.minWebSocketMessageToCompress();
            routeDatabase = okHttpClient.getRouteDatabase();
        }

        public Dispatcher getDispatcherokhttp() {
            return dispatcher;
        }

        public void setDispatcherokhttp(final Dispatcher dispatcher) {
            checkNotNullParameter(dispatcher, "<set-?>");
            this.dispatcher = dispatcher;
        }

        public ConnectionPool getConnectionPoolokhttp() {
            return connectionPool;
        }

        public void setConnectionPoolokhttp(final ConnectionPool connectionPool) {
            checkNotNullParameter(connectionPool, "<set-?>");
            this.connectionPool = connectionPool;
        }

        public List<Interceptor> getInterceptorsokhttp() {
            return interceptors;
        }

        public List<Interceptor> getNetworkInterceptorsokhttp() {
            return networkInterceptors;
        }

        public EventListener.Factory getEventListenerFactoryokhttp() {
            return eventListenerFactory;
        }

        public void setEventListenerFactoryokhttp(final EventListener.Factory factory) {
            checkNotNullParameter(factory, "<set-?>");
            eventListenerFactory = factory;
        }

        public boolean getRetryOnConnectionFailureokhttp() {
            return retryOnConnectionFailure;
        }

        public void setRetryOnConnectionFailureokhttp(final boolean z) {
            retryOnConnectionFailure = z;
        }

        public Authenticator getAuthenticatorokhttp() {
            return authenticator;
        }

        public void setAuthenticatorokhttp(final Authenticator authenticator) {
            checkNotNullParameter(authenticator, "<set-?>");
            this.authenticator = authenticator;
        }

        public boolean getFollowRedirectsokhttp() {
            return followRedirects;
        }

        public void setFollowRedirectsokhttp(final boolean z) {
            followRedirects = z;
        }

        public boolean getFollowSslRedirectsokhttp() {
            return followSslRedirects;
        }

        public void setFollowSslRedirectsokhttp(final boolean z) {
            followSslRedirects = z;
        }

        public CookieJar getCookieJarokhttp() {
            return cookieJar;
        }

        public void setCookieJarokhttp(final CookieJar cookieJar) {
            checkNotNullParameter(cookieJar, "<set-?>");
            this.cookieJar = cookieJar;
        }

        public Cache getCacheokhttp() {
            return cache;
        }

        public void setCacheokhttp(final Cache cache) {
            this.cache = cache;
        }

        public Dns getDnsokhttp() {
            return dns;
        }

        public void setDnsokhttp(final Dns dns) {
            checkNotNullParameter(dns, "<set-?>");
            this.dns = dns;
        }

        public Proxy getProxyokhttp() {
            return proxy;
        }

        public void setProxyokhttp(final Proxy proxy) {
            this.proxy = proxy;
        }

        public ProxySelector getProxySelectorokhttp() {
            return proxySelector;
        }

        public void setProxySelectorokhttp(final ProxySelector proxySelector) {
            this.proxySelector = proxySelector;
        }

        public Authenticator getProxyAuthenticatorokhttp() {
            return proxyAuthenticator;
        }

        public void setProxyAuthenticatorokhttp(final Authenticator authenticator) {
            checkNotNullParameter(authenticator, "<set-?>");
            proxyAuthenticator = authenticator;
        }

        public SocketFactory getSocketFactoryokhttp() {
            return socketFactory;
        }

        public void setSocketFactoryokhttp(final SocketFactory socketFactory) {
            checkNotNullParameter(socketFactory, "<set-?>");
            this.socketFactory = socketFactory;
        }

        public SSLSocketFactory getSslSocketFactoryOrNullokhttp() {
            return sslSocketFactoryOrNull;
        }

        public void setSslSocketFactoryOrNullokhttp(final SSLSocketFactory sSLSocketFactory) {
            sslSocketFactoryOrNull = sSLSocketFactory;
        }

        public X509TrustManager getX509TrustManagerOrNullokhttp() {
            return x509TrustManagerOrNull;
        }

        public void setX509TrustManagerOrNullokhttp(final X509TrustManager x509TrustManager) {
            x509TrustManagerOrNull = x509TrustManager;
        }

        public List<ConnectionSpec> getConnectionSpecsokhttp() {
            return connectionSpecs;
        }

        public void setConnectionSpecsokhttp(final List<ConnectionSpec> list) {
            checkNotNullParameter(list, "<set-?>");
            connectionSpecs = list;
        }

        public List<Protocol> getProtocolsokhttp() {
            return protocols;
        }

        public void setProtocolsokhttp(final List<Protocol> list) {
            checkNotNullParameter(list, "<set-?>");
            protocols = list;
        }

        public HostnameVerifier getHostnameVerifierokhttp() {
            return hostnameVerifier;
        }

        public void setHostnameVerifierokhttp(final HostnameVerifier hostnameVerifier) {
            checkNotNullParameter(hostnameVerifier, "<set-?>");
            this.hostnameVerifier = hostnameVerifier;
        }

        public CertificatePinner getCertificatePinnerokhttp() {
            return certificatePinner;
        }

        public void setCertificatePinnerokhttp(final CertificatePinner certificatePinner) {
            checkNotNullParameter(certificatePinner, "<set-?>");
            this.certificatePinner = certificatePinner;
        }

        public CertificateChainCleaner getCertificateChainCleanerokhttp() {
            return certificateChainCleaner;
        }

        public void setCertificateChainCleanerokhttp(final CertificateChainCleaner certificateChainCleaner) {
            this.certificateChainCleaner = certificateChainCleaner;
        }

        public int getCallTimeoutokhttp() {
            return callTimeout;
        }

        public void setCallTimeoutokhttp(final int i2) {
            callTimeout = i2;
        }

        public int getConnectTimeoutokhttp() {
            return connectTimeout;
        }

        public void setConnectTimeoutokhttp(final int i2) {
            connectTimeout = i2;
        }

        public int getReadTimeoutokhttp() {
            return readTimeout;
        }

        public void setReadTimeoutokhttp(final int i2) {
            readTimeout = i2;
        }

        public int getWriteTimeoutokhttp() {
            return writeTimeout;
        }

        public void setWriteTimeoutokhttp(final int i2) {
            writeTimeout = i2;
        }

        public int getPingIntervalokhttp() {
            return pingInterval;
        }

        public void setPingIntervalokhttp(final int i2) {
            pingInterval = i2;
        }

        public long getMinWebSocketMessageToCompressokhttp() {
            return minWebSocketMessageToCompress;
        }

        public void setMinWebSocketMessageToCompressokhttp(final long j2) {
            minWebSocketMessageToCompress = j2;
        }

        public RouteDatabase getRouteDatabaseokhttp() {
            return routeDatabase;
        }

        public void setRouteDatabaseokhttp(final RouteDatabase routeDatabase) {
            this.routeDatabase = routeDatabase;
        }

        public Builder dispatcher(final Dispatcher dispatcher) {
            checkNotNullParameter(dispatcher, "dispatcher");
            this.setDispatcherokhttp(dispatcher);
            return this;
        }

        public Builder connectionPool(final ConnectionPool connectionPool) {
            checkNotNullParameter(connectionPool, "connectionPool");
            this.setConnectionPoolokhttp(connectionPool);
            return this;
        }

        public List<Interceptor> interceptors() {
            return interceptors;
        }

        public Builder addInterceptor(final Interceptor interceptor) {
            checkNotNullParameter(interceptor, "interceptor");
            this.interceptors.add(interceptor);
            return this;
        }
        public Builder m1825addInterceptor(Function1<? super Interceptor.Chain, Response> block) {
            checkNotNullParameter(block, "block");
            return this.addInterceptor(new Interceptor() { // from class: okhttp3.OkHttpClientBuilderaddInterceptor2
                @Override // okhttp3.Interceptor
                public Response intercept(final Chain chain) {
                    checkNotNullParameter(chain, "chain");
                    return block.invoke(chain);
                }
            });
        }
        public List<Interceptor> networkInterceptors() {
            return networkInterceptors;
        }
        public Builder addNetworkInterceptor(final Interceptor interceptor) {
            checkNotNullParameter(interceptor, "interceptor");
            this.networkInterceptors.add(interceptor);
            return this;
        }
        public Builder m1826addNetworkInterceptor(Function1<? super Interceptor.Chain, Response> block) {
            checkNotNullParameter(block, "block");
            return this.addNetworkInterceptor(new Interceptor() { // from class: okhttp3.OkHttpClientBuilderaddNetworkInterceptor2
                @Override // okhttp3.Interceptor
                public Response intercept(final Chain chain) {
                    checkNotNullParameter(chain, "chain");
                    return block.invoke(chain);
                }
            });
        }
        public Builder eventListener(final EventListener eventListener) {
            checkNotNullParameter(eventListener, "eventListener");
          Object call = null;
          this.setEventListenerFactoryokhttp(Util.asFactory(eventListener, call));
            return this;
        }
        public Builder eventListenerFactory(final EventListener.Factory eventListenerFactory) {
            checkNotNullParameter(eventListenerFactory, "eventListenerFactory");
            this.setEventListenerFactoryokhttp(eventListenerFactory);
            return this;
        }

        public Builder retryOnConnectionFailure(final boolean z) {
            this.retryOnConnectionFailure = z;
            return this;
        }

        public Builder authenticator(final Authenticator authenticator) {
            checkNotNullParameter(authenticator, "authenticator");
            this.setAuthenticatorokhttp(authenticator);
            return this;
        }

        public Builder followRedirects(final boolean z) {
            this.followRedirects = z;
            return this;
        }

        public Builder followSslRedirects(final boolean z) {
            this.followSslRedirects = z;
            return this;
        }

        public Builder cookieJar(final CookieJar cookieJar) {
            checkNotNullParameter(cookieJar, "cookieJar");
            this.setCookieJarokhttp(cookieJar);
            return this;
        }

        public Builder cache(final Cache cache) {
            this.cache = cache;
            return this;
        }

        public Builder dns(final Dns dns) {
            checkNotNullParameter(dns, "dns");
            if (!areEqual(dns, this.dns)) {
                this.routeDatabase = null;
            }
            this.setDnsokhttp(dns);
            return this;
        }

        public Builder proxy(final Proxy proxy) {
            if (!areEqual(proxy, this.proxy)) {
                this.routeDatabase = null;
            }
            this.proxy = proxy;
            return this;
        }

        public Builder proxySelector(final ProxySelector proxySelector) {
            checkNotNullParameter(proxySelector, "proxySelector");
            if (!areEqual(proxySelector, this.proxySelector)) {
                this.routeDatabase = null;
            }
            this.proxySelector = proxySelector;
            return this;
        }

        public Builder proxyAuthenticator(final Authenticator proxyAuthenticator) {
            checkNotNullParameter(proxyAuthenticator, "proxyAuthenticator");
            if (!areEqual(proxyAuthenticator, this.proxyAuthenticator)) {
                this.routeDatabase = null;
            }
            this.setProxyAuthenticatorokhttp(proxyAuthenticator);
            return this;
        }

        public Builder socketFactory(final SocketFactory socketFactory) {
            checkNotNullParameter(socketFactory, "socketFactory");
            if (socketFactory instanceof SSLSocketFactory) {
                throw new IllegalArgumentException("socketFactory instanceof SSLSocketFactory");
            }
            if (!areEqual(socketFactory, this.socketFactory)) {
                this.routeDatabase = null;
            }
            this.setSocketFactoryokhttp(socketFactory);
            return this;
        }

        public Builder sslSocketFactory(final SSLSocketFactory sslSocketFactory) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
            checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
            if (!areEqual(sslSocketFactory, this.sslSocketFactoryOrNull)) {
                this.routeDatabase = null;
            }
            this.sslSocketFactoryOrNull = sslSocketFactory;
            final Platform.Companion companion = Platform.Companion;
            final X509TrustManager x509TrustManagerTrustManager = companion.get().trustManager(sslSocketFactory);
            if (null == x509TrustManagerTrustManager) {
                throw new IllegalStateException("Unable to extract the trust manager on " + companion.get() + ", sslSocketFactory is " + sslSocketFactory.getClass());
            }
            this.x509TrustManagerOrNull = x509TrustManagerTrustManager;
            final Platform platform = companion.get();
            final X509TrustManager x509TrustManagerOrNullokhttp = this.x509TrustManagerOrNull;
            checkNotNull(x509TrustManagerOrNullokhttp);
            this.certificateChainCleaner = platform.buildCertificateChainCleaner(x509TrustManagerOrNullokhttp);
            return this;
        }

        public Builder sslSocketFactory(final SSLSocketFactory sslSocketFactory, final X509TrustManager trustManager) {
            checkNotNullParameter(sslSocketFactory, "sslSocketFactory");
            checkNotNullParameter(trustManager, "trustManager");
            if (!areEqual(sslSocketFactory, this.sslSocketFactoryOrNull) || !areEqual(trustManager, this.x509TrustManagerOrNull)) {
                this.routeDatabase = null;
            }
            this.sslSocketFactoryOrNull = sslSocketFactory;
            this.certificateChainCleaner = CertificateChainCleaner.Companion.get(trustManager);
            this.x509TrustManagerOrNull = trustManager;
            return this;
        }

        public Builder connectionSpecs(final List<ConnectionSpec> connectionSpecs) {
            checkNotNullParameter(connectionSpecs, "connectionSpecs");
            if (!areEqual(connectionSpecs, this.connectionSpecs)) {
                this.routeDatabase = null;
            }
            this.setConnectionSpecsokhttp(Util.toImmutableList(connectionSpecs));
            return this;
        }

        public Builder protocols(final List<Protocol> protocols) {
            checkNotNullParameter(protocols, "protocols");
            final List mutableList = CollectionsKt.toMutableList((Collection) protocols);
            final Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            if (!mutableList.contains(protocol) && !mutableList.contains(Protocol.HTTP_1_1)) {
                throw new IllegalArgumentException(stringPlus("protocols must contain h2_prior_knowledge or http/1.1: ", mutableList));
            }
            if (mutableList.contains(protocol) && 1 < mutableList.size()) {
                throw new IllegalArgumentException(stringPlus("protocols containing h2_prior_knowledge cannot use other protocols: ", mutableList));
            }
            if (mutableList.contains(Protocol.HTTP_1_0)) {
                throw new IllegalArgumentException(stringPlus("protocols must not contain http/1.0: ", mutableList));
            }
            if (mutableList.contains(null)) {
                throw new IllegalArgumentException("protocols must not contain null");
            }
            mutableList.remove(Protocol.SPDY_3);
            if (!areEqual(mutableList, protocols)) {
                this.routeDatabase = null;
            }
            final List<Protocol> listUnmodifiableList = Collections.unmodifiableList(mutableList);
            checkNotNullExpressionValue(listUnmodifiableList, "unmodifiableList(protocolsCopy)");
            this.setProtocolsokhttp(listUnmodifiableList);
            return this;
        }

        public Builder hostnameVerifier(final HostnameVerifier hostnameVerifier) {
            checkNotNullParameter(hostnameVerifier, "hostnameVerifier");
            if (!areEqual(hostnameVerifier, this.hostnameVerifier)) {
                this.routeDatabase = null;
            }
            this.setHostnameVerifierokhttp(hostnameVerifier);
            return this;
        }

        public Builder certificatePinner(final CertificatePinner certificatePinner) {
            checkNotNullParameter(certificatePinner, "certificatePinner");
            if (!areEqual(certificatePinner, this.certificatePinner)) {
                this.routeDatabase = null;
            }
            this.setCertificatePinnerokhttp(certificatePinner);
            return this;
        }

        public Builder callTimeout(final long j2, final TimeUnit unit) {
            checkNotNullParameter(unit, "unit");
            this.callTimeout = Util.checkDuration("timeout", j2, unit);
            return this;
        }
        public Builder callTimeout(final Duration duration) {
            checkNotNullParameter(duration, "duration");
            this.callTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public Builder connectTimeout(final long j2, final TimeUnit unit) {
            checkNotNullParameter(unit, "unit");
            this.connectTimeout = Util.checkDuration("timeout", j2, unit);
            return this;
        }
        public Builder connectTimeout(final Duration duration) {
            checkNotNullParameter(duration, "duration");
            this.connectTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public Builder readTimeout(final long j2, final TimeUnit unit) {
            checkNotNullParameter(unit, "unit");
            this.readTimeout = Util.checkDuration("timeout", j2, unit);
            return this;
        }
        public Builder readTimeout(final Duration duration) {
            checkNotNullParameter(duration, "duration");
            this.readTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public Builder writeTimeout(final long j2, final TimeUnit unit) {
            checkNotNullParameter(unit, "unit");
            this.writeTimeout = Util.checkDuration("timeout", j2, unit);
            return this;
        }
        public Builder writeTimeout(final Duration duration) {
            checkNotNullParameter(duration, "duration");
            this.writeTimeout(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public Builder pingInterval(final long j2, final TimeUnit unit) {
            checkNotNullParameter(unit, "unit");
            this.pingInterval = Util.checkDuration("interval", j2, unit);
            return this;
        }
        public Builder pingInterval(final Duration duration) {
            checkNotNullParameter(duration, "duration");
            this.pingInterval(duration.toMillis(), TimeUnit.MILLISECONDS);
            return this;
        }

        public Builder minWebSocketMessageToCompress(final long j2) {
            if (0 > j2) {
                throw new IllegalArgumentException(stringPlus("minWebSocketMessageToCompress must be positive: ", Long.valueOf(j2)));
            }
            this.minWebSocketMessageToCompress = j2;
            return this;
        }

        public OkHttpClient build() {
            try {
                return new OkHttpClient(this);
            } catch (NoSuchAlgorithmException | KeyStoreException e) {
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

        public List<Protocol> getDEFAULT_PROTOCOLSokhttp() {
            return DEFAULT_PROTOCOLS;
        }

        public List<ConnectionSpec> getDEFAULT_CONNECTION_SPECSokhttp() {
            return DEFAULT_CONNECTION_SPECS;
        }
    }
}
