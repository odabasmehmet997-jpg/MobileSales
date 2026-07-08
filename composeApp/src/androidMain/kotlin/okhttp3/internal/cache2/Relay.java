package okhttp3.internal.cache2;

import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MultipartBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

import static kotlin.jvm.internal.Intrinsics.*;

public final class Relay {
    public static final Companion Companion = new Companion(null);
    public static final ByteString PREFIX_CLEAN;
    public static final ByteString PREFIX_DIRTY;
    private static final long FILE_HEADER_SIZE = 32;
    private static final int SOURCE_FILE = 2;
    private static final int SOURCE_UPSTREAM = 1;
    static {
        final ByteString.Companion companion = ByteString.Companion;
        PREFIX_CLEAN = companion.encodeUtf8("OkHttp cache v1\n");
        PREFIX_DIRTY = companion.encodeUtf8("OkHttp DIRTY :(\n");
    }
    private final Buffer buffer;
    private final long bufferMaxSize;
    private final ByteString metadata;
    private final Buffer upstreamBuffer;
    private boolean complete;
    private RandomAccessFile file;
    private int sourceCount;
    private Source upstream;
    private long upstreamPos;
    private Thread upstreamReader;
    public Relay(final RandomAccessFile randomAccessFile, final Source source, final long j2, final ByteString byteString, final long j3, final DefaultConstructorMarker defaultConstructorMarker) {
        this(randomAccessFile, source, j2, byteString, j3);
    }
    private Relay(final RandomAccessFile randomAccessFile, final Source source, final long j2, final ByteString byteString, final long j3) {
        file = randomAccessFile;
        upstream = source;
        upstreamPos = j2;
        metadata = byteString;
        bufferMaxSize = j3;
        upstreamBuffer = new Buffer();
        complete = null == this.upstream;
        buffer = new Buffer();
    }
    public RandomAccessFile getFile() {
        return file;
    }
    public void setFile(final RandomAccessFile randomAccessFile) {
        file = randomAccessFile;
    }
    public Source getUpstream() {
        return upstream;
    }
    public void setUpstream(final Source source) {
        upstream = source;
    }
    public long getUpstreamPos() {
        return upstreamPos;
    }
    public void setUpstreamPos(final long j2) {
        upstreamPos = j2;
    }
    public long getBufferMaxSize() {
        return bufferMaxSize;
    }
    public Thread getUpstreamReader() {
        return upstreamReader;
    }
    public void setUpstreamReader(final Thread thread) {
        upstreamReader = thread;
    }
    public Buffer getUpstreamBuffer() {
        return upstreamBuffer;
    }
    public boolean getComplete() {
        return complete;
    }
    public void setComplete(final boolean z) {
        complete = z;
    }
    public Buffer getBuffer() {
        return buffer;
    }
    public int getSourceCount() {
        return sourceCount;
    }
    public void setSourceCount(final int i2) {
        sourceCount = i2;
    }
    public boolean isClosed() {
        return null == this.file;
    }
    private void writeHeader(final ByteString byteString, final long j2, final long j3) throws IOException {
        final Buffer buffer = new Buffer();
        buffer.write(byteString);
        buffer.writeLong(j2);
        buffer.writeLong(j3);
        if (Relay.FILE_HEADER_SIZE != buffer.size()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        final RandomAccessFile randomAccessFile = file;
        checkNotNull(randomAccessFile);
        final FileChannel channel = randomAccessFile.getChannel();
        checkNotNullExpressionValue(channel, "file!!.channel");
        new FileOperator(channel).write(0L, buffer, Relay.FILE_HEADER_SIZE);
    }
    private void writeMetadata(final long j2) throws IOException {
        final Buffer buffer = new Buffer();
        buffer.write(metadata);
        final RandomAccessFile randomAccessFile = file;
        checkNotNull(randomAccessFile);
        final FileChannel channel = randomAccessFile.getChannel();
        checkNotNullExpressionValue(channel, "file!!.channel");
        new FileOperator(channel).write(Relay.FILE_HEADER_SIZE + j2, buffer, metadata.size());
    }
    public void commit(final long j2) throws IOException {
        this.writeMetadata(j2);
        final RandomAccessFile randomAccessFile = file;
        checkNotNull(randomAccessFile);
        randomAccessFile.getChannel().force(false);
        this.writeHeader(Relay.PREFIX_CLEAN, j2, metadata.size());
        final RandomAccessFile randomAccessFile2 = file;
        checkNotNull(randomAccessFile2);
        randomAccessFile2.getChannel().force(false);
        synchronized (this) {
            this.complete = true;
            final Unit unit = Unit.INSTANCE;
        }
        final Source source = upstream;
        if (null != source) {
            Util.closeQuietly(source);
        }
        upstream = null;
    }
    public ByteString metadata() {
        return metadata;
    }
    public Source newSource() {
        synchronized (this) {
            if (null == file) {
                return null;
            }
            this.sourceCount = this.sourceCount + 1;
            return new RelaySource(this);
        }
    }
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public Relay edit(final File file, final Source upstream, final ByteString metadata, final long j2) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(upstream, "upstream");
            Intrinsics.checkNotNullParameter(metadata, "metadata");
            final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            final Relay relay = new Relay(randomAccessFile, upstream, 0L, metadata, j2, null);
            randomAccessFile.setLength(0L);
            relay.writeHeader(PREFIX_DIRTY, -1L, -1L);
            return relay;
        }

