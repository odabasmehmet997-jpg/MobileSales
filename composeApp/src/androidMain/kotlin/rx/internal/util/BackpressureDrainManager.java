package rx.internal.util;

import androidx.core.location.LocationRequestCompat;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;

public final class BackpressureDrainManager extends AtomicLong implements Producer {
    private static final long serialVersionUID = 2826241102729529449L;
    final BackpressureQueueCallback actual = null;
    boolean emitting;
    Throwable exception;
    volatile boolean terminated;

    public interface BackpressureQueueCallback {
    }

    public BackpressureDrainManager(BackpressureQueueCallback backpressureQueueCallback) {
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    public void terminate() {
        this.terminated = true;
    }

    public void terminate(Throwable th) {
        if (this.terminated) {
            return;
        }
        this.exception = th;
        this.terminated = true;
    }

    public void terminateAndDrain() {
        this.terminated = true;
        drain();
    }

    public void terminateAndDrain(Throwable th) {
        if (this.terminated) {
            return;
        }
        this.exception = th;
        this.terminated = true;
        drain();
    }
    public void request(long j2) {
        boolean z;
        if (j2 == 0) {
            return;
        }
        while (true) {
            long j3 = get();
            boolean z2 = true;
            z = j3 == 0;
            long j4 = LocationRequestCompat.PASSIVE_INTERVAL;
            if (j3 == LocationRequestCompat.PASSIVE_INTERVAL) {
                break;
            }
            if (j2 == LocationRequestCompat.PASSIVE_INTERVAL) {
                j4 = j2;
            } else {
                if (j3 <= LocationRequestCompat.PASSIVE_INTERVAL - j2) {
                    j4 = j3 + j2;
                }
                z2 = z;
            }
            if (compareAndSet(j3, j4)) {
                z = z2;
                break;
            }
        }
        if (z) {
            drain();
        }
    }

    public void drain() {
        synchronized (this) {
            if (this.emitting) {
                return;
            }
            this.emitting = true;
            boolean z = this.terminated;
            try {
                if (get() <= 0 && !z) {
                    synchronized (this) {
                        throw null;
                    }
                }
                if (z) {
                    throw null;
                }
                throw null;
            } catch (Throwable th) {
                synchronized (this) {
                    this.emitting = false;
                    throw th;
                }
            }
        }
    }
}
