package okhttp3.internal.connection;

import androidx.core.app.NotificationCompat;
import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.ExceptionsKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.internal.platform.Platform;
import okio.AsyncTimeout;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public final class RealCall implements Call<T> {
    private final OkHttpClient client;
    private final RealConnectionPool connectionPool;
    private final EventListener eventListener;
    private final AtomicBoolean executed;
    private final boolean forWebSocket;
    private final Request originalRequest;
    private final C35881 timeout;
    private Object callStackTrace;
    private volatile boolean canceled;
    private RealConnection connection;
    private volatile RealConnection connectionToCancel;
    private volatile Exchange exchange;
    private ExchangeFinder exchangeFinder;
    private boolean expectMoreExchanges;
    private Exchange interceptorScopedExchange;
    private boolean requestBodyOpen;
    private boolean responseBodyOpen;
    private boolean timeoutEarlyExit;
    public RealCall(OkHttpClient client, Request originalRequest, boolean z) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(originalRequest, "originalRequest");
        this.client = client;
        this.originalRequest = originalRequest;
        this.forWebSocket = z;
        this.connectionPool = client.connectionPool().getDelegate   ();
        this.eventListener = client.eventListenerFactory().create(this);
        C35881 c35881 = new C35881() {
            protected void timedOut() {
                RealCall.this.cancel();
            }
        };
        c35881.timeout(this.client.callTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.timeout = c35881;
        this.executed = new AtomicBoolean();
        this.expectMoreExchanges = true;
    }
    public OkHttpClient getClient() {
        return this.client;
    }
    public Request getOriginalRequest() {
        return this.originalRequest;
    }
    public boolean getForWebSocket() {
        return this.forWebSocket;
    }
    public EventListener getEventListenerokhttp() {
        return this.eventListener;
    }
    public RealConnection getConnection() {
        return this.connection;
    }
    public Exchange getInterceptorScopedExchangeokhttp() {
        return this.interceptorScopedExchange;
    }
    public RealConnection getConnectionToCancel() {
        return this.connectionToCancel;
    }
    public void setConnectionToCancel(RealConnection realConnection) {
        this.connectionToCancel = realConnection;
    }
    public AsyncTimeout timeout() {
        return this.timeout;
    }
    public RealCall clone() {
        return new RealCall(this.client, this.originalRequest, this.forWebSocket);
    }
    public void enqueue(retrofit2.Callback<T> callback) {
        Call.super.enqueue(callback);
    }
    public void enqueue(Callback callback) {

    }
    public Request request() {
        return this.originalRequest;
    }
    public EventListener getEventListener() {
        return null;
    }
    public void cancel() {
        if (this.canceled) {
            return;
        }
        this.canceled = true;
        Exchange exchange = this.exchange;
        if (null != exchange) {
            exchange.cancel();
        }
        RealConnection realConnection = this.connectionToCancel;
        if (null != realConnection) {
            realConnection.cancel();
        }
        this.eventListener.canceled(this);
    }
    public boolean isCanceled() {
        return this.canceled;
    }
    public Response execute() {
        if (!this.executed.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        this.timeout.enter();
        callStart();
        try {
            this.client.dispatcher().executedokhttp(this);
            try {
                return getResponseWithInterceptorChainokhttp();
            } catch (final Throwable e) {
                throw new RuntimeException(e);
            }
        } finally {
            this.client.dispatcher().finishedokhttp(this);
        }
    }
    public void enqueue(Call responseCallback) {
        Intrinsics.checkNotNullParameter(responseCallback, "responseCallback");
        if (!this.executed.compareAndSet(false, true)) {
            throw new IllegalStateException("Already Executed");
        }
        callStart();
        this.client.dispatcher().enqueueokhttp(new AsyncCall(this, responseCallback));
    }
    public boolean isExecuted() {
        return this.executed.get();
    }
    private void callStart() {
        this.callStackTrace = Platform.Companion.get().getStackTraceForCloseable("response.body().close()");
        this.eventListener.callStart(this);
    }
    public Response getResponseWithInterceptorChainokhttp() throws Throwable {
        ArrayList arrayList = new ArrayList();
        CollectionsKt.addAll(arrayList, this.client.interceptors());
        arrayList.add(new RetryAndFollowUpInterceptor(this.client));
        arrayList.add(new BridgeInterceptor(this.client.cookieJar()));
        arrayList.add(new CacheInterceptor(this.client.cache()));
        arrayList.add(ConnectInterceptor.INSTANCE);
        if (!this.forWebSocket) {
            CollectionsKt.addAll(arrayList, this.client.networkInterceptors());
        }
        arrayList.add(new CallServerInterceptor(this.forWebSocket));
        boolean z = false;
        try {
            Response responseProceed = new RealInterceptorChain(this, arrayList, 0, null, this.originalRequest, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis()).proceed(this.originalRequest);
            if (canceled) {
                Util.closeQuietly(responseProceed);
                throw new IOException("Canceled");
            }
            noMoreExchangesokhttp(null);
            return responseProceed;
        } catch (IOException e2) {
            try {
                IOException iOExceptionNoMoreExchangesokhttp = noMoreExchangesokhttp(e2);
                if (null == iOExceptionNoMoreExchangesokhttp) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Throwable");
                }
                throw iOExceptionNoMoreExchangesokhttp;
            } catch (Throwable th) {
                th = th;
                z = true;
                if (!z) {
                    noMoreExchangesokhttp(null);
                }
                throw th;
            }
        } catch (Throwable th2) {
            if (!z) {
            }
            throw th2;
        }
    }
    public void enterNetworkInterceptorExchange(Request request, boolean z) {
        Intrinsics.checkNotNullParameter(request, "request");
        if (null != interceptorScopedExchange) {
            throw new IllegalStateException("Check failed.");
        }
        synchronized (this) {
            if (this.responseBodyOpen) {
                throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()");
            }
            if (this.requestBodyOpen) {
                throw new IllegalStateException("Check failed.");
            }
            Unit unit = Unit.INSTANCE;
        }
        if (z) {
            this.exchangeFinder = new ExchangeFinder(this.connectionPool, createAddress(request.url()), this, this.eventListener);
        }
    }
    public Exchange initExchangeokhttp(RealInterceptorChain chain) throws IOException {
        Intrinsics.checkNotNullParameter(chain, "chain");
        synchronized (this) {
            if (!this.expectMoreExchanges) {
                throw new IllegalStateException("released");
            }
            if (this.responseBodyOpen) {
                throw new IllegalStateException("Check failed.");
            }
            if (this.requestBodyOpen) {
                throw new IllegalStateException("Check failed.");
            }
            Unit unit = Unit.INSTANCE;
        }
        ExchangeFinder exchangeFinder = this.exchangeFinder;
        Intrinsics.checkNotNull(exchangeFinder);
        Exchange exchange = new Exchange(this, this.eventListener, exchangeFinder, exchangeFinder.find(this.client, chain));
        this.interceptorScopedExchange = exchange;
        this.exchange = exchange;
        synchronized (this) {
            this.requestBodyOpen = true;
            this.responseBodyOpen = true;
        }
        if (this.canceled) {
            throw new IOException("Canceled");
        }
        return exchange;
    }
    public <E extends IOException> E messageDoneokhttp(Exchange exchange, boolean z, boolean z2, E e2) {
        boolean z3;
        boolean z4;
        Intrinsics.checkNotNullParameter(exchange, "exchange");
        if (!Intrinsics.areEqual(exchange, this.exchange)) {
            return e2;
        }
        synchronized (this) {
            z3 = false;
            if (z) {
                try {
                    if (!this.requestBodyOpen) {
                        if (z2 || !this.responseBodyOpen) {
                            z4 = false;
                        }
                    }
                    if (z) {
                        this.requestBodyOpen = false;
                    }
                    if (z2) {
                        this.responseBodyOpen = false;
                    }
                    boolean z5 = this.requestBodyOpen;
                    boolean z6 = !z5 && !this.responseBodyOpen;
                    if (!z5 && !this.responseBodyOpen && !this.expectMoreExchanges) {
                        z3 = true;
                    }
                    z4 = z3;
                    z3 = z6;
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                if (z2) {
                }
                z4 = false;
            }
            Unit unit = Unit.INSTANCE;
        }
        if (z3) {
            this.exchange = null;
            RealConnection realConnection = this.connection;
            if (null != realConnection) {
                realConnection.incrementSuccessCountokhttp();
            }
        }
        try {
            return z4 ? callDone(e2) : e2;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public IOException noMoreExchangesokhttp(IOException iOException) {
        boolean z;
        synchronized (this) {
            try {
                z = false;
                if (this.expectMoreExchanges) {
                    this.expectMoreExchanges = false;
                    if (!this.requestBodyOpen && !this.responseBodyOpen) {
                        z = true;
                    }
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
        try {
            return z ? callDone(iOException) : iOException;
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Socket releaseConnectionNoEventsokhttp() {
        RealConnection realConnection = this.connection;
        Intrinsics.checkNotNull(realConnection);
        if (!Util.assertionsEnabled || Thread.holdsLock(realConnection)) {
            List<Reference<RealCall>> calls = realConnection.getCalls();
            Iterator<Reference<RealCall>> it = calls.iterator();
            int i2 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i2 = -1;
                    break;
                }
                if (Intrinsics.areEqual(it.next().get(), this)) {
                    break;
                }
                i2++;
            }
            if (-1 == i2) {
                throw new IllegalStateException("Check failed.");
            }
            calls.remove(i2);
            this.connection = null;
            if (calls.isEmpty()) {
                realConnection.setIdleAtNsokhttp(System.nanoTime());
                if (this.connectionPool.connectionBecameIdle(realConnection)) {
                    return realConnection.socket();
                }
            }
            return null;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + realConnection);
    }
    private <E extends IOException> E timeoutExit(E e2) {
        if (this.timeoutEarlyExit || !this.timeout.exit()) {
            return e2;
        }
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (null != e2) {
            interruptedIOException.initCause(e2);
        }
        return (E) interruptedIOException;
    }
    public void timeoutEarlyExit() {
        if (this.timeoutEarlyExit) {
            throw new IllegalStateException("Check failed.");
        }
        this.timeoutEarlyExit = true;
        this.timeout.exit();
    }
    public void exitNetworkInterceptorExchangeokhttp(boolean z) {
        Exchange exchange;
        synchronized (this) {
            if (!this.expectMoreExchanges) {
                throw new IllegalStateException("released");
            }
            Unit unit = Unit.INSTANCE;
        }
        if (z && null != (exchange = this.exchange)) {
            exchange.detachWithViolence();
        }
        this.interceptorScopedExchange = null;
    }
    private Address createAddress(HttpUrl httpUrl) {
        SSLSocketFactory sslSocketFactory;
        HostnameVerifier hostnameVerifier;
        CertificatePinner certificatePinner;
        if (httpUrl.isHttps()) {
            sslSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            certificatePinner = this.client.certificatePinner();
        } else {
            sslSocketFactory = null;
            hostnameVerifier = null;
            certificatePinner = null;
        }
        return new Address(httpUrl.host(), httpUrl.port(), this.client.dns(), this.client.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }
    public boolean retryAfterFailure() {
        ExchangeFinder exchangeFinder = this.exchangeFinder;
        Intrinsics.checkNotNull(exchangeFinder);
        return exchangeFinder.retryAfterFailure();
    }
    private String toLoggableString() {
        final String sb = (canceled ? "canceled " : "") +
                (this.forWebSocket ? "web socket" : NotificationCompat.CATEGORY_CALL) +
                " to " +
                redactedUrlokhttp();
        return sb;
    }
    public String redactedUrlokhttp() {
        return this.originalRequest.url().redact();
    }
    public void acquireConnectionNoEvents(RealConnection connection) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        if (!Util.assertionsEnabled || Thread.holdsLock(connection)) {
            if (null != this.connection) {
                throw new IllegalStateException("Check failed.");
            }
            this.connection = connection;
            connection.getCalls().add(new CallReference(this, this.callStackTrace));
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + connection);
    }
    private <E extends IOException> E callDone(E e2) throws IOException {
        Socket socketReleaseConnectionNoEventsokhttp;
        boolean z = Util.assertionsEnabled;
        if (!z || !Thread.holdsLock(this)) {
            RealConnection realConnection = this.connection;
            if (null != realConnection) {
                if (!z || !Thread.holdsLock(realConnection)) {
                    synchronized (realConnection) {
                        socketReleaseConnectionNoEventsokhttp = releaseConnectionNoEventsokhttp();
                    }
                    if (null == connection) {
                        if (null != socketReleaseConnectionNoEventsokhttp) {
                            Util.closeQuietly(socketReleaseConnectionNoEventsokhttp);
                        }
                        this.eventListener.connectionReleased(this, realConnection);
                    } else if (null != socketReleaseConnectionNoEventsokhttp) {
                        throw new IllegalStateException("Check failed.");
                    }
                } else {
                    throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + realConnection);
                }
            }
            E e3 = timeoutExit(e2);
            if (null != e2) {
                EventListener eventListener = this.eventListener;
                Intrinsics.checkNotNull(e3);
                eventListener.callFailed(this, e3);
            } else {
                this.eventListener.callEnd(this);
            }
            return e3;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + this);
    }
    public class C35881 extends AsyncTimeout {
        C35881() {
        }
        protected void timedOut() {
            RealCall.this.cancel();
        }
    }
    public static final class CallReference extends WeakReference<RealCall> {
        private final Object callStackTrace;

        public CallReference(RealCall referent, Object obj) {
            super(referent);
            Intrinsics.checkNotNullParameter(referent, "referent");
            this.callStackTrace = obj;
        }

        public Object getCallStackTrace() {
            return this.callStackTrace;
        }
    }
    public final class AsyncCall implements Runnable {
        final RealCall this0;
        private final Callback responseCallback;
        private volatile AtomicInteger callsPerHost;

        public AsyncCall(RealCall this0, Call responseCallback) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            Intrinsics.checkNotNullParameter(responseCallback, "responseCallback");
            this.this0 = this0;
            this.responseCallback = (Callback) responseCallback;
            this.callsPerHost = new AtomicInteger(0);
        }

        public AtomicInteger getCallsPerHost() {
            return this.callsPerHost;
        }

        public void reuseCallsPerHostFrom(AsyncCall other) {
            Intrinsics.checkNotNullParameter(other, "other");
            this.callsPerHost = other.callsPerHost;
        }

        public String getHost() {
            return this.this0.getOriginalRequest().url().host();
        }

        public Request getRequest() {
            return this.this0.getOriginalRequest();
        }

        public RealCall getCall() {
            return this.this0;
        }

        public void executeOn(ExecutorService executorService) {
            Intrinsics.checkNotNullParameter(executorService, "executorService");
            Dispatcher dispatcher = this.this0.getClient().dispatcher();
            if (!Util.assertionsEnabled || !Thread.holdsLock(dispatcher)) {
                try {
                    try {
                        executorService.execute(this);
                        return;
                    } catch (RejectedExecutionException e2) {
                        InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                        interruptedIOException.initCause(e2);
                        this.this0.noMoreExchangesokhttp(interruptedIOException);
                        this.responseCallback.onFailure(this.this0, interruptedIOException);
                        this.this0.getClient().dispatcher().finishedokhttp(RealCall.this);
                        return;
                    }
                } catch (Throwable th) {
                    this.this0.getClient().dispatcher().finishedokhttp(RealCall.this);
                    throw th;
                }
            }
            throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST NOT hold lock on " + dispatcher);
        }
        public void run() {
            boolean z;
            Throwable th;
            final IOException e2;
            Dispatcher dispatcher;
            String strStringPlus = Intrinsics.stringPlus("OkHttp ", this.this0.redactedUrlokhttp());
            RealCall realCall = this.this0;
            Thread threadCurrentThread = Thread.currentThread();
            String name = threadCurrentThread.getName();
            threadCurrentThread.setName(strStringPlus);
            try {
                realCall.timeout.enter();
                try {
                    try {
                        z = true;
                    } catch (Throwable th2) {
                        z = false;
                        th = th2;
                    }
                    try {
                        this.responseCallback.onResponse(realCall, realCall.getResponseWithInterceptorChainokhttp());
                        dispatcher = realCall.getClient().dispatcher();
                    } catch (IOException e4) {
                        e2 = e4;
                        if (z) {
                            Platform.Companion.get().log(Intrinsics.stringPlus("Callback failure for ", realCall.toLoggableString()), 4, e2);
                        } else {
                            this.responseCallback.onFailure(realCall, e2);
                        }
                        dispatcher = realCall.getClient().dispatcher();
                        dispatcher.finishedokhttp(RealCall.this);
                    } catch (Throwable th3) {
                        th = th3;
                        realCall.cancel();
                        if (!z) {
                            IOException iOException = new IOException(Intrinsics.stringPlus("canceled due to ", th));
                            ExceptionsKt.addSuppressed(iOException, th);
                            this.responseCallback.onFailure(realCall, iOException);
                        }
                        throw th;
                    }
                    dispatcher.finishedokhttp(RealCall.this);
                } catch (Throwable th4) {
                    realCall.getClient().dispatcher().finishedokhttp(RealCall.this);
                    try {
                        throw th4;
                    } catch (final Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
            } finally {
                threadCurrentThread.setName(name);
            }
        }
    }
}