        public Relay read(final File file, MultipartBody r1) throws IOException {
            Intrinsics.checkNotNullParameter(file, "file");
            final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            final FileChannel channel = randomAccessFile.getChannel();
            checkNotNullExpressionValue(channel, "randomAccessFile.channel");
            final FileOperator fileOperator = new FileOperator(channel);
            final Buffer buffer = new Buffer();
            fileOperator.read(0L, buffer, FILE_HEADER_SIZE);

            if (!areEqual(buffer.readByteString(r1.size()), PREFIX_CLEAN)) {
                throw new IOException("unreadable cache file");
            }
            final long j2 = buffer.readLong();
            final long j3 = buffer.readLong();
            final Buffer buffer2 = new Buffer();
            fileOperator.read(j2 + FILE_HEADER_SIZE, buffer2, j3);
            return new Relay(randomAccessFile, null, j2, buffer2.readByteString(), 0L, null);
        }
    }
    public final class RelaySource implements Source {
        final Relay this0;
        private final Timeout timeout;
        private FileOperator fileOperator;
        private long sourcePos;

        public RelaySource(final Relay this0) {
            Intrinsics.checkNotNullParameter(this0, "this0");
            this.this0 = this0;
            timeout = new Timeout();
            final RandomAccessFile file = this0.getFile();
            checkNotNull(file);
            final FileChannel channel = file.getChannel();
            checkNotNullExpressionValue(channel, "file!!.channel");
            fileOperator = new FileOperator(channel);
        }
        public long read(final Buffer sink, final long j2) throws IOException {
            char c2;
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (null == this.fileOperator) {
                throw new IllegalStateException("Check failed.");
            }
            final Relay relay = this0;
            synchronized (relay) {
                while (true) {
                    if (sourcePos == relay.getUpstreamPos()) {
                        if (!relay.getComplete()) {
                            if (null == relay.getUpstreamReader()) {
                                relay.setUpstreamReader(Thread.currentThread());
                                c2 = 1;
                                break;
                            }
                            try {
                                timeout.waitUntilNotified(relay);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            return -1L;
                        }
                    } else {
                        final long upstreamPos = relay.getUpstreamPos() - relay.getBuffer().size();
                        if (sourcePos >= upstreamPos) {
                            final long jMin = Math.min(j2, relay.getUpstreamPos() - sourcePos);
                            relay.getBuffer().copyTo(sink, sourcePos - upstreamPos, jMin);
                            sourcePos += jMin;
                            return jMin;
                        }
                        c2 = 2;
                    }
                }
            }
            return j2;
        }

        public Timeout timeout() {
            return timeout;
        }
        public void close() throws IOException {
            if (null == this.fileOperator) {
                return;
            }
            RandomAccessFile randomAccessFile = null;
            fileOperator = null;
            final Relay relay = this0;
            synchronized (relay) {
                try {
                    relay.setSourceCount(relay.getSourceCount() - 1);
                    if (0 == relay.getSourceCount()) {
                        final RandomAccessFile file = relay.getFile();
                        relay.setFile(null);
                        randomAccessFile = file;
                    }
                    final Unit unit = Unit.INSTANCE;
                } catch (final Throwable th) {
                    throw th;
                }
            }
            if (null == randomAccessFile) {
                return;
            }
            Util.closeQuietly(randomAccessFile);
        }
    }
}
