package rx.observers;

import rx.Observer;
import rx.exceptions.Exceptions;
import rx.internal.operators.NotificationLite;


public class SerializedObserver<T> implements Observer<T> {
    private final Observer<? super T> actual;
    private boolean emitting;
    private FastList queue;
    private volatile boolean terminated;

    static final class FastList {
        Object[] array;
        int size;

        FastList() {
        }

        public void add(Object obj) {
            int i2 = this.size;
            Object[] objArr = this.array;
            if (objArr == null) {
                objArr = new Object[16];
                this.array = objArr;
            } else if (i2 == objArr.length) {
                Object[] objArr2 = new Object[(i2 >> 2) + i2];
                System.arraycopy(objArr, 0, objArr2, 0, i2);
                this.array = objArr2;
                objArr = objArr2;
            }
            objArr[i2] = obj;
            this.size = i2 + 1;
        }
    }

    public SerializedObserver(Observer<? super T> observer) {
        this.actual = observer;
    }

    /*  WARN: Code restructure failed: missing block: B:65:0x002f, code lost:

        continue;
     */
    @Override // rx.Observer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onNext(Object r7) {
        /*
            r6 = this;
            boolean r0 = r6.terminated
            if (r0 == 0) goto L5
            return
        L5:
            monitor-enter(r6)
            boolean r0 = r6.terminated     // Catch: java.lang.Throwable -> Lc
            if (r0 == 0) goto Le
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lc
            return
        Lc:
            r7 = move-exception
            goto L73
        Le:
            boolean r0 = r6.emitting     // Catch: java.lang.Throwable -> Lc
            if (r0 == 0) goto L26
            rx.observers.SerializedObserver$FastList r0 = r6.queue     // Catch: java.lang.Throwable -> Lc
            if (r0 != 0) goto L1d
            rx.observers.SerializedObserver$FastList r0 = new rx.observers.SerializedObserver$FastList     // Catch: java.lang.Throwable -> Lc
            r0.<init>()     // Catch: java.lang.Throwable -> Lc
            r6.queue = r0     // Catch: java.lang.Throwable -> Lc
        L1d:
            java.lang.Object r7 = rx.internal.operators.NotificationLite.next(r7)     // Catch: java.lang.Throwable -> Lc
            r0.add(r7)     // Catch: java.lang.Throwable -> Lc
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lc
            return
        L26:
            r0 = 1
            r6.emitting = r0     // Catch: java.lang.Throwable -> Lc
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lc
            rx.Observer<? super T> r1 = r6.actual     // Catch: java.lang.Throwable -> L6a
            r1.onNext(r7)     // Catch: java.lang.Throwable -> L6a
        L2f:
            monitor-enter(r6)
            rx.observers.SerializedObserver$FastList r1 = r6.queue     // Catch: java.lang.Throwable -> L39
            r2 = 0
            if (r1 != 0) goto L3b
            r6.emitting = r2     // Catch: java.lang.Throwable -> L39
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L39
            return
        L39:
            r7 = move-exception
            goto L68
        L3b:
            r3 = 0
            r6.queue = r3     // Catch: java.lang.Throwable -> L39
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L39
            java.lang.Object[] r1 = r1.array
            int r3 = r1.length
        L42:
            if (r2 >= r3) goto L2f
            r4 = r1[r2]
            if (r4 != 0) goto L49
            goto L2f
        L49:
            rx.Observer<? super T> r5 = r6.actual     // Catch: java.lang.Throwable -> L54
            boolean r4 = rx.internal.operators.NotificationLite.accept(r5, r4)     // Catch: java.lang.Throwable -> L54
            if (r4 == 0) goto L56
            r6.terminated = r0     // Catch: java.lang.Throwable -> L54
            return
        L54:
            r1 = move-exception
            goto L59
        L56:
            int r2 = r2 + 1
            goto L42
        L59:
            r6.terminated = r0
            rx.exceptions.Exceptions.throwIfFatal(r1)
            rx.Observer<? super T> r6 = r6.actual
            java.lang.Throwable r7 = rx.exceptions.OnErrorThrowable.addValueAsLastCause(r1, r7)
            r6.onError(r7)
            return
        L68:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L39
            throw r7
        L6a:
            r1 = move-exception
            r6.terminated = r0
            rx.Observer<? super T> r6 = r6.actual
            rx.exceptions.Exceptions.throwOrReport(r1, r6, r7)
            return
        L73:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> Lc
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.observers.SerializedObserver.onNext(java.lang.Object):void");
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        Exceptions.throwIfFatal(th);
        if (this.terminated) {
            return;
        }
        synchronized (this) {
            try {
                if (this.terminated) {
                    return;
                }
                this.terminated = true;
                if (this.emitting) {
                    FastList fastList = this.queue;
                    if (fastList == null) {
                        fastList = new FastList();
                        this.queue = fastList;
                    }
                    fastList.add(NotificationLite.error(th));
                    return;
                }
                this.emitting = true;
                this.actual.onError(th);
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.terminated) {
            return;
        }
        synchronized (this) {
            try {
                if (this.terminated) {
                    return;
                }
                this.terminated = true;
                if (this.emitting) {
                    FastList fastList = this.queue;
                    if (fastList == null) {
                        fastList = new FastList();
                        this.queue = fastList;
                    }
                    fastList.add(NotificationLite.completed());
                    return;
                }
                this.emitting = true;
                this.actual.onCompleted();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
