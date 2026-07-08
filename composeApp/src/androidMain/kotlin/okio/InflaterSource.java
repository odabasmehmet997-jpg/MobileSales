package okio;

import kotlin.jvm.internal.Intrinsics;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;

public final class InflaterSource implements Source {
    private int bufferBytesHeldByInflater;
    private boolean closed;
    private final Inflater inflater;
    private final BufferedSource source;
    public InflaterSource(final BufferedSource source, final Inflater inflater) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        this.source = source;
        this.inflater = inflater;
    }
    public InflaterSource(final Source source, final Inflater inflater) {
        this(Okio.buffer(source), inflater);
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
    }
    public long read(final Buffer sink, final long j2) throws DataFormatException, IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        do {
            final long orInflate = this.readOrInflate(sink, j2);
            if (0 < orInflate) {
                return orInflate;
            }
            if (inflater.finished() || inflater.needsDictionary()) {
                return -1L;
            }
        } while (!source.exhausted());
        throw new EOFException("source exhausted prematurely");
    }
    public long readOrInflate(final Buffer sink, final long j2) throws DataFormatException, IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (0 > j2) {
            throw new IllegalArgumentException(("byteCount < 0: " + j2));
        }
        if (closed) {
            throw new IllegalStateException("closed");
        }
        if (0 == j2) {
            return 0L;
        }
        try {
            final Segment segmentWritableSegmentokio = sink.writableSegmentokio(1);
            final int iMin = (int) Math.min(j2, 8192 - segmentWritableSegmentokio.limit);
            this.refill();
            final int iInflate = inflater.inflate(segmentWritableSegmentokio.data, segmentWritableSegmentokio.limit, iMin);
            this.releaseBytesAfterInflate();
            if (0 < iInflate) {
                segmentWritableSegmentokio.limit += iInflate;
                final long j3 = iInflate;
                sink.setSizeokio(sink.size() + j3);
                return j3;
            }
            if (segmentWritableSegmentokio.pos == segmentWritableSegmentokio.limit) {
                sink.head = segmentWritableSegmentokio.pop();
                SegmentPool.recycle(segmentWritableSegmentokio);
            }
            return 0L;
        } catch (final DataFormatException e2) {
            throw new IOException(e2);
        }
    }
    public boolean refill() throws IOException {
        if (!inflater.needsInput()) {
            return false;
        }
        if (source.exhausted()) {
            return true;
        }
        final Segment segment = source.getBuffer().head;
        checkNotNull(segment);
        final int i2 = segment.limit;
        final int i3 = segment.pos;
        final int i4 = i2 - i3;
        bufferBytesHeldByInflater = i4;
        inflater.setInput(segment.data, i3, i4);
        return false;
    }
    private void releaseBytesAfterInflate() throws IOException {
        final int i2 = bufferBytesHeldByInflater;
        if (0 == i2) {
            return;
        }
        final int remaining = i2 - inflater.getRemaining();
        bufferBytesHeldByInflater -= remaining;
        source.skip(remaining);
    }
    public Timeout timeout() {
        return source.timeout();
    }
    public void close() throws IOException {
        if (closed) {
            return;
        }
        inflater.end();
        closed = true;
        source.close();
    }
}
