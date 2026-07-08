package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Producer;


class OperatorElementAt$InnerProducer extends AtomicBoolean implements Producer {
    private static final long serialVersionUID = 1;
    final Producer actual;

    public OperatorElementAt$InnerProducer(Producer producer) {
        this.actual = producer;
    }

    @Override // rx.Producer
    public void request(long j2) {
        if (j2 < 0) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (j2 <= 0 || !compareAndSet(false, true)) {
            return;
        }
        this.actual.request(LocationRequestCompat.PASSIVE_INTERVAL);
    }
}
