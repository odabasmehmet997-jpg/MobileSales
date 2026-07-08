package rx.subjects;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.internal.operators.BackpressureUtils;
import rx.internal.operators.NotificationLite;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.SpscUnboundedArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;


public final class UnicastSubject<T> extends Subject<T, T> {
    final State<T> state;

    public static <T> UnicastSubject<T> create(int i2, Action0 action0) {
        return new UnicastSubject<>(new State(i2, false, action0));
    }

    private UnicastSubject(State<T> state) {
        super(state);
        this.state = state;
    }

    @Override // rx.Observer
    public void onNext(Object t) {
        this.state.onNext(t);
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.state.onError(th);
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.state.onCompleted();
    }

    static final class State<T> extends AtomicLong implements Producer, Observer<T>, Observable.OnSubscribe<T>, Subscription {
        private static final long serialVersionUID = -9044104859202255786L;
        volatile boolean caughtUp;
        final boolean delayError;
        volatile boolean done;
        boolean emitting;
        Throwable error;
        boolean missed;
        final Queue<Object> queue;
        final AtomicReference<Subscriber<? super T>> subscriber = new AtomicReference<>();
        final AtomicReference<Action0> terminateOnce;

        public State(int i2, boolean z, Action0 action0) {
            Queue<Object> spscLinkedQueue;
            this.terminateOnce = action0 != null ? new AtomicReference<>(action0) : null;
            this.delayError = z;
            if (i2 > 1) {
                spscLinkedQueue = UnsafeAccess.isUnsafeAvailable() ? new SpscUnboundedArrayQueue<>(i2) : new SpscUnboundedAtomicArrayQueue<>(i2);
            } else {
                spscLinkedQueue = UnsafeAccess.isUnsafeAvailable() ? new SpscLinkedQueue<>() : new SpscLinkedAtomicQueue<>();
            }
            this.queue = spscLinkedQueue;
        }

