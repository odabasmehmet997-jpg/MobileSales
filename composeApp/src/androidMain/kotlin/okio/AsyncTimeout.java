package okio;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import java.util.zip.DataFormatException;

import static kotlin.jvm.internal.Intrinsics.checkNotNull;

public class AsyncTimeout extends Timeout {
    public static final Companion Companion = new Companion(null);
    private static final long IDLE_TIMEOUT_MILLIS;
    private static final long IDLE_TIMEOUT_NANOS;
    private static final int TIMEOUT_WRITE_SIZE = 65536;
    private static AsyncTimeout head;
    private boolean inQueue;
    private AsyncTimeout next;
    private long timeoutAt;
    protected void timedOut() {
    }
    public static final class Companion {
        public Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private boolean cancelScheduledTimeout(AsyncTimeout asyncTimeout) {
            synchronized (AsyncTimeout.class) {
                if (!asyncTimeout.inQueue) {
                    return false;
                }
                asyncTimeout.inQueue = false;
                for (AsyncTimeout asyncTimeout2 = AsyncTimeout.head; null != asyncTimeout2; asyncTimeout2 = asyncTimeout2.next) {
                    if (asyncTimeout2.next == asyncTimeout) {
                        asyncTimeout2.next = asyncTimeout.next;
                        asyncTimeout.next = null;
                        return false;
                    }
                }
                return true;
            }
        }
        private void scheduleTimeout(AsyncTimeout asyncTimeout, long j2, boolean z) {
            synchronized (AsyncTimeout.class) {
                try {
                    if (!asyncTimeout.inQueue) {
                        asyncTimeout.inQueue = true;
                        if (null == head) {
                            AsyncTimeout.head = new AsyncTimeout();
                            new Watchdog().start();
                        }
                        long jNanoTime = System.nanoTime();
                        if (0 != j2 && z) {
                            asyncTimeout.timeoutAt = Math.min(j2, asyncTimeout.deadlineNanoTime() - jNanoTime) + jNanoTime;
                        } else if (0 != j2) {
                            asyncTimeout.timeoutAt = j2 + jNanoTime;
                        } else if (z) {
                            asyncTimeout.timeoutAt = asyncTimeout.deadlineNanoTime();
                        } else {
                            throw new AssertionError();
                        }
                        long jRemainingNanos = asyncTimeout.remainingNanos(jNanoTime);
                        AsyncTimeout asyncTimeout2 = AsyncTimeout.head;
                        checkNotNull(asyncTimeout2);
                        while (null != asyncTimeout2.next) {
                            AsyncTimeout asyncTimeout3 = asyncTimeout2.next;
                            checkNotNull(asyncTimeout3);
                            if (jRemainingNanos < asyncTimeout3.remainingNanos(jNanoTime)) {
                                break;
                            }
                            asyncTimeout2 = asyncTimeout2.next;
                            checkNotNull(asyncTimeout2);
                        }
                        asyncTimeout.next = asyncTimeout2.next;
                        asyncTimeout2.next = asyncTimeout;
                        if (asyncTimeout2 == AsyncTimeout.head) {
                            AsyncTimeout.class.notify();
                        }
                        Unit unit = Unit.INSTANCE;
                    } else {
                        throw new IllegalStateException("Unbalanced enter/exit");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        private Companion() {
        }
        public AsyncTimeout awaitTimeoutokio() throws InterruptedException {
            AsyncTimeout asyncTimeout = AsyncTimeout.head;
            checkNotNull(asyncTimeout);
            AsyncTimeout asyncTimeout2 = asyncTimeout.next;
            if (null != asyncTimeout2) {
                long jRemainingNanos = asyncTimeout2.remainingNanos(System.nanoTime());
                if (0 >= jRemainingNanos) {
                    AsyncTimeout asyncTimeout3 = AsyncTimeout.head;
                    checkNotNull(asyncTimeout3);
                    asyncTimeout3.next = asyncTimeout2.next;
                    asyncTimeout2.next = null;
                    return asyncTimeout2;
                }
                long j2 = jRemainingNanos / 1000000;
                AsyncTimeout.class.wait(j2, (int) (jRemainingNanos - (1000000 * j2)));
                return null;
            }
            long jNanoTime = System.nanoTime();
            AsyncTimeout.class.wait(AsyncTimeout.IDLE_TIMEOUT_MILLIS);
            AsyncTimeout asyncTimeout4 = AsyncTimeout.head;
            checkNotNull(asyncTimeout4);
            if (null != asyncTimeout4.next || System.nanoTime() - jNanoTime < AsyncTimeout.IDLE_TIMEOUT_NANOS) {
                return null;
            }
            return AsyncTimeout.head;
        }
    }
    public final void enter() {
        long jTimeoutNanos = timeoutNanos();
        boolean zHasDeadline = hasDeadline();
        if (0 != jTimeoutNanos || zHasDeadline) {
            Companion.scheduleTimeout(this, jTimeoutNanos, zHasDeadline);
        }
    }
    public final boolean exit() {
        return Companion.cancelScheduledTimeout(this);
    }
    private long remainingNanos(long j2) {
        return this.timeoutAt - j2;
    }
    public final Sink sink(final Sink sink) {
        Intrinsics.checkNotNullParameter(sink, "sink");
        return new Sink() {
            public void write(Buffer source, long j2) throws IOException {
                Intrinsics.checkNotNullParameter(source, "source");
                _UtilKt.checkOffsetAndCount(source.size(), 0L, j2);
                while (true) {
                    long j3 = 0;
                    if (0 >= j2) {
                        return;
                    }
                    Segment segment = source.head;
                    checkNotNull(segment);
                    while (true) {
                        if (65536 <= j3) {
                            break;
                        }
                        j3 += segment.limit - segment.pos;
                        if (j3 >= j2) {
                            j3 = j2;
                            break;
                        } else {
                            segment = segment.next;
                            checkNotNull(segment);
                        }
                    }
                    AsyncTimeout asyncTimeout = AsyncTimeout.this;
                    Sink sink2 = sink;
                    asyncTimeout.enter();
                    try {
                        sink2.write(source, j3);
                        Unit unit = Unit.INSTANCE;
                        if (asyncTimeout.exit()) {
                            throw asyncTimeout.accessnewTimeoutException(null);
                        }
                        j2 -= j3;
                    } catch (IOException e2) {
                        if (!asyncTimeout.exit()) {
                            throw e2;
                        }
                        throw asyncTimeout.accessnewTimeoutException(e2);
                    } finally {
                        asyncTimeout.exit();
                    }
                }
            }
            public void flush() throws IOException {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Sink sink2 = sink;
                asyncTimeout.enter();
                try {
                    sink2.flush();
                    Unit unit = Unit.INSTANCE;
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.accessnewTimeoutException(null);
                    }
                } catch (IOException e2) {
                    if (!asyncTimeout.exit()) {
                        throw e2;
                    }
                    throw asyncTimeout.accessnewTimeoutException(e2);
                } finally {
                    asyncTimeout.exit();
                }
            }
            public void close() throws IOException {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Sink sink2 = sink;
                asyncTimeout.enter();
                try {
                    sink2.close();
                    Unit unit = Unit.INSTANCE;
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.accessnewTimeoutException(null);
                    }
                } catch (IOException e2) {
                    if (!asyncTimeout.exit()) {
                        throw e2;
                    }
                    throw asyncTimeout.accessnewTimeoutException(e2);
                } finally {
                    asyncTimeout.exit();
                }
            }
            public AsyncTimeout timeout() {
                return AsyncTimeout.this;
            }
            public String toString() {
                return "AsyncTimeout.sink(" + sink + ')';
            }
        };
    }
    public final Source source(final Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        return new Source() {
            public long read(Buffer sink, long j2) throws IOException {
                Intrinsics.checkNotNullParameter(sink, "sink");
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Source source2 = source;
                asyncTimeout.enter();
                try {
                    long j3 = 0;
                    try {
                        j3 = source2.read(sink, j2);
                    } catch (DataFormatException e) {
                        throw new RuntimeException(e);
                    }
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.accessnewTimeoutException(null);
                    }
                    return j3;
                } catch (IOException e2) {
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.accessnewTimeoutException(e2);
                    }
                    throw e2;
                } finally {
                    asyncTimeout.exit();
                }
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                AsyncTimeout asyncTimeout = AsyncTimeout.this;
                Source source2 = source;
                asyncTimeout.enter();
                try {
                    source2.close();
                    Unit unit = Unit.INSTANCE;
                    if (asyncTimeout.exit()) {
                        throw asyncTimeout.accessnewTimeoutException(null);
                    }
                } catch (IOException e2) {
                    if (!asyncTimeout.exit()) {
                        throw e2;
                    }
                    throw asyncTimeout.accessnewTimeoutException(e2);
                } finally {
                    asyncTimeout.exit();
                }
            }
            public AsyncTimeout timeout() {
                return AsyncTimeout.this;
            }

            public String toString() {
                return "AsyncTimeout.source(" + source + ')';
            }
        };
    }
    public final <T> T withTimeout(Function0<? extends T> block) throws IOException {
        Intrinsics.checkNotNullParameter(block, "block");
        enter();
        try {
            try {
                T tInvoke = block.invoke();
                InlineMarker.finallyStart(1);
                if (exit()) {
                    throw accessnewTimeoutException(null);
                }
                InlineMarker.finallyEnd(1);
                return tInvoke;
            } catch (IOException e2) {
                if (exit()) {
                    throw accessnewTimeoutException(e2);
                }
                throw e2;
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            exit();
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }
    public final IOException accessnewTimeoutException(IOException iOException) {
        return newTimeoutException(iOException);
    }
    protected IOException newTimeoutException(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (null != iOException) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }
    private static final class Watchdog extends Thread {
        public Watchdog() {
            super("Okio Watchdog");
            setDaemon(true);
        }
        public void run() {
            AsyncTimeout asyncTimeoutAwaitTimeoutokio;
            while (true) {
                try {
                    synchronized (AsyncTimeout.class) {
                        asyncTimeoutAwaitTimeoutokio = AsyncTimeout.Companion.awaitTimeoutokio();
                        if (asyncTimeoutAwaitTimeoutokio == AsyncTimeout.head) {
                            AsyncTimeout.head = null;
                            return;
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                    if (null != asyncTimeoutAwaitTimeoutokio) {
                        asyncTimeoutAwaitTimeoutokio.timedOut();
                    }
                } catch (InterruptedException unused) {
                    continue;
                }
            }
        }
    }
    static {
        long millis = TimeUnit.SECONDS.toMillis(60L);
        IDLE_TIMEOUT_MILLIS = millis;
        IDLE_TIMEOUT_NANOS = TimeUnit.MILLISECONDS.toNanos(millis);
    }
}
