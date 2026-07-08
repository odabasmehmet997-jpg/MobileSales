package io.reactivex.internal.observers;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.plugins.RxJavaPlugins;


public class DeferredScalarDisposable<T> extends BasicIntQueueDisposable<T> {
    static final int DISPOSED = 4;
    static final int FUSED_CONSUMED = 32;
    static final int FUSED_EMPTY = 8;
    static final int FUSED_READY = 16;
    static final int TERMINATED = 2;
    private static final long serialVersionUID = -5502432239815349361L;
    protected final Observer<? super T> downstream;
    protected T value;
    public DeferredScalarDisposable(final Observer<? super T> observer) {
        downstream = observer;
    }
    public final int requestFusion(final int i2) {
        if (0 == (i2 & 2)) {
            return 0;
        }
        this.lazySet(8);
        return 2;
    }
    public final void complete(final T t) {
        final int i2 = this.get();
        if (0 != (i2 & 54)) {
            return;
        }
        final Observer<? super T> observer = downstream;
        if (8 == i2) {
            value = t;
            this.lazySet(16);
            observer.onNext(null);
        } else {
            this.lazySet(2);
            observer.onNext(t);
        }
        if (4 != get()) {
            observer.onComplete();
        }
    }
    public final void error(final Throwable th) {
        if (0 != (get() & 54)) {
            RxJavaPlugins.onError(th);
        } else {
            this.lazySet(2);
            downstream.onError(th);
        }
    }
    public final void complete() {
        if (0 != (get() & 54)) {
            return;
        }
        this.lazySet(2);
        downstream.onComplete();
    }
    public final T poll() throws Exception {
        if (16 != get()) {
            return null;
        }
        final T t = value;
        value = null;
        this.lazySet(32);
        return t;
    }
    public final boolean isEmpty() {
        return 16 != get();
    }
    public final void clear() {
        this.lazySet(32);
        value = null;
    }
    public void dispose() {
        this.set(4);
        value = null;
    }
    public final boolean tryDispose() {
        return 4 != getAndSet(4);
    }
    public final boolean isDisposed() {
        return 4 == get();
    }

    @Override
    public void onError(Throwable th) {

    }

    @Override
    public <T> Observable<T> observeOn(Scheduler scheduler) {
        return null;
    }
}
