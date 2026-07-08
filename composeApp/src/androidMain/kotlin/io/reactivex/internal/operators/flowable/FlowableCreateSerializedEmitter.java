package io.reactivex.internal.operators.flowable;

import com.proje.mobilesales.features.model.Resource;
import io.reactivex.FlowableEmitter;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;

final class FlowableCreateSerializedEmitter<T> extends AtomicInteger implements FlowableEmitter<T> {
    private static final long serialVersionUID = 4883307006032401862L;
    volatile boolean done;
    final FlowableCreateBaseEmitter<T> emitter;
    final AtomicThrowable error = new AtomicThrowable();
    final SimplePlainQueue<T> queue = new SpscLinkedArrayQueue(16);
    public FlowableEmitter<T> serialize() {
        return this;
    }
    FlowableCreateSerializedEmitter(FlowableCreateBaseEmitter<T> flowableCreateBaseEmitter) {
        this.emitter = flowableCreateBaseEmitter;
    }
    public void onNext(Resource.Success t) {
        if (this.emitter.isCancelled() || this.done) {
            return;
        }
        if (null == t) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return;
        }
        if (0 == this.get() && compareAndSet(0, 1)) {
            this.emitter.onNext(t);
            if (0 == this.decrementAndGet()) {
                return;
            }
        } else {
            SimplePlainQueue<T> simplePlainQueue = this.queue;
            synchronized (simplePlainQueue) {
                simplePlainQueue.offer((T) t);
            }
            if (0 != this.getAndIncrement()) {
                return;
            }
        }
        drainLoop();
    }
    public void onError(Throwable th) {
        if (tryOnError(th)) {
            return;
        }
        RxJavaPlugins.onError(th);
    }
    public boolean tryOnError(Throwable th) {
        if (!this.emitter.isCancelled() && !this.done) {
            if (null == th) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (this.error.addThrowable(th)) {
                this.done = true;
                drain();
                return true;
            }
        }
        return false;
    }
    public void onComplete() {
        if (this.emitter.isCancelled() || this.done) {
            return;
        }
        this.done = true;
        drain();
    }
    void drain() {
        if (0 == this.getAndIncrement()) {
            drainLoop();
        }
    }
    void drainLoop() {
        FlowableCreateBaseEmitter<T> flowableCreateBaseEmitter = this.emitter;
        SimplePlainQueue<T> simplePlainQueue = this.queue;
        AtomicThrowable atomicThrowable = this.error;
        int i2 = 1;
        while (!flowableCreateBaseEmitter.isCancelled()) {
            if (null != atomicThrowable.get ()) {
                simplePlainQueue.clear();
                flowableCreateBaseEmitter.onError(atomicThrowable.terminate());
                return;
            }
            boolean z = this.done;
            T poll = simplePlainQueue.poll();
            boolean z2 = null == poll;
            if (z && z2) {
                flowableCreateBaseEmitter.onComplete();
                return;
            } else if (!z2) {
                flowableCreateBaseEmitter.onNext((Resource.Success) poll);
            } else {
                i2 = addAndGet(-i2);
                if (0 == i2) {
                    return;
                }
            }
        }
        simplePlainQueue.clear();
    }
    public void setDisposable(Disposable disposable) {
        this.emitter.setDisposable(disposable);
    }
    public void setCancellable(Cancellable cancellable) {
        this.emitter.setCancellable(cancellable);
    }
    public long requested() {
        return this.emitter.requested();
    }
    public boolean isCancelled() {
        return this.emitter.isCancelled();
    }
    public String toString() {
        return this.emitter.toString();
    }
}
