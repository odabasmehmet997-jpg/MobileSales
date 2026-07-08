package okhttp3.internal.ws;

import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.ByteString;
import okio.DeflaterSink;
import okio.Sink;

import java.io.Closeable;
import java.io.IOException;
import java.util.zip.Deflater;

import static okio.Buffer.readAndWriteUnsafedefault;

public final class MessageDeflater implements Closeable {
    private final Buffer deflatedBytes;
    private final Deflater deflater;
    private final DeflaterSink deflaterSink;
    private final boolean noContextTakeover;
    public MessageDeflater(boolean z) {
        this.noContextTakeover = z;
        Buffer buffer = new Buffer();
        this.deflatedBytes = buffer;
        Deflater deflater = new Deflater(-1, true);
        this.deflater = deflater;
        this.deflaterSink = new DeflaterSink((Sink) buffer, deflater);
    }
    public void deflate(Buffer buffer) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (0 != deflatedBytes.size()) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (this.noContextTakeover) {
            this.deflater.reset();
        }
        this.deflaterSink.write(buffer, buffer.size());
        this.deflaterSink.flush();
        if (endsWith(this.deflatedBytes, MessageDeflater2.EMPTY_DEFLATE_BLOCK)) {
            long size = this.deflatedBytes.size() - 4;
            Buffer.UnsafeCursor andWriteUnsafedefault = readAndWriteUnsafedefault(this.deflatedBytes, null, 1, null);
            andWriteUnsafedefault.resizeBuffer(size);
            CloseableKt.closeFinally(andWriteUnsafedefault, null);
        } else {
            this.deflatedBytes.writeByte(0);
        }
        Buffer buffer2 = this.deflatedBytes;
        buffer.write(buffer2, buffer2.size());
    }
    public void close() {
        this.deflaterSink.close();
    }
    private boolean endsWith(Buffer buffer, ByteString byteString) {
        return buffer.rangeEquals(buffer.size() - byteString.size(), byteString);
    }
}
