package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;


final class OperatorMerge$MergeProducer<T> extends AtomicLong implements Producer {
    private static final long serialVersionUID = -1214379189873595503L;
    final OperatorMerge$MergeSubscriber<T> subscriber;

    public OperatorMerge$MergeProducer(OperatorMerge$MergeSubscriber<T> operatorMerge$MergeSubscriber) {
        this.subscriber = operatorMerge$MergeSubscriber;
    }

    @Override // rx.Producer
    public void request(long j2) {
        if (j2 <= 0) {
            if (j2 < 0) {
                throw new IllegalArgumentException("n >= 0 required");
            }
        } else {
            if (get() == LocationRequestCompat.PASSIVE_INTERVAL) {
                return;
            }
            BackpressureUtils.getAndAddRequest(this, j2);
            this.subscriber.emit();
        }
    }

    public long produced(int i2) {
        return addAndGet(-i2);
    }
}
