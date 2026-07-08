package okio;

import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;
import java.util.zip.Deflater;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;

public final class DeflaterSink implements Sink {
    private boolean closed;
    private final Deflater deflater;
    private final BufferedSink sink;
    public DeflaterSink(BufferedSink sink, Deflater deflater) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(deflater, "deflater");
        this.sink = sink;
        this.deflater = deflater;
    }
    public DeflaterSink(Sink sink, Deflater deflater) {
        this(Okio.buffer(sink), deflater);
        Intrinsics.checkNotNullParameter(sink, "sink");
        Intrinsics.checkNotNullParameter(deflater, "deflater");
    }
    public void write(Buffer source, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        _UtilKt.checkOffsetAndCount(source.size(), 0L, j2);
        while (0 < j2) {
            Segment segment = source.head;
            checkNotNull(segment);
            int iMin = (int) Math.min(j2, segment.limit - segment.pos);
            this.deflater.setInput(segment.data, segment.pos, iMin);
            deflate(false);
            long j3 = iMin;
            source.setSizeokio(source.size() - j3);
            int i2 = segment.pos + iMin;
            segment.pos = i2;
            if (i2 == segment.limit) {
                source.head = segment.pop();
                SegmentPool.recycle(segment);
            }
            j2 -= j3;
        }
    }
    private void deflate(boolean z) throws IOException {
        Segment segmentWritableSegmentokio;
        int iDeflate;
        Buffer buffer = this.sink.getBuffer();
        while (true) {
            segmentWritableSegmentokio = buffer.writableSegmentokio(1);
            if (z) {
                Deflater deflater = this.deflater;
                byte[] bArr = segmentWritableSegmentokio.data;
                int i2 = segmentWritableSegmentokio.limit;
                iDeflate = deflater.deflate(bArr, i2, 8192 - i2, 2);
            } else {
                Deflater deflater2 = this.deflater;
                byte[] bArr2 = segmentWritableSegmentokio.data;
                int i3 = segmentWritableSegmentokio.limit;
                iDeflate = deflater2.deflate(bArr2, i3, 8192 - i3);
            }
            if (0 < iDeflate) {
                segmentWritableSegmentokio.limit += iDeflate;
                buffer.setSizeokio(buffer.size() + iDeflate);
                this.sink.emitCompleteSegments();
            } else if (this.deflater.needsInput()) {
                break;
            }
        }
        if (segmentWritableSegmentokio.pos == segmentWritableSegmentokio.limit) {
            buffer.head = segmentWritableSegmentokio.pop();
            SegmentPool.recycle(segmentWritableSegmentokio);
        }
    }
    public void flush() throws IOException {
        deflate(true);
        this.sink.flush();
    }
    public void finishDeflateokio() throws IOException {
        this.deflater.finish();
        deflate(false);
    }
    public void close(){
        if (this.closed) {
            return;
        }
        Throwable th = null;
        try {
            try {
                finishDeflateokio();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            th = null;
        } catch (Throwable th1) {
            th = th1;
        }
        try {
            this.deflater.end();
        } catch (Throwable th2) {
            if (null == th) {
                th = th2;
            }
        }
        try {
            this.sink.close();
        } catch (Throwable th3) {
            if (null == th) {
                th = th3;
            }
        }
        this.closed = true;
        if (null != th) {
            try {
                throw th;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Timeout timeout() {
        return this.sink.timeout();
    }
    public String toString() {
        return "DeflaterSink(" + this.sink + ')';
    }
}
