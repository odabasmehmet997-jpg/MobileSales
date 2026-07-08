package okio;

import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;
import java.io.OutputStream;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;

final class JvmOkio3 implements Sink {
    private final OutputStream out;
    private final Timeout timeout;
    public JvmOkio3(OutputStream out, Timeout timeout) {
        Intrinsics.checkNotNullParameter(out, "out");
        Intrinsics.checkNotNullParameter(timeout, "timeout");
        this.out = out;
        this.timeout = timeout;
    }
    public void write(Buffer source, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        _UtilKt.checkOffsetAndCount(source.size(), 0L, j2);
        while (0 < j2) {
            this.timeout.throwIfReached();
            Segment segment = source.head;
            checkNotNull(segment);
            int iMin = (int) Math.min(j2, segment.limit - segment.pos);
            this.out.write(segment.data, segment.pos, iMin);
            segment.pos += iMin;
            long j3 = iMin;
            j2 -= j3;
            source.setSizeokio(source.size() - j3);
            if (segment.pos == segment.limit) {
                source.head = segment.pop();
                SegmentPool.recycle(segment);
            }
        }
    }
    public void flush() throws IOException {
        this.out.flush();
    }
    public void close() throws IOException {
        this.out.close();
    }
    public Timeout timeout() {
        return this.timeout;
    }
    public String toString() {
        return "sink(" + this.out + ')';
    }
}
