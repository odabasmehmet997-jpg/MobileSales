package io.reactivex.internal.operators.flowable;

import com.proje.mobilesales.features.model.Resource;
import io.reactivex.FlowableEmitter;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import io.reactivex.internal.disposables.CancellableDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import java.util.concurrent.atomic.AtomicLong;

abstract class FlowableCreateBaseEmitter<T> extends AtomicLong implements FlowableEmitter<T>, Subscription {
    private static final long serialVersionUID = 7326289992464377023L;
    final Subscriber<? super T> downstream;
    final SequentialDisposable serial = new SequentialDisposable();
    void onRequested() {
    }
    void onUnsubscribed() {
    }
    FlowableCreateBaseEmitter(Subscriber<? super T> subscriber) {
        this.downstream = subscriber;
    }
    protected void complete() {
        if (isCancelled()) {
            return;
        }
        try {
            this.downstream.onComplete();
        } finally {
            this.serial.dispose();
        }
    }
    public void onNext(Resource.Success t) {
        complete();
    }
    public final void onError(Throwable th) {
        if (tryOnError(th)) {
            return;
        }
        RxJavaPlugins.onError(th);
    }
    public boolean tryOnError(Throwable th) {
        return error(th);
    }
    protected boolean error(Throwable th) {
        if (null == th) {
            th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (isCancelled()) {
            return false;
        }
        try {
            this.downstream.onError(th);
            this.serial.dispose();
            return true;
        } catch (Throwable th2) {
            this.serial.dispose();
            throw th2;
        }
    }
    public final void cancel() {
        this.serial.dispose();
        onUnsubscribed();
    }
    public final boolean isCancelled() {
        return this.serial.isDisposed();
    }
    public final void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this, j2);
            onRequested();
        }
    }
    public final void setDisposable(Disposable disposable) {
        this.serial.update(disposable);
    }
    public final void setCancellable(Cancellable cancellable) {
        setDisposable(new CancellableDisposable(cancellable));
    }
    public final long requested() {
        return get();
    }
    public final FlowableCreateSerializedEmitter serialize() {
        return new FlowableCreateSerializedEmitter(this);
    }
    public String toString() {
        return String.format("%s{%s}", getClass().getSimpleName(), super.toString());
    }
}
