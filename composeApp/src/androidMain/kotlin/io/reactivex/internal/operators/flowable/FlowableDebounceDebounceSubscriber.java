package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.completable.CompletableAndThenCompletableSourceObserver;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subscribers.DisposableSubscriber;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

final class FlowableDebounceDebounceSubscriber<T, U> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = 6725975399620862591L;
    final Function<? super T, ? extends Publisher<U>> debounceSelector;
    final CompletableAndThenCompletableSourceObserver debouncer = new CompletableAndThenCompletableSourceObserver();
    boolean done;
    final Subscriber<? super T> downstream;
    volatile long index;
    Subscription upstream;
    FlowableDebounceDebounceSubscriber(Subscriber<? super T> subscriber, Function<? super T, ? extends Publisher<U>> function) {
        this.downstream = subscriber;
        this.debounceSelector = function;
    }
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        long j2 = this.index + 1;
        this.index = j2;
        Disposable disposable = this.debouncer.get();
        if (null != disposable) {
            disposable.dispose();
        }
        try {
            Publisher publisher = ObjectHelper.requireNonNull(this.debounceSelector.apply(t), "The publisher supplied is null");
            DebounceInnerSubscriber debounceInnerSubscriber = new DebounceInnerSubscriber(this, j2, t);
            EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0 LifecycleKtExternalSyntheticBackportWithForwarding0 = null;
            if (EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(this.debouncer, disposable, debounceInnerSubscriber)) {
                publisher.subscribe(debounceInnerSubscriber);
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            cancel();
            this.downstream.onError(th);
        }
    }
    public void onError(Throwable th) {
        DisposableHelper.dispose(this.debouncer);
        this.downstream.onError(th);
    }
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        Disposable disposable = this.debouncer.get();
        if (DisposableHelper.isDisposed(disposable)) {
            return;
        }
        DebounceInnerSubscriber debounceInnerSubscriber = (DebounceInnerSubscriber) disposable;
        if (null != debounceInnerSubscriber) {
            debounceInnerSubscriber.emit();
        }
        DisposableHelper.dispose(this.debouncer);
        this.downstream.onComplete();
    }
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this, j2);
        }
    }
    public void cancel() {
        this.upstream.cancel();
        DisposableHelper.dispose(this.debouncer);
    }
    void emit(long j2, T t) {
        if (j2 == this.index) {
            if (0 != this.get()) {
                this.downstream.onNext(t);
                BackpressureHelper.produced(this, 1L);
            } else {
                cancel();
                this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
            }
        }
    }
    static final class DebounceInnerSubscriber<T, U> extends DisposableSubscriber<U> {
        boolean done;
        final long index;
        final AtomicBoolean once = new AtomicBoolean();
        final FlowableDebounceDebounceSubscriber<T, U> parent;
        final T value;
        DebounceInnerSubscriber(FlowableDebounceDebounceSubscriber<T, U> flowableDebounceDebounceSubscriber, long j2, T t) {
            this.parent = flowableDebounceDebounceSubscriber;
            this.index = j2;
            this.value = t;
        }
        public void onNext(Object u) {
            if (this.done) {
                return;
            }
            this.done = true;
            this.cancel();
            emit();
        }

        void emit() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.emit(this.index, this.value);
            }
        }
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else {
                this.done = true;
                this.parent.onError(th);
            }
        }
        public <T> Observable<T> observeOn(Scheduler scheduler) {
            return null;
        }
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            emit();
        }
    }
}
