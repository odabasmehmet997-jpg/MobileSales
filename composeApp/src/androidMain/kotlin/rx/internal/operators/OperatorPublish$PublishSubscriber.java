package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.internal.util.RxRingBuffer;


final class OperatorPublish$PublishSubscriber<T> extends Subscriber<T> implements Subscription {
    static final OperatorPublish$InnerProducer[] EMPTY = new OperatorPublish$InnerProducer[0];
    static final OperatorPublish$InnerProducer[] TERMINATED = new OperatorPublish$InnerProducer[0];
    final AtomicReference<OperatorPublish$PublishSubscriber<T>> current;
    boolean emitting;
    boolean missed;
    final AtomicReference<OperatorPublish$InnerProducer[]> producers;
    final Queue<Object> queue;
    volatile Object terminalEvent;

    @Override // rx.Subscriber
    public void onStart() {
        request(RxRingBuffer.SIZE);
    }

    @Override // rx.Observer
    public void onNext(Object t) {
        if (!this.queue.offer(NotificationLite.next(t))) {
            onError(new MissingBackpressureException());
        } else {
            dispatch();
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        if (this.terminalEvent == null) {
            this.terminalEvent = NotificationLite.error(th);
            dispatch();
        }
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.terminalEvent == null) {
            this.terminalEvent = NotificationLite.completed();
            dispatch();
        }
    }

    void remove(OperatorPublish$InnerProducer<T> operatorPublish$InnerProducer) {
        OperatorPublish$InnerProducer[] operatorPublish$InnerProducerArr;
        OperatorPublish$InnerProducer[] operatorPublish$InnerProducerArr2;
        do {
            operatorPublish$InnerProducerArr = this.producers.get();
            if (operatorPublish$InnerProducerArr == EMPTY || operatorPublish$InnerProducerArr == TERMINATED) {
                return;
            }
            int length = operatorPublish$InnerProducerArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (operatorPublish$InnerProducerArr[i2].equals(operatorPublish$InnerProducer)) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 < 0) {
                return;
            }
            if (length == 1) {
                operatorPublish$InnerProducerArr2 = EMPTY;
            } else {
                OperatorPublish$InnerProducer[] operatorPublish$InnerProducerArr3 = new OperatorPublish$InnerProducer[length - 1];
                System.arraycopy(operatorPublish$InnerProducerArr, 0, operatorPublish$InnerProducerArr3, 0, i2);
                System.arraycopy(operatorPublish$InnerProducerArr, i2 + 1, operatorPublish$InnerProducerArr3, i2, (length - i2) - 1);
                operatorPublish$InnerProducerArr2 = operatorPublish$InnerProducerArr3;
            }
        } while (!LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.producers, operatorPublish$InnerProducerArr, operatorPublish$InnerProducerArr2));
    }

    boolean checkTerminated(Object obj, boolean z) {
        int i2 = 0;
        if (obj != null) {
            if (!NotificationLite.isCompleted(obj)) {
                Throwable error = NotificationLite.getError(obj);
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.current, this, null);
                OperatorPublish$InnerProducer[] andSet = this.producers.getAndSet(TERMINATED);
                int length = andSet.length;
                while (i2 < length) {
                    andSet[i2].child.onError(error);
                    i2++;
                }
                return true;
            }
            if (z) {
                LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.current, this, null);
                OperatorPublish$InnerProducer[] andSet2 = this.producers.getAndSet(TERMINATED);
                int length2 = andSet2.length;
                while (i2 < length2) {
                    andSet2[i2].child.onCompleted();
                    i2++;
                }
                return true;
            }
        }
        return false;
    }

    void dispatch() {
        boolean z;
        long j2;
        synchronized (this) {
            boolean z2 = true;
            if (this.emitting) {
                this.missed = true;
                return;
            }
            this.emitting = true;
            this.missed = false;
            while (true) {
                try {
                    Object obj = this.terminalEvent;
                    boolean zIsEmpty = this.queue.isEmpty();
                    if (checkTerminated(obj, zIsEmpty)) {
                        return;
                    }
                    if (!zIsEmpty) {
                        OperatorPublish$InnerProducer[] operatorPublish$InnerProducerArr = this.producers.get();
                        int length = operatorPublish$InnerProducerArr.length;
                        long jMin = LocationRequestCompat.PASSIVE_INTERVAL;
                        int i2 = 0;
                        for (OperatorPublish$InnerProducer operatorPublish$InnerProducer : operatorPublish$InnerProducerArr) {
                            long j3 = operatorPublish$InnerProducer.get();
                            if (j3 >= 0) {
                                jMin = Math.min(jMin, j3);
                            } else if (j3 == Long.MIN_VALUE) {
                                i2++;
                            }
                        }
                        if (length != i2) {
                            int i3 = 0;
                            while (true) {
                                j2 = i3;
                                if (j2 >= jMin) {
                                    break;
                                }
                                Object obj2 = this.terminalEvent;
                                Object objPoll = this.queue.poll();
                                boolean z3 = objPoll == null ? z2 : false;
                                if (checkTerminated(obj2, z3)) {
                                    return;
                                }
                                if (z3) {
                                    zIsEmpty = z3;
                                    break;
                                }
                                Object value = NotificationLite.getValue(objPoll);
                                for (OperatorPublish$InnerProducer operatorPublish$InnerProducer2 : operatorPublish$InnerProducerArr) {
                                    if (operatorPublish$InnerProducer2.get() > 0) {
                                        try {
                                            operatorPublish$InnerProducer2.child.onNext(value);
                                            operatorPublish$InnerProducer2.produced(1L);
                                        } catch (Throwable th) {
                                            operatorPublish$InnerProducer2.unsubscribe();
                                            Exceptions.throwOrReport(th, operatorPublish$InnerProducer2.child, value);
                                        }
                                    }
                                }
                                i3++;
                                zIsEmpty = z3;
                                z2 = true;
                            }
                            if (i3 > 0) {
                                request(j2);
                            }
                            if (jMin == 0 || zIsEmpty) {
                            }
                            z2 = true;
                        } else if (checkTerminated(this.terminalEvent, this.queue.poll() == null ? z2 : false)) {
                            return;
                        } else {
                            request(1L);
                        }
                    }
                    synchronized (this) {
                        try {
                            if (!this.missed) {
                                this.emitting = false;
                                try {
                                    return;
                                } catch (Throwable th2) {
                                    th = th2;
                                    z = true;
                                }
                            } else {
                                this.missed = false;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            z = false;
                        }
                        while (true) {
                            try {
                            } catch (Throwable th4) {
                                th = th4;
                            }
                        }
                    }
                    try {
                        throw th;
                    } catch (Throwable th5) {
                        th = th5;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    z = false;
                }
                if (!z) {
                    synchronized (this) {
                        this.emitting = false;
                    }
                }
                throw th;
            }
        }
    }
}
