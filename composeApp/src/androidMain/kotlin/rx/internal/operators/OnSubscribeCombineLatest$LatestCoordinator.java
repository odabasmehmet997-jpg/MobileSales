package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.FuncN;
import rx.internal.util.atomic.SpscLinkedArrayQueue;


final class OnSubscribeCombineLatest$LatestCoordinator<T, R> extends AtomicInteger implements Producer, Subscription {
    static final Object MISSING = new Object();
    private static final long serialVersionUID = 8567835998786448817L;
    int active;
    final Subscriber<? super R> actual;
    final int bufferSize;
    volatile boolean cancelled;
    final FuncN<? extends R> combiner;
    int complete;
    final boolean delayError;
    volatile boolean done;
    final AtomicReference<Throwable> error;
    final Object[] latest;
    final SpscLinkedArrayQueue<Object> queue;
    final AtomicLong requested;
    final OnSubscribeCombineLatest$CombinerSubscriber<T, R>[] subscribers;

    public OnSubscribeCombineLatest$LatestCoordinator(Subscriber<? super R> subscriber, FuncN<? extends R> funcN, int i2, int i3, boolean z) {
        this.actual = subscriber;
        this.bufferSize = i3;
        this.delayError = z;
        Object[] objArr = new Object[i2];
        this.latest = objArr;
        Arrays.fill(objArr, MISSING);
        this.subscribers = new OnSubscribeCombineLatest$CombinerSubscriber[i2];
        this.queue = new SpscLinkedArrayQueue<>(i3);
        this.requested = new AtomicLong();
        this.error = new AtomicReference<>();
    }

    public void subscribe(Observable<? extends T>[] observableArr) {
        OnSubscribeCombineLatest$CombinerSubscriber<T, R>[] onSubscribeCombineLatest$CombinerSubscriberArr = this.subscribers;
        int length = onSubscribeCombineLatest$CombinerSubscriberArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            onSubscribeCombineLatest$CombinerSubscriberArr[i2] = new OnSubscribeCombineLatest$CombinerSubscriber<>(this, i2);
        }
        lazySet(0);
        this.actual.add(this);
        this.actual.setProducer(this);
        for (int i3 = 0; i3 < length && !this.cancelled; i3++) {
            observableArr[i3].subscribe(onSubscribeCombineLatest$CombinerSubscriberArr[i3]);
        }
    }

    @Override // rx.Producer
    public void request(long j2) {
        if (j2 < 0) {
            throw new IllegalArgumentException("n >= required but it was " + j2);
        }
        if (j2 != 0) {
            BackpressureUtils.getAndAddRequest(this.requested, j2);
            drain();
        }
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        if (getAndIncrement() == 0) {
            cancel(this.queue);
        }
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.cancelled;
    }

    void cancel(Queue<?> queue) {
        queue.clear();
        for (OnSubscribeCombineLatest$CombinerSubscriber<T, R> onSubscribeCombineLatest$CombinerSubscriber : this.subscribers) {
            onSubscribeCombineLatest$CombinerSubscriber.unsubscribe();
        }
    }

    void combine(Object obj, int i2) {
        boolean z;
        OnSubscribeCombineLatest$CombinerSubscriber<T, R> onSubscribeCombineLatest$CombinerSubscriber = this.subscribers[i2];
        synchronized (this) {
            try {
                Object[] objArr = this.latest;
                int length = objArr.length;
                Object obj2 = objArr[i2];
                int i3 = this.active;
                Object obj3 = MISSING;
                if (obj2 == obj3) {
                    i3++;
                    this.active = i3;
                }
                int i4 = this.complete;
                if (obj == null) {
                    i4++;
                    this.complete = i4;
                } else {
                    objArr[i2] = NotificationLite.getValue(obj);
                }
                z = i3 == length;
                if (i4 == length || (obj == null && obj2 == obj3)) {
                    this.done = true;
                } else if (obj != null && z) {
                    this.queue.offer(onSubscribeCombineLatest$CombinerSubscriber, this.latest.clone());
                } else if (obj == null && this.error.get() != null && (obj2 == obj3 || !this.delayError)) {
                    this.done = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        if (!z && obj != null) {
            onSubscribeCombineLatest$CombinerSubscriber.requestMore(1L);
        } else {
            drain();
        }
    }

    void drain() {
        long j2;
        if (getAndIncrement() != 0) {
            return;
        }
        SpscLinkedArrayQueue<Object> spscLinkedArrayQueue = this.queue;
        Subscriber<? super R> subscriber = this.actual;
        boolean z = this.delayError;
        AtomicLong atomicLong = this.requested;
        int iAddAndGet = 1;
        while (!checkTerminated(this.done, spscLinkedArrayQueue.isEmpty(), subscriber, spscLinkedArrayQueue, z)) {
            long j3 = atomicLong.get();
            if (0 != j3) {
                boolean z2 = this.done;
                boolean z3 = spscLinkedArrayQueue.peek() == null;
                if (checkTerminated(z2, z3, subscriber, spscLinkedArrayQueue, z)) {
                    return;
                }
                if (!z3) {
                    spscLinkedArrayQueue.poll();
                    if (spscLinkedArrayQueue.poll() == null) {
                        this.cancelled = true;
                        cancel(spscLinkedArrayQueue);
                        subscriber.onError(new IllegalStateException("Broken queue?! Sender received but not the array."));
                        return;
                    } else {
                        try {
                            throw null;
                        } catch (Throwable th) {
                            this.cancelled = true;
                            cancel(spscLinkedArrayQueue);
                            subscriber.onError(th);
                            return;
                        }
                    }
                }
                j2 = 0;
            } else {
                j2 = 0;
            }
            if (j2 != j2 && j3 != LocationRequestCompat.PASSIVE_INTERVAL) {
                BackpressureUtils.produced(atomicLong, j2);
            }
            iAddAndGet = addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
        }
    }

    boolean checkTerminated(boolean z, boolean z2, Subscriber<?> subscriber, Queue<?> queue, boolean z3) {
        if (this.cancelled) {
            cancel(queue);
            return true;
        }
        if (!z) {
            return false;
        }
        if (z3) {
            if (!z2) {
                return false;
            }
            Throwable th = this.error.get();
            if (th != null) {
                subscriber.onError(th);
            } else {
                subscriber.onCompleted();
            }
            return true;
        }
        Throwable th2 = this.error.get();
        if (th2 != null) {
            cancel(queue);
            subscriber.onError(th2);
            return true;
        }
        if (!z2) {
            return false;
        }
        subscriber.onCompleted();
        return true;
    }

    void onError(Throwable th) {
        Throwable th2;
        Throwable compositeException;
        AtomicReference<Throwable> atomicReference = this.error;
        do {
            th2 = atomicReference.get();
            if (th2 == null) {
                compositeException = th;
            } else if (th2 instanceof CompositeException) {
                ArrayList arrayList = new ArrayList(((CompositeException) th2).getExceptions());
                arrayList.add(th);
                compositeException = new CompositeException(arrayList);
            } else {
                compositeException = new CompositeException(Arrays.asList(th2, th));
            }
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, th2, compositeException));
    }
}
