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


public final class OperatorGroupByEvicting$GroupBySubscriber<T, K, V> extends Subscriber<T> {
    static final Object NULL_KEY = new Object();
    final Subscriber<? super GroupedObservable<K, V>> actual;
    final int bufferSize;
    final AtomicBoolean cancelled;
    final boolean delayError;
    volatile boolean done;
    Throwable error;
    final Queue<OperatorGroupByEvicting$GroupedUnicast<K, V>> evictedGroups;
    final AtomicInteger groupCount;
    final Map<K, OperatorGroupByEvicting$GroupedUnicast<K, V>> groups;
    final Func1<? super T, ? extends K> keySelector;
    final Queue<OperatorGroupByEvicting$GroupedUnicast<K, V>> queue;
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
        if (this.done) {
            return;
        }
        Queue<?> queue = this.queue;
        Subscriber<? super GroupedObservable<K, V>> subscriber = this.actual;
        try {
            K kCall = this.keySelector.call(t);
            Object obj = kCall != null ? kCall : NULL_KEY;
            OperatorGroupByEvicting$GroupedUnicast<K, V> operatorGroupByEvicting$GroupedUnicastCreateWith = this.groups.get(obj);
            if (operatorGroupByEvicting$GroupedUnicastCreateWith == null) {
                if (this.cancelled.get()) {
                    return;
                }
                operatorGroupByEvicting$GroupedUnicastCreateWith = OperatorGroupByEvicting$GroupedUnicast.createWith(kCall, this.bufferSize, this, this.delayError);
                this.groups.put((K) obj, operatorGroupByEvicting$GroupedUnicastCreateWith);
                this.groupCount.getAndIncrement();
                queue.offer(operatorGroupByEvicting$GroupedUnicastCreateWith);
                drain();
            }
            try {
                operatorGroupByEvicting$GroupedUnicastCreateWith.onNext(this.valueSelector.call(t));
                if (this.evictedGroups == null) {
                    return;
                }
                while (true) {
                    OperatorGroupByEvicting$GroupedUnicast<K, V> operatorGroupByEvicting$GroupedUnicastPoll = this.evictedGroups.poll();
                    if (operatorGroupByEvicting$GroupedUnicastPoll == null) {
                        return;
                    } else {
                        operatorGroupByEvicting$GroupedUnicastPoll.onComplete();
                    }
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
        Iterator<OperatorGroupByEvicting$GroupedUnicast<K, V>> it = this.groups.values().iterator();
        while (it.hasNext()) {
            it.next().onComplete();
        }
        this.groups.clear();
        Queue<OperatorGroupByEvicting$GroupedUnicast<K, V>> queue = this.evictedGroups;
        if (queue != null) {
            queue.clear();
        }
        this.done = true;
        this.groupCount.decrementAndGet();
        drain();
    }

    public void cancel(K k2) {
        if (k2 == null) {
            k2 = (K) NULL_KEY;
        }
        if (this.groups.remove(k2) == null || this.groupCount.decrementAndGet() != 0) {
            return;
        }
        unsubscribe();
    }

    void drain() {
        if (this.wip.getAndIncrement() != 0) {
            return;
        }
        Queue<OperatorGroupByEvicting$GroupedUnicast<K, V>> queue = this.queue;
        Subscriber<? super GroupedObservable<K, V>> subscriber = this.actual;
        int iAddAndGet = 1;
        while (!checkTerminated(this.done, queue.isEmpty(), subscriber, queue)) {
            long j2 = this.requested.get();
            boolean z = j2 == LocationRequestCompat.PASSIVE_INTERVAL;
            long j3 = 0;
            while (j2 != 0) {
                boolean z2 = this.done;
                OperatorGroupByEvicting$GroupedUnicast<K, V> operatorGroupByEvicting$GroupedUnicastPoll = queue.poll();
                boolean z3 = operatorGroupByEvicting$GroupedUnicastPoll == null;
                if (checkTerminated(z2, z3, subscriber, queue)) {
                    return;
                }
                if (z3) {
                    break;
                }
                subscriber.onNext(operatorGroupByEvicting$GroupedUnicastPoll);
                j2--;
                j3--;
            }
            if (j3 != 0) {
                if (!z) {
                    this.requested.addAndGet(j3);
                }
                this.s.request(-j3);
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
        Queue<OperatorGroupByEvicting$GroupedUnicast<K, V>> queue2 = this.evictedGroups;
        if (queue2 != null) {
            queue2.clear();
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((OperatorGroupByEvicting$GroupedUnicast) it.next()).onError(th);
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
