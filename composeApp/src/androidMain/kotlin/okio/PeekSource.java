package okio;

import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;


public final class PeekSource implements Source {
    private final Buffer buffer;
    private boolean closed;
    private int expectedPos;
    private Segment expectedSegment;
    private long pos;
    private final BufferedSource upstream;

    public PeekSource(final BufferedSource upstream) {
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        this.upstream = upstream;
        final Buffer buffer = upstream.getBuffer();
        this.buffer = buffer;
        final Segment segment = buffer.head;
        expectedSegment = segment;
        expectedPos = null != segment ? segment.pos : -1;
    }
    public long read(final Buffer sink, final long j2) {
        final Segment segment;
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (0 > j2) {
            throw new IllegalArgumentException(("byteCount < 0: " + j2));
        }
        if (closed) {
            throw new IllegalStateException("closed");
        }
        final Segment segment2 = expectedSegment;
        if (null != segment2) {
            final Segment segment3 = buffer.head;
            if (segment2 == segment3) {
                final int i2 = expectedPos;
                checkNotNull(segment3);
            }
            throw new IllegalStateException("Peek source is invalid because upstream source was used");
        }
        if (0 == j2) {
            return 0L;
        }
        try {
            if (!upstream.request(pos + 1)) {
                return -1L;
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        if (null == this.expectedSegment && null != (segment = this.buffer.head)) {
            expectedSegment = segment;
            checkNotNull(segment);
            expectedPos = segment.pos;
        }
        final long jMin = Math.min(j2, buffer.size() - pos);
        buffer.copyTo(sink, pos, jMin);
        pos += jMin;
        return jMin;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return upstream.timeout();
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        closed = true;
    }
}
