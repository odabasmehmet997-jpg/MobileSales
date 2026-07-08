package okio;

import androidx.core.location.LocationRequestCompat;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import okio.internal._BufferKt;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class RealBufferedSource implements BufferedSource {
    public final Buffer bufferField;
    public boolean closed;
    public final Source source;
    public long read(final Buffer sink, final long j2) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (0 > j2) {
            throw new IllegalArgumentException(("byteCount < 0: " + j2));
        }
        if (closed) {
            throw new IllegalStateException("closed");
        }
        try {
            if (0 == this.bufferField.size() && -1 == this.source.read(this.bufferField, 8192L)) {
                return -1L;
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return bufferField.read(sink, Math.min(j2, bufferField.size()));
    }
    public boolean exhausted() {
        if (closed) {
            throw new IllegalStateException("closed");
        }
        try {
            return bufferField.exhausted() && -1 == this.source.read(this.bufferField, 8192L);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    public RealBufferedSource(final Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        bufferField = new Buffer();
    }
    public void require(final long j2) throws EOFException {
        if (!this.request(j2)) {
            throw new EOFException();
        }
    }
    public boolean request(final long j2) {
        if (0 > j2) {
            throw new IllegalArgumentException(("byteCount < 0: " + j2));
        }
        if (closed) {
            throw new IllegalStateException("closed");
        }
        while (bufferField.size() < j2) {
            try {
                if (-1 == this.source.read(this.bufferField, 8192L)) {
                    return false;
                }
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
    public Buffer getBuffer() {
        return bufferField;
    }
    public int read(final ByteBuffer sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            if (0 == this.bufferField.size() && -1 == this.source.read(this.bufferField, 8192L)) {
                return -1;
            }
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return bufferField.read(sink);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }
    public byte[] readByteArray() throws IOException {
        bufferField.writeAll(source);
        return bufferField.readByteArray();
    }
    public ByteString readByteString() throws IOException {
        bufferField.writeAll(source);
        return bufferField.readByteString();
    }
    public String readString(final Charset charset) throws IOException {
        Intrinsics.checkNotNullParameter(charset, "charset");
        bufferField.writeAll(source);
        return bufferField.readString(charset);
    }
    public byte readByte() throws EOFException {
        this.require(1L);
        return bufferField.readByte();
    }
    public ByteString readByteString(final long j2) throws EOFException {
        this.require(j2);
        return bufferField.readByteString(j2);
    }
    public int select(final Options options) throws EOFException {
        Intrinsics.checkNotNullParameter(options, "options");
        if (closed) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            final int iSelectPrefix = _BufferKt.selectPrefix(bufferField, options, true);
            if (-2 != iSelectPrefix) {
                if (-1 != iSelectPrefix) {
                    bufferField.skip(options.getByteStringsokio()[iSelectPrefix].size());
                    return iSelectPrefix;
                }
            } else {
                try {
                    if (-1 == this.source.read(this.bufferField, 8192L)) {
                        break;
                    }
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return -1;
    }
    public String readUtf8LineStrict() {
        try {
            return this.readUtf8LineStrict(LocationRequestCompat.PASSIVE_INTERVAL);
        } catch (final EOFException e) {
            throw new RuntimeException(e);
        }
    }
    public byte[] readByteArray(final long j2) throws EOFException {
        this.require(j2);
        return bufferField.readByteArray(j2);
    }
    public void readFully(final byte[] sink) throws EOFException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            this.require(sink.length);
            bufferField.readFully(sink);
        } catch (final EOFException e2) {
            int i2 = 0;
            while (0 < this.bufferField.size()) {
                final Buffer buffer = bufferField;
                final int i3 = buffer.read(sink, i2, (int) buffer.size());
                if (-1 == i3) {
                    throw new AssertionError();
                }
                i2 += i3;
            }
            throw e2;
        }
    }
    public long indexOf(final byte b2) {
        return this.indexOf(b2, 0L, LocationRequestCompat.PASSIVE_INTERVAL);
    }
    public boolean rangeEquals(final long j2, final ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return this.rangeEquals(j2, bytes, 0, bytes.size());
    }
    public InputStream inputStream() {
        return new InputStream() {
            public int read() throws IOException {
                final RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (realBufferedSource.closed) {
                    throw new IOException("closed");
                }
                if (0 == realBufferedSource.bufferField.size()) {
                    final RealBufferedSource realBufferedSource2 = RealBufferedSource.this;
                    if (-1 == realBufferedSource2.source.read(realBufferedSource2.bufferField, 8192L)) {
                        return -1;
                    }
                }
                return bufferField.readByte() & 255;
            }
            public int read(final byte[] data, final int i2, final int i3) throws IOException {
                Intrinsics.checkNotNullParameter(data, "data");
                if (closed) {
                    throw new IOException("closed");
                }
                _UtilKt.checkOffsetAndCount(data.length, i2, i3);
                if (0 == RealBufferedSource.this.bufferField.size()) {
                    final RealBufferedSource realBufferedSource = RealBufferedSource.this;
                    if (-1 == realBufferedSource.source.read(realBufferedSource.bufferField, 8192L)) {
                        return -1;
                    }
                }
                return bufferField.read(data, i2, i3);
            }
            public int available() throws IOException {
                final RealBufferedSource realBufferedSource = RealBufferedSource.this;
                if (!realBufferedSource.closed) {
                    return (int) Math.min(realBufferedSource.bufferField.size(), Integer.MAX_VALUE);
                }
                throw new IOException("closed");
            }
            public void close() throws IOException {
                RealBufferedSource.this.close();
            }
            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }
        };
    }
    public void readFully(final Buffer sink, final long j2) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            this.require(j2);
            bufferField.readFully(sink, j2);
        } catch (final EOFException e2) {
            sink.writeAll(bufferField);
            throw e2;
        }
    }
    public long readAll(final Sink sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long j2 = 0;
        while (-1 != this.source.read(this.bufferField, 8192L)) {
            final long jCompleteSegmentByteCount = bufferField.completeSegmentByteCount();
            if (0 < jCompleteSegmentByteCount) {
                j2 += jCompleteSegmentByteCount;
                sink.write(bufferField, jCompleteSegmentByteCount);
            }
        }
        if (0 >= this.bufferField.size()) {
            return j2;
        }
        final long size = j2 + bufferField.size();
        final Buffer buffer = bufferField;
        sink.write(buffer, buffer.size());
        return size;
    }
    public boolean isOpen() {
        return !closed;
    }
    public String readUtf8(final long j2) throws EOFException {
        this.require(j2);
        return bufferField.readUtf8(j2);
    }
    public String readUtf8LineStrict(final long j2) throws EOFException {
        if (0 > j2) {
            throw new IllegalArgumentException(("limit < 0: " + j2));
        }
        final long j3 = LocationRequestCompat.PASSIVE_INTERVAL == j2 ? Long.MAX_VALUE : j2 + 1;
        final byte b2 = 10;
        final long jIndexOf = this.indexOf(b2, 0L, j3);
        if (-1 != jIndexOf) {
            return _BufferKt.readUtf8Line(bufferField, jIndexOf);
        }
        if (LocationRequestCompat.PASSIVE_INTERVAL > j3 && this.request(j3) && ((byte) 13) == this.bufferField.getByte(j3 - 1) && this.request(1 + j3) && b2 == this.bufferField.getByte(j3)) {
            return _BufferKt.readUtf8Line(bufferField, j3);
        }
        final Buffer buffer = new Buffer();
        final Buffer buffer2 = bufferField;
        buffer2.copyTo(buffer, 0L, Math.min(32, buffer2.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(bufferField.size(), j2) + " content=" + buffer.readByteString().hex() + '\u2026');
    }
    public short readShort() throws EOFException {
        this.require(2L);
        return bufferField.readShort();
    }
    public short readShortLe() throws EOFException {
        this.require(2L);
        return bufferField.readShortLe();
    }
    public int readInt() throws EOFException {
        this.require(4L);
        return bufferField.readInt();
    }
    public int readIntLe() throws EOFException {
        this.require(4L);
        return bufferField.readIntLe();
    }
    public long readLong() throws EOFException {
        this.require(8L);
        return bufferField.readLong();
    }
    public long readLongLe() throws EOFException {
        this.require(8L);
        return bufferField.readLongLe();
    }
    public long readDecimalLong() throws EOFException {
        byte b2 = 0;
        this.require(1L);
        long j2 = 0;
        while (true) {
            final long j3 = j2 + 1;
            if (!this.request(j3)) {
                break;
            }
            b2 = bufferField.getByte(j2);
            if ((((byte) 48) > b2 || ((byte) 57) < b2) && !(0 == j2 && ((byte) 45) == b2)) {
                break;
            }
            j2 = j3;
        }
        if (0 == j2) {
            final StringBuilder sb = new StringBuilder();
            sb.append("Expected a digit or '-' but was 0x");
            final String string = Integer.toString(b2, CharsKt.checkRadix(CharsKt.checkRadix(16)));
            checkNotNullExpressionValue(string, "toString(this, checkRadix(radix))");
            sb.append(string);
            throw new NumberFormatException(sb.toString());
        }
        return bufferField.readDecimalLong();
    }

    @Override // okio.BufferedSource
    public long readHexadecimalUnsignedLong() throws EOFException {
        byte b2 = 0;
        this.require(1L);
        int i2 = 0;
        while (true) {
            final int i3 = i2 + 1;
            if (!this.request(i3)) {
                break;
            }
            b2 = bufferField.getByte(i2);
            if ((((byte) 48) > b2 || ((byte) 57) < b2) && ((((byte) 97) > b2 || ((byte) 102) < b2) && (((byte) 65) > b2 || ((byte) 70) < b2))) {
                break;
            }
            i2 = i3;
        }
        if (0 == i2) {
            final StringBuilder sb = new StringBuilder();
            sb.append("Expected leading [0-9a-fA-F] character but was 0x");
            final String string = Integer.toString(b2, CharsKt.checkRadix(CharsKt.checkRadix(16)));
            checkNotNullExpressionValue(string, "toString(this, checkRadix(radix))");
            sb.append(string);
            throw new NumberFormatException(sb.toString());
        }
        return bufferField.readHexadecimalUnsignedLong();
    }

    @Override // okio.BufferedSource
    public void skip(long j2) throws EOFException {
        if (closed) {
            throw new IllegalStateException("closed");
        }
        while (0 < j2) {
            try {
                if (0 == this.bufferField.size() && -1 == this.source.read(this.bufferField, 8192L)) {
                    throw new EOFException();
                }
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
            final long jMin = Math.min(j2, bufferField.size());
            bufferField.skip(jMin);
            j2 -= jMin;
        }
    }

    public long indexOf(final byte b2, long j2, final long j3) {
        if (closed) {
            throw new IllegalStateException("closed");
        }
        if (0 > j2 || j2 > j3) {
            throw new IllegalArgumentException(("fromIndex=" + j2 + " toIndex=" + j3));
        }
        while (j2 < j3) {
            final long jIndexOf = bufferField.indexOf(b2, j2, j3);
            if (-1 != jIndexOf) {
                return jIndexOf;
            }
            final long size = bufferField.size();
            try {
                if (size >= j3 || -1 == this.source.read(this.bufferField, 8192L)) {
                    return -1L;
                }
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }
            j2 = Math.max(j2, size);
        }
        return -1L;
    }

    public boolean rangeEquals(final long j2, final ByteString bytes, final int i2, final int i3) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (closed) {
            throw new IllegalStateException("closed");
        }
        if (0 > j2 || 0 > i2 || 0 > i3 || bytes.size() - i2 < i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            final long j3 = i4 + j2;
            if (!this.request(1 + j3) || bufferField.getByte(j3) != bytes.getByte(i2 + i4)) {
                return false;
            }
        }
        return true;
    }

    @Override // okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (closed) {
            return;
        }
        closed = true;
        source.close();
        bufferField.clear();
    }

    @Override // okio.Source
    public Timeout timeout() {
        return source.timeout();
    }

    public String toString() {
        return "buffer(" + source + ')';
    }
}
