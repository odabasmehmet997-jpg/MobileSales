package io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscriber;



final class FlowableFromArrayArraySubscription<T> extends FlowableFromArrayBaseArraySubscription<T> {
    private static final long serialVersionUID = 2587302975077663557L;
    final Subscriber<? super T> downstream;

    FlowableFromArrayArraySubscription(Subscriber<? super T> subscriber, T[] tArr) {
        super(tArr);
        this.downstream = subscriber;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableFromArrayBaseArraySubscription
    void fastPath() {
        T[] tArr = this.array;
        int length = tArr.length;
        Subscriber<? super T> subscriber = this.downstream;
        for (int i2 = this.index; i2 != length; i2++) {
            if (this.cancelled) {
                return;
            }
            T t = tArr[i2];
            if (null == t) {
                subscriber.onError(new NullPointerException("The element at index " + i2 + " is null"));
                return;
            }
            subscriber.onNext(t);
        }
        if (this.cancelled) {
            return;
        }
        subscriber.onComplete();
    }

    void slowPath(long j2) {
        T[] tArr = this.array;
        int length = tArr.length;
        int i2 = this.index;
        Subscriber<? super T> subscriber = this.downstream;
        do {
            long j3 = 0;
            while (true) {
                if (j3 == j2 || i2 == length) {
                    if (i2 == length) {
                        if (this.cancelled) {
                            return;
                        }
                        subscriber.onComplete();
                        return;
                    } else {
                        j2 = this.get();
                        if (j3 == j2) {
                            break;
                        }
                    }
                } else {
                    if (this.cancelled) {
                        return;
                    }
                    T t = tArr[i2];
                    if (null == t) {
                        subscriber.onError(new NullPointerException("The element at index " + i2 + " is null"));
                        return;
                    }
                    subscriber.onNext(t);
                    j3++;
                    i2++;
                }
            }
        } while (0 != j2);
    }
}
