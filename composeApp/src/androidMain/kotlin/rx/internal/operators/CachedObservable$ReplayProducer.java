package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;

final class CachedObservable$ReplayProducer<T> extends AtomicLong implements Producer, Subscription {
    private static final long serialVersionUID = -2557562030197141021L;
    final Subscriber<? super T> child;
    Object[] currentBuffer;
    int currentIndexInBuffer;
    boolean emitting;
    int index;
    boolean missed;
    final CachedObservableCacheState<T> state = null;
    public CachedObservable$ReplayProducer(Subscriber<? super T> subscriber, CachedObservableCacheState<T> cachedObservable$CacheState) {
        this.child = subscriber;
    }
    public void request(long j2) {
        long j3;
        long j4;
        do {
            j3 = get();
            if (j3 < 0) {
                return;
            }
            j4 = j3 + j2;
            if (j4 < 0) {
                j4 = LocationRequestCompat.PASSIVE_INTERVAL;
            }
        } while (!compareAndSet(j3, j4));
        replay();
    }
    public long produced(long j2) {
        return addAndGet(-j2);
    }
    public boolean isUnsubscribed() {
        return get() < 0;
    }
    public void unsubscribe() {
        if (get() >= 0 && getAndSet(-1L) >= 0) {
            throw null;
        }
    }
    public void replay() {
        synchronized (this) {
            try {
                if (this.emitting) {
                    this.missed = true;
                    return;
                }
                this.emitting = true;
                try {
                    if (get() < 0) {
                    } else {
                        throw null;
                    }
                } catch (Throwable th) {
                    synchronized (this) {
                        this.emitting = false;
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }
}
