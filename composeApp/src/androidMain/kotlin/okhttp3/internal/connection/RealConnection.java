package okhttp3.internal.connection;

import androidx.core.location.LocationRequestCompat;
import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http1.Http1ExchangeCodec;
import okhttp3.internal.http2.*;
import okhttp3.internal.ws.RealWebSocket;
import okhttp3.internal.platform.Platform;
import okhttp3.internal.tls.CertificateChainCleaner;
import okhttp3.internal.tls.OkHostnameVerifier;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Timeout;
import org.springframework.http.HttpHeaders;

import javax.net.ssl.*;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class RealConnection extends Http2Connection.Listener implements Connection {
    public static final Companion Companion = new Companion(null);
    public static final long IDLE_CONNECTION_HEALTHY_NS = 10000000000L;
    private static final int MAX_TUNNEL_ATTEMPTS = 21;
    private static final String NPE_THROW_WITH_NULL = "throw with null exception";
    private int allocationLimit;
    private final List<Reference<RealCall>> calls;
    private final RealConnectionPool connectionPool;
    private static Handshake handshake;
    private Http2Connection http2Connection;
    private long idleAtNs;
    private boolean noCoalescedConnections;
    private boolean noNewExchanges;
    private Protocol protocol;
    private Socket rawSocket;
    private int refusedStreamCount;
    private final Route route;
    private int routeFailureCount;
    private BufferedSink sink;
    private Socket socket;
    private BufferedSource source;
    private int successCount;
    private Throwable th;

    public RealConnection(RealConnectionPool connectionPool, Route route) {
        Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
        Intrinsics.checkNotNullParameter(route, "route");
        this.connectionPool = connectionPool;
        this.route = route;
        this.allocationLimit = 1;
        this.calls = new ArrayList();
        this.idleAtNs = LocationRequestCompat.PASSIVE_INTERVAL;
    }
    public RealConnectionPool getConnectionPool() {
        return this.connectionPool;
    }
    public boolean getNoNewExchanges() {
        return this.noNewExchanges;
    }
    public void setNoNewExchanges(boolean z) {
        this.noNewExchanges = z;
    }
    public int getRouteFailureCountokhttp() {
        return this.routeFailureCount;
    }
    public void setRouteFailureCountokhttp(int i2) {
        this.routeFailureCount = i2;
    }
    public List<Reference<RealCall>> getCalls() {
        return this.calls;
    }
    public long getIdleAtNsokhttp() {
        return this.idleAtNs;
    }
    public void setIdleAtNsokhttp(long j2) {
        this.idleAtNs = j2;
    }
    public boolean isMultiplexedokhttp() {
        return null != http2Connection;
    }
    public synchronized void noNewExchangesokhttp() {
        this.noNewExchanges = true;
    }
    public synchronized void noCoalescedConnectionsokhttp() {
        this.noCoalescedConnections = true;
    }
    public synchronized void incrementSuccessCountokhttp() {
        this.successCount++;
    }
    public void connect(int i2, int i3, int i4, int i5, boolean z, Call<T> call, EventListener eventListener) throws Throwable {
        Socket socket;
        Socket socket2;
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        if (null != protocol) {
            throw new IllegalStateException("already connected");
        }
        List<ConnectionSpec> listConnectionSpecs = this.route.address().connectionSpecs();
        ConnectionSpecSelector connectionSpecSelector = new ConnectionSpecSelector(listConnectionSpecs);
        if (null == route.address().sslSocketFactory()) {
            if (!listConnectionSpecs.contains(ConnectionSpec.CLEARTEXT)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication not enabled for client"));
            }
            String strHost = this.route.address().url().host();
            if (!Platform.Companion.get().isCleartextTrafficPermitted(strHost)) {
                throw new RouteException(new UnknownServiceException("CLEARTEXT communication to " + strHost + " not permitted by network security policy"));
            }
        } else if (this.route.address().protocols().contains(Protocol.H2_PRIOR_KNOWLEDGE)) {
            throw new RouteException(new UnknownServiceException("H2_PRIOR_KNOWLEDGE cannot be used with HTTPS"));
        }
        RouteException routeException = null;
        IOException e;
        do {
            try {
                try {
                    if (this.route.requiresTunnel()) {
                        connectTunnel(i2, i3, i4, call, eventListener);
                        if (null == rawSocket) {
                            if (!this.route.requiresTunnel() && null == rawSocket) {
                                throw new RouteException(new ProtocolException("Too many tunnel connections attempted: 21"));
                            }
                            this.idleAtNs = System.nanoTime();
                            return;
                        }
                    } else {
                        try {
                            connectSocket(i2, i3, call, eventListener);
                        } catch (IOException e2) {
                            e = e2;
                            socket = this.socket;
                            if (null != socket) {
                                Util.closeQuietly(socket);
                            }
                            socket2 = this.rawSocket;
                            if (null != socket2) {
                                Util.closeQuietly(socket2);
                            }
                            this.socket = null;
                            this.rawSocket = null;
                            this.source = null;
                            this.sink = null;
                            handshake = null;
                            this.protocol = null;
                            this.http2Connection = null;
                            this.allocationLimit = 1;
                            eventListener.connectFailed(call, this.route.socketAddress(), this.route.proxy(), null, e);
                            if (null != routeException) {
                                routeException = new RouteException(e);
                            } else {
                                routeException.addConnectException(e);
                            }
                            if (z) {
                                throw routeException;
                            }
                        }
                    }
                    establishProtocol(connectionSpecSelector, i5, call, eventListener);
                    eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), this.protocol);
                    if (!this.route.requiresTunnel()) {
                    }
                    this.idleAtNs = System.nanoTime();
                    return;
                } catch (IOException e3) {
                    e = e3;
                    socket = this.socket;
                    if (null != socket) {
                    }
                    socket2 = this.rawSocket;
                    if (null != socket2) {
                    }
                    this.socket = null;
                    this.rawSocket = null;
                    this.source = null;
                    this.sink = null;
                    handshake = null;
                    this.protocol = null;
                    this.http2Connection = null;
                    this.allocationLimit = 1;
                    eventListener.connectFailed(call, this.route.socketAddress(), this.route.proxy(), null, e);
                    if (null != routeException) {
                    }
                    if (z) {
                    }
                }
            } catch (IOException e4) {
                e = e4;
            }
        } while (connectionSpecSelector.connectionFailed(e));
        throw routeException;
    }
    private void connectTunnel(int i2, int i3, int i4, Call<T> call, EventListener eventListener) throws IOException {
        Request requestCreateTunnelRequest = createTunnelRequest();
        HttpUrl httpUrlUrl = requestCreateTunnelRequest.url();
        int i5 = 0;
        while (21 > i5) {
            i5++;
            connectSocket(i2, i3, call, eventListener);
            requestCreateTunnelRequest = createTunnel(i3, i4, requestCreateTunnelRequest, httpUrlUrl);
            if (null == requestCreateTunnelRequest) {
                return;
            }
            Socket socket = this.rawSocket;
            if (null != socket) {
                Util.closeQuietly(socket);
            }
            this.rawSocket = null;
            this.sink = null;
            this.source = null;
            eventListener.connectEnd(call, this.route.socketAddress(), this.route.proxy(), null);
        }
    }
    private void connectSocket(int i2, int i3, Call<T> call, EventListener eventListener) throws IOException {
        Socket socketCreateSocket;
        Proxy proxy = this.route.proxy();
        Address address = this.route.address();
        Proxy.Type type = proxy.type();
        int i4 = null == type ? -1 : WhenMappings.EnumSwitchMapping0[type.ordinal()];
        if (1 == i4 || 2 == i4) {
            socketCreateSocket = address.socketFactory().createSocket();
            Intrinsics.checkNotNull(socketCreateSocket);
        } else {
            socketCreateSocket = new Socket(proxy);
        }
        this.rawSocket = socketCreateSocket;
        eventListener.connectStart(call, this.route.socketAddress(), proxy);
        socketCreateSocket.setSoTimeout(i3);
        try {
            Platform.Companion.get().connectSocket(socketCreateSocket, this.route.socketAddress(), i2);
            try {
                this.source = Okio.buffer(Okio.source(socketCreateSocket));
                this.sink = Okio.buffer(Okio.sink(socketCreateSocket));
            } catch (NullPointerException e2) {
                if (Intrinsics.areEqual(e2.getMessage(), NPE_THROW_WITH_NULL)) {
                    throw new IOException(e2);
                }
            }
        } catch (ConnectException e3) {
            ConnectException connectException = new ConnectException(Intrinsics.stringPlus("Failed to connect to ", this.route.socketAddress()));
            connectException.initCause(e3);
            throw connectException;
        } catch (IOException e) {
            throw e;
        }
    }
    private void establishProtocol(ConnectionSpecSelector connectionSpecSelector, int i2, Call<T> call, EventListener eventListener) throws Throwable {
        if (null == route.address().sslSocketFactory()) {
            List<Protocol> listProtocols = this.route.address().protocols();
            final Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
            if (listProtocols.contains(protocol)) {
                this.socket = this.rawSocket;
                this.protocol = protocol;
                startHttp2(i2);
                return;
            } else {
                this.socket = this.rawSocket;
                this.protocol = Protocol.HTTP_1_1;
                return;
            }
        }
        eventListener.secureConnectStart(call);
        connectTls(connectionSpecSelector);
        eventListener.secureConnectEnd(call, handshake);
        if (Protocol.HTTP_2 == protocol) {
            startHttp2(i2);
        }
    }
    private void startHttp2(int i2) throws IOException {
        Socket socket = this.socket;
        Intrinsics.checkNotNull(socket);
        BufferedSource bufferedSource = this.source;
        Intrinsics.checkNotNull(bufferedSource);
        BufferedSink bufferedSink = this.sink;
        Intrinsics.checkNotNull(bufferedSink);
        socket.setSoTimeout(0);
        Http2Connection http2ConnectionBuild = new Http2Connection.Builder(true, TaskRunner.INSTANCE).socket(socket, this.route.address().url().host(), bufferedSource, bufferedSink).listener(this).pingIntervalMillis(i2).build();
        this.http2Connection = http2ConnectionBuild;
        this.allocationLimit = Http2Connection.Companion.getDEFAULT_SETTINGS().getMaxConcurrentStreams();
        Http2Connection.startdefault(http2ConnectionBuild, false, null, 3, null);
    }
    private  void connectTls(ConnectionSpecSelector connectionSpecSelector) throws Throwable {
        final Address address = this.route.address();
        SSLSocketFactory sslSocketFactory = address.sslSocketFactory();
        SSLSocket sSLSocket = null;
        try {
            Intrinsics.checkNotNull(sslSocketFactory);
            Socket socketCreateSocket = sslSocketFactory.createSocket(this.rawSocket, address.url().host(), address.url().port(), true);
            if (socketCreateSocket == null) {
                throw new NullPointerException("null cannot be cast to non-null type javax.net.ssl.SSLSocket");
            }
            SSLSocket sSLSocket2 = (SSLSocket) socketCreateSocket;
            try {
                ConnectionSpec connectionSpecConfigureSecureSocket = connectionSpecSelector.configureSecureSocket(sSLSocket2);
                if (connectionSpecConfigureSecureSocket.supportsTlsExtensions()) {
                    Platform.Companion.get().configureTlsExtensions(sSLSocket2, address.url().host(), address.protocols());
                }
                sSLSocket2.startHandshake();
                SSLSession sslSocketSession = sSLSocket2.getSession();
                Handshake.Companion companion = Handshake.Companion;
                Intrinsics.checkNotNullExpressionValue(sslSocketSession, "sslSocketSession");
                final Handshake handshake = companion.get(sslSocketSession);
                HostnameVerifier hostnameVerifier = address.hostnameVerifier();
                Intrinsics.checkNotNull(hostnameVerifier);
                if (hostnameVerifier.verify(address.url().host(), sslSocketSession)) {
                    final CertificatePinner certificatePinner = address.certificatePinner();
                    Intrinsics.checkNotNull(certificatePinner);
                    RealConnection.handshake = new Handshake(handshake.tlsVersion(), handshake.cipherSuite(), handshake.localCertificates(), new Function0<List<? extends Certificate>>() {
     
 
                        public List<? extends Certificate> invoke() {
                            CertificateChainCleaner certificateChainCleaner$okhttp = certificatePinner.getCertificateChainCleanerokhttp();
                            Intrinsics.checkNotNull(certificateChainCleaner$okhttp);
                            try {
                                return certificateChainCleaner$okhttp.clean(handshake.peerCertificates(), address.url().host());
                            } catch (SSLPeerUnverifiedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                    certificatePinner.checkokhttp(address.url().host(), new Function0<List<? extends X509Certificate>>() {   
                        public List<? extends X509Certificate> invoke() {
                            Handshake handshake2 = RealConnection.handshake;
                            Intrinsics.checkNotNull(handshake2);
                            List<Certificate> listPeerCertificates = handshake2.peerCertificates();
                            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPeerCertificates, 10));
                            Iterator<Certificate> it = listPeerCertificates.iterator();
                            while (it.hasNext()) {
                                arrayList.add(it.next());
                            }
                            return arrayList;
                        }
                    });
                    String selectedProtocol = connectionSpecConfigureSecureSocket.supportsTlsExtensions() ? Platform.Companion.get().getSelectedProtocol(sSLSocket2) : null;
                    this.socket = sSLSocket2;
                    this.source = Okio.buffer(Okio.source(sSLSocket2));
                    this.sink = Okio.buffer(Okio.sink(sSLSocket2));
                    this.protocol = selectedProtocol != null ? Protocol.Companion.get(selectedProtocol) : Protocol.HTTP_1_1;
                    Platform.Companion.get().afterHandshake(sSLSocket2);
                    return;
                }
                List<Certificate> listPeerCertificates = handshake.peerCertificates();
                if (listPeerCertificates.isEmpty()) {
                    throw new SSLPeerUnverifiedException("Hostname " + address.url().host() + " not verified (no certificates)");
                }
                X509Certificate x509Certificate = (X509Certificate) listPeerCertificates.get(0);
                throw new SSLPeerUnverifiedException(StringsKt.trimMargin("\n              |Hostname " + address.url().host() + " not verified:\n              |    certificate: " + CertificatePinner.Companion.pin(x509Certificate) + "\n              |    DN: " + x509Certificate.getSubjectDN().getName() + "\n              |    subjectAltNames: " + OkHostnameVerifier.INSTANCE.allSubjectAltNames(x509Certificate) + "\n              ", null));
            } catch (Throwable th) {
                th = th;
                sSLSocket = sSLSocket2;
                if (sSLSocket != null) {
                    Platform.Companion.get().afterHandshake(sSLSocket);
                }
                if (sSLSocket != null) {
                    Util.closeQuietly(sSLSocket);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
    private Request createTunnel(int i2, int i3, Request request, HttpUrl httpUrl) throws IOException {
        String str = "CONNECT " + Util.toHostHeader(httpUrl, true) + " HTTP/1.1";
        while (true) {
            BufferedSource bufferedSource = this.source;
            Intrinsics.checkNotNull(bufferedSource);
            BufferedSink bufferedSink = this.sink;
            Intrinsics.checkNotNull(bufferedSink);
            Http1ExchangeCodec http1ExchangeCodec = new Http1ExchangeCodec(null, this, bufferedSource, bufferedSink);
            final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            bufferedSource.timeout().timeout(i2, timeUnit);
            bufferedSink.timeout().timeout(i3, timeUnit);
            http1ExchangeCodec.writeRequest(request.headers(), str);
            http1ExchangeCodec.finishRequest();
            Response.Builder responseHeaders = http1ExchangeCodec.readResponseHeaders(false);
            Intrinsics.checkNotNull(responseHeaders);
            Response responseBuild = responseHeaders.request(request).build();
            http1ExchangeCodec.skipConnectBody(responseBuild);
            int iCode = responseBuild.code();
            if (200 == iCode) {
                if (bufferedSource.getBuffer().exhausted() && bufferedSink.getBuffer().exhausted()) {
                    return null;
                }
                throw new IOException("TLS tunnel buffered too many bytes!");
            }
            if (407 == iCode) {
                Request requestAuthenticate = this.route.address().proxyAuthenticator().authenticate(this.route, responseBuild);
                if (null == requestAuthenticate) {
                    throw new IOException("Failed to authenticate with proxy");
                }
                if (StringsKt.equals("close", Response.headerdefault(responseBuild, HttpHeaders.CONNECTION, null, 2, null), true)) {
                    return requestAuthenticate;
                }
                request = requestAuthenticate;
            } else {
                throw new IOException(Intrinsics.stringPlus("Unexpected response code for CONNECT: ", Integer.valueOf(responseBuild.code())));
            }
        }
    }
    private Request createTunnelRequest() throws IOException {
        Request requestBuild = new Request.Builder().url(this.route.address().url()).method("CONNECT", null).header(HttpHeaders.HOST, Util.toHostHeader(this.route.address().url(), true)).header("Proxy-Connection", "Keep-Alive").header(HttpHeaders.USER_AGENT, Util.userAgent).build();
        Request requestAuthenticate = this.route.address().proxyAuthenticator().authenticate(this.route, new Response.Builder().request(requestBuild).protocol(Protocol.HTTP_1_1).code(407).message("Preemptive Authenticate").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(-1L).header(HttpHeaders.PROXY_AUTHENTICATE, "OkHttp-Preemptive").build());
        return null == requestAuthenticate ? requestBuild : requestAuthenticate;
    }
    private boolean certificateSupportHost(HttpUrl httpUrl, Handshake handshake) {
        List<Certificate> listPeerCertificates = handshake.peerCertificates();
        return !listPeerCertificates.isEmpty() && OkHostnameVerifier.INSTANCE.verify(httpUrl.host(), (X509Certificate) listPeerCertificates.get(0));
    }
    public ExchangeCodec newCodecokhttp(OkHttpClient client, RealInterceptorChain chain) throws SocketException {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(chain, "chain");
        Socket socket = this.socket;
        Intrinsics.checkNotNull(socket);
        BufferedSource bufferedSource = this.source;
        Intrinsics.checkNotNull(bufferedSource);
        BufferedSink bufferedSink = this.sink;
        Intrinsics.checkNotNull(bufferedSink);
        Http2Connection http2Connection = this.http2Connection;
        if (null != http2Connection) {
            return new Http2ExchangeCodec(client, this, chain, http2Connection);
        }
        socket.setSoTimeout(chain.readTimeoutMillis());
        Timeout timeout = bufferedSource.timeout();
        long readTimeoutMillisokhttp = chain.getReadTimeoutMillisokhttp();
        final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        timeout.timeout(readTimeoutMillisokhttp, timeUnit);
        bufferedSink.timeout().timeout(chain.getWriteTimeoutMillisokhttp(), timeUnit);
        return new Http1ExchangeCodec(client, this, bufferedSource, bufferedSink);
    }
    private boolean supportsUrl(HttpUrl httpUrl) {
        Handshake handshake;
        if (!Util.assertionsEnabled || Thread.holdsLock(this)) {
            HttpUrl httpUrlUrl = this.route.address().url();
            if (httpUrl.port() != httpUrlUrl.port()) {
                return false;
            }
            if (Intrinsics.areEqual(httpUrl.host(), httpUrlUrl.host())) {
                return true;
            }
            if (this.noCoalescedConnections || null == (handshake = RealConnection.handshake)) {
                return false;
            }
            Intrinsics.checkNotNull(handshake);
            return certificateSupportHost(httpUrl, handshake);
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
    }
    public boolean isEligibleokhttp(Address address, List<Route> list) {
        Intrinsics.checkNotNullParameter(address, "address");
        if (!Util.assertionsEnabled || Thread.holdsLock(this)) {
            if (this.calls.size() >= this.allocationLimit || this.noNewExchanges || !this.route.address().equalsNonHost$okhttp(address)) {
                return false;
            }
            if (Intrinsics.areEqual(address.url().host(), route().address().url().host())) {
                return true;
            }
            if (null == http2Connection || !routeMatchesAny(list) || address.hostnameVerifier() != OkHostnameVerifier.INSTANCE || !supportsUrl(address.url())) {
                return false;
            }
            try {
                CertificatePinner certificatePinner = address.certificatePinner();
                Intrinsics.checkNotNull(certificatePinner);
                String strHost = address.url().host();
                Handshake handshake = handshake();
                Intrinsics.checkNotNull(handshake);
                certificatePinner.check(strHost, handshake.peerCertificates());
                return true;
            } catch (SSLPeerUnverifiedException unused) {
                return false;
            }
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
    }
    public boolean isHealthy(boolean z) {
        long idleAtNsokhttp;
        if (Util.assertionsEnabled && Thread.holdsLock(this)) {
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
        }
        long jNanoTime = System.nanoTime();
        Socket socket = this.rawSocket;
        Intrinsics.checkNotNull(socket);
        Socket socket2 = this.socket;
        Intrinsics.checkNotNull(socket2);
        BufferedSource bufferedSource = this.source;
        Intrinsics.checkNotNull(bufferedSource);
        if (socket.isClosed() || socket2.isClosed() || socket2.isInputShutdown() || socket2.isOutputShutdown()) {
            return false;
        }
        Http2Connection http2Connection = this.http2Connection;
        if (null != http2Connection) {
            return http2Connection.isHealthy(jNanoTime);
        }
        synchronized (this) {
            idleAtNsokhttp = jNanoTime - idleAtNs;
        }
        if (IDLE_CONNECTION_HEALTHY_NS > idleAtNsokhttp || !z) {
            return true;
        }
        return Util.isHealthy(socket2, bufferedSource);
    }
    public  RealWebSocket.Streams newWebSocketStreamsokhttp(final Exchange exchange) throws SocketException {
        Intrinsics.checkNotNullParameter(exchange, "exchange");
        Socket socket = this.socket;
        Intrinsics.checkNotNull(socket);
        final BufferedSource bufferedSource = this.source;
        Intrinsics.checkNotNull(bufferedSource);
        final BufferedSink bufferedSink = this.sink;
        Intrinsics.checkNotNull(bufferedSink);
        socket.setSoTimeout(0);
        noNewExchangesokhttp();
        return new RealWebSocket.Streams(bufferedSink, exchange) {
            final  Exchange $exchange;
            final   BufferedSink $sink;
            {
                super(true, this.getSource(), bufferedSink);
                this.$sink = bufferedSink;
                this.$exchange = exchange;
            }
            public void close() {
                this.$exchange.bodyComplete(-1L, true, true, null);
            }
        };
    }
    public Route route() {
        return this.route;
    }
    public void cancel() {
        Socket socket = this.rawSocket;
        if (socket == null) {
            return;
        }
        Util.closeQuietly(socket);
    }
    public Socket socket() {
        Socket socket = this.socket;
        Intrinsics.checkNotNull(socket);
        return socket;
    }
    public void onStream(Http2Stream stream) throws IOException {
        Intrinsics.checkNotNullParameter(stream, "stream");
        stream.close(ErrorCode.REFUSED_STREAM, null);
    }
    public synchronized void onSettings(Http2Connection connection, Settings settings) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.allocationLimit = settings.getMaxConcurrentStreams();
    }
    public Handshake handshake() {
        return handshake;
    }
    public void connectFailedokhttp(OkHttpClient client, Route failedRoute, IOException failure) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(failedRoute, "failedRoute");
        Intrinsics.checkNotNullParameter(failure, "failure");
        if (Proxy.Type.DIRECT != failedRoute.proxy().type()) {
            Address address = failedRoute.address();
            address.proxySelector().connectFailed(address.url().uri(), failedRoute.proxy().address(), failure);
        }
        client.getRouteDatabase().failed(failedRoute);
    }
    public synchronized void trackFailureokhttp(RealCall call, IOException iOException) {
        Intrinsics.checkNotNullParameter(call, "call");
        if (iOException instanceof StreamResetException) {
            if (ErrorCode.REFUSED_STREAM == ((StreamResetException) iOException).errorCode) {
                final int i2 = refusedStreamCount + 1;
                refusedStreamCount = i2;
                if (1 < i2) {
                    noNewExchanges = true;
                    routeFailureCount++;
                }
            } else if (ErrorCode.CANCEL != ((StreamResetException) iOException).errorCode || !call.isCanceled()) {
                noNewExchanges = true;
                routeFailureCount++;
            }
        } else if (!this.isMultiplexedokhttp() || (iOException instanceof ConnectionShutdownException)) {
            noNewExchanges = true;
            if (0 == this.successCount) {
                if (null != iOException) {
                    this.connectFailedokhttp(call.getClient(), route, iOException);
                }
                routeFailureCount++;
            }
        }
    }
    public Protocol protocol() {
        Protocol protocol = this.protocol;
        Intrinsics.checkNotNull(protocol);
        return protocol;
    }

    public String toString() {
        CipherSuite cipherSuite;
        StringBuilder sb = new StringBuilder();
        sb.append("Connection{");
        sb.append(this.route.address().url().host());
        sb.append(':');
        sb.append(this.route.address().url().port());
        sb.append(", proxy=");
        sb.append(this.route.proxy());
        sb.append(" hostAddress=");
        sb.append(this.route.socketAddress());
        sb.append(" cipherSuite=");
        Handshake handshake = RealConnection.handshake;
        Object obj = "none";
        if (handshake != null && handshake.cipherSuite() != null) {
            obj = handshake.cipherSuite();
        }
        sb.append(obj);
        sb.append(" protocol=");
        sb.append(this.protocol);
        sb.append('}');
        return sb.toString();
    }
    private boolean routeMatchesAny(List<Route> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (Route route : list) {
            Proxy.Type type = route.proxy().type();
            Proxy.Type type2 = Proxy.Type.DIRECT;
            if (type == type2 && this.route.proxy().type() == type2 && Intrinsics.areEqual(this.route.socketAddress(), route.socketAddress())) {
                return true;
            }
        }
        return false;
    }

    static final class C35902 extends Lambda implements Function0<List<? extends X509Certificate>> {
        C35902() {
            super(0);
        }
        public List<? extends X509Certificate> invoke() {
            Handshake handshake2 = handshake;
            Intrinsics.checkNotNull(handshake2);
            List<Certificate> listPeerCertificates = handshake2.peerCertificates();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listPeerCertificates, 10));
            for (Certificate certificate : listPeerCertificates) {
                arrayList.add(certificate);
            }
            return arrayList;
        }
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public RealConnection newTestConnection(RealConnectionPool connectionPool, Route route, Socket socket, long j2) {
            Intrinsics.checkNotNullParameter(connectionPool, "connectionPool");
            Intrinsics.checkNotNullParameter(route, "route");
            Intrinsics.checkNotNullParameter(socket, "socket");
            RealConnection realConnection = new RealConnection(connectionPool, route);
            realConnection.socket = socket;
            realConnection.setIdleAtNsokhttp(j2);
            return realConnection;
        }
    }
    public enum WhenMappings {
        ;
        public static final int[] EnumSwitchMapping0;

        static {
            int[] iArr = new int[Proxy.Type.values().length];
            iArr[Proxy.Type.DIRECT.ordinal()] = 1;
            iArr[Proxy.Type.HTTP.ordinal()] = 2;
            EnumSwitchMapping0 = iArr;
        }
    }
}
