package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;


final class OnSubscribeFromIterable$IterableProducer<T> extends AtomicLong implements Producer {
    private static final long serialVersionUID = -8730475647105475802L;
    private final Iterator<? extends T> it;
    private final Subscriber<? super T> o;

    OnSubscribeFromIterable$IterableProducer(Subscriber<? super T> subscriber, Iterator<? extends T> it) {
        this.o = subscriber;
        this.it = it;
    }

    @Override // rx.Producer
    public void request(long j2) {
        if (get() == LocationRequestCompat.PASSIVE_INTERVAL) {
            return;
        }
        if (j2 == LocationRequestCompat.PASSIVE_INTERVAL && compareAndSet(0L, LocationRequestCompat.PASSIVE_INTERVAL)) {
            fastPath();
        } else {
            if (j2 <= 0 || BackpressureUtils.getAndAddRequest(this, j2) != 0) {
                return;
            }
            slowPath(j2);
        }
    }

    /*  WARN: Code restructure failed: missing block: B:28:0x0046, code lost:

        r9 = rx.internal.operators.BackpressureUtils.produced(r8, r4);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void slowPath(long r9) {
        /*
            r8 = this;
            rx.Subscriber<? super T> r0 = r8.o
            java.util.Iterator<? extends T> r1 = r8.it
            r2 = 0
        L6:
            r4 = r2
        L7:
            int r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r6 == 0) goto L3e
            boolean r6 = r0.isUnsubscribed()
            if (r6 == 0) goto L12
            return
        L12:
            java.lang.Object r6 = r1.next()     // Catch: java.lang.Throwable -> L39
            r0.onNext(r6)
            boolean r6 = r0.isUnsubscribed()
            if (r6 == 0) goto L20
            return
        L20:
            boolean r6 = r1.hasNext()     // Catch: java.lang.Throwable -> L34
            if (r6 != 0) goto L30
            boolean r8 = r0.isUnsubscribed()
            if (r8 != 0) goto L2f
            r0.onCompleted()
        L2f:
            return
        L30:
            r6 = 1
            long r4 = r4 + r6
            goto L7
        L34:
            r8 = move-exception
            rx.exceptions.Exceptions.throwOrReport(r8, r0)
            return
        L39:
            r8 = move-exception
            rx.exceptions.Exceptions.throwOrReport(r8, r0)
            return
        L3e:
            long r9 = r8.get()
            int r6 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r6 != 0) goto L7
            long r9 = rx.internal.operators.BackpressureUtils.produced(r8, r4)
            int r4 = (r9 > r2 ? 1 : (r9 == r2 ? 0 : -1))
            if (r4 != 0) goto L6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OnSubscribeFromIterable$IterableProducer.slowPath(long):void");
    }

    void fastPath() {
        Subscriber<? super T> subscriber = this.o;
        Iterator<? extends T> it = this.it;
        while (!subscriber.isUnsubscribed()) {
            try {
                subscriber.onNext(it.next());
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                try {
                    if (!it.hasNext()) {
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        subscriber.onCompleted();
                        return;
                    }
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, subscriber);
                    return;
                }
            } catch (Throwable th2) {
                Exceptions.throwOrReport(th2, subscriber);
                return;
            }
        }
    }
}
