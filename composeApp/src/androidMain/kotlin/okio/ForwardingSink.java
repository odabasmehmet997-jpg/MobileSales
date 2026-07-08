package okio;

import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;

public abstract class ForwardingSink implements Sink {
    private final Sink delegate;
    protected ForwardingSink(final Sink delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }
    public final Sink delegate() {
        return delegate;
    }
    public void write(final Buffer source, final long j2) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        delegate.write(source, j2);
    }
    public void flush() throws IOException {
        delegate.flush();
    }
    public Timeout timeout() {
        return delegate.timeout();
    }
    public void close() throws IOException {
        delegate.close();
    }
    public String toString() {
        return this.getClass().getSimpleName() + '(' + delegate + ')';
    }
    public final Sink m1855deprecated_delegate() {
        return delegate;
    }
    public boolean getDone() {
        return false;
    }
    public void setDone(boolean b) {
    }
}
