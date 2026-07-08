package io.reactivex.internal.operators.flowable;

import com.proje.mobilesales.features.model.Resource;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

final class FlowableCreateLatestAsyncEmitter<T> extends FlowableCreateBaseEmitter<T> {
    private static final long serialVersionUID = 4023437720691792495L;
    volatile boolean done;
    Throwable error;
    final AtomicReference<T> queue;
    final AtomicInteger wip;
    FlowableCreateLatestAsyncEmitter(Subscriber<? super T> subscriber) {
        super(subscriber);
        this.queue = new AtomicReference<>();
        this.wip = new AtomicInteger();
    }
    public void onNext(Resource.Success t) {
        if (this.done || isCancelled()) {
            return;
        }
        if (null == t) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else {
            this.queue.set((T) t);
            drain();
        }
    }
    public boolean tryOnError(Throwable th) {
        if (this.done || isCancelled()) {
            return false;
        }
        if (null == th) {
            onError(new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources."));
        }
        this.error = th;
        this.done = true;
        drain();
        return true;
    }
    public void onComplete() {
        this.done = true;
        drain();
    }
    void onRequested() {
        drain();
    }
    void onUnsubscribed() {
        if (0 == wip.getAndIncrement ()) {
            this.queue.lazySet(null);
        }
    }
    void drain() {
        if (0 != wip.getAndIncrement ()) {
            return;
        }
        Subscriber<? super T> subscriber = this.downstream;
        AtomicReference<T> atomicReference = this.queue;
        int i2 = 1;
        do {
            long j2 = get();
            long j3 = 0;
            while (true) {
                if (j3 == j2) {
                    break;
                }
                if (isCancelled()) {
                    atomicReference.lazySet(null);
                    return;
                }
                boolean z = this.done;
                T andSet = atomicReference.getAndSet(null);
                boolean z2 = null == andSet;
                if (z && z2) {
                    Throwable th = this.error;
                    if (null != th) {
                        error(th);
                        return;
                    } else {
                        complete();
                        return;
                    }
                }
                if (z2) {
                    break;
                }
                subscriber.onNext(andSet);
                j3++;
            }
            if (j3 == j2) {
                if (isCancelled()) {
                    atomicReference.lazySet(null);
                    return;
                }
                boolean z3 = this.done;
                boolean z4 = null == atomicReference.get ();
                if (z3 && z4) {
                    Throwable th2 = this.error;
                    if (null != th2) {
                        error(th2);
                        return;
                    } else {
                        complete();
                        return;
                    }
                }
            }
            if (0 != j3) {
                BackpressureHelper.produced(this, j3);
            }
            i2 = this.wip.addAndGet(-i2);
        } while (0 != i2);
    }
}
