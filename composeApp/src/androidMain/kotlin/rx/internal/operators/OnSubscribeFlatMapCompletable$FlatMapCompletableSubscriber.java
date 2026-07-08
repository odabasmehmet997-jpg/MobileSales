package rx.internal.operators;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.util.ExceptionsUtils;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;


final class OnSubscribeFlatMapCompletable$FlatMapCompletableSubscriber<T> extends Subscriber<T> {
    final Subscriber<? super T> actual;
    final boolean delayErrors;
    final AtomicReference<Throwable> errors;
    final Func1<? super T, ? extends Completable> mapper;
    final int maxConcurrency;
    final CompositeSubscription set;
    final AtomicInteger wip;

    @Override // rx.Observer
    public void onNext(Object t) {
        try {
            Completable completableCall = this.mapper.call(t);
            if (completableCall == null) {
                throw new NullPointerException("The mapper returned a null Completable");
            }
            InnerSubscriber innerSubscriber = new InnerSubscriber();
            this.set.add(innerSubscriber);
            this.wip.getAndIncrement();
            completableCall.unsafeSubscribe(innerSubscriber);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            unsubscribe();
            onError(th);
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        if (this.delayErrors) {
            ExceptionsUtils.addThrowable(this.errors, th);
            onCompleted();
            return;
        }
        this.set.unsubscribe();
        if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.errors, null, th)) {
            this.actual.onError(ExceptionsUtils.terminate(this.errors));
        } else {
            RxJavaHooks.onError(th);
        }
    }

    @Override // rx.Observer
    public void onCompleted() {
        done();
    }

    boolean done() {
        if (this.wip.decrementAndGet() != 0) {
            return false;
        }
        Throwable thTerminate = ExceptionsUtils.terminate(this.errors);
        if (thTerminate != null) {
            this.actual.onError(thTerminate);
            return true;
        }
        this.actual.onCompleted();
        return true;
    }

    public void innerError(OnSubscribeFlatMapCompletable$FlatMapCompletableSubscriber<T>.InnerSubscriber innerSubscriber, Throwable th) {
        this.set.remove(innerSubscriber);
        if (this.delayErrors) {
            ExceptionsUtils.addThrowable(this.errors, th);
            if (done() || this.maxConcurrency == Integer.MAX_VALUE) {
                return;
            }
            request(1L);
            return;
        }
        this.set.unsubscribe();
        unsubscribe();
        if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.errors, null, th)) {
            this.actual.onError(ExceptionsUtils.terminate(this.errors));
        } else {
            RxJavaHooks.onError(th);
        }
    }

    public void innerComplete(OnSubscribeFlatMapCompletable$FlatMapCompletableSubscriber<T>.InnerSubscriber innerSubscriber) {
        this.set.remove(innerSubscriber);
        if (done() || this.maxConcurrency == Integer.MAX_VALUE) {
            return;
        }
        request(1L);
    }

    final class InnerSubscriber extends AtomicReference<Subscription> implements CompletableSubscriber, Subscription {
        private static final long serialVersionUID = -8588259593722659900L;

        InnerSubscriber() {
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            Subscription andSet = getAndSet(this);
            if (andSet == null || andSet == this) {
                return;
            }
            andSet.unsubscribe();
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() == this;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            OnSubscribeFlatMapCompletable$FlatMapCompletableSubscriber.this.innerComplete(this);
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            OnSubscribeFlatMapCompletable$FlatMapCompletableSubscriber.this.innerError(this, th);
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            if (compareAndSet(null, subscription)) {
                return;
            }
            subscription.unsubscribe();
            if (get() != this) {
                RxJavaHooks.onError(new IllegalStateException("Subscription already set!"));
            }
        }
    }
}
