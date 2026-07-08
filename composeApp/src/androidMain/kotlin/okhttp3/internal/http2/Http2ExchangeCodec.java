package okhttp3.internal.http2;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.*;
import okio.Sink;
import okio.Source;
import okio.Timeout;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static kotlin.jvm.internal.Intrinsics.*;

public final class Http2ExchangeCodec implements ExchangeCodec {
    public static final Companion Companion = new Companion(null);
    private static final String CONNECTION = "connection";
    private static final String HOST = "host";
    private static final String KEEP_ALIVE = "keep-alive";
    private static final String PROXY_CONNECTION = "proxy-connection";

    private static final String f2048TE = "te";
    private static final String TRANSFER_ENCODING = "transfer-encoding";
    private static final String ENCODING = "encoding";
    private static final String UPGRADE = "upgrade";
    private static final List<String> HTTP_2_SKIPPED_REQUEST_HEADERS = Util.immutableListOf(Http2ExchangeCodec.CONNECTION, Http2ExchangeCodec.HOST, Http2ExchangeCodec.KEEP_ALIVE, Http2ExchangeCodec.PROXY_CONNECTION, Http2ExchangeCodec.f2048TE, Http2ExchangeCodec.TRANSFER_ENCODING, Http2ExchangeCodec.ENCODING, Http2ExchangeCodec.UPGRADE, Header.TARGET_METHOD_UTF8, Header.TARGET_PATH_UTF8, Header.TARGET_SCHEME_UTF8, Header.TARGET_AUTHORITY_UTF8);
    private static final List<String> HTTP_2_SKIPPED_RESPONSE_HEADERS = Util.immutableListOf(Http2ExchangeCodec.CONNECTION, Http2ExchangeCodec.HOST, Http2ExchangeCodec.KEEP_ALIVE, Http2ExchangeCodec.PROXY_CONNECTION, Http2ExchangeCodec.f2048TE, Http2ExchangeCodec.TRANSFER_ENCODING, Http2ExchangeCodec.ENCODING, Http2ExchangeCodec.UPGRADE);
    private final RealInterceptorChain chain;
    private final RealConnection connection;
    private final Http2Connection http2Connection;
    private final Protocol protocol;
    private volatile boolean canceled;
    private volatile Http2Stream stream;

