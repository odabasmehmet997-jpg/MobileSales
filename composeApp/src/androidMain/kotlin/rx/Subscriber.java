package rx;

import androidx.core.location.LocationRequestCompat;
import rx.internal.util.SubscriptionList;

public abstract class Subscriber<T> implements Observer<T>, Subscription {
    private static final long NOT_SET = Long.MIN_VALUE;
    private Producer producer;
    private long requested;
    private final Subscriber<?> subscriber;
    private final SubscriptionList subscriptions;
    public void onStart() {
    }
    protected Subscriber() {
        this(null, false);
    }
    protected Subscriber(Subscriber<?> subscriber) {
        this(subscriber, true);
    }
    protected Subscriber(Subscriber<?> subscriber, boolean z) {
        this.requested = NOT_SET;
        this.subscriber = subscriber;
        this.subscriptions = (!z || subscriber == null) ? new SubscriptionList() : subscriber.subscriptions;
    }
    public final void add(Subscription subscription) {
        this.subscriptions.add(subscription);
    }
    public final void unsubscribe() {
        this.subscriptions.unsubscribe();
    }
    public final boolean isUnsubscribed() {
        return this.subscriptions.isUnsubscribed();
    }
    protected final void request(long j2) {
        if (j2 < 0) {
            throw new IllegalArgumentException("number requested cannot be negative: " + j2);
        }
        synchronized (this) {
            Producer producer = this.producer;
            if (producer == null) {
                addToRequested(j2);
            } else {
                producer.request(j2);
            }
        }
    }
    private void addToRequested(long j2) {
        long j3 = this.requested;
        if (j3 == NOT_SET) {
            this.requested = j2;
            return;
        }
        long j4 = j3 + j2;
        if (j4 < 0) {
            this.requested = LocationRequestCompat.PASSIVE_INTERVAL;
        } else {
            this.requested = j4;
        }
    }
    public void setProducer(Producer producer) {
        long j2;
        Subscriber<?> subscriber;
        boolean z;
        synchronized (this) {
            j2 = this.requested;
            this.producer = producer;
            subscriber = this.subscriber;
            z = subscriber != null && j2 == NOT_SET;
        }
        if (z) {
            subscriber.setProducer(producer);
        } else if (j2 == NOT_SET) {
            producer.request(LocationRequestCompat.PASSIVE_INTERVAL);
        } else {
            producer.request(j2);
        }
    }
}
