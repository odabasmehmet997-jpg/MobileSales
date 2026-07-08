package okhttp3.internal.http;

import com.google.android.material.card.MaterialCardViewHelper;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.connection.RouteException;
import okhttp3.internal.http2.ConnectionShutdownException;
import org.springframework.http.HttpHeaders;

import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.*;

public final class RetryAndFollowUpInterceptor implements Interceptor {
    public static final Companion Companion = new Companion(null);
    private static final int MAX_FOLLOW_UPS = 20;
    private final OkHttpClient client;
    public RetryAndFollowUpInterceptor(final OkHttpClient client) {
        Intrinsics.checkNotNullParameter(client, "client");
        this.client = client;
    }
    public Response intercept(final Chain chain) throws IOException {
        final Request requestFollowUpRequest = null;
        Intrinsics.checkNotNullParameter(chain, "chain");
        final RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Request requestokhttp = realInterceptorChain.getRequestokhttp();
        final RealCall callokhttp = realInterceptorChain.getCallokhttp();
        List listEmptyList = CollectionsKt.emptyList();
        final int i2 = 0;
        final Response response = null;
        while (true) {
            boolean z = true;
            while (true) {
                callokhttp.enterNetworkInterceptorExchange(requestokhttp, z);
                try {
                    if (callokhttp.isCanceled()) {
                        throw new IOException("Canceled");
                    }
                    try {
                        try {
                            final Response responseProceed = realInterceptorChain.proceed(requestokhttp);
                            break;
                        } catch (final RouteException e2) {
                            if (!this.recover(e2.getLastConnectException(), callokhttp, requestokhttp, false)) {
                                throw Util.withSuppressed(e2.getFirstConnectException(), listEmptyList);
                            }
                            listEmptyList = CollectionsKt.plus(listEmptyList, e2.getFirstConnectException());
                        }
                    } catch (final IOException e3) {
                        if (!this.recover(e3, callokhttp, requestokhttp, !(e3 instanceof ConnectionShutdownException))) {
                            throw Util.withSuppressed(e3, listEmptyList);
                        }
                        listEmptyList = CollectionsKt.plus(listEmptyList, e3);
                    }
                    callokhttp.exitNetworkInterceptorExchangeokhttp(true);
                    z = false;
                } catch (final Throwable th) {
                    callokhttp.exitNetworkInterceptorExchangeokhttp(true);
                    try {
                        throw th;
                    } catch (final Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            callokhttp.exitNetworkInterceptorExchangeokhttp(true);
            requestokhttp = requestFollowUpRequest;
        }
    }
    private boolean recover(final IOException iOException, final RealCall realCall, final Request request, final boolean z) {
        if (client.retryOnConnectionFailure()) {
            return !(z && this.requestIsOneShot(iOException, request)) && this.isRecoverable(iOException, z) && realCall.retryAfterFailure();
        }
        return false;
    }
    private boolean requestIsOneShot(final IOException iOException, final Request request) {
        final RequestBody requestBodyBody = request.body();
        return (null != requestBodyBody && requestBodyBody.isOneShot()) || (iOException instanceof FileNotFoundException);
    }
    private boolean isRecoverable(final IOException iOException, final boolean z) {
        if (iOException instanceof ProtocolException) {
            return false;
        }
        return iOException instanceof InterruptedIOException ? (iOException instanceof SocketTimeoutException) && !z : ((!(iOException instanceof SSLHandshakeException)) || (!(iOException.getCause() instanceof CertificateException))) && (!(iOException instanceof SSLPeerUnverifiedException));
    }
    private Request followUpRequest(final Response response, final Exchange exchange) throws IOException {
        final RealConnection connectionokhttp;
        final Route route = (null == exchange || null == (connectionokhttp = exchange.getConnectionokhttp())) ? null : connectionokhttp.route();
        final int iCode = response.code();
        final String strMethod = response.request().method();
        if (307 != iCode && 308 != iCode) {
            if (401 == iCode) {
                return client.authenticator().authenticate(route, response);
            }
            if (421 == iCode) {
                final RequestBody requestBodyBody = response.request().body();
                if ((null != requestBodyBody && requestBodyBody.isOneShot()) || null == exchange || !exchange.isCoalescedConnectionokhttp()) {
                    return null;
                }
                exchange.getConnectionokhttp().noCoalescedConnectionsokhttp();
                return response.request();
            }
            if (503 == iCode) {
                final Response responsePriorResponse = response.priorResponse();
                if ((null == responsePriorResponse || 503 != responsePriorResponse.code()) && 0 == retryAfter(response, Integer.MAX_VALUE)) {
                    return response.request();
                }
                return null;
            }
            if (407 == iCode) {
                checkNotNull(route);
                if (Proxy.Type.HTTP != route.proxy().type()) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                return client.proxyAuthenticator().authenticate(route, response);
            }
            if (408 == iCode) {
                if (!client.retryOnConnectionFailure()) {
                    return null;
                }
                final RequestBody requestBodyBody2 = response.request().body();
                if (null != requestBodyBody2 && requestBodyBody2.isOneShot()) {
                    return null;
                }
                final Response responsePriorResponse2 = response.priorResponse();
                if ((null == responsePriorResponse2 || 408 != responsePriorResponse2.code()) && 0 >= retryAfter(response, 0)) {
                    return response.request();
                }
                return null;
            }
            switch (iCode) {
                case MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION /* 300 */:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return null;
            }
        }
        return this.buildRedirectRequest(response, strMethod);
    }
    private Request buildRedirectRequest(final Response response, final String str) {
        final String strHeaderdefault;
        final HttpUrl httpUrlResolve;
        if (!client.followRedirects() || null == (strHeaderdefault = Response.headerdefault(response, HttpHeaders.LOCATION, null, 2, null)) || null == (httpUrlResolve = response.request().url().resolve(strHeaderdefault))) {
            return null;
        }
        if (!areEqual(httpUrlResolve.scheme(), response.request().url().scheme()) && !client.followSslRedirects()) {
            return null;
        }
        final Request.Builder builderNewBuilder = response.request().newBuilder();
        if (HttpMethod.permitsRequestBody(str)) {
            final int iCode = response.code();
            final HttpMethod httpMethod = HttpMethod.INSTANCE;
            final boolean z = httpMethod.redirectsWithBody(str) || 308 == iCode || 307 == iCode;
            if (httpMethod.redirectsToGet(str) && 308 != iCode && 307 != iCode) {
                builderNewBuilder.method("GET", null);
            } else {
                builderNewBuilder.method(str, z ? response.request().body() : null);
            }
            if (!z) {
                builderNewBuilder.removeHeader(HttpHeaders.TRANSFER_ENCODING);
                builderNewBuilder.removeHeader(HttpHeaders.CONTENT_LENGTH);
                builderNewBuilder.removeHeader(HttpHeaders.CONTENT_TYPE);
            }
        }
        if (!Util.canReuseConnectionFor(response.request().url(), httpUrlResolve)) {
            builderNewBuilder.removeHeader(HttpHeaders.AUTHORIZATION);
        }
        return builderNewBuilder.url(httpUrlResolve).build();
    }
    private int retryAfter(final Response response, final int i2) throws NumberFormatException {
        final String strHeaderdefault = Response.headerdefault(response, HttpHeaders.RETRY_AFTER, null, 2, null);
        if (null == strHeaderdefault) {
            return i2;
        }
        if (!new Regex("\\d+").matches(strHeaderdefault)) {
            return Integer.MAX_VALUE;
        }
        final Integer numValueOf = Integer.valueOf(strHeaderdefault);
        checkNotNullExpressionValue(numValueOf, "valueOf(header)");
        return numValueOf.intValue();
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
