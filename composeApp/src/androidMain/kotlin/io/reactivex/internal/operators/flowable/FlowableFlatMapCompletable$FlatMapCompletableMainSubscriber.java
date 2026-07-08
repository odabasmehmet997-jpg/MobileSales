package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;



final class FlowableFlatMapCompletableFlatMapCompletableMainSubscriber<T> extends BasicIntQueueSubscription<T> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = 8443155186132538303L;
    volatile boolean cancelled;
    final boolean delayErrors;
    final Subscriber<? super T> downstream;
    final Function<? super T, ? extends CompletableSource> mapper;
    final int maxConcurrency;
    Subscription upstream;
    final AtomicThrowable errors = new AtomicThrowable();
    final CompositeDisposable set = new CompositeDisposable();

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public void clear() {
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public boolean isEmpty() {
        return true;
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.SimpleQueue
    public T poll() throws Exception {
        return null;
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
    public void request(long j2) {
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, io.reactivex.internal.fuseable.QueueFuseable
    public int requestFusion(int i2) {
        return i2 & 2;
    }

    FlowableFlatMapCompletableFlatMapCompletableMainSubscriber(Subscriber<? super T> subscriber, Function<? super T, ? extends CompletableSource> function, boolean z, int i2) {
        this.downstream = subscriber;
        this.mapper = function;
        this.delayErrors = z;
        this.maxConcurrency = i2;
        this.lazySet(1);
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            int i2 = this.maxConcurrency;
            if (Integer.MAX_VALUE == i2) {
                subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
            } else {
                subscription.request(i2);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object t) {
        try {
            CompletableSource completableSource = ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null CompletableSource");
            this.getAndIncrement();
            InnerConsumer innerConsumer = new InnerConsumer();
            if (this.cancelled || !this.set.add(innerConsumer)) {
                return;
            }
            completableSource.subscribe(innerConsumer);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.upstream.cancel();
            onError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.errors.addThrowable(th)) {
            if (this.delayErrors) {
                if (0 == this.decrementAndGet()) {
                    this.downstream.onError(this.errors.terminate());
                    return;
                } else {
                    if (Integer.MAX_VALUE != maxConcurrency) {
                        this.upstream.request(1L);
                        return;
                    }
                    return;
                }
            }
            cancel();
            if (0 < this.getAndSet(0)) {
                this.downstream.onError(this.errors.terminate());
                return;
            }
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (0 == this.decrementAndGet()) {
            Throwable terminate = this.errors.terminate();
            if (null != terminate) {
                this.downstream.onError(terminate);
                return;
            } else {
                this.downstream.onComplete();
                return;
            }
        }
        if (Integer.MAX_VALUE != maxConcurrency) {
            this.upstream.request(1L);
        }
    }

    @Override // io.reactivex.internal.subscriptions.BasicIntQueueSubscription, org.reactivestreams.Subscription
    public void cancel() {
        this.cancelled = true;
        this.upstream.cancel();
        this.set.dispose();
    }

    void innerComplete(InnerConsumer innerConsumer) {
        this.set.delete(innerConsumer);
        onComplete();
    }

    void innerError(InnerConsumer innerConsumer, Throwable th) {
        this.set.delete(innerConsumer);
        onError(th);
    }

    final class InnerConsumer extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
        private static final long serialVersionUID = 8606673141535671828L;

        InnerConsumer() {
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            FlowableFlatMapCompletableFlatMapCompletableMainSubscriber.this.innerComplete(this);
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            FlowableFlatMapCompletableFlatMapCompletableMainSubscriber.this.innerError(this, th);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
