package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Action;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

final class C2975x31a081c4<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
    private static final long serialVersionUID = 3240706908776709697L;
    final long bufferSize;
    volatile boolean cancelled;
    volatile boolean done;
    final Subscriber<? super T> downstream;
    Throwable error;
    final Action onOverflow;
    final BackpressureOverflowStrategy strategy;
    Subscription upstream;
    final AtomicLong requested = new AtomicLong();
    final Deque<T> deque = new ArrayDeque();

    C2975x31a081c4(Subscriber<? super T> subscriber, Action action, BackpressureOverflowStrategy backpressureOverflowStrategy, long j2) {
        this.downstream = subscriber;
        this.onOverflow = action;
        this.strategy = backpressureOverflowStrategy;
        this.bufferSize = j2;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        boolean z;
        boolean z2;
        if (this.done) {
            return;
        }
        Deque<T> deque = this.deque;
        synchronized (deque) {
            try {
                z = false;
                if (deque.size() == this.bufferSize) {
                    int i2 = FlowableOnBackpressureBufferStrategy1.SwitchMapioreactivexBackpressureOverflowStrategy[this.strategy.ordinal()];
                    z2 = true;
                    if (i2 == 1) {
                        deque.pollLast();
                        deque.offer(t);
                    } else if (i2 == 2) {
                        deque.poll();
                        deque.offer(t);
                    }
                    z2 = false;
                    z = true;
                } else {
                    deque.offer(t);
                    z2 = false;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (!z) {
            if (z2) {
                this.upstream.cancel();
                onError(new MissingBackpressureException());
                return;
            } else {
                drain();
                return;
            }
        }
        Action action = this.onOverflow;
        if (action != null) {
            try {
                action.run();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.upstream.cancel();
                onError(th2);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.done = true;
        drain();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            BackpressureHelper.add(this.requested, j2);
            drain();
        }
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.cancelled = true;
        this.upstream.cancel();
        if (getAndIncrement() == 0) {
            clear(this.deque);
        }
    }

    void clear(Deque<T> deque) {
        synchronized (deque) {
            deque.clear();
        }
    }

    void drain() {
        boolean isEmpty;
        T poll;
        if (getAndIncrement() != 0) {
            return;
        }
        Deque<T> deque = this.deque;
        Subscriber<? super T> subscriber = this.downstream;
        int i2 = 1;
        do {
            long j2 = this.requested.get();
            long j3 = 0;
            while (j3 != j2) {
                if (this.cancelled) {
                    clear(deque);
                    return;
                }
                boolean z = this.done;
                synchronized (deque) {
                    poll = deque.poll();
                }
                boolean z2 = poll == null;
                if (z) {
                    Throwable th = this.error;
                    if (th != null) {
                        clear(deque);
                        subscriber.onError(th);
                        return;
                    } else if (z2) {
                        subscriber.onComplete();
                        return;
                    }
                }
                if (z2) {
                    break;
                }
                subscriber.onNext(poll);
                j3++;
            }
            if (j3 == j2) {
                if (this.cancelled) {
                    clear(deque);
                    return;
                }
                boolean z3 = this.done;
                synchronized (deque) {
                    isEmpty = deque.isEmpty();
                }
                if (z3) {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        clear(deque);
                        subscriber.onError(th2);
                        return;
                    } else if (isEmpty) {
                        subscriber.onComplete();
                        return;
                    }
                }
            }
            if (j3 != 0) {
                BackpressureHelper.produced(this.requested, j3);
            }
            i2 = addAndGet(-i2);
        } while (i2 != 0);
    }
}
