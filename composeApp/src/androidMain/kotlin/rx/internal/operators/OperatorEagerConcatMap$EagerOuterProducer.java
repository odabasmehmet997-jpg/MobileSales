package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;


final class OperatorEagerConcatMap$EagerOuterProducer extends AtomicLong implements Producer {
    private static final long serialVersionUID = -657299606803478389L;
    final OperatorEagerConcatMap$EagerOuterSubscriber<?, ?> parent;

    public OperatorEagerConcatMap$EagerOuterProducer(OperatorEagerConcatMap$EagerOuterSubscriber<?, ?> operatorEagerConcatMap$EagerOuterSubscriber) {
        this.parent = operatorEagerConcatMap$EagerOuterSubscriber;
    }

    @Override // rx.Producer
    public void request(long j2) {
        if (j2 < 0) {
            throw new IllegalStateException("n >= 0 required but it was " + j2);
        }
        if (j2 > 0) {
            BackpressureUtils.getAndAddRequest(this, j2);
            this.parent.drain();
        }
    }
}
