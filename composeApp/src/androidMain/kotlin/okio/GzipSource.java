package okio;

import kotlin.jvm.internal.Intrinsics;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;
import static kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue;

public final class GzipSource implements Source {
    private final CRC32 crc;
    private final Inflater inflater;
    private final InflaterSource inflaterSource;
    private byte section;
    private final RealBufferedSource source;
    public GzipSource(final Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        final RealBufferedSource realBufferedSource = new RealBufferedSource(source);
        this.source = realBufferedSource;
        final Inflater inflater = new Inflater(true);
        this.inflater = inflater;
        inflaterSource = new InflaterSource(realBufferedSource, inflater);
        crc = new CRC32();
    }
    public long read(final Buffer sink, final long j2) throws DataFormatException, IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        if (0 > j2) {
            throw new IllegalArgumentException(("byteCount < 0: " + j2));
        }
        if (0 == j2) {
            return 0L;
        }
        if (0 == this.section) {
            this.consumeHeader();
            section = 1;
        }
        if (1 == this.section) {
            final long size = sink.size();
            final long j3 = inflaterSource.read(sink, j2);
            if (-1 != j3) {
                this.updateCrc(sink, size, j3);
                return j3;
            }
            section = 2;
        }
        if (2 == this.section) {
            this.consumeTrailer();
            section = 3;
            if (!source.exhausted()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }
    private void consumeHeader() throws IOException {
        source.require(10L);
        final byte b2 = source.bufferField.getByte(3L);
        final boolean z = 1 == ((b2 >> 1) & 1);
        if (z) {
            this.updateCrc(source.bufferField, 0L, 10L);
        }
        this.checkEqual("ID1ID2", 8075, source.readShort());
        source.skip(8L);
        if (1 == ((b2 >> 2) & 1)) {
            source.require(2L);
            if (z) {
                this.updateCrc(source.bufferField, 0L, 2L);
            }
            final long shortLe = source.bufferField.readShortLe();
            source.require(shortLe);
            if (z) {
                this.updateCrc(source.bufferField, 0L, shortLe);
            }
            source.skip(shortLe);
        }
        if (1 == ((b2 >> 3) & 1)) {
            final long jIndexOf = source.indexOf((byte) 0);
            if (-1 == jIndexOf) {
                throw new EOFException();
            }
            if (z) {
                this.updateCrc(source.bufferField, 0L, jIndexOf + 1);
            }
            source.skip(jIndexOf + 1);
        }
        if (1 == ((b2 >> 4) & 1)) {
            final long jIndexOf2 = source.indexOf((byte) 0);
            if (-1 == jIndexOf2) {
                throw new EOFException();
            }
            if (z) {
                this.updateCrc(source.bufferField, 0L, jIndexOf2 + 1);
            }
            source.skip(jIndexOf2 + 1);
        }
        if (z) {
            this.checkEqual("FHCRC", source.readShortLe(), (short) crc.getValue());
            crc.reset();
        }
    }
    private void consumeTrailer() throws IOException {
        this.checkEqual("CRC", source.readIntLe(), (int) crc.getValue());
        this.checkEqual("ISIZE", source.readIntLe(), (int) inflater.getBytesWritten());
    }
    public Timeout timeout() {
        return source.timeout();
    }
    public void close() throws IOException {
        inflaterSource.close();
    }
    private void updateCrc(final Buffer buffer, long j2, long j3) {
        Segment segment = buffer.head;
        checkNotNull(segment);
        while (true) {
            final int i2 = segment.limit;
            final int i3 = segment.pos;
            if (j2 < i2 - i3) {
                break;
            }
            j2 -= i2 - i3;
            segment = segment.next;
            checkNotNull(segment);
        }
        while (0 < j3) {
            final int r6 = 0;
            final int iMin = (int) Math.min(segment.limit - r6, j3);
            crc.update(segment.data, (int) (segment.pos + j2), iMin);
            j3 -= iMin;
            segment = segment.next;
            checkNotNull(segment);
            j2 = 0;
        }
    }
    private void checkEqual(final String str, final int i2, final int i3) throws IOException {
        if (i3 == i2) {
            return;
        }
        final String str2 = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{str, Integer.valueOf(i3), Integer.valueOf(i2)}, 3));
        checkNotNullExpressionValue(str2, "format(this, *args)");
        throw new IOException(str2);
    }
}
