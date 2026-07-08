package okio;

import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;
import java.nio.ByteBuffer;

public final class RealBufferedSink implements BufferedSink {
    public final Buffer bufferField;
    public boolean closed;
    public final Sink sink;
    private Throwable th;

    public void write(final Buffer source, final long j2) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (closed) {
            throw new IllegalStateException("closed");
        }
        bufferField.write(source, j2);
        this.emitCompleteSegments();
    }
    public BufferedSink write(final ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        if (closed) {
            throw new IllegalStateException("closed");
        }
        bufferField.write(byteString);
        try {
            return this.emitCompleteSegments();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public RealBufferedSink(final Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.sink = sink;
        bufferField = new Buffer();
    }
    public Buffer getBuffer() {
        return bufferField;
    }
    public long writeAll(final Source source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        long j2 = 0;
        while (true) {
            final long j3 = source.read(bufferField, 8192L);
            if (-1 == j3) {
                return j2;
            }
            j2 += j3;
            this.emitCompleteSegments();
        }
    }
    public BufferedSink writeUtf8(final String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        if (closed) {
            throw new IllegalStateException("closed");
        }
        bufferField.writeUtf8(string);
        try {
            return this.emitCompleteSegments();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedSink write(final byte[] source) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (closed) {
            throw new IllegalStateException("closed");
        }
        bufferField.write(source);
        try {
            return this.emitCompleteSegments();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedSink write(final byte[] source, final int i2, final int i3) {
        Intrinsics.checkNotNullParameter(source, "source");
        if (closed) {
            throw new IllegalStateException("closed");
        }
        bufferField.write(source, i2, i3);
        try {
            return this.emitCompleteSegments();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int write(final ByteBuffer source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        if (closed) {
            throw new IllegalStateException("closed");
        }
        final int iWrite = bufferField.write(source);
        this.emitCompleteSegments();
        return iWrite;
    }
    public BufferedSink writeByte(final int i2) {
        if (closed) {
            throw new IllegalStateException("closed");
        }
        bufferField.writeByte(i2);
        try {
            return this.emitCompleteSegments();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedSink writeShort(final int i2) {
        if (closed) {
            throw new IllegalStateException("closed");
        }
        bufferField.writeShort(i2);
        try {
            return this.emitCompleteSegments();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedSink writeInt(final int i2) {
        if (closed) {
            throw new IllegalStateException("closed");
        }
        bufferField.writeInt(i2);
        try {
            return this.emitCompleteSegments();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isOpen() {
        return !closed;
    }
    public BufferedSink writeDecimalLong(final long j2) {
        if (closed) {
            throw new IllegalStateException("closed");
        }
        bufferField.writeDecimalLong(j2);
        try {
            return this.emitCompleteSegments();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedSink writeHexadecimalUnsignedLong(final long j2) {
        if (closed) {
            throw new IllegalStateException("closed");
        }
        bufferField.writeHexadecimalUnsignedLong(j2);
        try {
            return this.emitCompleteSegments();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public BufferedSink emitCompleteSegments() throws IOException {
        if (closed) {
            throw new IllegalStateException("closed");
        }
        final long jCompleteSegmentByteCount = bufferField.completeSegmentByteCount();
        if (0 < jCompleteSegmentByteCount) {
            sink.write(bufferField, jCompleteSegmentByteCount);
        }
        return this;
    }
    public BufferedSink emit() throws IOException {
        if (closed) {
            throw new IllegalStateException("closed");
        }
        final long size = bufferField.size();
        if (0 < size) {
            sink.write(bufferField, size);
        }
        return this;
    }
    public void flush() throws IOException {
        if (closed) {
            throw new IllegalStateException("closed");
        }
        if (0 < this.bufferField.size()) {
            final Sink sink = this.sink;
            final Buffer buffer = bufferField;
            sink.write(buffer, buffer.size());
        }
        sink.flush();
    }
    public void close() {
        if (closed) {
            return;
        }
        try {
            if (0 < this.bufferField.size()) {
                final Sink sink = this.sink;
                final Buffer buffer = bufferField;
                sink.write(buffer, buffer.size());
            }
            this.th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            sink.close();
        } catch (final Throwable th2) {
            if (null == th) {
                this.th = th2;
            }
        }
        closed = true;
        if (null != th) {
            try {
                throw this.th;
            } catch (final Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }
    public Timeout timeout() {
        return sink.timeout();
    }
    public String toString() {
        return "buffer(" + sink + ')';
    }
}