        @Override // rx.Observer
        public void onNext(Object t) {
            boolean z;
            if (this.done) {
                return;
            }
            if (!this.caughtUp) {
                synchronized (this) {
                    try {
                        if (this.caughtUp) {
                            z = false;
                        } else {
                            this.queue.offer(NotificationLite.next(t));
                            z = true;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (z) {
                    replay();
                    return;
                }
            }
            Subscriber<? super T> subscriber = this.subscriber.get();
            try {
                subscriber.onNext(t);
            } catch (Throwable th2) {
                Exceptions.throwOrReport(th2, subscriber, t);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            boolean z;
            if (this.done) {
                return;
            }
            doTerminate();
            this.error = th;
            this.done = true;
            if (!this.caughtUp) {
                synchronized (this) {
                    z = this.caughtUp;
                }
                if (!z) {
                    replay();
                    return;
                }
            }
            this.subscriber.get().onError(th);
        }

        @Override // rx.Observer
        public void onCompleted() {
            boolean z;
            if (this.done) {
                return;
            }
            doTerminate();
            this.done = true;
            if (!this.caughtUp) {
                synchronized (this) {
                    z = this.caughtUp;
                }
                if (!z) {
                    replay();
                    return;
                }
            }
            this.subscriber.get().onCompleted();
        }

        @Override // rx.Producer
        public void request(long j2) {
            if (j2 < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
            if (j2 > 0) {
                BackpressureUtils.getAndAddRequest(this, j2);
                replay();
            } else if (this.done) {
                replay();
            }
        }

        @Override // rx.functions.Action1
        public void call(Subscriber<? super T> subscriber) {
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.subscriber, null, subscriber)) {
                subscriber.add(this);
                subscriber.setProducer(this);
            } else {
                subscriber.onError(new IllegalStateException("Only a single subscriber is allowed"));
            }
        }

        /*  WARN: Code restructure failed: missing block: B:48:0x0084, code lost:

            if (r7 == false) goto L54;
         */
        /*  WARN: Code restructure failed: missing block: B:50:0x008a, code lost:

            if (r0.isEmpty() == false) goto L54;
         */
        /*  WARN: Code restructure failed: missing block: B:51:0x008c, code lost:

            r15.caughtUp = true;
         */
        /*  WARN: Code restructure failed: missing block: B:54:0x0091, code lost:

            r15.emitting = false;
         */
        /*  WARN: Code restructure failed: missing block: B:56:0x0094, code lost:

            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void replay() {
            /*
                r15 = this;
                monitor-enter(r15)
                boolean r0 = r15.emitting     // Catch: java.lang.Throwable -> La
                r1 = 1
                if (r0 == 0) goto Ld
                r15.missed = r1     // Catch: java.lang.Throwable -> La
                monitor-exit(r15)     // Catch: java.lang.Throwable -> La
                return
            La:
                r0 = move-exception
                goto L9c
            Ld:
                r15.emitting = r1     // Catch: java.lang.Throwable -> La
                monitor-exit(r15)     // Catch: java.lang.Throwable -> La
                java.util.Queue<java.lang.Object> r0 = r15.queue
                boolean r2 = r15.delayError
            L14:
                java.util.concurrent.atomic.AtomicReference<rx.Subscriber<? super T>> r3 = r15.subscriber
                java.lang.Object r3 = r3.get()
                rx.Subscriber r3 = (rx.Subscriber) r3
                r4 = 0
                if (r3 == 0) goto L7e
                boolean r5 = r15.done
                boolean r6 = r0.isEmpty()
                boolean r5 = r15.checkTerminated(r5, r6, r2, r3)
                if (r5 == 0) goto L2c
                return
            L2c:
                long r5 = r15.get()
                r7 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r7 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r7 != 0) goto L3b
                r7 = r1
                goto L3c
            L3b:
                r7 = r4
            L3c:
                r8 = 0
                r10 = r8
            L3f:
                int r12 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r12 == 0) goto L73
                boolean r12 = r15.done
                java.lang.Object r13 = r0.poll()
                if (r13 != 0) goto L4d
                r14 = r1
                goto L4e
            L4d:
                r14 = r4
            L4e:
                boolean r12 = r15.checkTerminated(r12, r14, r2, r3)
                if (r12 == 0) goto L55
                return
            L55:
                if (r14 == 0) goto L58
                goto L73
            L58:
                java.lang.Object r12 = rx.internal.operators.NotificationLite.getValue(r13)
                r3.onNext(r12)     // Catch: java.lang.Throwable -> L64
                r12 = 1
                long r5 = r5 - r12
                long r10 = r10 + r12
                goto L3f
            L64:
                r15 = move-exception
                r0.clear()
                rx.exceptions.Exceptions.throwIfFatal(r15)
                java.lang.Throwable r15 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r15, r12)
                r3.onError(r15)
                return
            L73:
                if (r7 != 0) goto L7f
                int r3 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                if (r3 == 0) goto L7f
                long r5 = -r10
                r15.addAndGet(r5)
                goto L7f
            L7e:
                r7 = r4
            L7f:
                monitor-enter(r15)
                boolean r3 = r15.missed     // Catch: java.lang.Throwable -> L8f
                if (r3 != 0) goto L95
                if (r7 == 0) goto L91
                boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L8f
                if (r0 == 0) goto L91
                r15.caughtUp = r1     // Catch: java.lang.Throwable -> L8f
                goto L91
            L8f:
                r0 = move-exception
                goto L9a
            L91:
                r15.emitting = r4     // Catch: java.lang.Throwable -> L8f
                monitor-exit(r15)     // Catch: java.lang.Throwable -> L8f
                return
            L95:
                r15.missed = r4     // Catch: java.lang.Throwable -> L8f
                monitor-exit(r15)     // Catch: java.lang.Throwable -> L8f
                goto L14
            L9a:
                monitor-exit(r15)     // Catch: java.lang.Throwable -> L8f
                throw r0
            L9c:
                monitor-exit(r15)     // Catch: java.lang.Throwable -> La
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.UnicastSubject.State.replay():void");
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            doTerminate();
            this.done = true;
            synchronized (this) {
                try {
                    if (this.emitting) {
                        return;
                    }
                    this.emitting = true;
                    this.queue.clear();
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.done;
        }

        boolean checkTerminated(boolean z, boolean z2, boolean z3, Subscriber<? super T> subscriber) {
            if (subscriber.isUnsubscribed()) {
                this.queue.clear();
                return true;
            }
            if (!z) {
                return false;
            }
            Throwable th = this.error;
            if (th != null && !z3) {
                this.queue.clear();
                subscriber.onError(th);
                return true;
            }
            if (!z2) {
                return false;
            }
            if (th != null) {
                subscriber.onError(th);
            } else {
                subscriber.onCompleted();
            }
            return true;
        }

        void doTerminate() {
            Action0 action0;
            AtomicReference<Action0> atomicReference = this.terminateOnce;
            if (atomicReference == null || (action0 = atomicReference.get()) == null || !LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, action0, null)) {
                return;
            }
            action0.call();
        }
    }
}
