package okhttp3.internal.http1;

import com.fasterxml.jackson.core.JsonFactory;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.*;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.http.RequestLine;
import okhttp3.internal.http.StatusLine;
import okio.*;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;

import static kotlin.jvm.internal.Intrinsics.*;

public final class Http1ExchangeCodec implements ExchangeCodec {
    public static final Companion Companion = new Companion(null);
    private static final long NO_CHUNK_YET = -1;
    private static final int STATE_CLOSED = 6;
    private static final int STATE_IDLE = 0;
    private static final int STATE_OPEN_REQUEST_BODY = 1;
    private static final int STATE_OPEN_RESPONSE_BODY = 4;
    private static final int STATE_READING_RESPONSE_BODY = 5;
    private static final int STATE_READ_RESPONSE_HEADERS = 3;
    private static final int STATE_WRITING_REQUEST_BODY = 2;
    private final OkHttpClient client;
    private final RealConnection connection;
    private final HeadersReader headersReader;
    private final BufferedSink sink;
    private final BufferedSource source;
    private int state;
    private Headers trailers;

    public Http1ExchangeCodec(final OkHttpClient okHttpClient, final RealConnection connection, final BufferedSource source, final BufferedSink sink) {
        Intrinsics.checkNotNullParameter(connection, "connection");
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(sink, "sink");
        client = okHttpClient;
        this.connection = connection;
        this.source = source;
        this.sink = sink;
        headersReader = new HeadersReader(source);
    }

    public RealConnection getConnection() {
        return connection;
    }

    private boolean isChunked(final Response response) {
        return StringsKt.equals("chunked", Response.headerdefault(response, HttpHeaders.TRANSFER_ENCODING, null, 2, null), true);
    }

    private boolean isChunked(final Request request) {
        return StringsKt.equals("chunked", request.header(HttpHeaders.TRANSFER_ENCODING), true);
    }

    public boolean isClosed() {
        return 6 == this.state;
    }

