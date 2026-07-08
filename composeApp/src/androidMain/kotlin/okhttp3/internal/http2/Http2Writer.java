package okhttp3.internal.http2;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;
import static kotlin.jvm.internal.Intrinsics.stringPlus;

public final class Http2Writer implements Closeable {
    public static final Companion Companion = new Companion(null);
    private static final Logger logger = Logger.getLogger(Http2.class.getName());
    private final boolean client;
    private final Buffer hpackBuffer;
    private final Hpack.Writer hpackWriter;
    private final BufferedSink sink;
    private boolean closed;
    private int maxFrameSize;

    public Http2Writer(final BufferedSink sink, final boolean z) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        this.sink = sink;
        client = z;
        final Buffer buffer = new Buffer();
        hpackBuffer = buffer;
        maxFrameSize = 16384;
        hpackWriter = new Hpack.Writer(0, false, buffer, 3, null);
    }

    public Hpack.Writer getHpackWriter() {
        return hpackWriter;
    }

    public synchronized void connectionPreface() throws IOException {
        try {
            if (closed) {
                throw new IOException("closed");
            }
            if (client) {
                final Logger logger2 = Http2Writer.logger;
                if (logger2.isLoggable(Level.FINE)) {
                    logger2.fine(Util.format(stringPlus(">> CONNECTION ", Http2.CONNECTION_PREFACE.hex())));
                }
                sink.write(Http2.CONNECTION_PREFACE);
                sink.flush();
            }
        } catch (final Throwable th) {
            throw th;
        }
    }

    public synchronized void applyAndAckSettings(final Settings peerSettings) throws IOException {
        try {
            Intrinsics.checkNotNullParameter(peerSettings, "peerSettings");
            if (closed) {
                throw new IOException("closed");
            }
            maxFrameSize = peerSettings.getMaxFrameSize(maxFrameSize);
            if (-1 != peerSettings.getHeaderTableSize()) {
                hpackWriter.resizeHeaderTable(peerSettings.getHeaderTableSize());
            }
            this.frameHeader(0, 0, 4, 1);
            sink.flush();
        } catch (final Throwable th) {
            throw th;
        }
    }

    public synchronized void pushPromise(final int i2, final int i3, final List<Header> requestHeaders) throws IOException {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        if (closed) {
            throw new IOException("closed");
        }
        hpackWriter.writeHeaders(requestHeaders);
        final long size = hpackBuffer.size();
        final int iMin = (int) Math.min(maxFrameSize - 4, size);
        final long j2 = iMin;
        this.frameHeader(i2, iMin + 4, 5, size == j2 ? 4 : 0);
        sink.writeInt(i3 & Integer.MAX_VALUE);
        sink.write(hpackBuffer, j2);
        if (size > j2) {
            this.writeContinuationFrames(i2, size - j2);
        }
    }

    public synchronized void flush() throws IOException {
        if (closed) {
            throw new IOException("closed");
        }
        sink.flush();
    }

    public synchronized void rstStream(final int i2, final ErrorCode errorCode) throws IOException {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        if (closed) {
            throw new IOException("closed");
        }
        if (-1 == errorCode.getHttpCode()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        this.frameHeader(i2, 4, 3, 0);
        sink.writeInt(errorCode.getHttpCode());
        sink.flush();
    }

    public int maxDataLength() {
        return maxFrameSize;
    }

    public synchronized void data(final boolean z, final int i2, final Buffer buffer, final int i3) throws IOException {
        if (closed) {
            throw new IOException("closed");
        }
        this.dataFrame(i2, z ? 1 : 0, buffer, i3);
    }

    public void dataFrame(final int i2, final int i3, final Buffer buffer, final int i4) throws IOException {
        this.frameHeader(i2, i4, 0, i3);
        if (0 < i4) {
            final BufferedSink bufferedSink = sink;
            checkNotNull(buffer);
            bufferedSink.write(buffer, i4);
        }
    }

    public synchronized void settings(final Settings settings) throws IOException {
        try {
            Intrinsics.checkNotNullParameter(settings, "settings");
            if (closed) {
                throw new IOException("closed");
            }
            int i2 = 0;
            this.frameHeader(0, settings.size() * 6, 4, 0);
            while (10 > i2) {
                final int i3 = i2 + 1;
                if (settings.isSet(i2)) {
                    sink.writeShort(4 != i2 ? 7 != i2 ? i2 : 4 : 3);
                    sink.writeInt(settings.get(i2));
                }
                i2 = i3;
            }
            sink.flush();
        } catch (final Throwable th) {
            throw th;
        }
    }

    public synchronized void ping(final boolean z, final int i2, final int i3) throws IOException {
        if (closed) {
            throw new IOException("closed");
        }
        this.frameHeader(0, 8, 6, z ? 1 : 0);
        sink.writeInt(i2);
        sink.writeInt(i3);
        sink.flush();
    }

    public synchronized void goAway(final int i2, final ErrorCode errorCode, final byte[] debugData) throws IOException {
        try {
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            Intrinsics.checkNotNullParameter(debugData, "debugData");
            if (closed) {
                throw new IOException("closed");
            }
            if (-1 == errorCode.getHttpCode()) {
                throw new IllegalArgumentException("errorCode.httpCode == -1");
            }
            this.frameHeader(0, debugData.length + 8, 7, 0);
            sink.writeInt(i2);
            sink.writeInt(errorCode.getHttpCode());
            if (0 != debugData.length) {
                sink.write(debugData);
            }
            sink.flush();
        } catch (final Throwable th) {
            throw th;
        }
    }

    public synchronized void windowUpdate(final int i2, final long j2) throws IOException {
        if (closed) {
            throw new IOException("closed");
        }
        if (0 == j2 || 2147483647L < j2) {
            throw new IllegalArgumentException(stringPlus("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: ", Long.valueOf(j2)));
        }
        this.frameHeader(i2, 4, 8, 0);
        sink.writeInt((int) j2);
        sink.flush();
    }

    public void frameHeader(final int i2, final int i3, final int i4, final int i5) throws IOException {
        final Logger logger2 = Http2Writer.logger;
        if (logger2.isLoggable(Level.FINE)) {
            logger2.fine(Http2.INSTANCE.frameLog(false, i2, i3, i4, i5));
        }
        if (i3 > maxFrameSize) {
            throw new IllegalArgumentException(("FRAME_SIZE_ERROR length > " + maxFrameSize + ": " + i3));
        }
        if (0 != (Integer.MIN_VALUE & i2)) {
            throw new IllegalArgumentException(stringPlus("reserved bit set: ", Integer.valueOf(i2)));
        }
        Util.writeMedium(sink, i3);
        sink.writeByte(i4 & 255);
        sink.writeByte(i5 & 255);
        sink.writeInt(i2 & Integer.MAX_VALUE);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        closed = true;
        sink.close();
    }

    private void writeContinuationFrames(final int i2, long j2) throws IOException {
        while (0 < j2) {
            final long jMin = Math.min(maxFrameSize, j2);
            j2 -= jMin;
            this.frameHeader(i2, (int) jMin, 9, 0 == j2 ? 4 : 0);
            sink.write(hpackBuffer, jMin);
        }
    }

    public synchronized void headers(final boolean z, final int i2, final List<Header> headerBlock) throws IOException {
        Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
        if (closed) {
            throw new IOException("closed");
        }
        hpackWriter.writeHeaders(headerBlock);
        final long size = hpackBuffer.size();
        final long jMin = Math.min(maxFrameSize, size);
        int i3 = size == jMin ? 4 : 0;
        if (z) {
            i3 |= 1;
        }
        this.frameHeader(i2, (int) jMin, 1, i3);
        sink.write(hpackBuffer, jMin);
        if (size > jMin) {
            this.writeContinuationFrames(i2, size - jMin);
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
