package io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscriber;



final class FlowableRangeRangeSubscription extends FlowableRangeBaseRangeSubscription {
    private static final long serialVersionUID = 2587302975077663557L;
    final Subscriber<? super Integer> downstream;

    FlowableRangeRangeSubscription(Subscriber<? super Integer> subscriber, int i2, int i3) {
        super(i2, i3);
        this.downstream = subscriber;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableRangeBaseRangeSubscription
    void fastPath() {
        int i2 = this.end;
        Subscriber<? super Integer> subscriber = this.downstream;
        for (int i3 = this.index; i3 != i2; i3++) {
            if (this.cancelled) {
                return;
            }
            subscriber.onNext(Integer.valueOf(i3));
        }
        if (this.cancelled) {
            return;
        }
        subscriber.onComplete();
    }

    void slowPath(long j2) {
        int i2 = this.end;
        int i3 = this.index;
        Subscriber<? super Integer> subscriber = this.downstream;
        do {
            long j3 = 0;
            while (true) {
                if (j3 == j2 || i3 == i2) {
                    if (i3 == i2) {
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
                    subscriber.onNext(Integer.valueOf(i3));
                    j3++;
                    i3++;
                }
            }
        } while (0 != j2);
    }
}
