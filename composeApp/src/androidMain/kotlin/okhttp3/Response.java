package okhttp3;

import com.google.android.material.card.MaterialCardViewHelper;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.connection.Exchange;
import okio.Buffer;
import okio.BufferedSource;
import org.springframework.http.HttpHeaders;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;
import static kotlin.jvm.internal.Intrinsics.stringPlus;

public final class Response implements Closeable {
    private final ResponseBody body;
    private final Response cacheResponse;
    private final int code;
    private final Exchange exchange;
    private final Handshake handshake;
    private final Headers headers;
    private final String message;
    private final Response networkResponse;
    private final Response priorResponse;
    private final Protocol protocol;
    private final long receivedResponseAtMillis;
    private final Request request;
    private final long sentRequestAtMillis;
    private CacheControl lazyCacheControl;
    public Response(final Request request, final Protocol protocol, final String message, final int i2, final Handshake handshake, final Headers headers, final ResponseBody responseBody, final Response response, final Response response2, final Response response3, final long j2, final long j3, final Exchange exchange) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.request = request;
        this.protocol = protocol;
        this.message = message;
        code = i2;
        this.handshake = handshake;
        this.headers = headers;
        body = responseBody;
        networkResponse = response;
        cacheResponse = response2;
        priorResponse = response3;
        sentRequestAtMillis = j2;
        receivedResponseAtMillis = j3;
        this.exchange = exchange;
    }
    public static String headerdefault(final Response response, final String str, String str2, final int i2, final Object obj) {
        if (0 != (i2 & 2)) {
            str2 = null;
        }
        return response.header(str, str2);
    }
    public static Object headerdefault(Response response, String expires, Object o, int i, Object o1) {
        return o;
    }

    public static String header(Response response, String contentType, Object o, int i, Object o1) {
        return contentType;
    }

    public String header(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return Response.headerdefault(this, name, null, 2, null);
    }
    public Request request() {
        return request;
    }
    public Protocol protocol() {
        return protocol;
    }
    public String message() {
        return message;
    }
    public int code() {
        return code;
    }
    public Handshake handshake() {
        return handshake;
    }
    public Headers headers() {
        return headers;
    }
    public ResponseBody body() {
        return body;
    }
    public Response networkResponse() {
        return networkResponse;
    }
    public Response cacheResponse() {
        return cacheResponse;
    }
    public Response priorResponse() {
        return priorResponse;
    }
    public long sentRequestAtMillis() {
        return sentRequestAtMillis;
    }
    public long receivedResponseAtMillis() {
        return receivedResponseAtMillis;
    }
    public Exchange exchange() {
        return exchange;
    } 
    public Request m1843deprecated_request() {
        return request;
    } 
    public Protocol m1841deprecated_protocol() {
        return protocol;
    } 
    public int m1835deprecated_code() {
        return code;
    }
    public boolean isSuccessful() {
        final int i2 = code;
        return 200 <= i2 && 300 > i2;
    } 
    public String m1838deprecated_message() {
        return message;
    } 
    public Handshake m1836deprecated_handshake() {
        return handshake;
    }
    public List<String> headers(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return headers.values(name);
    }
    public String header(final String name, final String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        final String str2 = headers.get(name);
        return null == str2 ? str : str2;
    } 
    public Headers m1837deprecated_headers() {
        return headers;
    }
    public Headers trailers() throws IOException {
        final Exchange exchange = this.exchange;
        if (null != exchange) {
            return exchange.trailers();
        }
        throw new IllegalStateException("trailers not available");
    }
    public ResponseBody peekBody(final long j2) throws IOException {
        final ResponseBody responseBody = body;
        checkNotNull(responseBody);
        final BufferedSource bufferedSourcePeek = responseBody.source().peek();
        final Buffer buffer = new Buffer();
        bufferedSourcePeek.request(j2);
        buffer.write(bufferedSourcePeek, Math.min(j2, bufferedSourcePeek.getBuffer().size()));
        return ResponseBody.Companion.create(buffer, body.contentType(), buffer.size());
    } 
    public ResponseBody m1832deprecated_body() {
        return body;
    }
    public Builder newBuilder() {
        return new Builder(this);
    }
    public boolean isRedirect() {
        final int i2 = code;
        if (307 != i2 && 308 != i2) {
            switch (i2) {
                case MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION /* 300 */:
                case 301:
                case 302:
                case 303:
                    break;
                default:
                    return false;
            }
        }
        return true;
    }
    public Response m1839deprecated_networkResponse() {
        return networkResponse;
    }
    public Response m1834deprecated_cacheResponse() {
        return cacheResponse;
    }
    public Response m1840deprecated_priorResponse() {
        return priorResponse;
    }
    public List<Challenge> challenges() {
        final String str;
        final Headers headers = this.headers;
        final int i2 = code;
        if (401 == i2) {
            str = HttpHeaders.WWW_AUTHENTICATE;
        } else if (407 == i2) {
            str = HttpHeaders.PROXY_AUTHENTICATE;
        } else {
            return CollectionsKt.emptyList();
        }
        return okhttp3.internal.http.HttpHeaders.parseChallenges(headers, str);
    }
    public CacheControl cacheControl() {
        final CacheControl cacheControl = lazyCacheControl;
        if (null != cacheControl) {
            return cacheControl;
        }
        final CacheControl cacheControl2 = CacheControl.Companion.parse(headers);
        lazyCacheControl = cacheControl2;
        return cacheControl2;
    }
    public CacheControl m1833deprecated_cacheControl() {
        return this.cacheControl();
    }
    public long m1844deprecated_sentRequestAtMillis() {
        return sentRequestAtMillis;
    }
    public long m1842deprecated_receivedResponseAtMillis() {
        return receivedResponseAtMillis;
    }
    public void close() {
        final ResponseBody responseBody = body;
        if (null == responseBody) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        responseBody.close();
    }
    public String toString() {
        return "Response{protocol=" + protocol + ", code=" + code + ", message=" + message + ", url=" + request.url() + '}';
    }
    public static class Builder {
        private ResponseBody body;
        private Response cacheResponse;
        private int code;
        private Exchange exchange;
        private Handshake handshake;
        private Headers.Builder headers;
        private String message;
        private Response networkResponse;
        private Response priorResponse;
        private Protocol protocol;
        private long receivedResponseAtMillis;
        private Request request;
        private long sentRequestAtMillis;

        public Builder() {
            code = -1;
            headers = new Headers.Builder();
        }

        public Builder(final Response response) {
            Intrinsics.checkNotNullParameter(response, "response");
            code = -1;
            request = response.request();
            protocol = response.protocol();
            code = response.code();
            message = response.message();
            handshake = response.handshake();
            headers = response.headers().newBuilder();
            body = response.body();
            networkResponse = response.networkResponse();
            cacheResponse = response.cacheResponse();
            priorResponse = response.priorResponse();
            sentRequestAtMillis = response.sentRequestAtMillis();
            receivedResponseAtMillis = response.receivedResponseAtMillis();
            exchange = response.exchange();
        }

        public final Request getRequestokhttp() {
            return request;
        }

        public final void setRequestokhttp(final Request request) {
            this.request = request;
        }

        public final Protocol getProtocolokhttp() {
            return protocol;
        }

        public final void setProtocolokhttp(final Protocol protocol) {
            this.protocol = protocol;
        }

        public final int getCodeokhttp() {
            return code;
        }

        public final void setCodeokhttp(final int i2) {
            code = i2;
        }

        public final String getMessageokhttp() {
            return message;
        }

        public final void setMessageokhttp(final String str) {
            message = str;
        }

        public final Handshake getHandshakeokhttp() {
            return handshake;
        }

        public final void setHandshakeokhttp(final Handshake handshake) {
            this.handshake = handshake;
        }

        public final Headers.Builder getHeadersokhttp() {
            return headers;
        }

        public final void setHeadersokhttp(final Headers.Builder builder) {
            Intrinsics.checkNotNullParameter(builder, "<set-?>");
            headers = builder;
        }

        public final ResponseBody getBodyokhttp() {
            return body;
        }

        public final void setBodyokhttp(final ResponseBody responseBody) {
            body = responseBody;
        }

        public final Response getNetworkResponseokhttp() {
            return networkResponse;
        }

        public final void setNetworkResponseokhttp(final Response response) {
            networkResponse = response;
        }

        public final Response getCacheResponseokhttp() {
            return cacheResponse;
        }

        public final void setCacheResponseokhttp(final Response response) {
            cacheResponse = response;
        }

        public final Response getPriorResponseokhttp() {
            return priorResponse;
        }

        public final void setPriorResponseokhttp(final Response response) {
            priorResponse = response;
        }

        public final long getSentRequestAtMillisokhttp() {
            return sentRequestAtMillis;
        }

        public final void setSentRequestAtMillisokhttp(final long j2) {
            sentRequestAtMillis = j2;
        }

        public final long getReceivedResponseAtMillisokhttp() {
            return receivedResponseAtMillis;
        }

        public final void setReceivedResponseAtMillisokhttp(final long j2) {
            receivedResponseAtMillis = j2;
        }

        public final Exchange getExchangeokhttp() {
            return exchange;
        }

        public final void setExchangeokhttp(final Exchange exchange) {
            this.exchange = exchange;
        }

        public Builder request(final Request request) {
            Intrinsics.checkNotNullParameter(request, "request");
            this.request = request;
            return this;
        }

        public Builder protocol(final Protocol protocol) {
            Intrinsics.checkNotNullParameter(protocol, "protocol");
            this.protocol = protocol;
            return this;
        }

        public Builder code(final int i2) {
            this.code = i2;
            return this;
        }

        public Builder message(final String message) {
            Intrinsics.checkNotNullParameter(message, "message");
            this.message = message;
            return this;
        }

        public Builder handshake(final Handshake handshake) {
            this.handshake = handshake;
            return this;
        }

        public Builder header(final String name, final String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            this.headers.set(name, value);
            return this;
        }

        public Builder addHeader(final String name, final String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            this.headers.add(name, value);
            return this;
        }

        public Builder removeHeader(final String name) {
            Intrinsics.checkNotNullParameter(name, "name");
            this.headers.removeAll(name);
            return this;
        }

        public Builder headers(final Headers headers) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            this.setHeadersokhttp(headers.newBuilder());
            return this;
        }

        public Builder body(final ResponseBody responseBody) {
            this.body = responseBody;
            return this;
        }

        public Builder networkResponse(final Response response) {
            this.checkSupportResponse("networkResponse", response);
            this.networkResponse = response;
            return this;
        }

        public Builder cacheResponse(final Response response) {
            this.checkSupportResponse("cacheResponse", response);
            this.cacheResponse = response;
            return this;
        }

        private final void checkSupportResponse(final String str, final Response response) {
            if (null == response) {
                return;
            }
            if (null != response.body()) {
                throw new IllegalArgumentException(stringPlus(str, ".body != null"));
            }
            if (null != response.networkResponse()) {
                throw new IllegalArgumentException(stringPlus(str, ".networkResponse != null"));
            }
            if (null != response.cacheResponse()) {
                throw new IllegalArgumentException(stringPlus(str, ".cacheResponse != null"));
            }
            if (null != response.priorResponse()) {
                throw new IllegalArgumentException(stringPlus(str, ".priorResponse != null"));
            }
        }

        public Builder priorResponse(final Response response) {
            this.checkPriorResponse(response);
            this.priorResponse = response;
            return this;
        }

        private final void checkPriorResponse(final Response response) {
            if (null != response && null != response.body()) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public Builder sentRequestAtMillis(final long j2) {
            this.sentRequestAtMillis = j2;
            return this;
        }

        public Builder receivedResponseAtMillis(final long j2) {
            this.receivedResponseAtMillis = j2;
            return this;
        }

        public final void initExchangeokhttp(final Exchange deferredTrailers) {
            Intrinsics.checkNotNullParameter(deferredTrailers, "deferredTrailers");
            exchange = deferredTrailers;
        }

        public Response build() {
            final int i2 = code;
            if (0 > i2) {
                throw new IllegalStateException(stringPlus("code < 0: ", Integer.valueOf(this.code)));
            }
            final Request request = this.request;
            if (null == request) {
                throw new IllegalStateException("request == null");
            }
            final Protocol protocol = this.protocol;
            if (null == protocol) {
                throw new IllegalStateException("protocol == null");
            }
            final String str = message;
            if (null != str) {
                return new Response(request, protocol, str, i2, handshake, headers.build(), body, networkResponse, cacheResponse, priorResponse, sentRequestAtMillis, receivedResponseAtMillis, exchange);
            }
            throw new IllegalStateException("message == null");
        }
    }
}
