package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;


final class OperatorPublish$InnerProducer<T> extends AtomicLong implements Producer, Subscription {
    static final long NOT_REQUESTED = -4611686018427387904L;
    static final long UNSUBSCRIBED = Long.MIN_VALUE;
    private static final long serialVersionUID = -4453897557930727610L;
    final Subscriber<? super T> child;
    final OperatorPublish$PublishSubscriber<T> parent;

    public OperatorPublish$InnerProducer(OperatorPublish$PublishSubscriber<T> operatorPublish$PublishSubscriber, Subscriber<? super T> subscriber) {
        this.parent = operatorPublish$PublishSubscriber;
        this.child = subscriber;
        lazySet(NOT_REQUESTED);
    }

    @Override // rx.Producer
    public void request(long j2) {
        long j3;
        long j4;
        if (j2 < 0) {
            return;
        }
        do {
            j3 = get();
            if (j3 == UNSUBSCRIBED) {
                return;
            }
            if (j3 >= 0 && j2 == 0) {
                return;
            }
            if (j3 == NOT_REQUESTED) {
                j4 = j2;
            } else {
                j4 = j3 + j2;
                if (j4 < 0) {
                    j4 = LocationRequestCompat.PASSIVE_INTERVAL;
                }
            }
        } while (!compareAndSet(j3, j4));
        this.parent.dispatch();
    }

    public long produced(long j2) {
        long j3;
        long j4;
        if (j2 <= 0) {
            throw new IllegalArgumentException("Cant produce zero or less");
        }
        do {
            j3 = get();
            if (j3 == NOT_REQUESTED) {
                throw new IllegalStateException("Produced without request");
            }
            if (j3 == UNSUBSCRIBED) {
                return UNSUBSCRIBED;
            }
            j4 = j3 - j2;
            if (j4 < 0) {
                throw new IllegalStateException("More produced (" + j2 + ") than requested (" + j3 + ")");
            }
        } while (!compareAndSet(j3, j4));
        return j4;
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return get() == UNSUBSCRIBED;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        if (get() == UNSUBSCRIBED || getAndSet(UNSUBSCRIBED) == UNSUBSCRIBED) {
            return;
        }
        this.parent.remove(this);
        this.parent.dispatch();
    }
}
