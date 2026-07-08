package okhttp3.internal.http;

import kotlin.ExceptionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.http2.ConnectionShutdownException;
import okio.BufferedSink;
import okio.Okio;

import java.io.IOException;
import java.net.ProtocolException;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;

public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(final boolean z) {
        forWebSocket = z;
    }

    private boolean shouldIgnoreAndWaitForRealResponse(final int i2) {
        if (100 == i2) {
            return true;
        }
        return 102 <= i2 && 200 > i2;
    }

    public Response intercept(final Chain chain) throws IOException {
        boolean z;
        Response.Builder builder;
        Response.Builder responseHeaders;
        Response responseBuild;
        int iCode;
        Response responseBuild2;
        ResponseBody responseBodyBody;
        final Response.Builder builder2;
        Intrinsics.checkNotNullParameter(chain, "chain");
        final RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        final Exchange exchangeokhttp = realInterceptorChain.getExchangeokhttp();
        checkNotNull(exchangeokhttp);
        final Request requestokhttp = realInterceptorChain.getRequestokhttp();
        final RequestBody requestBodyBody = requestokhttp.body();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        Long lValueOf = null;
        try {
            exchangeokhttp.writeRequestHeaders(requestokhttp);
            PermitsRequestBody = HttpMethod.permitsRequestBody(requestokhttp.method());
            try {
                if (0 != PermitsRequestBody && null != requestBodyBody) {
                    if (StringsKt.equals("100-continue", requestokhttp.header(org.springframework.http.HttpHeaders.EXPECT), true)) {
                        exchangeokhttp.flushRequest();
                        final Response.Builder responseHeaders2 = exchangeokhttp.readResponseHeaders(true);
                        try {
                            exchangeokhttp.responseHeadersStart();
                            z = false;
                            builder2 = responseHeaders2;
                        } catch (final IOException e2) {
                            e = e2;
                            z = true;
                            builder = responseHeaders2;
                            if (e instanceof ConnectionShutdownException) {
                                throw e;
                            }
                            responseHeaders = builder;
                            if (!exchangeokhttp.getHasFailureokhttp()) {
                                throw e;
                            }
                            if (null == responseHeaders) {
                            }
                            responseBuild = responseHeaders.request(requestokhttp).handshake(exchangeokhttp.getConnectionokhttp().handshake()).sentRequestAtMillis(jCurrentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
                            iCode = responseBuild.code();
                            if (this.shouldIgnoreAndWaitForRealResponse(iCode)) {
                            }
                            exchangeokhttp.responseHeadersEnd(responseBuild);
                            if (!forWebSocket) {
                                responseBuild2 = responseBuild.newBuilder().body(exchangeokhttp.openResponseBody(responseBuild)).build();
                            }
                            if (!StringsKt.equals("close", responseBuild2.request().header(org.springframework.http.HttpHeaders.CONNECTION), true)) {
                                exchangeokhttp.noNewExchangesOnConnection();
                            }
                            if (204 != iCode) {
                                responseBodyBody = responseBuild2.body();
                                if (0 < (null != responseBodyBody ? -1L : responseBodyBody.contentLength())) {
                                }
                            }
                            return responseBuild2;
                        }
                    } else {
                        z = true;
                        builder2 = null;
                    }
                    if (null == builder2) {
                        if (requestBodyBody.isDuplex()) {
                            exchangeokhttp.flushRequest();
                            requestBodyBody.writeTo(Okio.buffer(exchangeokhttp.createRequestBody(requestokhttp, true)));
                            PermitsRequestBody = builder2;
                        } else {
                            final BufferedSink bufferedSinkBuffer = Okio.buffer(exchangeokhttp.createRequestBody(requestokhttp, false));
                            requestBodyBody.writeTo(bufferedSinkBuffer);
                            bufferedSinkBuffer.close();
                            PermitsRequestBody = builder2;
                        }
                    } else {
                        exchangeokhttp.noRequestBody();
                        PermitsRequestBody = builder2;
                        if (!exchangeokhttp.getConnectionokhttp().isMultiplexedokhttp()) {
                            exchangeokhttp.noNewExchangesOnConnection();
                            PermitsRequestBody = builder2;
                        }
                    }
                } else {
                    exchangeokhttp.noRequestBody();
                    z = true;
                    PermitsRequestBody = 0;
                }
                if (null == requestBodyBody || !requestBodyBody.isDuplex()) {
                    exchangeokhttp.finishRequest();
                }
                e = null;
                responseHeaders = PermitsRequestBody;
            } catch (final IOException e3) {
                e = e3;
                builder = PermitsRequestBody;
            }
        } catch (final IOException e4) {
            e = e4;
            z = true;
            builder = null;
        }
        if (null == responseHeaders) {
            try {
                responseHeaders = exchangeokhttp.readResponseHeaders(false);
                checkNotNull(responseHeaders);
                if (z) {
                    exchangeokhttp.responseHeadersStart();
                    z = false;
                }
            } catch (final IOException e5) {
                if (null != e) {
                    ExceptionsKt.addSuppressed(e, e5);
                    throw e;
                }
                throw e5;
            }
        }
        responseBuild = responseHeaders.request(requestokhttp).handshake(exchangeokhttp.getConnectionokhttp().handshake()).sentRequestAtMillis(jCurrentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        iCode = responseBuild.code();
        if (this.shouldIgnoreAndWaitForRealResponse(iCode)) {
            final Response.Builder responseHeaders3 = exchangeokhttp.readResponseHeaders(false);
            checkNotNull(responseHeaders3);
            if (z) {
                exchangeokhttp.responseHeadersStart();
            }
            responseBuild = responseHeaders3.request(requestokhttp).handshake(exchangeokhttp.getConnectionokhttp().handshake()).sentRequestAtMillis(jCurrentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
            iCode = responseBuild.code();
        }
        exchangeokhttp.responseHeadersEnd(responseBuild);
        if (!forWebSocket && 101 == iCode) {
            responseBuild2 = responseBuild.newBuilder().body(Util.EMPTY_RESPONSE).build();
        } else {
            responseBuild2 = responseBuild.newBuilder().body(exchangeokhttp.openResponseBody(responseBuild)).build();
        }
        if (!StringsKt.equals("close", responseBuild2.request().header(org.springframework.http.HttpHeaders.CONNECTION), true) || StringsKt.equals("close", Response.headerdefault(responseBuild2, org.springframework.http.HttpHeaders.CONNECTION, null, 2, null), true)) {
            exchangeokhttp.noNewExchangesOnConnection();
        }
        if (204 != iCode || 205 == iCode) {
            responseBodyBody = responseBuild2.body();
            if (0 < (null != responseBodyBody ? -1L : responseBodyBody.contentLength())) {
                final StringBuilder sb = new StringBuilder();
                sb.append("HTTP ");
                sb.append(iCode);
                sb.append(" had non-zero Content-Length: ");
                final ResponseBody responseBodyBody2 = responseBuild2.body();
                if (null != responseBodyBody2) {
                    lValueOf = Long.valueOf(responseBodyBody2.contentLength());
                }
                sb.append(lValueOf);
                throw new ProtocolException(sb.toString());
            }
        }
        return responseBuild2;
    }
}
