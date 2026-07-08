package okhttp3;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.http1.HeadersReader;
import okio.*;

import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

import static kotlin.jvm.internal.Intrinsics.areEqual;
import static kotlin.jvm.internal.Intrinsics.stringPlus;

public final class MultipartReader implements Closeable {
    public static final Companion Companion = new Companion(null);
    private static final Options afterBoundaryOptions;

    static {
        final Options.Companion companion = Options.Companion;
        final ByteString.Companion companion2 = ByteString.Companion;
      try {
        afterBoundaryOptions = companion.of(companion2.encodeUtf8("\r\n"), companion2.encodeUtf8("--"), companion2.encodeUtf8(" "), companion2.encodeUtf8("\t"));
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }

    private final String boundary;
    private final ByteString crlfDashDashBoundary;
    private final ByteString dashDashBoundary;
    private final BufferedSource source;
    private boolean closed;
    private PartSource currentPart;
    private boolean noMoreParts;
    private int partCount;

    public MultipartReader(final BufferedSource source, final String boundary) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(boundary, "boundary");
        this.source = source;
        this.boundary = boundary;
        dashDashBoundary = new Buffer().writeUtf8("--").writeUtf8(boundary).readByteString();
        crlfDashDashBoundary = new Buffer().writeUtf8("\r\n--").writeUtf8(boundary).readByteString();
    }

    public MultipartReader(final ResponseBody response) throws IOException {
        Intrinsics.checkNotNullParameter(response, "response");
        final BufferedSource bufferedSourceSource = response.source();
        final MediaType mediaTypeContentType = response.contentType();
        final String strParameter = null == mediaTypeContentType ? null : mediaTypeContentType.parameter("boundary");
        if (null != strParameter) {
            this(bufferedSourceSource, strParameter);
            return;
        }
        throw new ProtocolException("expected the Content-Type to have a boundary parameter");
    }

    public String boundary() {
        return boundary;
    }

    public Part nextPart() throws IOException {
        if (closed) {
            throw new IllegalStateException("closed");
        }
        if (noMoreParts) {
            return null;
        }
        if (0 == this.partCount && source.rangeEquals(0L, dashDashBoundary)) {
            source.skip(dashDashBoundary.size());
        } else {
            while (true) {
                final long jCurrentPartBytesRemaining = this.currentPartBytesRemaining(8192L);
                if (0 == jCurrentPartBytesRemaining) {
                    break;
                }
                source.skip(jCurrentPartBytesRemaining);
            }
            source.skip(crlfDashDashBoundary.size());
        }
        boolean z = false;
        while (true) {
            final int iSelect = source.select(MultipartReader.afterBoundaryOptions);
            if (-1 == iSelect) {
                throw new ProtocolException("unexpected characters after boundary");
            }
            if (0 == iSelect) {
                partCount++;
                final Headers headers = new HeadersReader(source).readHeaders();
                final PartSource partSource = new PartSource(this);
                currentPart = partSource;
                return new Part(headers, Okio.buffer(partSource));
            }
            if (1 == iSelect) {
                if (z) {
                    throw new ProtocolException("unexpected characters after boundary");
                }
                if (0 == this.partCount) {
                    throw new ProtocolException("expected at least 1 part");
                }
                noMoreParts = true;
                return null;
            }
            if (2 == iSelect || 3 == iSelect) {
                z = true;
            }
        }
    }

    private long currentPartBytesRemaining(final long j2) throws IOException {
        source.require(crlfDashDashBoundary.size());
        final long jIndexOf = source.getBuffer().indexOf(crlfDashDashBoundary);
        if (-1 == jIndexOf) {
            return Math.min(j2, (source.getBuffer().size() - crlfDashDashBoundary.size()) + 1);
        }
        return Math.min(j2, jIndexOf);
    }

    public void close() throws IOException {
        if (closed) {
            return;
        }
        closed = true;
        currentPart = null;
        source.close();
    }
    public record Part(Headers headers, BufferedSource body) implements Closeable {
        public Part {
            Intrinsics.checkNotNullParameter(headers, "headers");
            Intrinsics.checkNotNullParameter(body, "body");
        }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                body.close();
            }
        }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Options getAfterBoundaryOptions() {
            return afterBoundaryOptions;
        }
    }
    private final class PartSource implements Source {
        final MultipartReader this0;
        private final Timeout timeout;

        public PartSource(final MultipartReader this0) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            this.this0 = this0;
            timeout = new Timeout();
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (areEqual(this0.currentPart, this)) {
                this0.currentPart = null;
            }
        }

        @Override // okio.Source
        public long read(final Buffer sink, final long j2) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (0 <= j2) {
                if (areEqual(this0.currentPart, this)) {
                    final Timeout timeout = this0.source.timeout();
                    final Timeout timeout2 = this.timeout;
                    final MultipartReader multipartReader = this0;
                    final long jTimeoutNanos = timeout.timeoutNanos();
                    final long jMinTimeout = Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos());
                    final TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                    timeout.timeout(jMinTimeout, timeUnit);
                    if (timeout.hasDeadline()) {
                        final long jDeadlineNanoTime = timeout.deadlineNanoTime();
                        if (timeout2.hasDeadline()) {
                            timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                        }
                        try {
                            final long jCurrentPartBytesRemaining = multipartReader.currentPartBytesRemaining(j2);
                            final long j3 = 0 == jCurrentPartBytesRemaining ? -1L : multipartReader.source.read(sink, jCurrentPartBytesRemaining);
                            timeout.timeout(jTimeoutNanos, timeUnit);
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(jDeadlineNanoTime);
                            }
                            return j3;
                        } catch (final Throwable th) {
                            timeout.timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(jDeadlineNanoTime);
                            }
                            throw th;
                        }
                    }
                    if (timeout2.hasDeadline()) {
                        timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
                    }
                    try {
                        final long jCurrentPartBytesRemaining2 = multipartReader.currentPartBytesRemaining(j2);
                        final long j4 = 0 == jCurrentPartBytesRemaining2 ? -1L : multipartReader.source.read(sink, jCurrentPartBytesRemaining2);
                        timeout.timeout(jTimeoutNanos, timeUnit);
                        if (timeout2.hasDeadline()) {
                            timeout.clearDeadline();
                        }
                        return j4;
                    } catch (final Throwable th2) {
                        timeout.timeout(jTimeoutNanos, TimeUnit.NANOSECONDS);
                        if (timeout2.hasDeadline()) {
                            timeout.clearDeadline();
                        }
                        throw th2;
                    }
                }
                throw new IllegalStateException("closed");
            }
            throw new IllegalArgumentException(stringPlus("byteCount < 0: ", Long.valueOf(j2)));
        }

        @Override // okio.Source
        public Timeout timeout() {
            return timeout;
        }
    }
}
