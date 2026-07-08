package io.reactivex.internal.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;



public abstract class SinglePostCompleteSubscriber<T, R> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
    static final long COMPLETE_MASK = Long.MIN_VALUE;
    static final long REQUEST_MASK = Long.MAX_VALUE;
    private static final long serialVersionUID = 7917814472626990048L;
    protected final Subscriber<? super R> downstream;
    protected long produced;
    protected Subscription upstream;
    protected R value;

    public abstract void onComplete();

    protected void onDrop(R r) {
    }

    public abstract void onError(Throwable th);

    public abstract void onNext(Object obj);

    protected SinglePostCompleteSubscriber(Subscriber<? super R> subscriber) {
        this.downstream = subscriber;
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.upstream, subscription)) {
            this.upstream = subscription;
            this.downstream.onSubscribe(this);
        }
    }

    protected final void complete(R r) {
        long j2 = this.produced;
        if (0 != j2) {
            BackpressureHelper.produced(this, j2);
        }
        while (true) {
            long j3 = get ();
            if (0 != (j3 & SinglePostCompleteSubscriber.COMPLETE_MASK)) {
                onDrop (r);
                return;
            }
            if (0 != (j3 & Long.MAX_VALUE)) {
                lazySet (-9223372036854775807L);
                this.downstream.onNext(r);
                this.downstream.onComplete();
                return;
            } else {
                this.value = r;
                if (compareAndSet (0L, COMPLETE_MASK)) {
                    return;
                } else {
                    this.value = null;
                }
            }
        }
    }

    @Override // org.reactivestreams.Subscription
    public final void request(long j2) {
        long j3;
        if (SubscriptionHelper.validate(j2)) {
            do {
                j3 = get ();
                if (0 != (j3 & SinglePostCompleteSubscriber.COMPLETE_MASK)) {
                    if (compareAndSet (COMPLETE_MASK, -9223372036854775807L)) {
                        this.downstream.onNext(this.value);
                        this.downstream.onComplete();
                        return;
                    }
                    return;
                }
            } while (!compareAndSet (j3, BackpressureHelper.addCap(j3, j2)));
            this.upstream.request(j2);
        }
    }

    public void cancel() {
        this.upstream.cancel();
    }
}
