package rx.internal.operators;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Producer;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.util.ExceptionsUtils;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;


final class OnSubscribeFlatMapSingle$FlatMapSingleSubscriber<T, R> extends Subscriber<T> {
    final AtomicInteger active;
    final Subscriber<? super R> actual;
    volatile boolean cancelled;
    final boolean delayErrors;
    volatile boolean done;
    final AtomicReference<Throwable> errors;
    final Func1<? super T, ? extends Single<? extends R>> mapper;
    final int maxConcurrency;
    final Queue<Object> queue;
    final OnSubscribeFlatMapSingle$FlatMapSingleSubscriber<T, R>.Requested requested;
    final CompositeSubscription set;
    final AtomicInteger wip;

    @Override // rx.Observer
    public void onNext(Object t) {
        try {
            Single<? extends R> singleCall = this.mapper.call(t);
            if (singleCall == null) {
                throw new NullPointerException("The mapper returned a null Single");
            }
            InnerSubscriber innerSubscriber = new InnerSubscriber();
            this.set.add(innerSubscriber);
            this.active.incrementAndGet();
            singleCall.subscribe(innerSubscriber);
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
        } else {
            this.set.unsubscribe();
            if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.errors, null, th)) {
                RxJavaHooks.onError(th);
                return;
            }
        }
        this.done = true;
        drain();
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.done = true;
        drain();
    }

    void innerSuccess(OnSubscribeFlatMapSingle$FlatMapSingleSubscriber<T, R>.InnerSubscriber innerSubscriber, R r) {
        this.queue.offer(NotificationLite.next(r));
        this.set.remove(innerSubscriber);
        this.active.decrementAndGet();
        drain();
    }

    void innerError(OnSubscribeFlatMapSingle$FlatMapSingleSubscriber<T, R>.InnerSubscriber innerSubscriber, Throwable th) {
        if (this.delayErrors) {
            ExceptionsUtils.addThrowable(this.errors, th);
            this.set.remove(innerSubscriber);
            if (!this.done && this.maxConcurrency != Integer.MAX_VALUE) {
                request(1L);
            }
        } else {
            this.set.unsubscribe();
            unsubscribe();
            if (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.errors, null, th)) {
                RxJavaHooks.onError(th);
                return;
            }
            this.done = true;
        }
        this.active.decrementAndGet();
        drain();
    }

    void drain() {
        if (this.wip.getAndIncrement() != 0) {
            return;
        }
        Subscriber<? super R> subscriber = this.actual;
        Queue<Object> queue = this.queue;
        boolean z = this.delayErrors;
        AtomicInteger atomicInteger = this.active;
        int iAddAndGet = 1;
        do {
            long j2 = this.requested.get();
            long j3 = 0;
            while (j3 != j2) {
                if (this.cancelled) {
                    queue.clear();
                    return;
                }
                boolean z2 = this.done;
                if (!z && z2 && this.errors.get() != null) {
                    queue.clear();
                    subscriber.onError(ExceptionsUtils.terminate(this.errors));
                    return;
                }
                Object objPoll = queue.poll();
                boolean z3 = objPoll == null;
                if (z2 && atomicInteger.get() == 0 && z3) {
                    if (this.errors.get() != null) {
                        subscriber.onError(ExceptionsUtils.terminate(this.errors));
                        return;
                    } else {
                        subscriber.onCompleted();
                        return;
                    }
                }
                if (z3) {
                    break;
                }
                subscriber.onNext(NotificationLite.getValue(objPoll));
                j3++;
            }
            if (j3 == j2) {
                if (this.cancelled) {
                    queue.clear();
                    return;
                }
                if (this.done) {
                    if (z) {
                        if (atomicInteger.get() == 0 && queue.isEmpty()) {
                            if (this.errors.get() != null) {
                                subscriber.onError(ExceptionsUtils.terminate(this.errors));
                                return;
                            } else {
                                subscriber.onCompleted();
                                return;
                            }
                        }
                    } else if (this.errors.get() != null) {
                        queue.clear();
                        subscriber.onError(ExceptionsUtils.terminate(this.errors));
                        return;
                    } else if (atomicInteger.get() == 0 && queue.isEmpty()) {
                        subscriber.onCompleted();
                        return;
                    }
                }
            }
            if (j3 != 0) {
                this.requested.produced(j3);
                if (!this.done && this.maxConcurrency != Integer.MAX_VALUE) {
                    request(j3);
                }
            }
            iAddAndGet = this.wip.addAndGet(-iAddAndGet);
        } while (iAddAndGet != 0);
    }

    final class Requested extends AtomicLong implements Producer, Subscription {
        private static final long serialVersionUID = -887187595446742742L;

        Requested() {
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 > 0) {
                BackpressureUtils.getAndAddRequest(this, j2);
                OnSubscribeFlatMapSingle$FlatMapSingleSubscriber.this.drain();
            }
        }

        void produced(long j2) {
            BackpressureUtils.produced(this, j2);
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            OnSubscribeFlatMapSingle$FlatMapSingleSubscriber.this.cancelled = true;
            OnSubscribeFlatMapSingle$FlatMapSingleSubscriber.this.unsubscribe();
            if (OnSubscribeFlatMapSingle$FlatMapSingleSubscriber.this.wip.getAndIncrement() == 0) {
                OnSubscribeFlatMapSingle$FlatMapSingleSubscriber.this.queue.clear();
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return OnSubscribeFlatMapSingle$FlatMapSingleSubscriber.this.cancelled;
        }
    }

    final class InnerSubscriber extends SingleSubscriber<R> {
        InnerSubscriber() {
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(R r) {
            OnSubscribeFlatMapSingle$FlatMapSingleSubscriber.this.innerSuccess(this, r);
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            OnSubscribeFlatMapSingle$FlatMapSingleSubscriber.this.innerError(this, th);
        }
    }
}
