package okio;

import androidx.core.location.LocationRequestCompat;
import androidx.work.WorkRequest;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.internal.connection.RealConnection;
import okio.internal._BufferKt;
import org.kxml2.wap.Wbxml;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.util.zip.DataFormatException;

public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {
    public Segment head;
    private long size;
    private Segment r1;
    public void close() {
    }
    public Buffer emit() {
        return this;
    }
    public Buffer emitCompleteSegments() {
        return this;
    }
    public void flush() {
    }
    public Buffer getBuffer() {
        return this;
    }
    public boolean isOpen() {
        return true;
    }
    public long size() {
        return this.size;
    }
    public void setSizeokio(long j2) {
        this.size = j2;
    }
    public final class C35991 extends OutputStream {
        public void close() {
        }
        public void flush() {
        }
        C35991() {
        }
        public void write(int i2) {
            Buffer.this.writeByte(i2);
        }
        public void write(byte[] data, int i2, int i3) {
            Intrinsics.checkNotNullParameter(data, "data");
            Buffer.this.write(data, i2, i3);
        }
        public String toString() {
            return Buffer.this + ".outputStream()";
        }
    }
    public OutputStream outputStream() {
        return new OutputStream() {
            public void close() {
            }
            public void flush() {
            }
            void C35991() {
            }
            public void write(int i2) {
                Buffer.this.writeByte(i2);
            }
            public void write(byte[] data, int i2, int i3) {
                Intrinsics.checkNotNullParameter(data, "data");
                Buffer.this.write(data, i2, i3);
            }

            public String toString() {
                return Buffer.this + ".outputStream()";
            }
        };
    }
    public boolean exhausted() {
        return 0 == size;
    }
    public long indexOfElement(ByteString targetBytes, long j2) {
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        long size = 0;
        if (0 <= j2) {
            Segment segment = this.head;
            if (null == segment) {
                return -1L;
            }
            if (size() - j2 < j2) {
                size = size();
                while (size > j2) {
                    segment = segment.prev;
                    Intrinsics.checkNotNull(segment);
                    size -= segment.limit - segment.pos;
                }
                if (2 == targetBytes.size()) {
                    byte b2 = targetBytes.getByte(0);
                    byte b3 = targetBytes.getByte(1);
                    while (size < size()) {
                        byte[] bArr = segment.data;
                        i2 = (int) ((segment.pos + j2) - size);
                        int i4 = segment.limit;
                        while (i2 < i4) {
                            byte b4 = bArr[i2];
                            if (b4 == b2 || b4 == b3) {
                                i3 = segment.pos;
                            } else {
                                i2++;
                            }
                        }
                        size += segment.limit - segment.pos;
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        j2 = size;
                    }
                    return -1L;
                }
                byte[] bArrInternalArrayokio = targetBytes.internalArrayokio();
                while (size < size()) {
                    byte[] bArr2 = segment.data;
                    i2 = (int) ((segment.pos + j2) - size);
                    int i5 = segment.limit;
                    while (i2 < i5) {
                        byte b5 = bArr2[i2];
                        for (byte b6 : bArrInternalArrayokio) {
                            if (b5 == b6) {
                                i3 = segment.pos;
                                break;
                            }
                        }
                        i2++;
                    }
                    size += segment.limit - segment.pos;
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    j2 = size;
                }
                return -1L;
            }
            while (true) {
                long j3 = (segment.limit - segment.pos) + size;
                if (j3 > j2) {
                    break;
                }
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                size = j3;
            }
            if (2 == targetBytes.size()) {
                byte b7 = targetBytes.getByte(0);
                byte b8 = targetBytes.getByte(1);
                while (size < size()) {
                    byte[] bArr3 = segment.data;
                    i2 = (int) ((segment.pos + j2) - size);
                    int i6 = segment.limit;
                    while (i2 < i6) {
                        byte b9 = bArr3[i2];
                        if (b9 == b7 || b9 == b8) {
                            i3 = segment.pos;
                        } else {
                            i2++;
                        }
                    }
                    size += segment.limit - segment.pos;
                    segment = segment.next;
                    Intrinsics.checkNotNull(segment);
                    j2 = size;
                }
                return -1L;
            }
            byte[] bArrInternalArrayokio2 = targetBytes.internalArrayokio();
            while (size < size()) {
                byte[] bArr4 = segment.data;
                i2 = (int) ((segment.pos + j2) - size);
                int i7 = segment.limit;
                while (i2 < i7) {
                    byte b10 = bArr4[i2];
                    for (byte b11 : bArrInternalArrayokio2) {
                        if (b10 == b11) {
                            i3 = segment.pos;
                            break;
                        }
                    }
                    i2++;
                }
                size += segment.limit - segment.pos;
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j2 = size;
            }
            return -1L;
        }
        throw new IllegalArgumentException(("fromIndex < 0: " + j2));
    }
    public void require(long j2) throws EOFException {
        if (this.size < j2) {
            throw new EOFException();
        }
    }
    public boolean request(long j2) {
        return this.size >= j2;
    }
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }
    public final class C35981 extends InputStream {
        public void close() {
        }
        C35981() {
        }
        public int read() {
            if (0 < size()) {
                try {
                    return Buffer.this.readByte() & 255;
                } catch (EOFException e) {
                    throw new RuntimeException(e);
                }
            }
            return -1;
        }
        public int read(byte[] sink, int i2, int i3) {
            Intrinsics.checkNotNullParameter(sink, "sink");
            return Buffer.this.read(sink, i2, i3);
        }
        public int available() {
            return (int) Math.min(Buffer.this.size(), Integer.MAX_VALUE);
        }
        public String toString() {
            return Buffer.this + ".inputStream()";
        }
    }
    public InputStream inputStream() {
        return new InputStream() {
            public void close() {
            }
            void C35981() {
            }
            public int read() {
                if (0 < size()) {
                    try {
                        return Buffer.this.readByte() & 255;
                    } catch (EOFException e) {
                        throw new RuntimeException(e);
                    }
                }
                return -1;
            }
            public int read(byte[] sink, int i2, int i3) {
                Intrinsics.checkNotNullParameter(sink, "sink");
                return Buffer.this.read(sink, i2, i3);
            }
            public int available() {
                return (int) Math.min(Buffer.this.size(), Integer.MAX_VALUE);
            }

            public String toString() {
                return Buffer.this + ".inputStream()";
            }
        };
    }
    public static UnsafeCursor readAndWriteUnsafedefault(Buffer buffer, UnsafeCursor unsafeCursor, int i2, Object obj) {
        if (0 != (i2 & 1)) {
            unsafeCursor = _UtilKt.getDEFAULT__new_UnsafeCursor();
        }
        return buffer.readAndWriteUnsafe(unsafeCursor);
    }
    public Buffer copyTo(Buffer out, long j2, long j3) {
        Intrinsics.checkNotNullParameter(out, "out");
        _UtilKt.checkOffsetAndCount(size(), j2, j3);
        if (0 != j3) {
            out.size = out.size() + j3;
            Segment segment = this.head;
            while (true) {
                Intrinsics.checkNotNull(segment);
                int i2 = segment.limit;
                int i3 = segment.pos;
                if (j2 < i2 - i3) {
                    break;
                }
                j2 -= i2 - i3;
                segment = segment.next;
            }
            while (0 < j3) {
                Intrinsics.checkNotNull(segment);
                Segment segmentSharedCopy = segment.sharedCopy();
                int i4 = segmentSharedCopy.pos + ((int) j2);
                segmentSharedCopy.pos = i4;
                segmentSharedCopy.limit = Math.min(i4 + ((int) j3), segmentSharedCopy.limit);
                Segment segment2 = out.head;
                if (null == segment2) {
                    segmentSharedCopy.prev = segmentSharedCopy;
                    segmentSharedCopy.next = segmentSharedCopy;
                    out.head = segmentSharedCopy;
                } else {
                    Intrinsics.checkNotNull(segment2);
                    Segment segment3 = segment2.prev;
                    Intrinsics.checkNotNull(segment3);
                    segment3.push(segmentSharedCopy);
                }
                j3 -= segmentSharedCopy.limit - segmentSharedCopy.pos;
                segment = segment.next;
                j2 = 0;
            }
        }
        return this;
    }
    public short readShortLe() throws EOFException {
        return _UtilKt.reverseBytes(readShort());
    }
    public int readIntLe() throws EOFException {
        return _UtilKt.reverseBytes(readInt());
    }
    public long readLongLe() throws EOFException {
        return _UtilKt.reverseBytes(readLong());
    }
    public long completeSegmentByteCount() {
        long size = size();
        if (0 == size) {
            return 0L;
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment.prev;
        Intrinsics.checkNotNull(segment2);
        if (8192 > segment2.limit && segment2.owner) {
            final long r3 = 0;
            size -= r3 - segment2.pos;
        }
        return size;
    }
    public byte readByte() throws EOFException {
        if (0 == this.size()) {
            throw new EOFException();
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        int i2 = segment.pos;
        int i3 = segment.limit;
        int i4 = i2 + 1;
        byte b2 = segment.data[i2];
        size = size() - 1;
        if (i4 == i3) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i4;
        }
        return b2;
    }
    public String readUtf8() {
        try {
            return readString(this.size, Charsets.UTF_8);
        } catch (EOFException e) {
            throw new RuntimeException(e);
        }
    }
    public String readUtf8(long j2) throws EOFException {
        return readString(j2, Charsets.UTF_8);
    }
    public String readString(Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        try {
            return readString(this.size, charset);
        } catch (EOFException e) {
            throw new RuntimeException(e);
        }
    }
    public String readString(long j2, Charset charset) throws EOFException {
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (0 > j2 || 2147483647L < j2) {
            throw new IllegalArgumentException(("byteCount: " + j2));
        }
        if (this.size < j2) {
            throw new EOFException();
        }
        if (0 == j2) {
            return "";
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        int i2 = segment.pos;
        if (i2 + j2 > segment.limit) {
            return new String(readByteArray(j2), charset);
        }
        int i3 = (int) j2;
        String str = new String(segment.data, i2, i3, charset);
        int i4 = segment.pos + i3;
        segment.pos = i4;
        this.size -= j2;
        if (i4 == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return str;
    }
    public short readShort() throws EOFException {
        if (2 > this.size()) {
            throw new EOFException();
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        int i2 = segment.pos;
        int i3 = segment.limit;
        if (2 > i3 - i2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = segment.data;
        int i4 = i2 + 1;
        int i5 = (bArr[i2] & 255) << 8;
        int i6 = i2 + 2;
        int i7 = (bArr[i4] & 255) | i5;
        size = size() - 2;
        if (i6 == i3) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i6;
        }
        return (short) i7;
    }
    public String readUtf8LineStrict() throws EOFException {
        return readUtf8LineStrict(LocationRequestCompat.PASSIVE_INTERVAL);
    }
    public int readInt() throws EOFException {
        if (4 > this.size()) {
            throw new EOFException();
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        int i2 = segment.pos;
        int i3 = segment.limit;
        if (4 > i3 - i2) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = segment.data;
        int i4 = i2 + 3;
        int i5 = ((bArr[i2 + 1] & 255) << 16) | ((bArr[i2] & 255) << 24) | ((bArr[i2 + 2] & 255) << 8);
        int i6 = i2 + 4;
        int i7 = (bArr[i4] & 255) | i5;
        size = size() - 4;
        if (i6 == i3) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i6;
        }
        return i7;
    }
    public int read(ByteBuffer sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        Segment segment = this.head;
        if (null == segment) {
            return -1;
        }
        int iMin = Math.min(sink.remaining(), segment.limit - segment.pos);
        sink.put(segment.data, segment.pos, iMin);
        int i2 = segment.pos + iMin;
        segment.pos = i2;
        this.size -= iMin;
        if (i2 == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return iMin;
    }
    public long readLong() throws EOFException {
        if (8 > this.size()) {
            throw new EOFException();
        }
        Segment segment = this.head;
        Intrinsics.checkNotNull(segment);
        int i2 = segment.pos;
        int i3 = segment.limit;
        if (8 > i3 - i2) {
            return ((readInt() & 4294967295L) << 32) | (4294967295L & readInt());
        }
        byte[] bArr = segment.data;
        int i4 = i2 + 7;
        long j2 = ((long) (bArr[i2] & 255) << 56) | ((long) (bArr[i2 + 1] & 255) << 48) | ((long) (bArr[i2 + 2] & 255) << 40) | ((long) (bArr[i2 + 3] & 255) << 32) | ((long) (bArr[i2 + 4] & 255) << 24) | ((bArr[i2 + 5] & 255) << 16) | ((bArr[i2 + 6] & 255) << 8);
        int i5 = i2 + 8;
        long j3 = j2 | (bArr[i4] & 255);
        size = size() - 8;
        if (i5 == i3) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        } else {
            segment.pos = i5;
        }
        return j3;
    }
    public Buffer writeUtf8(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        return writeUtf8(string, 0, string.length());
    }
    public Buffer writeString(String string, Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        return writeString(string, 0, string.length(), charset);
    }
    public Buffer writeString(String string, int i2, int i3, Charset charset) {
        Intrinsics.checkNotNullParameter(string, "string");
        Intrinsics.checkNotNullParameter(charset, "charset");
        if (0 > i2) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i2));
        }
        if (i3 < i2) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i3 + " < " + i2));
        }
        if (i3 > string.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i3 + " > " + string.length()));
        }
        if (Intrinsics.areEqual(charset, Charsets.UTF_8)) {
            return writeUtf8(string, i2, i3);
        }
        String strSubstring = string.substring(i2, i3);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String\u2026ing(startIndex, endIndex)");
        byte[] bytes = strSubstring.getBytes(charset);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return write(bytes, 0, bytes.length);
    }
    public byte getByte(long j2) {
        _UtilKt.checkOffsetAndCount(size(), j2, 1L);
        Segment segment = this.head;
        if (null == segment) {
            Intrinsics.checkNotNull(null);
            throw null;
        }
        if (size() - j2 < j2) {
            long size = size();
            while (size > j2) {
                segment = segment.prev;
                Intrinsics.checkNotNull(segment);
                size -= segment.limit - segment.pos;
            }
            Intrinsics.checkNotNull(segment);
            return segment.data[(int) ((segment.pos + j2) - size)];
        }
        long j3 = 0;
        while (true) {
            long j4 = (segment.limit - segment.pos) + j3;
            if (j4 <= j2) {
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j3 = j4;
            } else {
                Intrinsics.checkNotNull(segment);
                return segment.data[(int) ((segment.pos + j2) - j3)];
            }
        }
    }
    public void clear() throws EOFException {
        skip(size());
    }
    public int write(ByteBuffer source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        int iRemaining = source.remaining();
        int i2 = iRemaining;
        while (0 < i2) {
            Segment segmentWritableSegmentokio = writableSegmentokio(1);
            int iMin = Math.min(i2, 8192 - segmentWritableSegmentokio.limit);
            source.get(segmentWritableSegmentokio.data, segmentWritableSegmentokio.limit, iMin);
            i2 -= iMin;
            segmentWritableSegmentokio.limit += iMin;
        }
        this.size += iRemaining;
        return iRemaining;
    }
    public void skip(long j2) throws EOFException {
        while (0 < j2) {
            Segment segment = this.head;
            if (null != segment) {
                int iMin = (int) Math.min(j2, segment.limit - segment.pos);
                long j3 = iMin;
                size = size() - j3;
                j2 -= j3;
                int i2 = segment.pos + iMin;
                segment.pos = i2;
                if (i2 == segment.limit) {
                    this.head = segment.pop();
                    SegmentPool.recycle(segment);
                }
            } else {
                throw new EOFException();
            }
        }
    }
    public Buffer write(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "byteString");
        byteString.writeokio(this, 0, byteString.size());
        return this;
    }
    public Buffer writeDecimalLong(long j2) {
        boolean z;
        if (0 == j2) {
            return writeByte(48);
        }
        int i2 = 1;
        if (0 > j2) {
            j2 = -j2;
            if (0 > j2) {
                return writeUtf8("-9223372036854775808");
            }
            z = true;
        } else {
            z = false;
        }
        if (100000000 <= j2) {
            i2 = 1000000000000L > j2 ? RealConnection.IDLE_CONNECTION_HEALTHY_NS > j2 ? 1000000000 > j2 ? 9 : 10 : 100000000000L > j2 ? 11 : 12 : 1000000000000000L > j2 ? 10000000000000L > j2 ? 13 : 100000000000000L > j2 ? 14 : 15 : 100000000000000000L > j2 ? 10000000000000000L > j2 ? 16 : 17 : 1000000000000000000L > j2 ? 18 : 19;
        } else if (WorkRequest.MIN_BACKOFF_MILLIS <= j2) {
            i2 = 1000000 > j2 ? 100000 > j2 ? 5 : 6 : 10000000 > j2 ? 7 : 8;
        } else if (100 <= j2) {
            i2 = 1000 > j2 ? 3 : 4;
        } else if (10 <= j2) {
            i2 = 2;
        }
        if (z) {
            i2++;
        }
        Segment segmentWritableSegmentokio = writableSegmentokio(i2);
        byte[] bArr = segmentWritableSegmentokio.data;
        int i3 = segmentWritableSegmentokio.limit + i2;
        while (0 != j2) {
            final long j3 = 10;
            i3--;
            bArr[i3] = _BufferKt.getHEX_DIGIT_BYTES()[(int) (j2 % j3)];
            j2 /= j3;
        }
        if (z) {
            bArr[i3 - 1] = 45;
        }
        segmentWritableSegmentokio.limit += i2;
        size = size() + i2;
        return this;
    }
    public long indexOf(ByteString bytes) throws IOException {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return indexOf(bytes, 0L);
    }
    public long indexOfElement(ByteString targetBytes) {
        Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
        return indexOfElement(targetBytes, 0L);
    }
    public boolean rangeEquals(long j2, ByteString bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        return rangeEquals(j2, bytes, 0, bytes.size());
    }
    public Timeout timeout() {
        return Timeout.NONE;
    }
    public Buffer writeHexadecimalUnsignedLong(long j2) {
        if (0 == j2) {
            return writeByte(48);
        }
        long j3 = (j2 >>> 1) | j2;
        long j4 = j3 | (j3 >>> 2);
        long j5 = j4 | (j4 >>> 4);
        long j6 = j5 | (j5 >>> 8);
        long j7 = j6 | (j6 >>> 16);
        long j8 = j7 | (j7 >>> 32);
        long j9 = j8 - ((j8 >>> 1) & 6148914691236517205L);
        long j10 = ((j9 >>> 2) & 3689348814741910323L) + (j9 & 3689348814741910323L);
        long j11 = ((j10 >>> 4) + j10) & 1085102592571150095L;
        long j12 = j11 + (j11 >>> 8);
        long j13 = j12 + (j12 >>> 16);
        int i2 = (int) ((((j13 & 63) + ((j13 >>> 32) & 63)) + 3) / 4);
        Segment segmentWritableSegmentokio = writableSegmentokio(i2);
        byte[] bArr = segmentWritableSegmentokio.data;
        int i3 = segmentWritableSegmentokio.limit;
        for (int i4 = (i3 + i2) - 1; i4 >= i3; i4--) {
            bArr[i4] = _BufferKt.getHEX_DIGIT_BYTES()[(int) (15 & j2)];
            j2 >>>= 4;
        }
        segmentWritableSegmentokio.limit += i2;
        size = size() + i2;
        return this;
    }
    public Segment writableSegmentokio(int i2) {
        if (1 > i2 || 8192 < i2) {
            throw new IllegalArgumentException("unexpected capacity");
        }
        Segment segment = this.head;
        if (null == segment) {
            Segment segmentTake = SegmentPool.take();
            this.head = segmentTake;
            segmentTake.prev = segmentTake;
            segmentTake.next = segmentTake;
            return segmentTake;
        }
        Intrinsics.checkNotNull(segment);
        Segment segment2 = segment.prev;
        Intrinsics.checkNotNull(segment2);
        return (8192 < segment2.limit + i2 || !segment2.owner) ? segment2.push(SegmentPool.take()) : segment2;
    }
    public Buffer write(byte[] source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return write(source, 0, source.length);
    }
    public String toString() {
        return snapshot().toString();
    }
    public Buffer clone() {
        return copy();
    }
    public Buffer write(byte[] source, int i2, int i3) {
        Intrinsics.checkNotNullParameter(source, "source");
        long j2 = i3;
        _UtilKt.checkOffsetAndCount(source.length, i2, j2);
        int i4 = i3 + i2;
        while (i2 < i4) {
            Segment segmentWritableSegmentokio = writableSegmentokio(1);
            int iMin = Math.min(i4 - i2, 8192 - segmentWritableSegmentokio.limit);
            int i5 = i2 + iMin;
            ArraysKt.copyInto(source, segmentWritableSegmentokio.data, segmentWritableSegmentokio.limit, i2, i5);
            segmentWritableSegmentokio.limit += iMin;
            i2 = i5;
        }
        size = size() + j2;
        return this;
    }
    public UnsafeCursor readAndWriteUnsafe(UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        return _BufferKt.commonReadAndWriteUnsafe(this, unsafeCursor);
    }
    public byte[] readByteArray() {
        try {
            return readByteArray(size());
        } catch (EOFException e) {
            throw new RuntimeException(e);
        }
    }
    public byte[] readByteArray(long j2) throws EOFException {
        if (0 > j2 || 2147483647L < j2) {
            throw new IllegalArgumentException(("byteCount: " + j2));
        }
        if (size() < j2) {
            throw new EOFException();
        }
        byte[] bArr = new byte[(int) j2];
        readFully(bArr);
        return bArr;
    }
    public static final class UnsafeCursor implements Closeable {
        public Buffer buffer;
        public byte[] data;
        public boolean readWrite;
        private Segment segment;
        public long offset = -1;
        public int start = -1;
        public int end = -1;
        public Segment getSegmentokio() {
            return this.segment;
        }
        public void setSegmentokio(Segment segment) {
            this.segment = segment;
        }
        public int next() {
            long j2 = this.offset;
            Buffer buffer = this.buffer;
            Intrinsics.checkNotNull(buffer);
            if (j2 == buffer.size()) {
                throw new IllegalStateException("no more bytes");
            }
            long j3 = this.offset;
            return seek(-1 == j3 ? 0L : j3 + (this.end - this.start));
        }
        public int seek(long j2) {
            Segment segmentPush;
            Buffer buffer = this.buffer;
            if (null == buffer) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if (-1 > j2 || j2 > buffer.size()) {
                throw new ArrayIndexOutOfBoundsException("offset=" + j2 + " > size=" + buffer.size());
            }
            if (-1 == j2 || j2 == buffer.size()) {
                segment = null;
                this.offset = j2;
                this.data = null;
                this.start = -1;
                this.end = -1;
                return -1;
            }
            long size = buffer.size();
            Segment segmentokio = buffer.head;
            long j3 = 0;
            if (null != this.segment) {
                long j4 = this.offset;
                int i2 = this.start;
                Intrinsics.checkNotNull(segment);
                Segment r9 = null;
                long j5 = j4 - (i2 - r9.pos);
                if (j5 > j2) {
                    segmentPush = segmentokio;
                    segmentokio = segment;
                    size = j5;
                } else {
                    segmentPush = segment;
                    j3 = j5;
                }
            } else {
                segmentPush = segmentokio;
            }
            if (size - j2 > j2 - j3) {
                while (true) {
                    Intrinsics.checkNotNull(segmentPush);
                    int i3 = segmentPush.limit;
                    int i4 = segmentPush.pos;
                    if (j2 < (i3 - i4) + j3) {
                        break;
                    }
                    j3 += i3 - i4;
                    segmentPush = segmentPush.next;
                }
            } else {
                while (size > j2) {
                    Intrinsics.checkNotNull(segmentokio);
                    segmentokio = segmentokio.prev;
                    Intrinsics.checkNotNull(segmentokio);
                    size -= segmentokio.limit - segmentokio.pos;
                }
                j3 = size;
                segmentPush = segmentokio;
            }
            if (this.readWrite) {
                Intrinsics.checkNotNull(segmentPush);
                if (segmentPush.shared) {
                    Segment segmentUnsharedCopy = segmentPush.unsharedCopy();
                    if (buffer.head == segmentPush) {
                        buffer.head = segmentUnsharedCopy;
                    }
                    segmentPush = segmentPush.push(segmentUnsharedCopy);
                    Segment segment = segmentPush.prev;
                    Intrinsics.checkNotNull(segment);
                    segment.pop();
                }
            }
            segment = segmentPush;
            this.offset = j2;
            Intrinsics.checkNotNull(segmentPush);
            this.data = segmentPush.data;
            int i5 = segmentPush.pos + ((int) (j2 - j3));
            this.start = i5;
            int i6 = segmentPush.limit;
            this.end = i6;
            return i6 - i5;
        }
        public long resizeBuffer(long j2) {
            Buffer buffer = this.buffer;
            if (null == buffer) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if (!this.readWrite) {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
            }
            long size = buffer.size();
            if (j2 <= size) {
                if (0 > j2) {
                    throw new IllegalArgumentException(("newSize < 0: " + j2));
                }
                long j3 = size - j2;
                while (true) {
                    if (0 >= j3) {
                        break;
                    }
                    Segment segment = buffer.head;
                    Intrinsics.checkNotNull(segment);
                    Segment segment2 = segment.prev;
                    Intrinsics.checkNotNull(segment2);
                    int i2 = segment2.limit;
                    long j4 = i2 - segment2.pos;
                    if (j4 <= j3) {
                        buffer.head = segment2.pop();
                        SegmentPool.recycle(segment2);
                        j3 -= j4;
                    } else {
                        segment2.limit = i2 - ((int) j3);
                        break;
                    }
                }
                segment = null;
                this.offset = j2;
                this.data = null;
                this.start = -1;
                this.end = -1;
            } else if (j2 > size) {
                long j5 = j2 - size;
                boolean z = true;
                while (0 < j5) {
                    Segment segmentWritableSegmentokio = buffer.writableSegmentokio(1);
                    int iMin = (int) Math.min(j5, 8192 - segmentWritableSegmentokio.limit);
                    segmentWritableSegmentokio.limit += iMin;
                    j5 -= iMin;
                    if (z) {
                        segment = segmentWritableSegmentokio;
                        this.offset = size;
                        this.data = segmentWritableSegmentokio.data;
                        int i3 = segmentWritableSegmentokio.limit;
                        this.start = i3 - iMin;
                        this.end = i3;
                        z = false;
                    }
                }
            }
            buffer.setSizeokio(j2);
            return size;
        }
        public void close() {
            if (null == buffer) {
                throw new IllegalStateException("not attached to a buffer");
            }
            this.buffer = null;
            segment = null;
            this.offset = -1L;
            this.data = null;
            this.start = -1;
            this.end = -1;
        }
    }
    public void readFully(byte[] sink) throws EOFException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        int i2 = 0;
        while (i2 < sink.length) {
            int i3 = read(sink, i2, sink.length - i2);
            if (-1 == i3) {
                throw new EOFException();
            }
            i2 += i3;
        }
    }
    public int read(byte[] sink, int i2, int i3) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        _UtilKt.checkOffsetAndCount(sink.length, i2, i3);
        Segment segment = this.head;
        if (null == segment) {
            return -1;
        }
        int iMin = Math.min(i3, segment.limit - segment.pos);
        byte[] bArr = segment.data;
        int i4 = segment.pos;
        ArraysKt.copyInto(bArr, sink, i2, i4, i4 + iMin);
        segment.pos += iMin;
        size = size() - iMin;
        if (segment.pos == segment.limit) {
            this.head = segment.pop();
            SegmentPool.recycle(segment);
        }
        return iMin;
    }
    public long readDecimalLong() throws EOFException {
        if (0 == this.size()) {
            throw new EOFException();
        }
        int i2 = 0;
        boolean z = false;
        long j2 = 0;
        long j3 = -7;
        boolean z2 = false;
        do {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            byte[] bArr = segment.data;
            int i3 = segment.pos;
            int i4 = segment.limit;
            while (i3 < i4) {
                byte b2 = bArr[i3];
                final byte b3 = 48;
                if (b3 <= b2 && ((byte) 57) >= b2) {
                    int i5 = b3 - b2;
                    if (-922337203685477580L > j2 || (-922337203685477580L == j2 && i5 < j3)) {
                        Buffer bufferWriteByte = new Buffer().writeDecimalLong(j2).writeByte(b2);
                        if (!z) {
                            bufferWriteByte.readByte();
                        }
                        throw new NumberFormatException("Number too large: " + bufferWriteByte.readUtf8());
                    }
                    j2 = (j2 * 10) + i5;
                } else {
                    if (((byte) 45) != b2 || 0 != i2) {
                        z2 = true;
                        break;
                    }
                    j3--;
                    z = true;
                }
                i3++;
                i2++;
            }
            if (i3 == i4) {
                this.head = segment.pop();
                SegmentPool.recycle(segment);
            } else {
                segment.pos = i3;
            }
            if (z2) {
                break;
            }
        } while (null != head);
        size = size() - i2;
        if (i2 >= (z ? 2 : 1)) {
            return z ? j2 : -j2;
        }
        if (0 == this.size()) {
            throw new EOFException();
        }
        throw new NumberFormatException((z ? "Expected a digit" : "Expected a digit or '-'") + " but was 0x" + _UtilKt.toHexString(getByte(0L)));
    }
    public long readHexadecimalUnsignedLong() throws EOFException {
        int i2 = 0;
        if (0 == this.size()) {
            throw new EOFException();
        }
        int i3 = 0;
        boolean z = false;
        long j2 = 0;
        do {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            byte[] bArr = segment.data;
            int i4 = segment.pos;
            int i5 = segment.limit;
            while (i4 < i5) {
                byte b2 = bArr[i4];
                final byte b3 = 48;
                if (b3 > b2 || ((byte) 57) < b2) {
                    byte b4 = 97;
                    if ((b2 >= b4 && ((byte) 102) >= b2) || (b2 >= (b4 = 65) && ((byte) 70) >= b2)) {
                        i2 = (b2 - b4) + 10;
                    } else {
                        if (0 == i3) {
                            throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x" + _UtilKt.toHexString(b2));
                        }
                        z = true;
                        if (i4 != i5) {
                            this.head = segment.pop();
                            SegmentPool.recycle(segment);
                        } else {
                            segment.pos = i4;
                        }
                        if (!z) {
                            break;
                        }
                    }
                } else {
                    i2 = b2 - b3;
                }
                if (0 != ((-1152921504606846976L) & j2)) {
                    throw new NumberFormatException("Number too large: " + new Buffer().writeHexadecimalUnsignedLong(j2).writeByte(b2).readUtf8());
                }
                j2 = (j2 << 4) | i2;
                i4++;
                i3++;
            }
            if (i4 != i5) {
            }
            if (!z) {
            }
        } while (null != head);
        size = size() - i3;
        return j2;
    }
    public ByteString readByteString() {
        try {
            return readByteString(size());
        } catch (EOFException e) {
            throw new RuntimeException(e);
        }
    }
    public ByteString readByteString(long j2) throws EOFException {
        if (0 > j2 || 2147483647L < j2) {
            throw new IllegalArgumentException(("byteCount: " + j2));
        }
        if (size() < j2) {
            throw new EOFException();
        }
        if (4096 <= j2) {
            ByteString byteStringSnapshot = snapshot((int) j2);
            skip(j2);
            return byteStringSnapshot;
        }
        return new ByteString(readByteArray(j2));
    }
    public int select(Options options) throws EOFException {
        Intrinsics.checkNotNullParameter(options, "options");
        int iSelectPrefixdefault = _BufferKt.selectPrefixdefault(this, options, false, 2, null);
        if (-1 == iSelectPrefixdefault) {
            return -1;
        }
        skip(options.getByteStringsokio()[iSelectPrefixdefault].size());
        return iSelectPrefixdefault;
    }
    public void readFully(Buffer sink, long j2) throws EOFException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (size() < j2) {
            sink.write(this, size());
            throw new EOFException();
        }
        sink.write(this, j2);
    }
    public long readAll(Sink sink) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        long size = size();
        if (0 < size) {
            sink.write(this, size);
        }
        return size;
    }
    public String readUtf8LineStrict(long j2) throws EOFException {
        if (0 > j2) {
            throw new IllegalArgumentException(("limit < 0: " + j2));
        }
        long j3 = LocationRequestCompat.PASSIVE_INTERVAL;
        if (LocationRequestCompat.PASSIVE_INTERVAL != j2) {
            j3 = j2 + 1;
        }
        final byte b2 = 10;
        long jIndexOf = indexOf(b2, 0L, j3);
        if (-1 != jIndexOf) {
            return _BufferKt.readUtf8Line(this, jIndexOf);
        }
        if (j3 < size() && ((byte) 13) == this.getByte(j3 - 1) && b2 == this.getByte(j3)) {
            return _BufferKt.readUtf8Line(this, j3);
        }
        Buffer buffer = new Buffer();
        copyTo(buffer, 0L, Math.min(32, size()));
        throw new EOFException("\\n not found: limit=" + Math.min(size(), j2) + " content=" + buffer.readByteString().hex() + '\u2026');
    }
    public int readUtf8CodePoint() throws EOFException {
        int i2;
        int i3;
        int i4;
        if (0 == this.size()) {
            throw new EOFException();
        }
        byte b2 = getByte(0L);
        if (0 == (b2 & 128)) {
            i2 = b2 & 127;
            i4 = 0;
            i3 = 1;
        } else if (192 == (b2 & 224)) {
            i2 = b2 & 31;
            i3 = 2;
            i4 = 128;
        } else if (224 == (b2 & 240)) {
            i2 = b2 & 15;
            i3 = 3;
            i4 = 2048;
        } else {
            if (240 != (b2 & 248)) {
                skip(1L);
                return 65533;
            }
            i2 = b2 & 7;
            i3 = 4;
            i4 = 65536;
        }
        long j2 = i3;
        if (size() < j2) {
            throw new EOFException("size < " + i3 + ": " + size() + " (to read code point prefixed 0x" + _UtilKt.toHexString(b2) + ')');
        }
        for (int i5 = 1; i5 < i3; i5++) {
            long j3 = i5;
            byte b3 = getByte(j3);
            if (128 != (b3 & 192)) {
                skip(j3);
                return 65533;
            }
            i2 = (i2 << 6) | (b3 & 63);
        }
        skip(j2);
        if (1114111 < i2) {
            return 65533;
        }
        if ((55296 > i2 || 57344 <= i2) && i2 >= i4) {
            return i2;
        }
        return 65533;
    }
    public Buffer writeUtf8(String string, int i2, int i3) {
        char cCharAt;
        Intrinsics.checkNotNullParameter(string, "string");
        if (0 > i2) {
            throw new IllegalArgumentException(("beginIndex < 0: " + i2));
        }
        if (i3 < i2) {
            throw new IllegalArgumentException(("endIndex < beginIndex: " + i3 + " < " + i2));
        }
        if (i3 > string.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i3 + " > " + string.length()));
        }
        while (i2 < i3) {
            char cCharAt2 = string.charAt(i2);
            if ('\u0080' > cCharAt2) {
                Segment segmentWritableSegmentokio = writableSegmentokio(1);
                byte[] bArr = segmentWritableSegmentokio.data;
                int i4 = segmentWritableSegmentokio.limit - i2;
                int iMin = Math.min(i3, 8192 - i4);
                int i5 = i2 + 1;
                bArr[i2 + i4] = (byte) cCharAt2;
                while (true) {
                    i2 = i5;
                    if (i2 >= iMin || '\u0080' <= (cCharAt = string.charAt(i2))) {
                        break;
                    }
                    i5 = i2 + 1;
                    bArr[i2 + i4] = (byte) cCharAt;
                }
                int i6 = segmentWritableSegmentokio.limit;
                int i7 = (i4 + i2) - i6;
                segmentWritableSegmentokio.limit = i6 + i7;
                size = size() + i7;
            } else {
                if ('\u0800' > cCharAt2) {
                    Segment segmentWritableSegmentokio2 = writableSegmentokio(2);
                    byte[] bArr2 = segmentWritableSegmentokio2.data;
                    int i8 = segmentWritableSegmentokio2.limit;
                    bArr2[i8] = (byte) ((cCharAt2 >> 6) | Wbxml.EXT_0);
                    bArr2[i8 + 1] = (byte) ((cCharAt2 & '?') | 128);
                    segmentWritableSegmentokio2.limit = i8 + 2;
                    size = size() + 2;
                } else if ('\ud800' > cCharAt2 || '\udfff' < cCharAt2) {
                    Segment segmentWritableSegmentokio3 = writableSegmentokio(3);
                    byte[] bArr3 = segmentWritableSegmentokio3.data;
                    int i9 = segmentWritableSegmentokio3.limit;
                    bArr3[i9] = (byte) ((cCharAt2 >> '\f') | 224);
                    bArr3[i9 + 1] = (byte) ((63 & (cCharAt2 >> 6)) | 128);
                    bArr3[i9 + 2] = (byte) ((cCharAt2 & '?') | 128);
                    segmentWritableSegmentokio3.limit = i9 + 3;
                    size = size() + 3;
                } else {
                    int i10 = i2 + 1;
                    char cCharAt3 = i10 < i3 ? string.charAt(i10) : (char) 0;
                    if ('\udbff' < cCharAt2 || '\udc00' > cCharAt3 || '\ue000' <= cCharAt3) {
                        writeByte(63);
                        i2 = i10;
                    } else {
                        int i11 = (((cCharAt2 & '\u03ff') << 10) | (cCharAt3 & '\u03ff')) + 65536;
                        Segment segmentWritableSegmentokio4 = writableSegmentokio(4);
                        byte[] bArr4 = segmentWritableSegmentokio4.data;
                        int i12 = segmentWritableSegmentokio4.limit;
                        bArr4[i12] = (byte) ((i11 >> 18) | 240);
                        bArr4[i12 + 1] = (byte) (((i11 >> 12) & 63) | 128);
                        bArr4[i12 + 2] = (byte) (((i11 >> 6) & 63) | 128);
                        bArr4[i12 + 3] = (byte) ((i11 & 63) | 128);
                        segmentWritableSegmentokio4.limit = i12 + 4;
                        size = size() + 4;
                        i2 += 2;
                    }
                }
                i2++;
            }
        }
        return this;
    }
    public Buffer writeUtf8CodePoint(int i2) {
        if (128 > i2) {
            writeByte(i2);
        } else if (2048 > i2) {
            Segment segmentWritableSegmentokio = writableSegmentokio(2);
            byte[] bArr = segmentWritableSegmentokio.data;
            int i3 = segmentWritableSegmentokio.limit;
            bArr[i3] = (byte) ((i2 >> 6) | Wbxml.EXT_0);
            bArr[i3 + 1] = (byte) ((i2 & 63) | 128);
            segmentWritableSegmentokio.limit = i3 + 2;
            size = size() + 2;
        } else if (55296 <= i2 && 57344 > i2) {
            writeByte(63);
        } else if (65536 > i2) {
            Segment segmentWritableSegmentokio2 = writableSegmentokio(3);
            byte[] bArr2 = segmentWritableSegmentokio2.data;
            int i4 = segmentWritableSegmentokio2.limit;
            bArr2[i4] = (byte) ((i2 >> 12) | 224);
            bArr2[i4 + 1] = (byte) (((i2 >> 6) & 63) | 128);
            bArr2[i4 + 2] = (byte) ((i2 & 63) | 128);
            segmentWritableSegmentokio2.limit = i4 + 3;
            size = size() + 3;
        } else if (1114111 >= i2) {
            Segment segmentWritableSegmentokio3 = writableSegmentokio(4);
            byte[] bArr3 = segmentWritableSegmentokio3.data;
            int i5 = segmentWritableSegmentokio3.limit;
            bArr3[i5] = (byte) ((i2 >> 18) | 240);
            bArr3[i5 + 1] = (byte) (((i2 >> 12) & 63) | 128);
            bArr3[i5 + 2] = (byte) (((i2 >> 6) & 63) | 128);
            bArr3[i5 + 3] = (byte) ((i2 & 63) | 128);
            segmentWritableSegmentokio3.limit = i5 + 4;
            size = size() + 4;
        } else {
            throw new IllegalArgumentException("Unexpected code point: 0x" + _UtilKt.toHexString(i2));
        }
        return this;
    }
    public long writeAll(Source source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        long j2 = 0;
        while (true) {
            long j3 = 0;
            try {
                j3 = source.read(this, 8192L);
            } catch (DataFormatException e) {
                throw new RuntimeException(e);
            }
            if (-1 == j3) {
                return j2;
            }
            j2 += j3;
        }
    }
    public Buffer write(Source source, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        while (0 < j2) {
            long j3 = 0;
            try {
                j3 = source.read(this, j2);
            } catch (DataFormatException e) {
                throw new RuntimeException(e);
            }
            if (-1 == j3) {
                throw new EOFException();
            }
            j2 -= j3;
        }
        return this;
    }
    public Buffer writeByte(int i2) {
        Segment segmentWritableSegmentokio = writableSegmentokio(1);
        byte[] bArr = segmentWritableSegmentokio.data;
        int i3 = segmentWritableSegmentokio.limit;
        segmentWritableSegmentokio.limit = i3 + 1;
        bArr[i3] = (byte) i2;
        size = size() + 1;
        return this;
    }
    public Buffer writeShort(int i2) {
        Segment segmentWritableSegmentokio = writableSegmentokio(2);
        byte[] bArr = segmentWritableSegmentokio.data;
        int i3 = segmentWritableSegmentokio.limit;
        bArr[i3] = (byte) ((i2 >>> 8) & 255);
        bArr[i3 + 1] = (byte) (i2 & 255);
        segmentWritableSegmentokio.limit = i3 + 2;
        size = size() + 2;
        return this;
    }
    public Buffer writeInt(int i2) {
        Segment segmentWritableSegmentokio = writableSegmentokio(4);
        byte[] bArr = segmentWritableSegmentokio.data;
        int i3 = segmentWritableSegmentokio.limit;
        bArr[i3] = (byte) ((i2 >>> 24) & 255);
        bArr[i3 + 1] = (byte) ((i2 >>> 16) & 255);
        bArr[i3 + 2] = (byte) ((i2 >>> 8) & 255);
        bArr[i3 + 3] = (byte) (i2 & 255);
        segmentWritableSegmentokio.limit = i3 + 4;
        size = size() + 4;
        return this;
    }
    public Buffer writeLong(long j2) {
        Segment segmentWritableSegmentokio = writableSegmentokio(8);
        byte[] bArr = segmentWritableSegmentokio.data;
        int i2 = segmentWritableSegmentokio.limit;
        bArr[i2] = (byte) ((j2 >>> 56) & 255);
        bArr[i2 + 1] = (byte) ((j2 >>> 48) & 255);
        bArr[i2 + 2] = (byte) ((j2 >>> 40) & 255);
        bArr[i2 + 3] = (byte) ((j2 >>> 32) & 255);
        bArr[i2 + 4] = (byte) ((j2 >>> 24) & 255);
        bArr[i2 + 5] = (byte) ((j2 >>> 16) & 255);
        bArr[i2 + 6] = (byte) ((j2 >>> 8) & 255);
        bArr[i2 + 7] = (byte) (j2 & 255);
        segmentWritableSegmentokio.limit = i2 + 8;
        size = size() + 8;
        return this;
    }
    public void write(Buffer source, long j2) {
        Segment segment;
        Intrinsics.checkNotNullParameter(source, "source");
        if (source == this) {
            throw new IllegalArgumentException("source == this");
        }
        _UtilKt.checkOffsetAndCount(source.size(), 0L, j2);
        while (0 < j2) {
            Segment segment2 = source.head;
            Intrinsics.checkNotNull(segment2);
            int i2 = segment2.limit;
            Intrinsics.checkNotNull(source.head);
            if (j2 < i2 - r1.pos) {
                Segment segment3 = this.head;
                if (null != segment3) {
                    Intrinsics.checkNotNull(segment3);
                    segment = segment3.prev;
                } else {
                    segment = null;
                }
                if (null != segment && segment.owner) {
                    if (8192 >= (segment.limit + j2) - (segment.shared ? 0 : segment.pos)) {
                        Segment segment4 = source.head;
                        Intrinsics.checkNotNull(segment4);
                        segment4.writeTo(segment, (int) j2);
                        source.size = source.size() - j2;
                        size = size() + j2;
                        return;
                    }
                }
                Segment segment5 = source.head;
                Intrinsics.checkNotNull(segment5);
                source.head = segment5.split((int) j2);
            }
            Segment segment6 = source.head;
            Intrinsics.checkNotNull(segment6);
            long j3 = segment6.limit - segment6.pos;
            source.head = segment6.pop();
            Segment segment7 = this.head;
            if (null == segment7) {
                this.head = segment6;
                segment6.prev = segment6;
                segment6.next = segment6;
            } else {
                Intrinsics.checkNotNull(segment7);
                Segment segment8 = segment7.prev;
                Intrinsics.checkNotNull(segment8);
                segment8.push(segment6).compact();
            }
            source.size = source.size() - j3;
            size = size() + j3;
            j2 -= j3;
        }
    }
    public long read(Buffer sink, long j2) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (0 > j2) {
            throw new IllegalArgumentException(("byteCount < 0: " + j2));
        }
        if (0 == this.size()) {
            return -1L;
        }
        if (j2 > size()) {
            j2 = size();
        }
        sink.write(this, j2);
        return j2;
    }
    public long indexOf(byte b2, long j2, long j3) {
        Segment segment;
        int i2;
        long size = 0;
        if (0 > j2 || j2 > j3) {
            throw new IllegalArgumentException(("size=" + size() + " fromIndex=" + j2 + " toIndex=" + j3));
        }
        if (j3 > size()) {
            j3 = size();
        }
        if (j2 == j3 || null == (segment = head)) {
            return -1L;
        }
        if (size() - j2 < j2) {
            size = size();
            while (size > j2) {
                segment = segment.prev;
                Intrinsics.checkNotNull(segment);
                size -= segment.limit - segment.pos;
            }
            while (size < j3) {
                byte[] bArr = segment.data;
                int iMin = (int) Math.min(segment.limit, (segment.pos + j3) - size);
                i2 = (int) ((segment.pos + j2) - size);
                while (i2 < iMin) {
                    if (bArr[i2] != b2) {
                        i2++;
                    }
                }
                size += segment.limit - segment.pos;
                segment = segment.next;
                Intrinsics.checkNotNull(segment);
                j2 = size;
            }
            return -1L;
        }
        while (true) {
            long j4 = (segment.limit - segment.pos) + size;
            if (j4 > j2) {
                break;
            }
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
            size = j4;
        }
        while (size < j3) {
            byte[] bArr2 = segment.data;
            int iMin2 = (int) Math.min(segment.limit, (segment.pos + j3) - size);
            i2 = (int) ((segment.pos + j2) - size);
            while (i2 < iMin2) {
                if (bArr2[i2] != b2) {
                    i2++;
                }
            }
            size += segment.limit - segment.pos;
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
            j2 = size;
        }
        return -1L;
    }
    public long indexOf(ByteString bytes, long j2) throws IOException {
        int i2;
        long j3 = j2;
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (0 >= bytes.size()) {
            throw new IllegalArgumentException("bytes is empty");
        }
        long size = 0;
        if (0 <= j3) {
            Segment segment = this.head;
            if (null != segment) {
                if (size() - j3 < j3) {
                    size = size();
                    while (size > j3) {
                        segment = segment.prev;
                        Intrinsics.checkNotNull(segment);
                        size -= segment.limit - segment.pos;
                    }
                    byte[] bArrInternalArrayokio = bytes.internalArrayokio();
                    byte b2 = bArrInternalArrayokio[0];
                    int size2 = bytes.size();
                    long size3 = (size() - size2) + 1;
                    while (size < size3) {
                        byte[] bArr = segment.data;
                        long j4 = size3;
                        int iMin = (int) Math.min(segment.limit, (segment.pos + size3) - size);
                        i2 = (int) ((segment.pos + j3) - size);
                        while (i2 < iMin) {
                            if (bArr[i2] == b2 && _BufferKt.rangeEquals(segment, i2 + 1, bArrInternalArrayokio, 1, size2)) {
                                return (i2 - segment.pos) + size;
                            }
                            i2++;
                        }
                        size += segment.limit - segment.pos;
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        j3 = size;
                        size3 = j4;
                    }
                } else {
                    while (true) {
                        long j5 = (segment.limit - segment.pos) + size;
                        if (j5 > j3) {
                            break;
                        }
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        size = j5;
                    }
                    byte[] bArrInternalArrayokio2 = bytes.internalArrayokio();
                    byte b3 = bArrInternalArrayokio2[0];
                    int size4 = bytes.size();
                    long size5 = (size() - size4) + 1;
                    while (size < size5) {
                        byte[] bArr2 = segment.data;
                        int iMin2 = (int) Math.min(segment.limit, (segment.pos + size5) - size);
                        i2 = (int) ((segment.pos + j3) - size);
                        while (i2 < iMin2) {
                            if (bArr2[i2] == b3 && _BufferKt.rangeEquals(segment, i2 + 1, bArrInternalArrayokio2, 1, size4)) {
                                return (i2 - segment.pos) + size;
                            }
                            i2++;
                        }
                        size += segment.limit - segment.pos;
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        j3 = size;
                    }
                }
            }
            return -1L;
        }
        throw new IllegalArgumentException(("fromIndex < 0: " + j3));
    }
    public boolean rangeEquals(long j2, ByteString bytes, int i2, int i3) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        if (0 > j2 || 0 > i2 || 0 > i3 || size() - j2 < i3 || bytes.size() - i2 < i3) {
            return false;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            if (getByte(i4 + j2) != bytes.getByte(i2 + i4)) {
                return false;
            }
        }
        return true;
    }
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof final Buffer buffer) {
            if (size() == buffer.size()) {
                if (0 == this.size()) {
                    return true;
                }
                Segment segment = this.head;
                Intrinsics.checkNotNull(segment);
                Segment segment2 = buffer.head;
                Intrinsics.checkNotNull(segment2);
                int i2 = segment.pos;
                int i3 = segment2.pos;
                long j2 = 0;
                while (j2 < size()) {
                    long jMin = Math.min(segment.limit - i2, segment2.limit - i3);
                    long j3 = 0;
                    while (j3 < jMin) {
                        int i4 = i2 + 1;
                        int i5 = i3 + 1;
                        if (segment.data[i2] == segment2.data[i3]) {
                            j3++;
                            i2 = i4;
                            i3 = i5;
                        }
                    }
                    if (i2 == segment.limit) {
                        segment = segment.next;
                        Intrinsics.checkNotNull(segment);
                        i2 = segment.pos;
                    }
                    if (i3 == segment2.limit) {
                        segment2 = segment2.next;
                        Intrinsics.checkNotNull(segment2);
                        i3 = segment2.pos;
                    }
                    j2 += jMin;
                }
                return true;
            }
        }
        return false;
    }
    public int hashCode() {
        Segment segment = this.head;
        if (null == segment) {
            return 0;
        }
        int i2 = 1;
        do {
            int i3 = segment.limit;
            for (int i4 = segment.pos; i4 < i3; i4++) {
                i2 = (i2 * 31) + segment.data[i4];
            }
            segment = segment.next;
            Intrinsics.checkNotNull(segment);
        } while (segment != this.head);
        return i2;
    }
    public Buffer copy() {
        Buffer buffer = new Buffer();
        if (0 != this.size()) {
            Segment segment = this.head;
            Intrinsics.checkNotNull(segment);
            Segment segmentSharedCopy = segment.sharedCopy();
            buffer.head = segmentSharedCopy;
            segmentSharedCopy.prev = segmentSharedCopy;
            segmentSharedCopy.next = segmentSharedCopy;
            for (Segment segment2 = segment.next; segment2 != segment; segment2 = segment2.next) {
                Segment segment3 = segmentSharedCopy.prev;
                Intrinsics.checkNotNull(segment3);
                Intrinsics.checkNotNull(segment2);
                segment3.push(segment2.sharedCopy());
            }
            buffer.size = size();
        }
        return buffer;
    }
    public ByteString snapshot() {
        if (2147483647L < this.size()) {
            throw new IllegalStateException(("size > Int.MAX_VALUE: " + size()));
        }
        return snapshot((int) size());
    }
    public ByteString snapshot(int i2) {
        if (0 == i2) {
            return ByteString.EMPTY;
        }
        _UtilKt.checkOffsetAndCount(size(), 0L, i2);
        Segment segment = this.head;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            Intrinsics.checkNotNull(segment);
            int i6 = segment.limit;
            int i7 = segment.pos;
            if (i6 == i7) {
                throw new AssertionError("s.limit == s.pos");
            }
            i4 += i6 - i7;
            i5++;
            segment = segment.next;
        }
        byte[][] bArr = new byte[i5][];
        int[] iArr = new int[i5 * 2];
        Segment segment2 = this.head;
        int i8 = 0;
        while (i3 < i2) {
            Intrinsics.checkNotNull(segment2);
            bArr[i8] = segment2.data;
            i3 += segment2.limit - segment2.pos;
            iArr[i8] = Math.min(i3, i2);
            iArr[i8 + i5] = segment2.pos;
            segment2.shared = true;
            i8++;
            segment2 = segment2.next;
        }
        return new SegmentedByteString(bArr, iArr);
    }
}
