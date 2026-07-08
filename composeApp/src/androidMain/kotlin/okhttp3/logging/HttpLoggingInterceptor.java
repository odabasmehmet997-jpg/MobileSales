package okhttp3.logging;

import androidx.core.location.LocationRequestCompat;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.*;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import static kotlin.jvm.internal.Intrinsics.*;

public final class HttpLoggingInterceptor implements Interceptor {
    private final Logger logger;
    private volatile Set<String> headersToRedact;
    private volatile Level level;

    public HttpLoggingInterceptor() {
        this(null, 1, null);
    }

    public HttpLoggingInterceptor(final Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.logger = logger;
        headersToRedact = SetsKt.emptySet();
        level = Level.NONE;
    }

    public HttpLoggingInterceptor(final Logger logger, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i2 & 1) ? Logger.DEFAULT : logger);
    }

    public Level getLevel() {
        return level;
    }

    public HttpLoggingInterceptor setLevel(final Level level) {
        Intrinsics.checkNotNullParameter(level, "level");
        this.level(level);
        return this;
    }

    public void level(final Level level) {
        Intrinsics.checkNotNullParameter(level, "<set-?>");
        this.level = level;
    }

    public void redactHeader(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        final TreeSet treeSet = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(PrimitiveCompanionObjects.INSTANCE));
        CollectionsKt.addAll(treeSet, headersToRedact);
        treeSet.add(name);
        headersToRedact = treeSet;
    }
    public Level m1851deprecated_level() {
        return level;
    }
    public Response intercept(final Chain chain) {
        final String str;
        final char c2;
        final String string;
        Charset charset;
        final Long lValueOf;
        Intrinsics.checkNotNullParameter(chain, "chain");
        final Level level = this.level;
        final Request request = chain.request();
        if (Level.NONE == level) {
            try {
                return chain.proceed(request);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        final boolean z = Level.BODY == level;
        final boolean z2 = z || Level.HEADERS == level;
        final RequestBody requestBodyBody = request.body();
        final Connection connection = chain.connection();
        String string2 = "--> " +
                request.method() +
                ' ' +
                request.url() +
                (null != connection ? stringPlus(" ", connection.protocol()) : "");
        if (!z2 && null != requestBodyBody) {
            try {
                string2 = string2 + " (" + requestBodyBody.contentLength() + "-byte body)";
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        logger.log(string2);
        if (z2) {
            final Headers headers = request.headers();
            if (null != requestBodyBody) {
                final MediaType mediaTypeContentType = requestBodyBody.contentType();
                if (null != mediaTypeContentType && null == headers.get(HttpHeaders.CONTENT_TYPE)) {
                    logger.log(stringPlus("Content-Type: ", mediaTypeContentType));
                }
                try {
                    if (-1 != requestBodyBody.contentLength() && null == headers.get(HttpHeaders.CONTENT_LENGTH)) {
                        logger.log(stringPlus("Content-Length: ", Long.valueOf(requestBodyBody.contentLength())));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            final int size = headers.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.logHeader(headers, i2);
            }
            if (!z || null == requestBodyBody) {
                logger.log(stringPlus("--> END ", request.method()));
            } else if (this.bodyHasUnknownEncoding(request.headers())) {
                logger.log("--> END " + request.method() + " (encoded body omitted)");
            } else if (requestBodyBody.isDuplex()) {
                logger.log("--> END " + request.method() + " (duplex request body omitted)");
            } else if (requestBodyBody.isOneShot()) {
                logger.log("--> END " + request.method() + " (one-shot body omitted)");
            } else {
                final Buffer buffer = new Buffer();
                try {
                    requestBodyBody.writeTo(buffer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                final MediaType mediaTypeContentType2 = requestBodyBody.contentType();
                Charset UTF_8 = null == mediaTypeContentType2 ? null : mediaTypeContentType2.charset(StandardCharsets.UTF_8);
                if (null == UTF_8) {
                    UTF_8 = StandardCharsets.UTF_8;
                    checkNotNullExpressionValue(UTF_8, "UTF_8");
                }
                logger.log("");
                if (utf8.isProbablyUtf8(buffer)) {
                    logger.log(buffer.readString(UTF_8));
                    try {
                        logger.log("--> END " + request.method() + " (" + requestBodyBody.contentLength() + "-byte body)");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        logger.log("--> END " + request.method() + " (binary " + requestBodyBody.contentLength() + "-byte body omitted)");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
        final long jNanoTime = System.nanoTime();
        try {
            final Response responseProceed = chain.proceed(request);
            final long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime);
            final ResponseBody responseBodyBody = responseProceed.body();
            checkNotNull(responseBodyBody);
            final long jContentLength = responseBodyBody.contentLength();
            final String str2 = -1 != jContentLength ? jContentLength + "-byte" : "unknown-length";
            final Logger logger = this.logger;
            final StringBuilder sb2 = new StringBuilder();
            sb2.append("<-- ");
            sb2.append(responseProceed.code());
            if (0 == responseProceed.message().length()) {
                str = "-byte body omitted)";
                string = "";
                c2 = ' ';
            } else {
                final String strMessage = responseProceed.message();
                final StringBuilder sb3 = new StringBuilder();
                str = "-byte body omitted)";
                c2 = ' ';
                sb3.append(' ');
                sb3.append(strMessage);
                string = sb3.toString();
            }
            sb2.append(string);
            sb2.append(c2);
            sb2.append(responseProceed.request().url());
            sb2.append(" (");
            sb2.append(millis);
            sb2.append("ms");
            sb2.append(z2 ? "" : ", " + str2 + " body");
            sb2.append(')');
            logger.log(sb2.toString());
            if (z2) {
                final Headers headers2 = responseProceed.headers();
                final int size2 = headers2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    this.logHeader(headers2, i3);
                }
                if (!z || !okhttp3.internal.http.HttpHeaders.promisesBody(responseProceed)) {
                    this.logger.log("<-- END HTTP");
                } else if (this.bodyHasUnknownEncoding(responseProceed.headers())) {
                    this.logger.log("<-- END HTTP (encoded body omitted)");
                } else {
                    final BufferedSource bufferedSourceSource = responseBodyBody.source();
                    bufferedSourceSource.request(LocationRequestCompat.PASSIVE_INTERVAL);
                    Buffer buffer2 = bufferedSourceSource.getBuffer();
                    if (StringsKt.equals("gzip", headers2.get(HttpHeaders.CONTENT_ENCODING), true)) {
                        lValueOf = Long.valueOf(buffer2.size());
                        final GzipSource gzipSource = new GzipSource(buffer2.clone());
                        buffer2 = new Buffer();
                        buffer2.writeAll(gzipSource);
                        charset = null;
                        Closeable.closeFinally(gzipSource, null);
                    } else {
                        charset = null;
                        lValueOf = null;
                    }
                    final MediaType mediaTypeContentType3 = responseBodyBody.contentType();
                    Charset UTF_82 = null == mediaTypeContentType3 ? charset : mediaTypeContentType3.charset(StandardCharsets.UTF_8);
                    if (null == UTF_82) {
                        UTF_82 = StandardCharsets.UTF_8;
                        checkNotNullExpressionValue(UTF_82, "UTF_8");
                    }
                    if (!utf8.isProbablyUtf8(buffer2)) {
                        this.logger.log("");
                        this.logger.log("<-- END HTTP (binary " + buffer2.size() + str);
                        return responseProceed;
                    }
                    if (0 != jContentLength) {
                        this.logger.log("");
                        this.logger.log(buffer2.clone().readString(UTF_82));
                    }
                    if (null != lValueOf) {
                        this.logger.log("<-- END HTTP (" + buffer2.size() + "-byte, " + lValueOf + "-gzipped-byte body)");
                    } else {
                        this.logger.log("<-- END HTTP (" + buffer2.size() + "-byte body)");
                    }
                }
            }
            return responseProceed;
        } catch (final Exception e2) {
            logger.log(stringPlus("<-- HTTP FAILED: ", e2));
            try {
                throw e2;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void logHeader(final Headers headers, final int i2) {
        final String strValue = headersToRedact.contains(headers.name(i2)) ? "\u2588\u2588" : headers.value(i2);
        logger.log(headers.name(i2) + ": " + strValue);
    }

    private boolean bodyHasUnknownEncoding(final Headers headers) {
        final String str = headers.get(HttpHeaders.CONTENT_ENCODING);
        return null != str && !StringsKt.equals(str, "identity", true) && !StringsKt.equals(str, "gzip", true);
    }

    /* compiled from: HttpLoggingInterceptor.kt */
    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }
    public interface Logger {
        Logger DEFAULT = new Companion.DefaultLogger();
        Companion Companion = Logger.Companion.INSTANCE;

        void log(String str);
        final class Companion {
            static final Companion INSTANCE = new Companion();

            private Companion() {
            }
            private static final class DefaultLogger implements Logger {
                public void log(final String message) {
                    Intrinsics.checkNotNullParameter(message, "message");
                    Platform.logdefault(Platform.Companion.get(), message, 0, null, 6, null);
                }
            }
        }


    }
}
