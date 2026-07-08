package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.fuseable.ConditionalSubscriber;



final class FlowableRangeLongRangeConditionalSubscription extends FlowableRangeLongBaseRangeSubscription {
    private static final long serialVersionUID = 2587302975077663557L;
    final ConditionalSubscriber<? super Long> downstream;

    FlowableRangeLongRangeConditionalSubscription(ConditionalSubscriber<? super Long> conditionalSubscriber, long j2, long j3) {
        super(j2, j3);
        this.downstream = conditionalSubscriber;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableRangeLongBaseRangeSubscription
    void fastPath() {
        long j2 = this.end;
        ConditionalSubscriber<? super Long> conditionalSubscriber = this.downstream;
        for (long j3 = this.index; j3 != j2; j3++) {
            if (this.cancelled) {
                return;
            }
            conditionalSubscriber.tryOnNext(Long.valueOf(j3));
        }
        if (this.cancelled) {
            return;
        }
        conditionalSubscriber.onComplete();
    }

    void slowPath(long j2) {
        long j3 = this.end;
        long j4 = this.index;
        ConditionalSubscriber<? super Long> conditionalSubscriber = this.downstream;
        do {
            long j5 = 0;
            while (true) {
                if (j5 == j2 || j4 == j3) {
                    if (j4 == j3) {
                        if (this.cancelled) {
                            return;
                        }
                        conditionalSubscriber.onComplete();
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
                    if (conditionalSubscriber.tryOnNext(Long.valueOf(j4))) {
                        j5++;
                    }
                    j4++;
                }
            }
        } while (0 != j2);
    }
}
