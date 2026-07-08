package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscriber;


final class OnSubscribeCreate$LatestEmitter<T> extends OnSubscribeCreate$BaseEmitter<T> {
    private static final long serialVersionUID = 4023437720691792495L;
    volatile boolean done;
    Throwable error;
    final AtomicReference<Object> queue;
    final AtomicInteger wip;

    public OnSubscribeCreate$LatestEmitter(Subscriber<? super T> subscriber) {
        super(subscriber);
        this.queue = new AtomicReference<>();
        this.wip = new AtomicInteger();
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter, rx.Observer
    public void onNext(T t) {
        this.queue.set(NotificationLite.next(t));
        drain();
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter, rx.Observer
    public void onError(Throwable th) {
        this.error = th;
        this.done = true;
        drain();
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter, rx.Observer
    public void onCompleted() {
        this.done = true;
        drain();
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter
    void onRequested() {
        drain();
    }

    @Override // rx.internal.operators.OnSubscribeCreate$BaseEmitter
    void onUnsubscribed() {
        if (this.wip.getAndIncrement() == 0) {
            this.queue.lazySet(null);
        }
    }

    /*  WARN: Code restructure failed: missing block: B:27:0x0053, code lost:

        if (r9 != r5) goto L42;
     */
    /*  WARN: Code restructure failed: missing block: B:29:0x0059, code lost:

        if (r1.isUnsubscribed() == false) goto L32;
     */
    /*  WARN: Code restructure failed: missing block: B:30:0x005b, code lost:

        r2.lazySet(null);
     */
    /*  WARN: Code restructure failed: missing block: B:31:0x005e, code lost:

        return;
     */
    /*  WARN: Code restructure failed: missing block: B:32:0x005f, code lost:

        r5 = r17.done;
     */
    /*  WARN: Code restructure failed: missing block: B:33:0x0065, code lost:

        if (r2.get() != null) goto L35;
     */
    /*  WARN: Code restructure failed: missing block: B:34:0x0067, code lost:

        r12 = true;
     */
    /*  WARN: Code restructure failed: missing block: B:35:0x0068, code lost:

        if (r5 == false) goto L42;
     */
    /*  WARN: Code restructure failed: missing block: B:36:0x006a, code lost:

        if (r12 == false) goto L42;
     */
    /*  WARN: Code restructure failed: missing block: B:37:0x006c, code lost:

        r1 = r17.error;
     */
    /*  WARN: Code restructure failed: missing block: B:38:0x006e, code lost:

        if (r1 == null) goto L40;
     */
    /*  WARN: Code restructure failed: missing block: B:39:0x0070, code lost:

        super.onError(r1);
     */
    /*  WARN: Code restructure failed: missing block: B:40:0x0074, code lost:

        super.onCompleted();
     */
    /*  WARN: Code restructure failed: missing block: B:41:0x0077, code lost:

        return;
     */
    /*  WARN: Code restructure failed: missing block: B:43:0x007a, code lost:

        if (r9 == 0) goto L45;
     */
    /*  WARN: Code restructure failed: missing block: B:44:0x007c, code lost:

        rx.internal.operators.BackpressureUtils.produced(r17, r9);
     */
    /*  WARN: Code restructure failed: missing block: B:45:0x007f, code lost:

        r4 = r17.wip.addAndGet(-r4);
     */
    /*  WARN: Code restructure failed: missing block: B:57:?, code lost:

        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void drain() {
        /*
            r17 = this;
            r0 = r17
            java.util.concurrent.atomic.AtomicInteger r1 = r0.wip
            int r1 = r1.getAndIncrement()
            if (r1 == 0) goto Lb
            return
        Lb:
            rx.Subscriber<? super T> r1 = r0.actual
            java.util.concurrent.atomic.AtomicReference<java.lang.Object> r2 = r0.queue
            r3 = 1
            r4 = r3
        L11:
            long r5 = r17.get()
            r7 = 0
            r9 = r7
        L18:
            int r11 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            r12 = 0
            r13 = 0
            if (r11 == 0) goto L53
            boolean r14 = r1.isUnsubscribed()
            if (r14 == 0) goto L28
            r2.lazySet(r13)
            return
        L28:
            boolean r14 = r0.done
            java.lang.Object r15 = r2.getAndSet(r13)
            if (r15 != 0) goto L33
            r16 = r3
            goto L35
        L33:
            r16 = r12
        L35:
            if (r14 == 0) goto L45
            if (r16 == 0) goto L45
            java.lang.Throwable r1 = r0.error
            if (r1 == 0) goto L41
            super.onError(r1)
            goto L44
        L41:
            super.onCompleted()
        L44:
            return
        L45:
            if (r16 == 0) goto L48
            goto L53
        L48:
            java.lang.Object r11 = rx.internal.operators.NotificationLite.getValue(r15)
            r1.onNext(r11)
            r11 = 1
            long r9 = r9 + r11
            goto L18
        L53:
            if (r11 != 0) goto L78
            boolean r5 = r1.isUnsubscribed()
            if (r5 == 0) goto L5f
            r2.lazySet(r13)
            return
        L5f:
            boolean r5 = r0.done
            java.lang.Object r6 = r2.get()
            if (r6 != 0) goto L68
            r12 = r3
        L68:
            if (r5 == 0) goto L78
            if (r12 == 0) goto L78
            java.lang.Throwable r1 = r0.error
            if (r1 == 0) goto L74
            super.onError(r1)
            goto L77
        L74:
            super.onCompleted()
        L77:
            return
        L78:
            int r5 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r5 == 0) goto L7f
            rx.internal.operators.BackpressureUtils.produced(r0, r9)
        L7f:
            java.util.concurrent.atomic.AtomicInteger r5 = r0.wip
            int r4 = -r4
            int r4 = r5.addAndGet(r4)
            if (r4 != 0) goto L11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeCreate$LatestEmitter.drain():void");
    }
}
