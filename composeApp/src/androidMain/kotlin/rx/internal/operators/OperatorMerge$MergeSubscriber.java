package rx.internal.operators;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.Pow2;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;


final class OperatorMerge$MergeSubscriber<T> extends Subscriber<Observable<? extends T>> {
    static final OperatorMerge$InnerSubscriber<?>[] EMPTY = new OperatorMerge$InnerSubscriber[0];
    final Subscriber<? super T> child;
    final boolean delayErrors;
    volatile boolean done;
    boolean emitting;
    volatile ConcurrentLinkedQueue<Throwable> errors;
    final Object innerGuard;
    volatile OperatorMerge$InnerSubscriber<?>[] innerSubscribers;
    long lastId;
    int lastIndex;
    final int maxConcurrent;
    boolean missed;
    OperatorMerge$MergeProducer<T> producer;
    volatile Queue<Object> queue;
    int scalarEmissionCount;
    final int scalarEmissionLimit;
    volatile CompositeSubscription subscriptions;
    long uniqueId;

    Queue<Throwable> getOrCreateErrorQueue() {
        ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.errors;
        if (concurrentLinkedQueue == null) {
            synchronized (this) {
                concurrentLinkedQueue = this.errors;
                if (concurrentLinkedQueue == null) {
                    concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
                    this.errors = concurrentLinkedQueue;
                }
            }
        }
        return concurrentLinkedQueue;
    }

