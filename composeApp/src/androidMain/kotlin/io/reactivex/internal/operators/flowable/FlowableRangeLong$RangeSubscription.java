package io.reactivex.internal.operators.flowable;

import org.reactivestreams.Subscriber;



final class FlowableRangeLongRangeSubscription extends FlowableRangeLongBaseRangeSubscription {
    private static final long serialVersionUID = 2587302975077663557L;
    final Subscriber<? super Long> downstream;

    FlowableRangeLongRangeSubscription(Subscriber<? super Long> subscriber, long j2, long j3) {
        super(j2, j3);
        this.downstream = subscriber;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableRangeLongBaseRangeSubscription
    void fastPath() {
        long j2 = this.end;
        Subscriber<? super Long> subscriber = this.downstream;
        for (long j3 = this.index; j3 != j2; j3++) {
            if (this.cancelled) {
                return;
            }
            subscriber.onNext(Long.valueOf(j3));
        }
        if (this.cancelled) {
            return;
        }
        subscriber.onComplete();
    }

    void slowPath(long j2) {
        long j3 = this.end;
        long j4 = this.index;
        Subscriber<? super Long> subscriber = this.downstream;
        do {
            long j5 = 0;
            while (true) {
                if (j5 == j2 || j4 == j3) {
                    if (j4 == j3) {
                        if (this.cancelled) {
                            return;
                        }
                        subscriber.onComplete();
                        return;
                    } else {
                        j2 = this.get();
                        if (j5 == j2) {
                            break;
                        }
                    }
                } else {
                    if (this.cancelled) {
                        return;
                    }
                    subscriber.onNext(Long.valueOf(j4));
                    j5++;
                    j4++;
                }
            }
        } while (0 != j2);
    }
}
