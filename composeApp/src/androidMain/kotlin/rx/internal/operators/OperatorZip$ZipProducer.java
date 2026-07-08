package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;


final class OperatorZip$ZipProducer<R> extends AtomicLong implements Producer {
    private static final long serialVersionUID = -1216676403723546796L;
    final OperatorZip$Zip<R> zipper;

    public OperatorZip$ZipProducer(OperatorZip$Zip<R> operatorZip$Zip) {
        this.zipper = operatorZip$Zip;
    }

    @Override // rx.Producer
    public void request(long j2) {
        BackpressureUtils.getAndAddRequest(this, j2);
        this.zipper.tick();
    }
}
