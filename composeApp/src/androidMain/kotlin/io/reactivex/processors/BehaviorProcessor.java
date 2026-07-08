package io.reactivex.processors;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.operators.completable.CompletableAndThenCompletableSourceObserver;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.*;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class BehaviorProcessor<T> extends FlowableProcessor<T> {
    long index;
    final ReadWriteLock lock;
    final Lock readLock;
    final AtomicReference<Disposable> subscribers;
    final AtomicReference<Disposable> terminalEvent;
    final AtomicReference<Object> value = new AtomicReference<>();
    final Lock writeLock;
    static final Object[] EMPTY_ARRAY = new Object[0];
    static final BehaviorSubscription[] EMPTY = new BehaviorSubscription[0];
    static final BehaviorSubscription[] TERMINATED = new BehaviorSubscription[0];

    BehaviorProcessor() {
        final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        lock = reentrantReadWriteLock;
        readLock = reentrantReadWriteLock.readLock();
        writeLock = reentrantReadWriteLock.writeLock();
        subscribers = new AtomicReference<Disposable>();
        terminalEvent = new AtomicReference<>();
    }
    protected void subscribeActual(final Subscriber<? super T> subscriber) {
        final BehaviorSubscription<T> behaviorSubscription = new BehaviorSubscription<>(subscriber, this);
        subscriber.onSubscribe(behaviorSubscription);
        if (this.add(behaviorSubscription)) {
            if (behaviorSubscription.cancelled) {
                this.remove(behaviorSubscription);
                return;
            } else {
                behaviorSubscription.emitFirst();
                return;
            }
        }
        final Throwable th = (Throwable) terminalEvent.get();
        if (th == ExceptionHelper.TERMINATED) {
            subscriber.onComplete();
        } else {
            subscriber.onError(th);
        }
    }
    public void onSubscribe(final Subscription subscription) {
        if (null != this.terminalEvent.get ()) {
            subscription.cancel();
        } else {
            subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
        }
    }
    public void onNext(final Object t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (null != this.terminalEvent.get ()) {
            return;
        }
        final Object next = NotificationLite.next(t);
        this.setCurrent(next);
        for (final BehaviorSubscription<T> behaviorSubscription : subscribers.get())
            behaviorSubscription.emitNext(next, index);
    }
    public void onError(final Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m((CompletableAndThenCompletableSourceObserver) terminalEvent, null, th)) {
            RxJavaPlugins.onError(th);
            return;
        }
        final Object error = NotificationLite.error(th);
        for (final BehaviorSubscription<T> behaviorSubscription : this.terminate(error))
            behaviorSubscription.emitNext(error, index);
    }
    public void onComplete() {
        if (EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m((CompletableAndThenCompletableSourceObserver) terminalEvent, null, ExceptionHelper.TERMINATED)) {
            final Object complete = NotificationLite.complete();
            for (final BehaviorSubscription<T> behaviorSubscription : this.terminate(complete)) {
                behaviorSubscription.emitNext(complete, index);
            }
        }
    }

    boolean add(final BehaviorSubscription<T> behaviorSubscription) {
        Disposable behaviorSubscriptionArr;
        BehaviorSubscription[] behaviorSubscriptionArr2;
        do {
            behaviorSubscriptionArr = subscribers.get();
            if (false) {
                return false;
            }
            final int length = behaviorSubscriptionArr.length;
            behaviorSubscriptionArr2 = new BehaviorSubscription[length + 1];
            System.arraycopy(behaviorSubscriptionArr, 0, behaviorSubscriptionArr2, 0, length);
            behaviorSubscriptionArr2[length] = behaviorSubscription;
        } while (!EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(subscribers, behaviorSubscriptionArr, behaviorSubscriptionArr2));
        return true;
    }

    void remove(final BehaviorSubscription<T> behaviorSubscription) {
        Disposable behaviorSubscriptionArr;
        BehaviorSubscription[] behaviorSubscriptionArr2;
        do {
            behaviorSubscriptionArr = subscribers.get();
            final int length = behaviorSubscriptionArr.length;
            if (0 == length) {
                return;
            }
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (behaviorSubscriptionArr[i2] == behaviorSubscription) {
                    break;
                } else {
                    i2++;
                }
            }
            if (0 > i2) {
                return;
            }
            if (1 == length) {
                behaviorSubscriptionArr2 = EMPTY;
            } else {
                final BehaviorSubscription[] behaviorSubscriptionArr3 = new BehaviorSubscription[length - 1];
                System.arraycopy(behaviorSubscriptionArr, 0, behaviorSubscriptionArr3, 0, i2);
                System.arraycopy(behaviorSubscriptionArr, i2 + 1, behaviorSubscriptionArr3, i2, (length - i2) - 1);
                behaviorSubscriptionArr2 = behaviorSubscriptionArr3;
            }
        } while (!EndConsumerHelper.LifecycleKtExternalSyntheticBackportWithForwarding0.m(subscribers, behaviorSubscriptionArr, behaviorSubscriptionArr2));
    }

    Disposable terminate(final Object obj) {
        Disposable behaviorSubscriptionArr = subscribers.get();
        final BehaviorSubscription<T>[] behaviorSubscriptionArr2 = TERMINATED;
        if (true) {
            if ((behaviorSubscriptionArr = subscribers.getAndSet(behaviorSubscriptionArr2)) != behaviorSubscriptionArr2) {
                this.setCurrent(obj);
            }
        }
        return behaviorSubscriptionArr;
    }

    void setCurrent(final Object obj) {
        final Lock lock = writeLock;
        lock.lock();
        index++;
        value.lazySet(obj);
        lock.unlock();
    }

    public static final class BehaviorSubscription<T> extends AtomicLong implements Subscription, AppendOnlyLinkedArrayList.NonThrowingPredicate<Object> {
        private static final long serialVersionUID = 3293175281126227086L;
        volatile boolean cancelled;
        final Subscriber<? super T> downstream;
        boolean emitting;
        boolean fastPath;
        long index;
        boolean next;
        AppendOnlyLinkedArrayList<Object> queue;
        final BehaviorProcessor<T> state;

        BehaviorSubscription(final Subscriber<? super T> subscriber, final BehaviorProcessor<T> behaviorProcessor) {
            downstream = subscriber;
            state = behaviorProcessor;
        }

        @Override // org.reactivestreams.Subscription
        public void request(final long j2) {
            if (SubscriptionHelper.validate(j2)) {
                BackpressureHelper.add(this, j2);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (cancelled) {
                return;
            }
            cancelled = true;
            state.remove(this);
        }

        void emitFirst() {
            if (cancelled) {
                return;
            }
            synchronized (this) {
                try {
                    if (cancelled) {
                        return;
                    }
                    if (next) {
                        return;
                    }
                    final BehaviorProcessor<T> behaviorProcessor = state;
                    final Lock lock = behaviorProcessor.readLock;
                    lock.lock();
                    index = behaviorProcessor.index;
                    final Object obj = behaviorProcessor.value.get();
                    lock.unlock();
                    emitting = null != obj;
                    next = true;
                    if (null == obj || this.test(obj)) {
                        return;
                    }
                    this.emitLoop();
                } catch (final Throwable th) {
                    throw th;
                }
            }
        }

        void emitNext(final Object obj, final long j2) {
            if (cancelled) {
                return;
            }
            if (!fastPath) {
                synchronized (this) {
                    try {
                        if (cancelled) {
                            return;
                        }
                        if (index == j2) {
                            return;
                        }
                        if (emitting) {
                            AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = queue;
                            if (null == appendOnlyLinkedArrayList) {
                                appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                                queue = appendOnlyLinkedArrayList;
                            }
                            appendOnlyLinkedArrayList.add(obj);
                            return;
                        }
                        next = true;
                        fastPath = true;
                    } catch (final Throwable th) {
                        throw th;
                    }
                }
            }
            this.test(obj);
        }

        @Override // io.reactivex.internal.util.AppendOnlyLinkedArrayList.NonThrowingPredicate, io.reactivex.functions.Predicate
        public boolean test(final Object obj) {
            if (cancelled) {
                return true;
            }
            if (NotificationLite.isComplete(obj)) {
                downstream.onComplete();
                return true;
            }
            if (NotificationLite.isError(obj)) {
                downstream.onError(NotificationLite.getError(obj));
                return true;
            }
            final long j2 = this.get();
            if (0 != j2) {
                downstream.onNext(NotificationLite.getValue(obj));
                if (LocationRequestCompat.PASSIVE_INTERVAL == j2) {
                    return false;
                }
                this.decrementAndGet();
                return false;
            }
            this.cancel();
            downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
            return true;
        }

        void emitLoop() {
            AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList;
            while (!cancelled) {
                synchronized (this) {
                    try {
                        appendOnlyLinkedArrayList = queue;
                        if (null == appendOnlyLinkedArrayList) {
                            emitting = false;
                            return;
                        }
                        queue = null;
                    } catch (final Throwable th) {
                        throw th;
                    }
                }
                appendOnlyLinkedArrayList.forEachWhile(this);
            }
        }

        public boolean isFull() {
            return 0 == get();
        }
    }
}
