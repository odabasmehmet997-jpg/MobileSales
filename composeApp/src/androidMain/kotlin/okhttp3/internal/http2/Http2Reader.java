package okhttp3.internal.http2;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import okhttp3.internal.Util;
import okio.*;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;

import static kotlin.jvm.internal.Intrinsics.*;

public final class Http2Reader implements Closeable {
    public static final Companion Companion = new Companion(null);
    private static final Logger logger;

    static {
        final Logger logger2 = Logger.getLogger(Http2.class.getName());
        checkNotNullExpressionValue(logger2, "getLogger(Http2::class.java.name)");
        logger = logger2;
    }

    private final boolean client;
    private final ContinuationSource continuation;
    private final Hpack.Reader hpackReader;
    private final BufferedSource source;

    public Http2Reader(final BufferedSource source, final boolean z) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        client = z;
        final ContinuationSource continuationSource = new ContinuationSource(source);
        continuation = continuationSource;
        hpackReader = new Hpack.Reader(continuationSource, 4096, 0, 4, null);
    }

    public void readConnectionPreface(final Handler handler) throws IOException {
        Intrinsics.checkNotNullParameter(handler, "handler");
        if (client) {
            if (!this.nextFrame(true, handler)) {
                throw new IOException("Required SETTINGS preface not received");
            }
            return;
        }
        final BufferedSource bufferedSource = source;
        final ByteString byteString = Http2.CONNECTION_PREFACE;
        final ByteString byteString2 = bufferedSource.readByteString(byteString.size());
        final Logger logger2 = Http2Reader.logger;
        if (logger2.isLoggable(Level.FINE)) {
            logger2.fine(Util.format(stringPlus("<< CONNECTION ", byteString2.hex())));
        }
        if (!areEqual(byteString, byteString2)) {
            throw new IOException(stringPlus("Expected a connection header but was ", byteString2.utf8()));
        }
    }

    public boolean nextFrame(final boolean z, final Handler handler) throws IOException {
        Intrinsics.checkNotNullParameter(handler, "handler");
        try {
            source.require(9L);
            final int medium = Util.readMedium(source);
            if (16384 < medium) {
                throw new IOException(stringPlus("FRAME_SIZE_ERROR: ", Integer.valueOf(medium)));
            }
            final int iAnd = Util.and(source.readByte(), 255);
            final int iAnd2 = Util.and(source.readByte(), 255);
            final int i2 = source.readInt() & Integer.MAX_VALUE;
            final Logger logger2 = Http2Reader.logger;
            if (logger2.isLoggable(Level.FINE)) {
                logger2.fine(Http2.INSTANCE.frameLog(true, i2, medium, iAnd, iAnd2));
            }
            if (z && 4 != iAnd) {
                throw new IOException(stringPlus("Expected a SETTINGS frame but was ", Http2.INSTANCE.formattedTypeokhttp(iAnd)));
            }
            switch (iAnd) {
                case 0:
                    this.readData(handler, medium, iAnd2, i2);
                    return true;
                case 1:
                    this.readHeaders(handler, medium, iAnd2, i2);
                    return true;
                case 2:
                    this.readPriority(handler, medium, iAnd2, i2);
                    return true;
                case 3:
                    this.readRstStream(handler, medium, iAnd2, i2);
                    return true;
                case 4:
                    this.readSettings(handler, medium, iAnd2, i2);
                    return true;
                case 5:
                    this.readPushPromise(handler, medium, iAnd2, i2);
                    return true;
                case 6:
                    this.readPing(handler, medium, iAnd2, i2);
                    return true;
                case 7:
                    this.readGoAway(handler, medium, iAnd2, i2);
                    return true;
                case 8:
                    this.readWindowUpdate(handler, medium, iAnd2, i2);
                    return true;
                default:
                    source.skip(medium);
                    return true;
            }
        } catch (final EOFException unused) {
            return false;
        }
    }
    private void readHeaders(final Handler handler, int i2, final int i3, final int i4) throws IOException {
        if (0 == i4) {
            throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
        }
        final boolean z = 0 != (i3 & 1);
        final int iAnd = 0 != (i3 & 8) ? Util.and(source.readByte(), 255) : 0;
        if (0 != (i3 & 32)) {
            this.readPriority(handler, i4);
            i2 -= 5;
        }
        handler.headers(z, i4, -1, this.readHeaderBlock(Http2Reader.Companion.lengthWithoutPadding(i2, i3, iAnd), iAnd, i3, i4));
    }
    private List<Header> readHeaderBlock(final int i2, final int i3, final int i4, final int i5) throws IOException {
        continuation.setLeft(i2);
        final ContinuationSource continuationSource = continuation;
        continuationSource.setLength(continuationSource.getLeft());
        continuation.setPadding(i3);
        continuation.setFlags(i4);
        continuation.setStreamId(i5);
        hpackReader.readHeaders();
        return hpackReader.getAndResetHeaderList();
    }
    private void readData(final Handler handler, final int i2, final int i3, final int i4) throws IOException {
        if (0 == i4) {
            throw new IOException("PROTOCOL_ERROR: TYPE_DATA streamId == 0");
        }
        final boolean z = 0 != (i3 & 1);
        if (0 != (i3 & 32)) {
            throw new IOException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA");
        }
        final int iAnd = 0 != (i3 & 8) ? Util.and(source.readByte(), 255) : 0;
        handler.data(z, i4, source, Http2Reader.Companion.lengthWithoutPadding(i2, i3, iAnd));
        source.skip(iAnd);
    }

    private void readPriority(final Handler handler, final int i2, final int i3, final int i4) throws IOException {
        if (5 == i2) {
            if (0 == i4) {
                throw new IOException("TYPE_PRIORITY streamId == 0");
            }
            this.readPriority(handler, i4);
        } else {
            throw new IOException("TYPE_PRIORITY length: " + i2 + " != 5");
        }
    }
    private void readPriority(final Handler handler, final int i2) throws IOException {
        final int i3 = source.readInt();
        handler.priority(i2, i3 & Integer.MAX_VALUE, Util.and(source.readByte(), 255) + 1, 0 != (Integer.MIN_VALUE & i3));
    }
    private void readRstStream(final Handler handler, final int i2, final int i3, final int i4) throws IOException {
        if (4 != i2) {
            throw new IOException("TYPE_RST_STREAM length: " + i2 + " != 4");
        }
        if (0 == i4) {
            throw new IOException("TYPE_RST_STREAM streamId == 0");
        }
        final int i5 = source.readInt();
        final ErrorCode errorCodeFromHttp2 = ErrorCode.Companion.fromHttp2(i5);
        if (null == errorCodeFromHttp2) {
            throw new IOException(stringPlus("TYPE_RST_STREAM unexpected error code: ", Integer.valueOf(i5)));
        }
        handler.rstStream(i4, errorCodeFromHttp2);
    }
    private void readSettings(final Handler handler, final int i2, final int i3, final int i4) throws IOException {
        int i5;
        if (0 != i4) {
            throw new IOException("TYPE_SETTINGS streamId != 0");
        }
        if (0 != (i3 & 1)) {
            if (0 != i2) {
                throw new IOException("FRAME_SIZE_ERROR ack frame should be empty!");
            }
            handler.ackSettings();
            return;
        }
        if (0 != i2 % 6) {
            throw new IOException(stringPlus("TYPE_SETTINGS length % 6 != 0: ", Integer.valueOf(i2)));
        }
        final Settings settings = new Settings();
        final kotlin.ranges.IntProgression progressionsStep = RangesKt.step(RangesKt.until(0, i2), 6);
        int first = progressionsStep.getFirst();
        final int last = progressionsStep.getLast();
        final int step = progressionsStep.getStep();
        if ((0 < step && first <= last) || (0 > step && last <= first)) {
            while (true) {
                final int i6 = first + step;
                int iAnd = Util.and(source.readShort(), 65535);
                i5 = source.readInt();
                if (2 != iAnd) {
                    if (3 == iAnd) {
                        iAnd = 4;
                    } else if (4 != iAnd) {
                        if (5 == iAnd && (16384 > i5 || 16777215 < i5)) {
                            break;
                        }
                    } else {
                        if (0 > i5) {
                            throw new IOException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1");
                        }
                        iAnd = 7;
                    }
                } else if (0 != i5 && 1 != i5) {
                    throw new IOException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1");
                }
                settings.set(iAnd, i5);
                if (first == last) {
                    break;
                } else {
                    first = i6;
                }
            }
            throw new IOException(stringPlus("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: ", Integer.valueOf(i5)));
        }
        handler.settings(false, settings);
    }
    private void readPushPromise(final Handler handler, final int i2, final int i3, final int i4) throws IOException {
        if (0 == i4) {
            throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
        }
        final int iAnd = 0 != (i3 & 8) ? Util.and(source.readByte(), 255) : 0;
        handler.pushPromise(i4, source.readInt() & Integer.MAX_VALUE, this.readHeaderBlock(Http2Reader.Companion.lengthWithoutPadding(i2 - 4, i3, iAnd), iAnd, i3, i4));
    }
    private void readPing(final Handler handler, final int i2, final int i3, final int i4) throws IOException {
        if (8 != i2) {
            throw new IOException(stringPlus("TYPE_PING length != 8: ", Integer.valueOf(i2)));
        }
        if (0 != i4) {
            throw new IOException("TYPE_PING streamId != 0");
        }
        handler.ping(0 != (i3 & 1), source.readInt(), source.readInt());
    }
    private void readGoAway(final Handler handler, final int i2, final int i3, final int i4) throws IOException {
        if (8 > i2) {
            throw new IOException(stringPlus("TYPE_GOAWAY length < 8: ", Integer.valueOf(i2)));
        }
        if (0 != i4) {
            throw new IOException("TYPE_GOAWAY streamId != 0");
        }
        final int i5 = source.readInt();
        final int i6 = source.readInt();
        final int i7 = i2 - 8;
        final ErrorCode errorCodeFromHttp2 = ErrorCode.Companion.fromHttp2(i6);
        if (null == errorCodeFromHttp2) {
            throw new IOException(stringPlus("TYPE_GOAWAY unexpected error code: ", Integer.valueOf(i6)));
        }
        ByteString byteString = ByteString.EMPTY;
        if (0 < i7) {
            byteString = source.readByteString(i7);
        }
        handler.goAway(i5, errorCodeFromHttp2, byteString);
    }

    private void readWindowUpdate(final Handler handler, final int i2, final int i3, final int i4) throws IOException {
        if (4 != i2) {
            throw new IOException(stringPlus("TYPE_WINDOW_UPDATE length !=4: ", Integer.valueOf(i2)));
        }
        final long jAnd = Util.and(source.readInt(), 2147483647L);
        if (0 == jAnd) {
            throw new IOException("windowSizeIncrement was 0");
        }
        handler.windowUpdate(i4, jAnd);
    }
    public void close() throws IOException {
        source.close();
    }
    public interface Handler {
        void ackSettings();

        void alternateService(int i2, String str, ByteString byteString, String str2, int i3, long j2);

        void data(boolean z, int i2, BufferedSource bufferedSource, int i3) throws IOException;

        void goAway(int i2, ErrorCode errorCode, ByteString byteString);

        void headers(boolean z, int i2, int i3, List<Header> list);

        void ping(boolean z, int i2, int i3);

        void priority(int i2, int i3, int i4, boolean z);

        void pushPromise(int i2, int i3, List<Header> list) throws IOException;

        void rstStream(int i2, ErrorCode errorCode);

        void settings(boolean z, Settings settings);

        void windowUpdate(int i2, long j2);
    }

    /* compiled from: Http2Reader.kt */
    public static final class ContinuationSource implements Source {
        private final BufferedSource source;
        private int flags;
        private int left;
        private int length;
        private int padding;
        private int streamId;

        public ContinuationSource(final BufferedSource source) {
            Intrinsics.checkNotNullParameter(source, "source");
            this.source = source;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public int getLength() {
            return length;
        }

        public void setLength(final int i2) {
            length = i2;
        }

        public int getFlags() {
            return flags;
        }

        public void setFlags(final int i2) {
            flags = i2;
        }

        public int getStreamId() {
            return streamId;
        }

        public void setStreamId(final int i2) {
            streamId = i2;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(final int i2) {
            left = i2;
        }

        public int getPadding() {
            return padding;
        }

        public void setPadding(final int i2) {
            padding = i2;
        }

        @Override // okio.Source
        public long read(final Buffer sink, final long j2) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            while (true) {
                final int i2 = left;
                if (0 == i2) {
                    source.skip(padding);
                    padding = 0;
                    if (0 != (this.flags & 4)) {
                        return -1L;
                    }
                    this.readContinuationHeader();
                } else {
                    final long j3;
                    try {
                        j3 = source.read(sink, Math.min(j2, i2));
                    } catch (DataFormatException e) {
                        throw new RuntimeException(e);
                    }
                    if (-1 == j3) {
                        return -1L;
                    }
                    left -= (int) j3;
                    return j3;
                }
            }
        }
        public Timeout timeout() {
            return source.timeout();
        }

        private void readContinuationHeader() throws IOException {
            final int i2 = streamId;
            final int medium = Util.readMedium(source);
            left = medium;
            length = medium;
            final int iAnd = Util.and(source.readByte(), 255);
            flags = Util.and(source.readByte(), 255);
            final Companion companion = Companion;
            if (companion.getLogger().isLoggable(Level.FINE)) {
                companion.getLogger().fine(Http2.INSTANCE.frameLog(true, streamId, length, iAnd, flags));
            }
            final int i3 = source.readInt() & Integer.MAX_VALUE;
            streamId = i3;
            if (9 == iAnd) {
                if (i3 != i2) {
                    throw new IOException("TYPE_CONTINUATION streamId changed");
                }
            } else {
                throw new IOException(iAnd + " != TYPE_CONTINUATION");
            }
        }
    }

    /* compiled from: Http2Reader.kt */
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Logger getLogger() {
            return logger;
        }

        public int lengthWithoutPadding(int i2, final int i3, final int i4) throws IOException {
            if (0 != (i3 & 8)) {
                i2--;
            }
            if (i4 <= i2) {
                return i2 - i4;
            }
            throw new IOException("PROTOCOL_ERROR padding " + i4 + " > remaining length " + i2);
        }
    }
}
