package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class FlowableConcatArrayConcatArraySubscriber<T> extends SubscriptionArbiter implements FlowableSubscriber<T> {
    private static final long serialVersionUID = -8158322871608889516L;
    final boolean delayError;
    final Subscriber<? super T> downstream;
    List<Throwable> errors;
    int index;
    long produced;
    final Publisher<? extends T>[] sources;
    final AtomicInteger wip;
    FlowableConcatArrayConcatArraySubscriber(Publisher<? extends T>[] publisherArr, boolean z, Subscriber<? super T> subscriber) {
        super(false);
        this.downstream = subscriber;
        this.sources = publisherArr;
        this.delayError = z;
        this.wip = new AtomicInteger();
    }
    public void onSubscribe(Subscription subscription) {
        this.setSubscription(subscription);
    }
    public void onNext(Object t) {
        this.produced++;
        this.downstream.onNext(t);
    }
    public void onError(Throwable th) {
        if (this.delayError) {
            List<Throwable> list = this.errors;
            if (null == list) {
                list = new ArrayList((this.sources.length - this.index) + 1);
                this.errors = list;
            }
            list.add(th);
            onComplete();
            return;
        }
        this.downstream.onError(th);
    }
    public void onComplete() {
        if (0 == wip.getAndIncrement ()) {
            Publisher<? extends T>[] publisherArr = this.sources;
            int length = publisherArr.length;
            int i2 = this.index;
            while (i2 != length) {
                Publisher<? extends T> publisher = publisherArr[i2];
                if (null == publisher) {
                    NullPointerException nullPointerException = new NullPointerException("A Publisher entry is null");
                    if (this.delayError) {
                        List<Throwable> list = this.errors;
                        if (null == list) {
                            list = new ArrayList<Throwable>((length - i2) + 1);
                            this.errors = list;
                        }
                        list.add(nullPointerException);
                        i2++;
                    } else {
                        this.downstream.onError(nullPointerException);
                        return;
                    }
                } else {
                    long j2 = this.produced;
                    if (0 != j2) {
                        this.produced = 0L;
                        this.produced(j2);
                    }
                    publisher.subscribe(this);
                    i2++;
                    this.index = i2;
                    if (0 == wip.decrementAndGet ()) {
                        return;
                    }
                }
            }
            List<Throwable> list2 = this.errors;
            if (null != list2) {
                if (1 == list2.size ()) {
                    this.downstream.onError(list2.get(0));
                    return;
                } else {
                    this.downstream.onError(new CompositeException(list2));
                    return;
                }
            }
            this.downstream.onComplete();
        }
    }
}
