package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.MissingBackpressureException;
import rx.functions.FuncN;
import rx.internal.util.RxRingBuffer;
import rx.subscriptions.CompositeSubscription;


final class OperatorZip$Zip<R> extends AtomicLong {
    static final int THRESHOLD = (int) (((double) RxRingBuffer.SIZE) * 0.7d);
    private static final long serialVersionUID = 5995274816189928317L;
    final Observer<? super R> child;
    private final CompositeSubscription childSubscription;
    int emitted;
    private AtomicLong requested;
    private volatile Object[] subscribers;
    private final FuncN<? extends R> zipFunction;

    public OperatorZip$Zip(Subscriber<? super R> subscriber, FuncN<? extends R> funcN) {
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        this.childSubscription = compositeSubscription;
        this.child = subscriber;
        subscriber.add(compositeSubscription);
    }

    public void start(Observable[] observableArr, AtomicLong atomicLong) {
        Object[] objArr = new Object[observableArr.length];
        for (int i2 = 0; i2 < observableArr.length; i2++) {
            InnerSubscriber innerSubscriber = new InnerSubscriber();
            objArr[i2] = innerSubscriber;
            this.childSubscription.add(innerSubscriber);
        }
        this.requested = atomicLong;
        this.subscribers = objArr;
        for (int i3 = 0; i3 < observableArr.length; i3++) {
            observableArr[i3].unsafeSubscribe((InnerSubscriber) objArr[i3]);
        }
    }

    void tick() {
        Object[] objArr = this.subscribers;
        if (objArr != null && getAndIncrement() == 0) {
            int length = objArr.length;
            Observer<? super R> observer = this.child;
            AtomicLong atomicLong = this.requested;
            do {
                Object[] objArr2 = new Object[length];
                boolean z = true;
                for (int i2 = 0; i2 < length; i2++) {
                    RxRingBuffer rxRingBuffer = ((InnerSubscriber) objArr[i2]).items;
                    Object objPeek = rxRingBuffer.peek();
                    if (objPeek == null) {
                        z = false;
                    } else {
                        if (rxRingBuffer.isCompleted(objPeek)) {
                            observer.onCompleted();
                            this.childSubscription.unsubscribe();
                            return;
                        }
                        objArr2[i2] = rxRingBuffer.getValue(objPeek);
                    }
                }
                if (z && atomicLong.get() > 0) {
                    try {
                        throw null;
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, observer, objArr2);
                        return;
                    }
                }
            } while (decrementAndGet() > 0);
        }
    }

    final class InnerSubscriber extends Subscriber {
        final RxRingBuffer items = RxRingBuffer.getSpmcInstance();

        InnerSubscriber() {
        }

        @Override // rx.Subscriber
        public void onStart() {
            request(RxRingBuffer.SIZE);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.items.onCompleted();
            OperatorZip$Zip.this.tick();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            OperatorZip$Zip.this.child.onError(th);
        }

        @Override // rx.Observer
        public void onNext(Object obj) {
            try {
                this.items.onNext(obj);
            } catch (MissingBackpressureException e2) {
                onError(e2);
            }
            OperatorZip$Zip.this.tick();
        }
    }
}