    public Http2ExchangeCodec(final OkHttpClient client, final RealConnection connection, final RealInterceptorChain chain, final Http2Connection http2Connection) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(http2Connection, "http2Connection");
        this.connection = connection;
        this.chain = chain;
        this.http2Connection = http2Connection;
        final List<Protocol> listProtocols = client.protocols();
        final Protocol protocol = Protocol.H2_PRIOR_KNOWLEDGE;
        this.protocol = listProtocols.contains(protocol) ? protocol : Protocol.HTTP_2;
    }
    public RealConnection getConnection() {
        return connection;
    }

    public Sink createRequestBody(final Request request, final long j2) {
        Intrinsics.checkNotNullParameter(request, "request");
        final Http2Stream http2Stream = stream;
        checkNotNull(http2Stream);
        return http2Stream.getSink();
    }
    public void writeRequestHeaders(final Request request) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        if (null != this.stream) {
            return;
        }
        stream = http2Connection.newStream(Http2ExchangeCodec.Companion.http2HeadersList(request), null != request.body());
        if (canceled) {
            final Http2Stream http2Stream = stream;
            checkNotNull(http2Stream);
            http2Stream.closeLater(ErrorCode.CANCEL);
            throw new IOException("Canceled");
        }
        final Http2Stream http2Stream2 = stream;
        checkNotNull(http2Stream2);
        final Timeout timeout = http2Stream2.readTimeout();
        final long readTimeoutMillisokhttp = chain.getReadTimeoutMillisokhttp();
        final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        timeout.timeout(readTimeoutMillisokhttp, timeUnit);
        final Http2Stream http2Stream3 = stream;
        checkNotNull(http2Stream3);
        http2Stream3.writeTimeout().timeout(chain.getWriteTimeoutMillisokhttp(), timeUnit);
    }
    public void flushRequest() throws IOException {
        http2Connection.flush();
    }
    public void finishRequest() throws IOException {
        final Http2Stream http2Stream = stream;
        checkNotNull(http2Stream);
        http2Stream.getSink().close();
    }
    public Response.Builder readResponseHeaders(final boolean z) throws IOException, NumberFormatException {
        final Http2Stream http2Stream = stream;
        if (null == http2Stream) {
            throw new IOException("stream wasn't created");
        }
        final Response.Builder http2HeadersList = Http2ExchangeCodec.Companion.readHttp2HeadersList(http2Stream.takeHeaders(), protocol);
        if (z && 100 == http2HeadersList.getCodeokhttp()) {
            return null;
        }
        return http2HeadersList;
    }
    public long reportedContentLength(final Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (HttpHeaders.promisesBody(response)) {
            return Util.headersContentLength(response);
        }
        return 0L;
    }

    public Source openResponseBodySource(final Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        final Http2Stream http2Stream = stream;
        checkNotNull(http2Stream);
        return http2Stream.getSourceokhttp();
    }
    public Headers trailers() {
        final Http2Stream http2Stream = stream;
        checkNotNull(http2Stream);
        try {
            return http2Stream.trailers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void cancel() {
        canceled = true;
        final Http2Stream http2Stream = stream;
        if (null == http2Stream) {
            return;
        }
        http2Stream.closeLater(ErrorCode.CANCEL);
    }

    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public List<Header> http2HeadersList(final Request request) {
            Intrinsics.checkNotNullParameter(request, "request");
            final Headers headers = request.headers();
            final ArrayList arrayList = new ArrayList(headers.size() + 4);
            arrayList.add(new Header(Header.TARGET_METHOD, request.method()));
            arrayList.add(new Header(Header.TARGET_PATH, RequestLine.INSTANCE.requestPath(request.url())));
            final String strHeader = request.header(org.springframework.http.HttpHeaders.HOST);
            if (null != strHeader) {
                arrayList.add(new Header(Header.TARGET_AUTHORITY, strHeader));
            }
            arrayList.add(new Header(Header.TARGET_SCHEME, request.url().scheme()));
            final int size = headers.size();
            int i2 = 0;
            while (i2 < size) {
                final int i3 = i2 + 1;
                final String strName = headers.name(i2);
                final Locale US = Locale.US;
                checkNotNullExpressionValue(US, "US");
                final String lowerCase = strName.toLowerCase(US);
                checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (!HTTP_2_SKIPPED_REQUEST_HEADERS.contains(lowerCase) || (areEqual(lowerCase, f2048TE) && areEqual(headers.value(i2), "trailers"))) {
                    arrayList.add(new Header(lowerCase, headers.value(i2)));
                }
                i2 = i3;
            }
            return arrayList;
        }

        public Response.Builder readHttp2HeadersList(final Headers headerBlock, final Protocol protocol) throws NumberFormatException, IOException {
            Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
            Intrinsics.checkNotNullParameter(protocol, "protocol");
            final Headers.Builder builder = new Headers.Builder();
            final int size = headerBlock.size();
            StatusLine statusLine = null;
            int i2 = 0;
            while (i2 < size) {
                final int i3 = i2 + 1;
                final String strName = headerBlock.name(i2);
                final String strValue = headerBlock.value(i2);
                if (!areEqual(strName, Header.RESPONSE_STATUS_UTF8)) {
                    if (!HTTP_2_SKIPPED_RESPONSE_HEADERS.contains(strName)) {
                        builder.addLenientokhttp(strName, strValue);
                    }
                } else {
                    statusLine = StatusLine.Companion.parse(stringPlus("HTTP/1.1 ", strValue));
                }
                i2 = i3;
            }
            if (null == statusLine) {
                throw new ProtocolException("Expected ':status' header not present");
            }
            return new Response.Builder().protocol(protocol).code(statusLine.code()).message(statusLine.message()).headers(builder.build());
        }
    }
}
