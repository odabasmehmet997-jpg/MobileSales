package okio;

import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ForwardingTimeout extends Timeout {
    private Timeout delegate;
    public final Timeout delegate() {
        return this.delegate;
    }
    public ForwardingTimeout(Timeout delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
    }
    public final ForwardingTimeout setDelegate(Timeout delegate) {
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        this.delegate = delegate;
        return this;
    }
    public Timeout timeout(long j2, TimeUnit unit) {
        Intrinsics.checkNotNullParameter(unit, "unit");
        return this.delegate.timeout(j2, unit);
    }
    public long timeoutNanos() {
        return this.delegate.timeoutNanos();
    }
    public boolean hasDeadline() {
        return this.delegate.hasDeadline();
    }
    public long deadlineNanoTime() {
        return this.delegate.deadlineNanoTime();
    }
    public Timeout deadlineNanoTime(long j2) {
        return this.delegate.deadlineNanoTime(j2);
    }
    public Timeout clearTimeout() {
        return this.delegate.clearTimeout();
    }
    public Timeout clearDeadline() {
        return this.delegate.clearDeadline();
    }
    public void throwIfReached() throws IOException {
        this.delegate.throwIfReached();
    }
}
