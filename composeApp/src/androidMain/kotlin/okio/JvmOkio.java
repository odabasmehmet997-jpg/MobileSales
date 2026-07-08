package okio;

import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;
import java.io.InputStream;

class JvmOkio implements Source {
    private final InputStream input;
    private final Timeout timeout;
    public JvmOkio(InputStream input, Timeout timeout) {
        Intrinsics.checkNotNullParameter(input, "input");
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        this.input = input;
        this.timeout = timeout;
    }
    public long read(Buffer sink, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (0 == j2) {
            return 0L;
        }
        if (0 > j2) {
            throw new IllegalArgumentException(("byteCount < 0: " + j2));
        }
        try {
            this.timeout.throwIfReached();
            Segment segmentWritableSegmentokio = sink.writableSegmentokio(1);
            int i2 = this.input.read(segmentWritableSegmentokio.data, segmentWritableSegmentokio.limit, (int) Math.min(j2, 8192 - segmentWritableSegmentokio.limit));
            if (-1 == i2) {
                if (segmentWritableSegmentokio.pos != segmentWritableSegmentokio.limit) {
                    return -1L;
                }
                sink.head = segmentWritableSegmentokio.pop();
                SegmentPool.recycle(segmentWritableSegmentokio);
                return -1L;
            }
            segmentWritableSegmentokio.limit += i2;
            long j3 = i2;
            sink.setSizeokio(sink.size() + j3);
            return j3;
        } catch (AssertionError e2) {
            if (Okio.isAndroidGetsocknameError(e2)) {
                throw new IOException(e2);
            }
            throw e2;
        }
    }
    public void close() throws IOException {
        this.input.close();
    }
    public Timeout timeout() {
        return this.timeout;
    }
    public String toString() {
        return "source(" + this.input + ')';
    }
}
