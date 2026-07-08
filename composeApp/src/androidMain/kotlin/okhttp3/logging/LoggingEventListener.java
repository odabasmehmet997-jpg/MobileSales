package okhttp3.logging;

import com.proje.mobilesales.C2442R;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static kotlin.jvm.internal.Intrinsics.stringPlus;

public final class LoggingEventListener extends EventListener {
    private final HttpLoggingInterceptor.Logger logger;
    private long startNs;

    public LoggingEventListener(final HttpLoggingInterceptor.Logger logger, final DefaultConstructorMarker defaultConstructorMarker) {
        this(logger);
    }

    private LoggingEventListener(final HttpLoggingInterceptor.Logger logger) {
        this.logger = logger;
    }

    public void callStart(final Call<R> call) {
        Intrinsics.checkNotNullParameter(call, "call");
        startNs = System.nanoTime();
        this.logWithTime(stringPlus("callStart: ", call.request()));
    }
    public void proxySelectStart(final Call<R> call, final HttpUrl url) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(url, "url");
        this.logWithTime(stringPlus("proxySelectStart: ", url));
    }

    
    public void proxySelectEnd(final Call<R> call, final HttpUrl url, final List<Proxy> proxies) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(url, "url");
        Intrinsics.checkNotNullParameter(proxies, "proxies");
        this.logWithTime(stringPlus("proxySelectEnd: ", proxies));
    }

    
    public void dnsStart(final Call<R> call, final String domainName) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(domainName, "domainName");
        this.logWithTime(stringPlus("dnsStart: ", domainName));
    }

    
    public void dnsEnd(final Call<R> call, final String domainName, final List<InetAddress> inetAddressList) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(domainName, "domainName");
        Intrinsics.checkNotNullParameter(inetAddressList, "inetAddressList");
        this.logWithTime(stringPlus("dnsEnd: ", inetAddressList));
    }

    
    public void connectStart(final Call<R> call, final InetSocketAddress inetSocketAddress, final Proxy proxy) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        this.logWithTime("connectStart: " + inetSocketAddress + ' ' + proxy);
    }

    
    public void secureConnectStart(final Call<R> call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.logWithTime("secureConnectStart");
    }

    
    public void secureConnectEnd(final Call<R> call, final Handshake handshake) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.logWithTime(stringPlus("secureConnectEnd: ", handshake));
    }

    
    public void connectEnd(final Call<R> call, final InetSocketAddress inetSocketAddress, final Proxy proxy, final Protocol protocol) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        this.logWithTime(stringPlus("connectEnd: ", protocol));
    }

    
    public void connectFailed(final Call<R> call, final InetSocketAddress inetSocketAddress, final Proxy proxy, final Protocol protocol, final IOException ioe) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(inetSocketAddress, "inetSocketAddress");
        Intrinsics.checkNotNullParameter(proxy, "proxy");
        Intrinsics.checkNotNullParameter(ioe, "ioe");
        this.logWithTime("connectFailed: " + protocol + ' ' + ioe);
    }

    
    public void connectionAcquired(final Call<R> call, final Connection connection) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(connection, "connection");
        this.logWithTime(stringPlus("connectionAcquired: ", connection));
    }

    
    public void connectionReleased(final Call<R> call, final Connection connection) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(connection, "connection");
        this.logWithTime("connectionReleased");
    }

    
    public void requestHeadersStart(final Call<R> call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.logWithTime("requestHeadersStart");
    }

    
    public void requestHeadersEnd(final Call<R> call, final Request request) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(request, "request");
        this.logWithTime("requestHeadersEnd");
    }

    
    public void requestBodyStart(final Call<R> call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.logWithTime("requestBodyStart");
    }

    
    public void requestBodyEnd(final Call<R> call, final long j2) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.logWithTime(stringPlus("requestBodyEnd: byteCount=", Long.valueOf(j2)));
    }

    
    public void requestFailed(final Call<R> call, final IOException ioe) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(ioe, "ioe");
        this.logWithTime(stringPlus("requestFailed: ", ioe));
    }

    
    public void responseHeadersStart(final Call<R> call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.logWithTime("responseHeadersStart");
    }

    
    public void responseHeadersEnd(final Call<R> call, final Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        this.logWithTime(stringPlus("responseHeadersEnd: ", response));
    }

    
    public void responseBodyStart(final Call<R> call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.logWithTime("responseBodyStart");
    }

    
    public void responseBodyEnd(final Call<R> call, final long j2) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.logWithTime(stringPlus("responseBodyEnd: byteCount=", Long.valueOf(j2)));
    }

    
    public void responseFailed(final Call<R> call, final IOException ioe) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(ioe, "ioe");
        this.logWithTime(stringPlus("responseFailed: ", ioe));
    }

    
    public void callEnd(final Call<R> call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.logWithTime("callEnd");
    }

    
    public void callFailed(final Call<R> call, final IOException ioe) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(ioe, "ioe");
        this.logWithTime(stringPlus("callFailed: ", ioe));
    }

    
    public void canceled(final Call<R> call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.logWithTime("canceled");
    }

    
    public void satisfactionFailure(final retrofit2.Call<T> call, final Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        this.logWithTime(stringPlus("satisfactionFailure: ", response));
    }

    
    public void cacheHit(final retrofit2.Call<T> call, final Response response) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        this.logWithTime(stringPlus("cacheHit: ", response));
    }

    
    public void cacheMiss(final retrofit2.Call<T> call) {
        Intrinsics.checkNotNullParameter(call, "call");
        this.logWithTime("cacheMiss");
    }

    
    public void cacheConditionalHit(final retrofit2.Call<T> call, final Response cachedResponse) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(cachedResponse, "cachedResponse");
        this.logWithTime(stringPlus("cacheConditionalHit: ", cachedResponse));
    }

    private void logWithTime(final String str) {
        final long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
        logger.log('[' + millis + " ms] " + str);
    }
    public static class Factory implements EventListener.Factory {
        private final HttpLoggingInterceptor.Logger logger;

        public Factory() {
            this(null, 1, 0 = 1);
        }

        public Factory(final HttpLoggingInterceptor.Logger logger) {
            Intrinsics.checkNotNullParameter(logger, "logger");
            this.logger = logger;
        }

        public Factory(final HttpLoggingInterceptor.Logger logger, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
            this(0 != (i2 & 1) ? HttpLoggingInterceptor.Logger.DEFAULT : logger);
        }
        public EventListener create(final Call<R> call) {
            Intrinsics.checkNotNullParameter(call, "call");
            return new LoggingEventListener(logger, null);
        }
    }
}
