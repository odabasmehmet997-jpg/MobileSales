package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;


final class OperatorGroupBy$State<T, K> extends AtomicInteger implements Producer, Subscription, Observable.OnSubscribe<T> {
    private static final long serialVersionUID = -3852313036005250360L;
    final boolean delayError;
    volatile boolean done;
    Throwable error;
    final K key;
    final OperatorGroupBy$GroupBySubscriber<?, K, T> parent;
    final Queue<Object> queue = new ConcurrentLinkedQueue();
    final AtomicBoolean cancelled = new AtomicBoolean();
    final AtomicReference<Subscriber<? super T>> actual = new AtomicReference<>();
    final AtomicBoolean once = new AtomicBoolean();
    final AtomicLong requested = new AtomicLong();

    public OperatorGroupBy$State(int i2, OperatorGroupBy$GroupBySubscriber<?, K, T> operatorGroupBy$GroupBySubscriber, K k2, boolean z) {
        this.parent = operatorGroupBy$GroupBySubscriber;
        this.key = k2;
        this.delayError = z;
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
    public boolean isUnsubscribed() {
        return this.cancelled.get();
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        if (this.cancelled.compareAndSet(false, true) && getAndIncrement() == 0) {
            this.parent.cancel(this.key);
        }
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> subscriber) {
        if (this.once.compareAndSet(false, true)) {
            subscriber.add(this);
            subscriber.setProducer(this);
            this.actual.lazySet(subscriber);
            drain();
            return;
        }
        subscriber.onError(new IllegalStateException("Only one Subscriber allowed!"));
    }

    public void onNext(T t) {
        if (t == null) {
            this.error = new NullPointerException();
            this.done = true;
        } else {
            this.queue.offer(NotificationLite.next(t));
        }
        drain();
    }

    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        drain();
    }

    public void onComplete() {
        this.done = true;
        drain();
    }

    void drain() {
        if (getAndIncrement() != 0) {
            return;
        }
        Queue<Object> queue = this.queue;
        boolean z = this.delayError;
        Subscriber<? super T> subscriber = this.actual.get();
        int iAddAndGet = 1;
        while (true) {
            if (subscriber != null) {
                if (checkTerminated(this.done, queue.isEmpty(), subscriber, z)) {
                    return;
                }
                long j2 = this.requested.get();
                long j3 = 0;
                while (j3 != j2) {
                    boolean z2 = this.done;
                    Object objPoll = queue.poll();
                    boolean z3 = objPoll == null;
                    if (checkTerminated(z2, z3, subscriber, z)) {
                        return;
                    }
                    if (z3) {
                        break;
                    }
                    subscriber.onNext(NotificationLite.getValue(objPoll));
                    j3++;
                }
                if (j3 != 0) {
                    if (j2 != LocationRequestCompat.PASSIVE_INTERVAL) {
                        BackpressureUtils.produced(this.requested, j3);
                    }
                    this.parent.s.request(j3);
                }
            }
            iAddAndGet = addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
            if (subscriber == null) {
                subscriber = this.actual.get();
            }
        }
    }

    boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber, boolean z3) {
        if (this.cancelled.get()) {
            this.queue.clear();
            this.parent.cancel(this.key);
            return true;
        }
        if (!z) {
            return false;
        }
        if (z3) {
            if (!z2) {
                return false;
            }
            Throwable th = this.error;
            if (th != null) {
                subscriber.onError(th);
            } else {
                subscriber.onCompleted();
            }
            return true;
        }
        Throwable th2 = this.error;
        if (th2 != null) {
            this.queue.clear();
            subscriber.onError(th2);
            return true;
        }
        if (!z2) {
            return false;
        }
        subscriber.onCompleted();
        return true;
    }
}
