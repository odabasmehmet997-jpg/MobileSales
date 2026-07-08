package okhttp3.internal.cache;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;

import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.http.HttpHeaders;
import okhttp3.internal.http.HttpMethod;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;
import com.proje.mobilesales.R;

public final class CacheInterceptor implements Interceptor {
    public static final Companion Companion = new Companion(null);
    private final Cache cache;
    public CacheInterceptor(Cache cache) {
        this.cache = cache;
    }
    public Cache getCache$okhttp() {
        return this.cache;
    }
    public Response intercept(Chain chain) throws IOException {
        ResponseBody responseBodyBody = null;
        ResponseBody responseBodyBody2;
        Intrinsics.checkNotNullParameter(chain, "chain");
        retrofit2.Call<T> call = (retrofit2.Call<T>) chain.call();
        Cache cache = this.cache;
        Response response = cache == null ? null : cache.get(chain.request());
        CacheStrategy cacheStrategyCompute = new CacheStrategy.Factory(System.currentTimeMillis(), chain.request(), response).compute();
        Request networkRequest = cacheStrategyCompute.getNetworkRequest();
        Response cacheResponse = cacheStrategyCompute.getCacheResponse();
        Cache cache2 = this.cache;
        if (cache2 != null) {
            cache2.trackResponse(cacheStrategyCompute);
        }
        RealCall realCall = null;
        EventListener eventListenerokhttp = realCall != null ? realCall.getEventListener() : null;
        if (eventListenerokhttp == null) {
            eventListenerokhttp = EventListener.NONE;
        }
        if (response != null && cacheResponse == null && (responseBodyBody2 = response.body()) != null) {
            Util.closeQuietly(responseBodyBody2);
        }
        if (networkRequest == null && cacheResponse == null) {
            Response responseBuild = new Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(TypedValues.PositionType.TYPE_PERCENT_HEIGHT).message("Unsatisfiable Request (only-if-cached)").body(Util.EMPTY_RESPONSE).sentRequestAtMillis(-1L).receivedResponseAtMillis(System.currentTimeMillis()).build();
            eventListenerokhttp.satisfactionFailure(call, responseBuild);
            return responseBuild;
        }
        if (networkRequest == null) {
            Intrinsics.checkNotNull(cacheResponse);
            Response responseBuild2 = cacheResponse.newBuilder().cacheResponse(Companion.stripBody(cacheResponse)).build();
            eventListenerokhttp.cacheHit(call, responseBuild2);
            return responseBuild2;
        }
        if (cacheResponse != null) {
            eventListenerokhttp.cacheConditionalHit(call, cacheResponse);
        } else if (this.cache != null) {
            eventListenerokhttp.cacheMiss(call);
        }
        try {
            Response responseProceed = chain.proceed(networkRequest);

            if (responseProceed == null && response != null && responseBodyBody != null) {
                eventListenerokhttp.cacheMiss(call);
                return response;
            }
            if (cacheResponse != null) {
                if (responseProceed != null && responseProceed.code() == 304) {
                    Response.Builder builderNewBuilder = cacheResponse.newBuilder();
                    Companion companion = Companion;
                    Response responseBuild3 = builderNewBuilder.headers(companion.combine(cacheResponse.headers(), responseProceed.headers())).sentRequestAtMillis(responseProceed.sentRequestAtMillis()).receivedResponseAtMillis(responseProceed.receivedResponseAtMillis()).cacheResponse(companion.stripBody(cacheResponse)).networkResponse(companion.stripBody(responseProceed)).build();
                    ResponseBody responseBodyBody3 = responseProceed.body();
                    Intrinsics.checkNotNull(responseBodyBody3);
                    responseBodyBody3.close();
                    Cache cache3 = this.cache;
                    Intrinsics.checkNotNull(cache3);
                    cache3.trackConditionalCacheHit$okhttp();
                    this.cache.update(cacheResponse, responseBuild3);
                    eventListenerokhttp.cacheHit(call, responseBuild3);
                    return responseBuild3;
                }
                ResponseBody responseBodyBody4 = cacheResponse.body();
                if (responseBodyBody4 != null) {
                    Util.closeQuietly(responseBodyBody4);
                }
            }
            Intrinsics.checkNotNull(responseProceed);
            Response.Builder builderNewBuilder2 = responseProceed.newBuilder();
            Companion companion2 = Companion;
            Response responseBuild4 = builderNewBuilder2.cacheResponse(companion2.stripBody(cacheResponse)).networkResponse(companion2.stripBody(responseProceed)).build();
            if (this.cache != null) {
                if (HttpHeaders.promisesBody(responseBuild4) && CacheStrategy.Companion.isCacheable(responseBuild4, networkRequest)) {
                    Response responseCacheWritingResponse = cacheWritingResponse(this.cache.put(responseBuild4), responseBuild4);
                    if (cacheResponse != null) {
                        eventListenerokhttp.cacheMiss(call);
                    }
                    return responseCacheWritingResponse;
                }
                if (HttpMethod.INSTANCE.invalidatesCache(networkRequest.method())) {
                    try {
                        this.cache.remove$okhttp(networkRequest);
                    } catch (IOException unused) {
                        throw new RuntimeException(unused);
                    }
                }
            }
            return responseBuild4;
        } finally {
            if (response != null && (responseBodyBody = response.body()) != null) {
                Util.closeQuietly(responseBodyBody);
            }
        }
    }
    private Response cacheWritingResponse(final CacheRequest cacheRequest, Response response) throws IOException {
        if (cacheRequest == null) {
            return response;
        }
        Sink sinkBody = cacheRequest.body();
        ResponseBody responseBodyBody = response.body();
        Intrinsics.checkNotNull(responseBodyBody);
        final BufferedSource bufferedSourceSource = responseBodyBody.source();
        final BufferedSink bufferedSinkBuffer = Okio.buffer(sinkBody);
        Source source = new Source() {
            private boolean cacheRequestClosed;
            public long read(Buffer sink, long j2) throws IOException {
                Intrinsics.checkNotNullParameter(sink, "sink");
                try {
                    long j3 = 0;
                    try {
                        j3 = bufferedSourceSource.read(sink, j2);
                    } catch (DataFormatException e) {
                        throw new RuntimeException(e);
                    }
                    if (j3 == -1) {
                        if (!this.cacheRequestClosed) {
                            this.cacheRequestClosed = true;
                            bufferedSinkBuffer.close();
                        }
                        return -1L;
                    }
                    sink.copyTo(bufferedSinkBuffer.getBuffer(), sink.size() - j3, j3);
                    bufferedSinkBuffer.emitCompleteSegments();
                    return j3;
                } catch (IOException e2) {
                    if (!this.cacheRequestClosed) {
                        this.cacheRequestClosed = true;
                        cacheRequest.abort();
                    }
                    throw e2;
                }
            }
            public Timeout timeout() {
                return bufferedSourceSource.timeout();
            }
            public void close() throws IOException {
                if (!this.cacheRequestClosed && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.cacheRequestClosed = true;
                    cacheRequest.abort();
                }
                bufferedSourceSource.close();
            }
        };
        return response.newBuilder().body(new RealResponseBody(Response.header(response, org.springframework.http.HttpHeaders.CONTENT_TYPE, null, 2, null), response.body().contentLength(), Okio.buffer(source))).build();
    }
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {}
        public Response stripBody(Response response) {
            return (response == null ? null : response.body()) != null ? response.newBuilder().body(null).build() : response;
        }
        public Headers combine(Headers headers, Headers headers2) {
            Headers.Builder builder = new Headers.Builder();
            int size = headers.size();
            int i2 = 0;
            int i3 = 0;
            while (i3 < size) {
                int i4 = i3 + 1;
                String strName = headers.name(i3);
                String strValue = headers.value(i3);
                if ((!StringsKt.equals(org.springframework.http.HttpHeaders.WARNING, strName, true) || !StringsKt.startsWith(strValue, "1", false)) && (isContentSpecificHeader(strName) || !isEndToEnd(strName) || headers2.get(strName) == null)) {
                    builder.addLenient(strName);
                }
                i3 = i4;
            }
            int size2 = headers2.size();
            while (i2 < size2) {
                int i5 = i2 + 1;
                String strName2 = headers2.name(i2);
                if (!isContentSpecificHeader(strName2) && isEndToEnd(strName2)) {
                    builder.addLenient(strName2);
                }
                i2 = i5;
            }
            return builder.build();
        }
        private boolean isEndToEnd(String str) {
            return !StringsKt.equals(org.springframework.http.HttpHeaders.CONNECTION, str, true) && !StringsKt.equals("Keep-Alive", str, true) && !StringsKt.equals(org.springframework.http.HttpHeaders.PROXY_AUTHENTICATE, str, true) && !StringsKt.equals(org.springframework.http.HttpHeaders.PROXY_AUTHORIZATION, str, true) && !StringsKt.equals(org.springframework.http.HttpHeaders.TE, str, true) && !StringsKt.equals("Trailers", str, true) && !StringsKt.equals(org.springframework.http.HttpHeaders.TRANSFER_ENCODING, str, true) && !StringsKt.equals(org.springframework.http.HttpHeaders.UPGRADE, str, true);
        }
        private boolean isContentSpecificHeader(String str) {
            return StringsKt.equals(org.springframework.http.HttpHeaders.CONTENT_LENGTH, str, true) || StringsKt.equals(org.springframework.http.HttpHeaders.CONTENT_ENCODING, str, true) || StringsKt.equals(org.springframework.http.HttpHeaders.CONTENT_TYPE, str, true);
        }
    }
}
