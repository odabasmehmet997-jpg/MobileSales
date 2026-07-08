package rx.subjects;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.internal.operators.BackpressureUtils;
final class ReplaySubject$ReplayProducer<T> extends AtomicInteger implements Producer, Subscription {
    private static final long serialVersionUID = -5006209596735204567L;
    final Subscriber<? super T> actual;
    int index;
    Object node;
    final AtomicLong requested = new AtomicLong();
    final ReplaySubject$ReplayState<T> state;
    int tailIndex;
    public ReplaySubject$ReplayProducer(Subscriber<? super T> subscriber, ReplaySubject$ReplayState<T> replaySubject$ReplayState) {
        this.actual = subscriber;
        this.state = replaySubject$ReplayState;
    }
    public void unsubscribe() {
        this.state.remove(this);
    }
    public boolean isUnsubscribed() {
        return this.actual.isUnsubscribed();
    }
    public void request(long j2) {
        if (j2 > 0) {
            BackpressureUtils.getAndAddRequest(this.requested, j2);
            this.state.getClass();
            throw null;
        }
        if (j2 >= 0) {
            return;
        }
        throw new IllegalArgumentException("n >= required but it was " + j2);
    }
}
