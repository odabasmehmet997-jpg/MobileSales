package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.fuseable.ConditionalSubscriber;



final class FlowableFromArrayArrayConditionalSubscription<T> extends FlowableFromArrayBaseArraySubscription<T> {
    private static final long serialVersionUID = 2587302975077663557L;
    final ConditionalSubscriber<? super T> downstream;

    FlowableFromArrayArrayConditionalSubscription(ConditionalSubscriber<? super T> conditionalSubscriber, T[] tArr) {
        super(tArr);
        this.downstream = conditionalSubscriber;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableFromArrayBaseArraySubscription
    void fastPath() {
        T[] tArr = this.array;
        int length = tArr.length;
        ConditionalSubscriber<? super T> conditionalSubscriber = this.downstream;
        for (int i2 = this.index; i2 != length; i2++) {
            if (this.cancelled) {
                return;
            }
            T t = tArr[i2];
            if (null == t) {
                conditionalSubscriber.onError(new NullPointerException("The element at index " + i2 + " is null"));
                return;
            }
            conditionalSubscriber.tryOnNext(t);
        }
        if (this.cancelled) {
            return;
        }
        conditionalSubscriber.onComplete();
    }

    void slowPath(long j2) {
        T[] tArr = this.array;
        int length = tArr.length;
        int i2 = this.index;
        ConditionalSubscriber<? super T> conditionalSubscriber = this.downstream;
        do {
            long j3 = 0;
            while (true) {
                if (j3 == j2 || i2 == length) {
                    if (i2 == length) {
                        if (this.cancelled) {
                            return;
                        }
                        conditionalSubscriber.onComplete();
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
                        conditionalSubscriber.onError(new NullPointerException("The element at index " + i2 + " is null"));
                        return;
                    }
                    if (conditionalSubscriber.tryOnNext(t)) {
                        j3++;
                    }
                    i2++;
                }
            }
        } while (0 != j2);
    }
}
