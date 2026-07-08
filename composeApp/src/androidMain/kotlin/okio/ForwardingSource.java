package okio;

import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.cache.DiskLruCache;

import java.io.IOException;
import java.util.zip.DataFormatException;

public abstract class ForwardingSource implements Source {
    private Source delegate = null;
    protected Source source;
    protected Object fileSource;
    protected ForwardingSource(DiskLruCache diskLruCache, DiskLruCache.Entry entry) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }

    public ForwardingSource(Source delegate) {
    }

    public final Source delegate() {
        return this.delegate;
    }
    public long read(Buffer sink, long j2) throws IOException {
        Intrinsics.checkNotNullParameter(sink, "sink");
        try {
            return this.delegate.read(sink, j2);
        } catch (DataFormatException e) {
            throw new RuntimeException(e);
        }
    }
    public Timeout timeout() {
        return this.delegate.timeout();
    }
    public void close() throws IOException {
        this.delegate.close();
    }
    public String toString() {
        return getClass().getSimpleName() + '(' + this.delegate + ')';
    }
    public final Source m1856deprecated_delegate() {
        return this.delegate;
    }
    public AutoCloseable getSnapshot() {
        return null;
    }
    public int getLockingSourceCountokhttp() {
        return 0;
    }
    public void timeout(int i) {
    }
    public boolean getZombieokhttp() {
        return false;
    }
}