    public Sink createRequestBody(final Request request, final long j2) throws ProtocolException {
        Intrinsics.checkNotNullParameter(request, "request");
        if (null != request.body() && request.body().isDuplex()) {
            throw new ProtocolException("Duplex connections are not supported for HTTP/1");
        }
        if (this.isChunked(request)) {
            return this.newChunkedSink();
        }
        if (-1 != j2) {
            return this.newKnownLengthSink();
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    public void cancel() {
        this.connection.cancel();
    }

    public void writeRequestHeaders(final Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        final RequestLine requestLine = RequestLine.INSTANCE;
        final Proxy.Type type = this.connection.route().proxy().type();
        checkNotNullExpressionValue(type, "connection.route().proxy.type()");
        this.writeRequest(request.headers(), requestLine.get(request, type));
    }

    public long reportedContentLength(final Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (!okhttp3.internal.http.HttpHeaders.promisesBody(response)) {
            return 0L;
        }
        if (this.isChunked(response)) {
            return -1L;
        }
        return Util.headersContentLength(response);
    }
    public Source openResponseBodySource(final Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        if (!okhttp3.internal.http.HttpHeaders.promisesBody(response)) {
            return this.newFixedLengthSource(0L);
        }
        if (this.isChunked(response)) {
            return this.newChunkedSource(response.request().url());
        }
        final long jHeadersContentLength = Util.headersContentLength(response);
        if (-1 != jHeadersContentLength) {
            return this.newFixedLengthSource(jHeadersContentLength);
        }
        return this.newUnknownLengthSource();
    }

    public Headers trailers() {
        if (6 != this.state) {
            throw new IllegalStateException("too early; can't read the trailers yet");
        }
        final Headers headers = trailers;
        return null == headers ? Util.EMPTY_HEADERS : headers;
    }

    public void flushRequest() throws IOException {
        sink.flush();
    }

    public void finishRequest() {
        try {
            sink.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeRequest(final Headers headers, final String requestLine) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(requestLine, "requestLine");
        final int i2 = state;
        if (0 != i2) {
            throw new IllegalStateException(stringPlus("state: ", Integer.valueOf(i2)));
        }
        try {
            sink.writeUtf8(requestLine).writeUtf8("\r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final int size = headers.size();
        for (int i3 = 0; i3 < size; i3++) {
            try {
                sink.writeUtf8(headers.name(i3)).writeUtf8(": ").writeUtf8(headers.value(i3)).writeUtf8("\r\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            sink.writeUtf8("\r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        state = 1;
    }
    public Response.Builder readResponseHeaders(final boolean z) {
        final int i2 = state;
        if (1 != i2 && 2 != i2 && 3 != i2) {
            throw new IllegalStateException(stringPlus("state: ", Integer.valueOf(i2)));
        }
        final StatusLine statusLine;
        try {
            statusLine = StatusLine.Companion.parse(headersReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final Response.Builder builderHeaders;
        try {
            builderHeaders = new Response.Builder().protocol(statusLine.protocol()).code(statusLine.code()).message(statusLine.message()).headers(headersReader.readHeaders());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (z && 100 == statusLine.code()) {
            return null;
        }
        final int i3 = statusLine.code();
        if (100 == i3) {
            state = 3;
            return builderHeaders;
        }
        if (102 <= i3 && 200 > i3) {
            state = 3;
            return builderHeaders;
        }
        state = 4;
        return builderHeaders;
    }

    private Sink newChunkedSink() {
        final int i2 = state;
        if (1 != i2) {
            throw new IllegalStateException(stringPlus("state: ", Integer.valueOf(i2)));
        }
        state = 2;
        return new ChunkedSink(this);
    }

    private Sink newKnownLengthSink() {
        final int i2 = state;
        if (1 != i2) {
            throw new IllegalStateException(stringPlus("state: ", Integer.valueOf(i2)));
        }
        state = 2;
        return new KnownLengthSink(this);
    }

    private Source newFixedLengthSource(final long j2) {
        final int i2 = state;
        if (4 != i2) {
            throw new IllegalStateException(stringPlus("state: ", Integer.valueOf(i2)));
        }
        state = 5;
        return new FixedLengthSource(this, j2);
    }

    private Source newChunkedSource(final HttpUrl httpUrl) {
        final int i2 = state;
        if (4 != i2) {
            throw new IllegalStateException(stringPlus("state: ", Integer.valueOf(i2)));
        }
        state = 5;
        return new ChunkedSource(this, httpUrl);
    }

    private Source newUnknownLengthSource() {
        final int i2 = state;
        if (4 != i2) {
            throw new IllegalStateException(stringPlus("state: ", Integer.valueOf(i2)));
        }
        state = 5;
        this.connection.noNewExchangesokhttp();
        return new UnknownLengthSource(this);
    }

    private void detachTimeout(final ForwardingTimeout forwardingTimeout) {
        final Timeout timeoutDelegate = forwardingTimeout.delegate();
        forwardingTimeout.setDelegate(Timeout.NONE);
        timeoutDelegate.clearDeadline();
        timeoutDelegate.clearTimeout();
    }

    public void skipConnectBody(final Response response) {
        Intrinsics.checkNotNullParameter(response, "response");
        final long jHeadersContentLength = Util.headersContentLength(response);
        if (-1 == jHeadersContentLength) {
            return;
        }
        final Source sourceNewFixedLengthSource = this.newFixedLengthSource(jHeadersContentLength);
        try {
            Util.skipAll(sourceNewFixedLengthSource, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            sourceNewFixedLengthSource.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
    private final class KnownLengthSink implements Sink {
        final Http1ExchangeCodec this0;
        private final ForwardingTimeout timeout;
        private boolean closed;

        public KnownLengthSink(final Http1ExchangeCodec this0) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            this.this0 = this0;
            timeout = new ForwardingTimeout(this0.sink.timeout());
        }

        public Timeout timeout() {
            return timeout;
        }

        public void write(final Buffer source, final long j2) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            if (closed) {
                throw new IllegalStateException("closed");
            }
            Util.checkOffsetAndCount(source.size(), 0L, j2);
            this0.sink.write(source, j2);
        }

        public void flush() throws IOException {
            if (closed) {
                return;
            }
            this0.sink.flush();
        }
        public void close() {
            if (closed) {
                return;
            }
            closed = true;
            this0.detachTimeout(timeout);
            this0.state = 3;
        }
    }
    private final class ChunkedSink implements Sink {
        final Http1ExchangeCodec this0;
        private final ForwardingTimeout timeout;
        private boolean closed;

        public ChunkedSink(final Http1ExchangeCodec this0) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            this.this0 = this0;
            timeout = new ForwardingTimeout(this0.sink.timeout());
        }
        public Timeout timeout() {
            return timeout;
        }

        public void write(final Buffer source, final long j2) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            if (closed) {
                throw new IllegalStateException("closed");
            }
            if (0 == j2) {
                return;
            }
            this0.sink.writeHexadecimalUnsignedLong(j2);
            this0.sink.writeUtf8("\r\n");
            this0.sink.write(source, j2);
            this0.sink.writeUtf8("\r\n");
        }

        public synchronized void flush() {
            if (closed) {
                return;
            }
            try {
                this0.sink.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public synchronized void close() {
            if (closed) {
                return;
            }
            closed = true;
            try {
                this0.sink.writeUtf8("0\r\n\r\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            this0.detachTimeout(timeout);
            this0.state = 3;
        }
    }

    private abstract class AbstractSource implements Source {
        final Http1ExchangeCodec this0;
        private final ForwardingTimeout timeout;
        private boolean closed;

        protected AbstractSource(final Http1ExchangeCodec this0) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            this.this0 = this0;
            timeout = new ForwardingTimeout(this0.source.timeout());
        }
        public abstract void close() throws IOException;

        protected final ForwardingTimeout getTimeout() {
            return timeout;
        }

        protected final boolean getClosed() {
            return closed;
        }

        protected final void setClosed(final boolean z) {
            closed = z;
        }

        public Timeout timeout() {
            return timeout;
        }

        public long read(final Buffer sink, final long j2) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            try {
                try {
                    return this0.source.read(sink, j2);
                } catch (DataFormatException e) {
                    throw new RuntimeException(e);
                }
            } catch (final IOException e2) {
                this0.getConnection().noNewExchangesokhttp();
                this.responseBodyComplete();
                throw e2;
            }
        }

        public final void responseBodyComplete() {
            if (6 == this.this0.state) {
                return;
            }
            if (5 == this.this0.state) {
                this0.detachTimeout(timeout);
                this0.state = 6;
                return;
            }
            throw new IllegalStateException(stringPlus("state: ", Integer.valueOf(this0.state)));
        }
    }
    private final class FixedLengthSource extends AbstractSource {
        final Http1ExchangeCodec this0;
        private long bytesRemaining;
        public FixedLengthSource(final Http1ExchangeCodec this0, final long j2) {
            super(this0);
            Intrinsics.checkNotNullParameter(this0, "this0");
            this.this0 = this0;
            bytesRemaining = j2;
            if (0 == j2) {
                this.responseBodyComplete();
            }
        }
        public long read(final Buffer sink, final long j2) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (0 > j2) {
                throw new IllegalArgumentException(stringPlus("byteCount < 0: ", Long.valueOf(j2)));
            }
            if (this.getClosed()) {
                throw new IllegalStateException("closed");
            }
            final long j3 = bytesRemaining;
            if (0 == j3) {
                return -1L;
            }
            final long j4 = super.read(sink, Math.min(j3, j2));
            if (-1 == j4) {
                this0.getConnection().noNewExchangesokhttp();
                final ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                this.responseBodyComplete();
                throw protocolException;
            }
            final long j5 = bytesRemaining - j4;
            bytesRemaining = j5;
            if (0 == j5) {
                this.responseBodyComplete();
            }
            return j4;
        }
        public void close() {
            if (this.getClosed()) {
                return;
            }
            if (0 != this.bytesRemaining && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                this0.getConnection().noNewExchangesokhttp();
                this.responseBodyComplete();
            }
            this.setClosed(true);
        }
    }
    private final class ChunkedSource extends AbstractSource {
        final Http1ExchangeCodec this0;
        private final HttpUrl url;
        private long bytesRemainingInChunk;
        private boolean hasMoreChunks;

        public ChunkedSource(final Http1ExchangeCodec this0, final HttpUrl url) {
            super(this0);
            Intrinsics.checkNotNullParameter(this0, "this0");
            Intrinsics.checkNotNullParameter(url, "url");
            this.this0 = this0;
            this.url = url;
            bytesRemainingInChunk = -1L;
            hasMoreChunks = true;
        }
        public long read(final Buffer sink, final long j2) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (0 > j2) {
                throw new IllegalArgumentException(stringPlus("byteCount < 0: ", Long.valueOf(j2)));
            }
            if (this.getClosed()) {
                throw new IllegalStateException("closed");
            }
            if (!hasMoreChunks) {
                return -1L;
            }
            final long j3 = bytesRemainingInChunk;
            if (0 == j3 || -1 == j3) {
                this.readChunkSize();
                if (!hasMoreChunks) {
                    return -1L;
                }
            }
            final long j4 = super.read(sink, Math.min(j2, bytesRemainingInChunk));
            if (-1 == j4) {
                this0.getConnection().noNewExchangesokhttp();
                final ProtocolException protocolException = new ProtocolException("unexpected end of stream");
                this.responseBodyComplete();
                throw protocolException;
            }
            bytesRemainingInChunk -= j4;
            return j4;
        }

        private void readChunkSize() throws IOException {
            if (-1 != this.bytesRemainingInChunk) {
                this0.source.readUtf8LineStrict();
            }
            try {
                bytesRemainingInChunk = this0.source.readHexadecimalUnsignedLong();
                final String string = StringsKt.trim(this0.source.readUtf8LineStrict()).toString();
                if (0 > this.bytesRemainingInChunk || (0 < string.length() && !StringsKt.startsWith(string, ";", false))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + bytesRemainingInChunk + string + JsonFactory.DEFAULT_QUOTE_CHAR);
                }
                if (0 == this.bytesRemainingInChunk) {
                    hasMoreChunks = false;
                    final Http1ExchangeCodec http1ExchangeCodec = this0;
                    http1ExchangeCodec.trailers = http1ExchangeCodec.headersReader.readHeaders();
                    final OkHttpClient okHttpClient = this0.client;
                    checkNotNull(okHttpClient);
                    final CookieJar cookieJar = okHttpClient.cookieJar();
                    final HttpUrl httpUrl = url;
                    final Headers headers = this0.trailers;
                    checkNotNull(headers);
                    okhttp3.internal.http.HttpHeaders.receiveHeaders(cookieJar, httpUrl, headers);
                    this.responseBodyComplete();
                }
            } catch (final NumberFormatException e2) {
                throw new ProtocolException(e2.getMessage());
            }
        }
        public void close() {
            if (this.getClosed()) {
                return;
            }
            if (hasMoreChunks && !Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                this0.getConnection().noNewExchangesokhttp();
                this.responseBodyComplete();
            }
            this.setClosed(true);
        }
    }
    private final class UnknownLengthSource extends AbstractSource {
        final Http1ExchangeCodec this0;
        private boolean inputExhausted;

        public UnknownLengthSource(final Http1ExchangeCodec this0) {
            super(this0);
            Intrinsics.checkNotNullParameter(this0, "this0");
            this.this0 = this0;
        }
        public long read(final Buffer sink, final long j2) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (0 > j2) {
                throw new IllegalArgumentException(stringPlus("byteCount < 0: ", Long.valueOf(j2)));
            }
            if (this.getClosed()) {
                throw new IllegalStateException("closed");
            }
            if (inputExhausted) {
                return -1L;
            }
            final long j3 = super.read(sink, j2);
            if (-1 != j3) {
                return j3;
            }
            inputExhausted = true;
            this.responseBodyComplete();
            return -1L;
        }
        public void close() {
            if (this.getClosed()) {
                return;
            }
            if (!inputExhausted) {
                this.responseBodyComplete();
            }
            this.setClosed(true);
        }
    }
}
