package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.observables.GroupedObservable;
import rx.plugins.RxJavaHooks;


public final class OperatorGroupBy$GroupBySubscriber<T, K, V> extends Subscriber<T> {
    static final Object NULL_KEY = new Object();
    final Subscriber<? super GroupedObservable<K, V>> actual;
    final int bufferSize;
    final AtomicBoolean cancelled;
    final boolean delayError;
    volatile boolean done;
    Throwable error;
    final Queue<K> evictedKeys;
    final AtomicInteger groupCount;
    final Map<Object, OperatorGroupBy$GroupedUnicast<K, V>> groups;
    final Map<Object, OperatorGroupBy$GroupedUnicast<K, V>> groupsCopy;
    final Func1<? super T, ? extends K> keySelector;
    final Queue<GroupedObservable<K, V>> queue;
    final AtomicLong requested;
    final ProducerArbiter s;
    final Func1<? super T, ? extends V> valueSelector;
    final AtomicInteger wip;

    @Override // rx.Subscriber
    public void setProducer(Producer producer) {
        this.s.setProducer(producer);
    }

    @Override // rx.Observer
    public void onNext(Object t) {
        boolean z;
        if (this.done) {
            return;
        }
        Queue<?> queue = this.queue;
        Subscriber<? super GroupedObservable<K, V>> subscriber = this.actual;
        try {
            K kCall = this.keySelector.call(t);
            Object obj = kCall != null ? kCall : NULL_KEY;
            OperatorGroupBy$GroupedUnicast<K, V> operatorGroupBy$GroupedUnicastCreateWith = this.groups.get(obj);
            if (operatorGroupBy$GroupedUnicastCreateWith != null) {
                z = false;
            } else {
                if (this.cancelled.get()) {
                    return;
                }
                operatorGroupBy$GroupedUnicastCreateWith = OperatorGroupBy$GroupedUnicast.createWith(kCall, this.bufferSize, this, this.delayError);
                this.groups.put(obj, operatorGroupBy$GroupedUnicastCreateWith);
                if (this.evictedKeys != null) {
                    this.groupsCopy.put(obj, operatorGroupBy$GroupedUnicastCreateWith);
                }
                this.groupCount.getAndIncrement();
                z = true;
            }
            try {
                operatorGroupBy$GroupedUnicastCreateWith.onNext(this.valueSelector.call(t));
                if (this.evictedKeys != null) {
                    while (true) {
                        K kPoll = this.evictedKeys.poll();
                        if (kPoll == null) {
                            break;
                        }
                        OperatorGroupBy$GroupedUnicast<K, V> operatorGroupBy$GroupedUnicastRemove = this.groupsCopy.remove(kPoll);
                        if (operatorGroupBy$GroupedUnicastRemove != null) {
                            operatorGroupBy$GroupedUnicastRemove.onComplete();
                        }
                    }
                }
                if (z) {
                    queue.offer(operatorGroupBy$GroupedUnicastCreateWith);
                    drain();
                }
            } catch (Throwable th) {
                unsubscribe();
                errorAll(subscriber, queue, th);
            }
        } catch (Throwable th2) {
            unsubscribe();
            errorAll(subscriber, queue, th2);
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaHooks.onError(th);
            return;
        }
        this.error = th;
        this.done = true;
        this.groupCount.decrementAndGet();
        drain();
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.done) {
            return;
        }
        Iterator<OperatorGroupBy$GroupedUnicast<K, V>> it = this.groups.values().iterator();
        while (it.hasNext()) {
            it.next().onComplete();
        }
        this.groups.clear();
        if (this.evictedKeys != null) {
            this.groupsCopy.clear();
            this.evictedKeys.clear();
        }
        this.done = true;
        this.groupCount.decrementAndGet();
        drain();
    }

    public void cancel(K k2) {
        if (k2 == null) {
            k2 = (K) NULL_KEY;
        }
        if (this.groups.remove(k2) != null && this.groupCount.decrementAndGet() == 0) {
            unsubscribe();
        }
        if (this.evictedKeys != null) {
            this.groupsCopy.remove(k2);
        }
    }

    void drain() {
        if (this.wip.getAndIncrement() != 0) {
            return;
        }
        Queue<GroupedObservable<K, V>> queue = this.queue;
        Subscriber<? super GroupedObservable<K, V>> subscriber = this.actual;
        int iAddAndGet = 1;
        while (!checkTerminated(this.done, queue.isEmpty(), subscriber, queue)) {
            long j2 = this.requested.get();
            long j3 = 0;
            while (j3 != j2) {
                boolean z = this.done;
                GroupedObservable<K, V> groupedObservablePoll = queue.poll();
                boolean z2 = groupedObservablePoll == null;
                if (checkTerminated(z, z2, subscriber, queue)) {
                    return;
                }
                if (z2) {
                    break;
                }
                subscriber.onNext(groupedObservablePoll);
                j3++;
            }
            if (j3 != 0) {
                if (j2 != LocationRequestCompat.PASSIVE_INTERVAL) {
                    BackpressureUtils.produced(this.requested, j3);
                }
                this.s.request(j3);
            }
            iAddAndGet = this.wip.addAndGet(-iAddAndGet);
            if (iAddAndGet == 0) {
                return;
            }
        }
    }

    void errorAll(Subscriber<? super GroupedObservable<K, V>> subscriber, Queue<?> queue, Throwable th) {
        queue.clear();
        ArrayList arrayList = new ArrayList(this.groups.values());
        this.groups.clear();
        if (this.evictedKeys != null) {
            this.groupsCopy.clear();
            this.evictedKeys.clear();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((OperatorGroupBy$GroupedUnicast) it.next()).onError(th);
        }
        subscriber.onError(th);
    }

    boolean checkTerminated(boolean z, boolean z2, Subscriber<? super GroupedObservable<K, V>> subscriber, Queue<?> queue) {
        if (!z) {
            return false;
        }
        Throwable th = this.error;
        if (th != null) {
            errorAll(subscriber, queue, th);
            return true;
        }
        if (!z2) {
            return false;
        }
        this.actual.onCompleted();
        return true;
    }
}
