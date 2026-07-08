package okhttp3.internal.http;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.*;
import okhttp3.internal.Util;
import okio.GzipSource;
import okio.Okio;

import java.io.IOException;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class BridgeInterceptor implements Interceptor {
    private final CookieJar cookieJar;

    public BridgeInterceptor(final CookieJar cookieJar) {
        Intrinsics.checkNotNullParameter(cookieJar, "cookieJar");
        this.cookieJar = cookieJar;
    }
    public Response intercept(final Chain chain) throws IOException {
        final ResponseBody responseBodyBody;
        Intrinsics.checkNotNullParameter(chain, "chain");
        final Request request = chain.request();
        final Request.Builder builderNewBuilder = request.newBuilder();
        final RequestBody requestBodyBody = request.body();
        if (null != requestBodyBody) {
            final MediaType mediaTypeContentType = requestBodyBody.contentType();
            if (null != mediaTypeContentType) {
                builderNewBuilder.header(org.springframework.http.HttpHeaders.CONTENT_TYPE, mediaTypeContentType.toString());
            }
            final long jContentLength = requestBodyBody.contentLength();
            if (-1 == jContentLength) {
                builderNewBuilder.header(org.springframework.http.HttpHeaders.TRANSFER_ENCODING, "chunked");
                builderNewBuilder.removeHeader(org.springframework.http.HttpHeaders.CONTENT_LENGTH);
            } else {
                builderNewBuilder.header(org.springframework.http.HttpHeaders.CONTENT_LENGTH, String.valueOf(jContentLength));
                builderNewBuilder.removeHeader(org.springframework.http.HttpHeaders.TRANSFER_ENCODING);
            }
        }
        boolean z = false;
        if (null == request.header(org.springframework.http.HttpHeaders.HOST)) {
            builderNewBuilder.header(org.springframework.http.HttpHeaders.HOST, Util.toHostHeader(request.url(), false, 1, null));
        }
        if (null == request.header(org.springframework.http.HttpHeaders.CONNECTION)) {
            builderNewBuilder.header(org.springframework.http.HttpHeaders.CONNECTION, "Keep-Alive");
        }
        if (null == request.header(org.springframework.http.HttpHeaders.ACCEPT_ENCODING) && null == request.header(org.springframework.http.HttpHeaders.RANGE)) {
            builderNewBuilder.header(org.springframework.http.HttpHeaders.ACCEPT_ENCODING, "gzip");
            z = true;
        }
        final List<Cookie> listLoadForRequest = cookieJar.loadForRequest(request.url());
        if (!listLoadForRequest.isEmpty()) {
            builderNewBuilder.header(org.springframework.http.HttpHeaders.COOKIE, this.cookieHeader(listLoadForRequest));
        }
        if (null == request.header(org.springframework.http.HttpHeaders.USER_AGENT)) {
            builderNewBuilder.header(org.springframework.http.HttpHeaders.USER_AGENT, Util.userAgent);
        }
        final Response responseProceed = chain.proceed(builderNewBuilder.build());
        HttpHeaders.receiveHeaders(cookieJar, request.url(), responseProceed.headers());
        final Response.Builder builderRequest = responseProceed.newBuilder().request(request);
        if (z && StringsKt.equals("gzip", Response.headerdefault(responseProceed, org.springframework.http.HttpHeaders.CONTENT_ENCODING, null, 2, null), true) && HttpHeaders.promisesBody(responseProceed) && null != (responseBodyBody = responseProceed.body())) {
            final GzipSource gzipSource = new GzipSource(responseBodyBody.source());
            builderRequest.headers(responseProceed.headers().newBuilder().removeAll(org.springframework.http.HttpHeaders.CONTENT_ENCODING).removeAll(org.springframework.http.HttpHeaders.CONTENT_LENGTH).build());
            builderRequest.body(new RealResponseBody(Response.headerdefault(responseProceed, org.springframework.http.HttpHeaders.CONTENT_TYPE, null, 2, null), -1L, Okio.buffer(gzipSource)));
        }
        return builderRequest.build();
    }

    private String cookieHeader(final List<Cookie> list) {
        final StringBuilder sb = new StringBuilder();
        int i2 = 0;
        for (final Object obj : list) {
            final int i3 = i2 + 1;
            if (0 > i2) {
                CollectionsKt.throwIndexOverflow();
            }
            final Cookie cookie = (Cookie) obj;
            if (0 < i2) {
                sb.append("; ");
            }
            sb.append(cookie.name());
            sb.append('=');
            sb.append(cookie.value());
            i2 = i3;
        }
        final String string = sb.toString();
        checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
