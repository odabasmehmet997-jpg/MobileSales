package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.fuseable.ConditionalSubscriber;



final class FlowableRangeRangeConditionalSubscription extends FlowableRangeBaseRangeSubscription {
    private static final long serialVersionUID = 2587302975077663557L;
    final ConditionalSubscriber<? super Integer> downstream;

    FlowableRangeRangeConditionalSubscription(ConditionalSubscriber<? super Integer> conditionalSubscriber, int i2, int i3) {
        super(i2, i3);
        this.downstream = conditionalSubscriber;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowableRangeBaseRangeSubscription
    void fastPath() {
        int i2 = this.end;
        ConditionalSubscriber<? super Integer> conditionalSubscriber = this.downstream;
        for (int i3 = this.index; i3 != i2; i3++) {
            if (this.cancelled) {
                return;
            }
            conditionalSubscriber.tryOnNext(Integer.valueOf(i3));
        }
        if (this.cancelled) {
            return;
        }
        conditionalSubscriber.onComplete();
    } ath(long j2) {
        int i2 = this.end;
        int i3 = this.index;
        ConditionalSubscriber<? super Integer> conditionalSubscriber = this.downstream;
        do {
            long j3 = 0;
            while (true) {
                if (j3 == j2 || i3 == i2) {
                    if (i3 == i2) {
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
                    if (conditionalSubscriber.tryOnNext(Integer.valueOf(i3))) {
                        j3++;
                    }
                    i3++;
                }
            }
        } while (0 != j2);
    }
}
