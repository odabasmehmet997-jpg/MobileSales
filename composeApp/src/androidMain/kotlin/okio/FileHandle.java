package okio;

import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

import java.io.Closeable;
import java.io.IOException;

public abstract class FileHandle implements Closeable {
    private boolean closed;
    private int openStreamCount;
    private final boolean readWrite;
    protected abstract void protectedClose() throws IOException;
    protected abstract int protectedRead(long j2, byte[] bArr, int i2, int i3) throws IOException;
    protected abstract long protectedSize() throws IOException;
    public final void close() throws IOException {
        synchronized (this) {
            if (this.closed) {
                return;
            }
            this.closed = true;
            if (0 != openStreamCount) {
                return;
            }
            Unit unit = Unit.INSTANCE;
            protectedClose();
        }
    }
    public final long size() throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            Unit unit = Unit.INSTANCE;
        }
        return protectedSize();
    }
    public final Source source(long j2) throws IOException {
        synchronized (this) {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            this.openStreamCount++;
        }
        return new FileHandleSource(this, j2);
    }
    protected FileHandle(boolean z) {
        this.readWrite = z;
    }
    private long readNoCloseCheck(long j2, Buffer buffer, long j3) throws IOException {
        if (0 > j3) {
            throw new IllegalArgumentException(("byteCount < 0: " + j3));
        }
        long j4 = j3 + j2;
        long j5 = j2;
        while (true) {
            if (j5 >= j4) {
                break;
            }
            Segment segmentWritableSegmentokio = buffer.writableSegmentokio(1);
            final int r7 = 0;
            int iProtectedRead = protectedRead(j5, segmentWritableSegmentokio.data, segmentWritableSegmentokio.limit, (int) Math.min(j4 - j5, 8192 - r7));
            if (-1 == iProtectedRead) {
                if (segmentWritableSegmentokio.pos == segmentWritableSegmentokio.limit) {
                    buffer.head = segmentWritableSegmentokio.pop();
                    SegmentPool.recycle(segmentWritableSegmentokio);
                }
                if (j2 == j5) {
                    return -1L;
                }
            } else {
                segmentWritableSegmentokio.limit += iProtectedRead;
                long j6 = iProtectedRead;
                j5 += j6;
                buffer.setSizeokio(buffer.size() + j6);
            }
        }
        return j5 - j2;
    }
    private static final class FileHandleSource implements Source {
        private boolean closed;
        private final FileHandle fileHandle;
        private long position;

        public FileHandleSource(FileHandle fileHandle, long j2) {
            Intrinsics.checkNotNullParameter(fileHandle, "fileHandle");
            this.fileHandle = fileHandle;
            this.position = j2;
        }
        public long read(Buffer sink, long j2) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (!this.closed) {
                long noCloseCheck = this.fileHandle.readNoCloseCheck(this.position, sink, j2);
                if (-1 != noCloseCheck) {
                    this.position += noCloseCheck;
                }
                return noCloseCheck;
            }
            throw new IllegalStateException("closed");
        }
        public Timeout timeout() {
            return Timeout.NONE;
        }
        public void close() throws IOException {
            if (this.closed) {
                return;
            }
            this.closed = true;
            synchronized (this.fileHandle) {
                FileHandle fileHandle = this.fileHandle;
                fileHandle.openStreamCount--;
                if (0 == this.fileHandle.openStreamCount && this.fileHandle.closed) {
                    Unit unit = Unit.INSTANCE;
                    this.fileHandle.protectedClose();
                }
            }
        }
    }
}
