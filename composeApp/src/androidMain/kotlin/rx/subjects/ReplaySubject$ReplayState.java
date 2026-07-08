package rx.subjects;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;


final class ReplaySubject$ReplayState<T> extends AtomicReference<ReplaySubject$ReplayProducer<T>[]> implements Observable.OnSubscribe<T>, Observer<T> {
    static final ReplaySubject$ReplayProducer[] EMPTY = new ReplaySubject$ReplayProducer[0];
    static final ReplaySubject$ReplayProducer[] TERMINATED = new ReplaySubject$ReplayProducer[0];
    private static final long serialVersionUID = 5952362471246910544L;
    final ReplaySubject$ReplayBuffer<T> buffer;

    public ReplaySubject$ReplayState(ReplaySubject$ReplayBuffer<T> replaySubject$ReplayBuffer) {
        lazySet(EMPTY);
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> subscriber) {
        ReplaySubject$ReplayProducer<T> replaySubject$ReplayProducer = new ReplaySubject$ReplayProducer<>(subscriber, this);
        subscriber.add(replaySubject$ReplayProducer);
        subscriber.setProducer(replaySubject$ReplayProducer);
        if (add(replaySubject$ReplayProducer) && replaySubject$ReplayProducer.isUnsubscribed()) {
            remove(replaySubject$ReplayProducer);
            return;
        }
        throw null;
    }

    boolean add(ReplaySubject$ReplayProducer<T> replaySubject$ReplayProducer) {
        ReplaySubject$ReplayProducer<T>[] replaySubject$ReplayProducerArr;
        ReplaySubject$ReplayProducer[] replaySubject$ReplayProducerArr2;
        do {
            replaySubject$ReplayProducerArr = get();
            if (replaySubject$ReplayProducerArr == TERMINATED) {
                return false;
            }
            int length = replaySubject$ReplayProducerArr.length;
            replaySubject$ReplayProducerArr2 = new ReplaySubject$ReplayProducer[length + 1];
            System.arraycopy(replaySubject$ReplayProducerArr, 0, replaySubject$ReplayProducerArr2, 0, length);
            replaySubject$ReplayProducerArr2[length] = replaySubject$ReplayProducer;
        } while (!compareAndSet(replaySubject$ReplayProducerArr, replaySubject$ReplayProducerArr2));
        return true;
    }

    void remove(ReplaySubject$ReplayProducer<T> replaySubject$ReplayProducer) {
        ReplaySubject$ReplayProducer<T>[] replaySubject$ReplayProducerArr;
        ReplaySubject$ReplayProducer[] replaySubject$ReplayProducerArr2;
        do {
            replaySubject$ReplayProducerArr = get();
            if (replaySubject$ReplayProducerArr == TERMINATED || replaySubject$ReplayProducerArr == EMPTY) {
                return;
            }
            int length = replaySubject$ReplayProducerArr.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (replaySubject$ReplayProducerArr[i2] == replaySubject$ReplayProducer) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 < 0) {
                return;
            }
            if (length == 1) {
                replaySubject$ReplayProducerArr2 = EMPTY;
            } else {
                ReplaySubject$ReplayProducer[] replaySubject$ReplayProducerArr3 = new ReplaySubject$ReplayProducer[length - 1];
                System.arraycopy(replaySubject$ReplayProducerArr, 0, replaySubject$ReplayProducerArr3, 0, i2);
                System.arraycopy(replaySubject$ReplayProducerArr, i2 + 1, replaySubject$ReplayProducerArr3, i2, (length - i2) - 1);
                replaySubject$ReplayProducerArr2 = replaySubject$ReplayProducerArr3;
            }
        } while (!compareAndSet(replaySubject$ReplayProducerArr, replaySubject$ReplayProducerArr2));
    }

    @Override // rx.Observer
    public void onNext(Object t) {
        throw null;
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        throw null;
    }

    @Override // rx.Observer
    public void onCompleted() {
        throw null;
    }

    boolean isTerminated() {
        return get() == TERMINATED;
    }
}
