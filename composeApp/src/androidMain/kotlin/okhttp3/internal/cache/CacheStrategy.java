package okhttp3.internal.cache;

import com.google.android.material.card.MaterialCardViewHelper;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import org.springframework.http.HttpHeaders;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static kotlin.jvm.internal.Intrinsics.checkNotNull;

public record CacheStrategy(Request networkRequest, Response cacheResponse) {
    public static final Companion Companion = new Companion(null);
    public Request getNetworkRequest() {
        return null;
    }
    public Response getCacheResponse() {
        return null;
    }
    public static final class Factory {
        private final Response cacheResponse;
        private final long nowMillis;
        private final Request request;
        private int ageSeconds;
        private String etag;
        private Date expires;
        private Date lastModified;
        private String lastModifiedString;
        private long receivedResponseMillis;
        private long sentRequestMillis;
        private Date servedDate;
        private String servedDateString;
        private CacheControl r0;
        public Factory(final long j2, final Request request, final Response response) {
            Intrinsics.checkNotNullParameter(request, "request");
            nowMillis = j2;
            this.request = request;
            cacheResponse = response;
            ageSeconds = -1;
            if (response != null) {
                sentRequestMillis = response.sentRequestAtMillis();
                receivedResponseMillis = response.receivedResponseAtMillis();
                final Headers headers = response.headers();
                final int size = headers.size();
                int i2 = 0;
                while (i2 < size) {
                    final int i3 = i2 + 1;
                    final String strName = headers.name(i2);
                    final String strValue = headers.value(i2);
                    DatesKt dates = null;
                    if (StringsKt.equals(strName, HttpHeaders.DATE, true)) {
                        servedDate = DatesKt.toHttpDateOrNull(strValue);
                        servedDateString = strValue;
                    } else if (StringsKt.equals(strName, HttpHeaders.EXPIRES, true)) {
                        expires = DatesKt.toHttpDateOrNull(strValue);
                    } else if (StringsKt.equals(strName, HttpHeaders.LAST_MODIFIED, true)) {
                        lastModified = DatesKt.toHttpDateOrNull(strValue);
                        lastModifiedString = strValue;
                    } else if (StringsKt.equals(strName, HttpHeaders.ETAG, true)) {
                        etag = strValue;
                    } else if (StringsKt.equals(strName, HttpHeaders.AGE, true)) {
                        ageSeconds = Util.toNonNegativeInt(strValue, -1);
                    }
                    i2 = i3;
                }
            }
        }
        public Request getRequestokhttp() {
            return request;
        }
        private boolean isFreshnessLifetimeHeuristic() {
            final Response response = cacheResponse;
            checkNotNull(response);
            return -1 == response.cacheControl().maxAgeSeconds() && null == this.expires;
        }
        public CacheStrategy compute() {
            final CacheStrategy cacheStrategyComputeCandidate = computeCandidate();
            return (null == cacheStrategyComputeCandidate.networkRequest() || !request.cacheControl().onlyIfCached()) ? cacheStrategyComputeCandidate : new CacheStrategy(null, null);
        }
        private CacheStrategy computeCandidate() {
            final String str;
            if (null == this.cacheResponse) {
                return new CacheStrategy(request, null);
            }
            if (request.isHttps() && null == this.cacheResponse.handshake()) {
                return new CacheStrategy(request, null);
            }
            if (!Companion.isCacheable(cacheResponse, request)) {
                return new CacheStrategy(request, null);
            }
            final CacheControl cacheControl = request.cacheControl();
            if (cacheControl.noCache() || this.hasConditions(request)) {
                return new CacheStrategy(request, null);
            }
            final CacheControl cacheControl2 = cacheResponse.cacheControl();
            final long jCacheResponseAge = this.cacheResponseAge();
            long jComputeFreshnessLifetime = this.computeFreshnessLifetime();
            if (-1 != cacheControl.maxAgeSeconds()) {
                jComputeFreshnessLifetime = Math.min(jComputeFreshnessLifetime, TimeUnit.SECONDS.toMillis(cacheControl.maxAgeSeconds()));
            }
            long millis = 0;
            final long millis2 = -1 != cacheControl.minFreshSeconds() ? TimeUnit.SECONDS.toMillis(cacheControl.minFreshSeconds()) : 0L;
            if (!cacheControl2.mustRevalidate() && -1 != cacheControl.maxStaleSeconds()) {
                millis = TimeUnit.SECONDS.toMillis(cacheControl.maxStaleSeconds());
            }
            if (!cacheControl2.noCache()) {
                final long j2 = millis2 + jCacheResponseAge;
                if (j2 < millis + jComputeFreshnessLifetime) {
                    final Response.Builder builderNewBuilder = cacheResponse.newBuilder();
                    if (j2 >= jComputeFreshnessLifetime) {
                        builderNewBuilder.addHeader(HttpHeaders.WARNING, "110 HttpURLConnection \"Response is stale\"");
                    }
                    if (86400000 < jCacheResponseAge && this.isFreshnessLifetimeHeuristic()) {
                        builderNewBuilder.addHeader(HttpHeaders.WARNING, "113 HttpURLConnection \"Heuristic expiration\"");
                    }
                    return new CacheStrategy(null, builderNewBuilder.build());
                }
            }
            String str2 = etag;
            if (null != str2) {
                str = HttpHeaders.IF_NONE_MATCH;
            } else {
                if (null != this.lastModified) {
                    str2 = lastModifiedString;
                } else if (null != this.servedDate) {
                    str2 = servedDateString;
                } else {
                    return new CacheStrategy(request, null);
                }
                str = HttpHeaders.IF_MODIFIED_SINCE;
            }
            final Headers.Builder builderNewBuilder2 = request.headers().newBuilder();
            checkNotNull(str2);
            builderNewBuilder2.addLenientokhttp(str, str2);
            return new CacheStrategy(request.newBuilder().headers(builderNewBuilder2.build()).build(), cacheResponse);
        }
        private long computeFreshnessLifetime() {
            final Long lValueOf;
            final Response response = cacheResponse;
            checkNotNull(response);
            if (-1 != response.cacheControl().maxAgeSeconds()) {
                return TimeUnit.SECONDS.toMillis(r0.maxAgeSeconds());
            }
            final Date date = expires;
            if (null != date) {
                final Date date2 = servedDate;
                lValueOf = date2 != null ? Long.valueOf(date2.getTime()) : null;
                final long time = date.getTime() - (lValueOf == null ? receivedResponseMillis : lValueOf.longValue());
                if (0 < time) {
                    return time;
                }
                return 0L;
            }
            if (lastModified == null || cacheResponse.request().url().query() != null) {
                return 0L;
            }
            final Date date3 = servedDate;
            lValueOf = date3 != null ? Long.valueOf(date3.getTime()) : null;
            final long jLongValue = lValueOf == null ? sentRequestMillis : lValueOf.longValue();
            final Date date4 = lastModified;
            checkNotNull(date4);
            final long time2 = jLongValue - date4.getTime();
            if (0 < time2) {
                return time2 / 10;
            }
            return 0L;
        }
        private long cacheResponseAge() {
            final Date date = servedDate;
            long jMax = date != null ? Math.max(0L, receivedResponseMillis - date.getTime()) : 0L;
            final int i2 = ageSeconds;
            if (-1 != i2) {
                jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(i2));
            }
            final long j2 = receivedResponseMillis;
            return jMax + (j2 - sentRequestMillis) + (nowMillis - j2);
        }
        private boolean hasConditions(final Request request) {
            return request.header(HttpHeaders.IF_MODIFIED_SINCE) != null || request.header(HttpHeaders.IF_NONE_MATCH) != null;
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
        public boolean isCacheable(final Response response, final Request request) {
            Intrinsics.checkNotNullParameter(response, "response");
            Intrinsics.checkNotNullParameter(request, "request");
            final int iCode = response.code();
            if (200 != iCode && 410 != iCode && 414 != iCode && 501 != iCode && 203 != iCode && 204 != iCode) {
                if (307 == iCode) {
                    if (null == Response.headerdefault(response, HttpHeaders.EXPIRES, null, 2, null) && -1 == response.cacheControl().maxAgeSeconds() && !response.cacheControl().isPublic() && !response.cacheControl().isPrivate()) {
                        return false;
                    }
                } else if (308 != iCode && 404 != iCode && 405 != iCode) {
                    switch (iCode) {
                        case MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION /* 300 */:
                        case 301:
                            break;
                        case 302:
                            break;
                        default:
                            return false;
                    }
                }
            }
            return !response.cacheControl().noStore() && !request.cacheControl().noStore();
        }
    }
}