    CompositeSubscription getOrCreateComposite() {
        boolean z;
        CompositeSubscription compositeSubscription = this.subscriptions;
        if (compositeSubscription == null) {
            synchronized (this) {
                try {
                    compositeSubscription = this.subscriptions;
                    if (compositeSubscription == null) {
                        compositeSubscription = new CompositeSubscription();
                        this.subscriptions = compositeSubscription;
                        z = true;
                    } else {
                        z = false;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (z) {
                add(compositeSubscription);
            }
        }
        return compositeSubscription;
    }

    /*  WARN: Multi-variable type inference failed */
    @Override // rx.Observer
    public void onNext(Object observable) throws Throwable {
        if (observable == null) {
            return;
        }
        if (observable == Observable.empty()) {
            emitEmpty();
            return;
        }
        if (observable instanceof ScalarSynchronousObservable) {
            tryEmit(((ScalarSynchronousObservable) observable).get());
            return;
        }
        long j2 = this.uniqueId;
        this.uniqueId = 1 + j2;
        OperatorMerge$InnerSubscriber operatorMerge$InnerSubscriber = new OperatorMerge$InnerSubscriber(this, j2);
        addInner(operatorMerge$InnerSubscriber);
        observable.unsafeSubscribe(operatorMerge$InnerSubscriber);
        emit();
    }

    void emitEmpty() {
        int i2 = this.scalarEmissionCount + 1;
        if (i2 == this.scalarEmissionLimit) {
            this.scalarEmissionCount = 0;
            requestMore(i2);
        } else {
            this.scalarEmissionCount = i2;
        }
    }

    private void reportError() {
        ArrayList arrayList = new ArrayList(this.errors);
        if (arrayList.size() == 1) {
            this.child.onError((Throwable) arrayList.get(0));
        } else {
            this.child.onError(new CompositeException(arrayList));
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        getOrCreateErrorQueue().offer(th);
        this.done = true;
        emit();
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.done = true;
        emit();
    }

    /*  WARN: Multi-variable type inference failed */
    void addInner(OperatorMerge$InnerSubscriber<T> operatorMerge$InnerSubscriber) {
        getOrCreateComposite().add(operatorMerge$InnerSubscriber);
        synchronized (this.innerGuard) {
            OperatorMerge$InnerSubscriber<?>[] operatorMerge$InnerSubscriberArr = this.innerSubscribers;
            int length = operatorMerge$InnerSubscriberArr.length;
            OperatorMerge$InnerSubscriber<?>[] operatorMerge$InnerSubscriberArr2 = new OperatorMerge$InnerSubscriber[length + 1];
            System.arraycopy(operatorMerge$InnerSubscriberArr, 0, operatorMerge$InnerSubscriberArr2, 0, length);
            operatorMerge$InnerSubscriberArr2[length] = operatorMerge$InnerSubscriber;
            this.innerSubscribers = operatorMerge$InnerSubscriberArr2;
        }
    }

    void removeInner(OperatorMerge$InnerSubscriber<T> operatorMerge$InnerSubscriber) {
        RxRingBuffer rxRingBuffer = operatorMerge$InnerSubscriber.queue;
        if (rxRingBuffer != null) {
            rxRingBuffer.release();
        }
        this.subscriptions.remove(operatorMerge$InnerSubscriber);
        synchronized (this.innerGuard) {
            OperatorMerge$InnerSubscriber<?>[] operatorMerge$InnerSubscriberArr = this.innerSubscribers;
            int length = operatorMerge$InnerSubscriberArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (!operatorMerge$InnerSubscriber.equals(operatorMerge$InnerSubscriberArr[i2])) {
                    i2++;
                }
            }
            if (i2 < 0) {
                return;
            }
            if (length == 1) {
                this.innerSubscribers = EMPTY;
                return;
            }
            OperatorMerge$InnerSubscriber<?>[] operatorMerge$InnerSubscriberArr2 = new OperatorMerge$InnerSubscriber[length - 1];
            System.arraycopy(operatorMerge$InnerSubscriberArr, 0, operatorMerge$InnerSubscriberArr2, 0, i2);
            System.arraycopy(operatorMerge$InnerSubscriberArr, i2 + 1, operatorMerge$InnerSubscriberArr2, i2, (length - i2) - 1);
            this.innerSubscribers = operatorMerge$InnerSubscriberArr2;
        }
    }

    void tryEmit(OperatorMerge$InnerSubscriber<T> operatorMerge$InnerSubscriber, T t) {
        long j2 = this.producer.get();
        boolean z = false;
        if (j2 != 0) {
            synchronized (this) {
                j2 = this.producer.get();
                if (!this.emitting && j2 != 0) {
                    z = true;
                    this.emitting = true;
                }
            }
        }
        if (z) {
            RxRingBuffer rxRingBuffer = operatorMerge$InnerSubscriber.queue;
            if (rxRingBuffer == null || rxRingBuffer.isEmpty()) {
                emitScalar(operatorMerge$InnerSubscriber, t, j2);
                return;
            } else {
                queueScalar(operatorMerge$InnerSubscriber, t);
                emitLoop();
                return;
            }
        }
        queueScalar(operatorMerge$InnerSubscriber, t);
        emit();
    }

    private void queueScalar(OperatorMerge$InnerSubscriber<T> operatorMerge$InnerSubscriber, T t) {
        RxRingBuffer spscInstance = operatorMerge$InnerSubscriber.queue;
        if (spscInstance == null) {
            spscInstance = RxRingBuffer.getSpscInstance();
            operatorMerge$InnerSubscriber.add(spscInstance);
            operatorMerge$InnerSubscriber.queue = spscInstance;
        }
        try {
            spscInstance.onNext(NotificationLite.next(t));
        } catch (IllegalStateException e2) {
            if (operatorMerge$InnerSubscriber.isUnsubscribed()) {
                return;
            }
            operatorMerge$InnerSubscriber.unsubscribe();
            operatorMerge$InnerSubscriber.onError(e2);
        } catch (MissingBackpressureException e3) {
            operatorMerge$InnerSubscriber.unsubscribe();
            operatorMerge$InnerSubscriber.onError(e3);
        }
    }

    /*  WARN: Removed duplicated region for block: B:35:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void emitScalar(rx.internal.operators.OperatorMerge$InnerSubscriber<T> r5, T r6, long r7) throws java.lang.Throwable {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            rx.Subscriber<? super T> r2 = r4.child     // Catch: java.lang.Throwable -> L8
            r2.onNext(r6)     // Catch: java.lang.Throwable -> L8
            goto L23
        L8:
            r6 = move-exception
            boolean r2 = r4.delayErrors     // Catch: java.lang.Throwable -> L19
            if (r2 != 0) goto L1c
            rx.exceptions.Exceptions.throwIfFatal(r6)     // Catch: java.lang.Throwable -> L19
            r5.unsubscribe()     // Catch: java.lang.Throwable -> L17
            r5.onError(r6)     // Catch: java.lang.Throwable -> L17
            return
        L17:
            r5 = move-exception
            goto L4a
        L19:
            r5 = move-exception
            r0 = r1
            goto L4a
        L1c:
            java.util.Queue r2 = r4.getOrCreateErrorQueue()     // Catch: java.lang.Throwable -> L19
            r2.offer(r6)     // Catch: java.lang.Throwable -> L19
        L23:
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r6 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r6 == 0) goto L31
            rx.internal.operators.OperatorMerge$MergeProducer<T> r6 = r4.producer     // Catch: java.lang.Throwable -> L19
            r6.produced(r0)     // Catch: java.lang.Throwable -> L19
        L31:
            r6 = 1
            r5.requestMore(r6)     // Catch: java.lang.Throwable -> L19
            monitor-enter(r4)     // Catch: java.lang.Throwable -> L19
            boolean r5 = r4.missed     // Catch: java.lang.Throwable -> L3f
            if (r5 != 0) goto L41
            r4.emitting = r1     // Catch: java.lang.Throwable -> L3f
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3f
            return
        L3f:
            r5 = move-exception
            goto L48
        L41:
            r4.missed = r1     // Catch: java.lang.Throwable -> L3f
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3f
            r4.emitLoop()
            return
        L48:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L3f
            throw r5     // Catch: java.lang.Throwable -> L17
        L4a:
            if (r0 != 0) goto L54
            monitor-enter(r4)
            r4.emitting = r1     // Catch: java.lang.Throwable -> L51
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L51
            goto L54
        L51:
            r5 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L51
            throw r5
        L54:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMerge$MergeSubscriber.emitScalar(rx.internal.operators.OperatorMerge$InnerSubscriber, java.lang.Object, long):void");
    }

    public void requestMore(long j2) {
        request(j2);
    }

    void tryEmit(T t) throws Throwable {
        long j2 = this.producer.get();
        boolean z = false;
        if (j2 != 0) {
            synchronized (this) {
                j2 = this.producer.get();
                if (!this.emitting && j2 != 0) {
                    z = true;
                    this.emitting = true;
                }
            }
        }
        if (z) {
            Queue<Object> queue = this.queue;
            if (queue == null || queue.isEmpty()) {
                emitScalar(t, j2);
                return;
            } else {
                queueScalar(t);
                emitLoop();
                return;
            }
        }
        queueScalar(t);
        emit();
    }

    private void queueScalar(T t) {
        Queue<Object> spscExactAtomicArrayQueue;
        Queue<Object> spscUnboundedAtomicArrayQueue = this.queue;
        if (spscUnboundedAtomicArrayQueue == null) {
            int i2 = this.maxConcurrent;
            if (i2 == Integer.MAX_VALUE) {
                spscUnboundedAtomicArrayQueue = new SpscUnboundedAtomicArrayQueue<>(RxRingBuffer.SIZE);
            } else {
                if (Pow2.isPowerOfTwo(i2)) {
                    if (UnsafeAccess.isUnsafeAvailable()) {
                        spscExactAtomicArrayQueue = new SpscArrayQueue<>(i2);
                    } else {
                        spscExactAtomicArrayQueue = new SpscAtomicArrayQueue<>(i2);
                    }
                } else {
                    spscExactAtomicArrayQueue = new SpscExactAtomicArrayQueue<>(i2);
                }
                spscUnboundedAtomicArrayQueue = spscExactAtomicArrayQueue;
            }
            this.queue = spscUnboundedAtomicArrayQueue;
        }
        if (spscUnboundedAtomicArrayQueue.offer(NotificationLite.next(t))) {
            return;
        }
        unsubscribe();
        onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), t));
    }

    /*  WARN: Removed duplicated region for block: B:38:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void emitScalar(T r5, long r6) throws java.lang.Throwable {
        /*
            r4 = this;
            r0 = 1
            r1 = 0
            rx.Subscriber<? super T> r2 = r4.child     // Catch: java.lang.Throwable -> L8
            r2.onNext(r5)     // Catch: java.lang.Throwable -> L8
            goto L23
        L8:
            r5 = move-exception
            boolean r2 = r4.delayErrors     // Catch: java.lang.Throwable -> L19
            if (r2 != 0) goto L1c
            rx.exceptions.Exceptions.throwIfFatal(r5)     // Catch: java.lang.Throwable -> L19
            r4.unsubscribe()     // Catch: java.lang.Throwable -> L17
            r4.onError(r5)     // Catch: java.lang.Throwable -> L17
            return
        L17:
            r5 = move-exception
            goto L55
        L19:
            r5 = move-exception
            r0 = r1
            goto L55
        L1c:
            java.util.Queue r2 = r4.getOrCreateErrorQueue()     // Catch: java.lang.Throwable -> L19
            r2.offer(r5)     // Catch: java.lang.Throwable -> L19
        L23:
            r2 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r5 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r5 == 0) goto L31
            rx.internal.operators.OperatorMerge$MergeProducer<T> r5 = r4.producer     // Catch: java.lang.Throwable -> L19
            r5.produced(r0)     // Catch: java.lang.Throwable -> L19
        L31:
            int r5 = r4.scalarEmissionCount     // Catch: java.lang.Throwable -> L19
            int r5 = r5 + r0
            int r6 = r4.scalarEmissionLimit     // Catch: java.lang.Throwable -> L19
            if (r5 != r6) goto L3f
            r4.scalarEmissionCount = r1     // Catch: java.lang.Throwable -> L19
            long r5 = (long) r5     // Catch: java.lang.Throwable -> L19
            r4.requestMore(r5)     // Catch: java.lang.Throwable -> L19
            goto L41
        L3f:
            r4.scalarEmissionCount = r5     // Catch: java.lang.Throwable -> L19
        L41:
            monitor-enter(r4)     // Catch: java.lang.Throwable -> L19
            boolean r5 = r4.missed     // Catch: java.lang.Throwable -> L4a
            if (r5 != 0) goto L4c
            r4.emitting = r1     // Catch: java.lang.Throwable -> L4a
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L4a
            return
        L4a:
            r5 = move-exception
            goto L53
        L4c:
            r4.missed = r1     // Catch: java.lang.Throwable -> L4a
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L4a
            r4.emitLoop()
            return
        L53:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L4a
            throw r5     // Catch: java.lang.Throwable -> L17
        L55:
            if (r0 != 0) goto L5f
            monitor-enter(r4)
            r4.emitting = r1     // Catch: java.lang.Throwable -> L5c
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L5c
            goto L5f
        L5c:
            r5 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L5c
            throw r5
        L5f:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMerge$MergeSubscriber.emitScalar(java.lang.Object, long):void");
    }

    void emit() {
        synchronized (this) {
            try {
                if (this.emitting) {
                    this.missed = true;
                } else {
                    this.emitting = true;
                    emitLoop();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /*  WARN: Code restructure failed: missing block: B:39:0x007a, code lost:

        if (r6 <= 0) goto L43;
     */
    /*  WARN: Code restructure failed: missing block: B:40:0x007c, code lost:

        if (r10 == false) goto L42;
     */
    /*  WARN: Code restructure failed: missing block: B:41:0x007e, code lost:

        r16 = androidx.core.location.LocationRequestCompat.PASSIVE_INTERVAL;
     */
    /*  WARN: Code restructure failed: missing block: B:42:0x0084, code lost:

        r16 = r23.producer.produced(r6);
     */
    /*  WARN: Code restructure failed: missing block: B:44:0x008e, code lost:

        if (r16 == 0) goto L196;
     */
    /*  WARN: Code restructure failed: missing block: B:45:0x0090, code lost:

        if (r0 != null) goto L47;
     */
    /*  WARN: Removed duplicated region for block: B:159:0x01ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void emitLoop() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 438
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorMerge$MergeSubscriber.emitLoop():void");
    }

    boolean checkTerminate() {
        if (this.child.isUnsubscribed()) {
            return true;
        }
        ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.errors;
        if (this.delayErrors || concurrentLinkedQueue == null || concurrentLinkedQueue.isEmpty()) {
            return false;
        }
        try {
            reportError();
            return true;
        } finally {
            unsubscribe();
        }
    }
}
