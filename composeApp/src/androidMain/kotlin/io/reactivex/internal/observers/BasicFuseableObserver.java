package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.plugins.RxJavaPlugins;

public abstract class BasicFuseableObserver<T, R> implements Observer<T>, QueueDisposable<R> {
    protected boolean done;
    protected final Observer<? super R> downstream;
    protected QueueDisposable<T> qd;
    protected int sourceMode;
    protected Disposable upstream;
    protected void afterDownstream() {
    }
    protected boolean beforeDownstream() {
        return true;
    }
    protected BasicFuseableObserver(Observer<? super R> observer) {
        this.downstream = observer;
    }
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.upstream, disposable)) {
            this.upstream = disposable;
            if (disposable instanceof QueueDisposable) {
                this.qd = (QueueDisposable) disposable;
            }
            if (beforeDownstream()) {
                this.downstream.onSubscribe(this);
                afterDownstream();
            }
        }
    }
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
        } else {
            this.done = true;
            this.downstream.onError(th);
        }
    }
    protected final void fail(Throwable th) {
        Exceptions.throwIfFatal(th);
        this.upstream.dispose();
        onError(th);
    }
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.downstream.onComplete();
    }
    protected final int transitiveBoundaryFusion(int i2) {
        QueueDisposable<T> queueDisposable = this.qd;
        if (null == queueDisposable || 0 != (i2 & 4)) {
            return 0;
        }
        int requestFusion = queueDisposable.requestFusion(i2);
        if (0 != requestFusion) {
            this.sourceMode = requestFusion;
        }
        return requestFusion;
    }
    public void dispose() {
        this.upstream.dispose();
    }
    public boolean isDisposed() {
        return this.upstream.isDisposed();
    }
    public boolean isEmpty() {
        return this.qd.isEmpty();
    }
    public void clear() {
        this.qd.clear();
    }
    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
