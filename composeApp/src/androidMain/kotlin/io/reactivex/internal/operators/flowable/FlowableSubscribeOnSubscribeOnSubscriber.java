package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;



final class FlowableSubscribeOnSubscribeOnSubscriber<T> extends AtomicReference<Thread> implements FlowableSubscriber<T>, Subscription, Runnable {
    private static final long serialVersionUID = 8094547886072529208L;
    final Subscriber<? super T> downstream;
    final boolean nonScheduledRequests;
    Publisher<T> source;
    final Scheduler.Worker worker;
    final AtomicReference<Subscription> upstream = new AtomicReference<>();
    final AtomicLong requested = new AtomicLong();

    FlowableSubscribeOnSubscribeOnSubscriber(Subscriber<? super T> subscriber, Scheduler.Worker worker, Publisher<T> publisher, boolean z) {
        this.downstream = subscriber;
        this.worker = worker;
        this.source = publisher;
        this.nonScheduledRequests = !z;
    }
    public void run() {
        lazySet(Thread.currentThread());
        Publisher<T> publisher = this.source;
        this.source = null;
        publisher.subscribe(this);
    }
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this.upstream, subscription)) {
            long andSet = this.requested.getAndSet(0L);
            if (0 != andSet) {
                requestUpstream(andSet, subscription);
            }
        }
    }
    public void onNext(Object t) {
        this.downstream.onNext(t);
    }

    class
    public void onError(Throwable th) {
        this.downstream.onError(th);
        this.worker.dispose();
    }

    class
    public void onComplete() {
        this.downstream.onComplete();
        this.worker.dispose();
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j2) {
        if (SubscriptionHelper.validate(j2)) {
            Subscription subscription = this.upstream.get();
            if (null != subscription) {
                requestUpstream(j2, subscription);
                return;
            }
            BackpressureHelper.add(this.requested, j2);
            Subscription subscription2 = this.upstream.get();
            if (null != subscription2) {
                long andSet = this.requested.getAndSet(0L);
                if (0 != andSet) {
                    requestUpstream(andSet, subscription2);
                }
            }
        }
    }

    void requestUpstream(long j2, Subscription subscription) {
        if (this.nonScheduledRequests || Thread.currentThread() == get()) {
            subscription.request(j2);
        } else {
            this.worker.schedule(new Request(subscription, j2));
        }
    }
 
    public void cancel() {
        SubscriptionHelper.cancel(this.upstream);
        this.worker.dispose();
    }

    class Request(Subscription upstream, long n) implements Runnable {
 
            public void run() {
            this.upstream.request(this.n);
            }
        }
}
