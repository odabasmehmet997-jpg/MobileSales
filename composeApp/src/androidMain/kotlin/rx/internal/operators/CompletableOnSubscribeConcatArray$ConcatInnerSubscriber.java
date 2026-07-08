package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.internal.subscriptions.SequentialSubscription;


final class CompletableOnSubscribeConcatArray$ConcatInnerSubscriber extends AtomicInteger implements CompletableSubscriber {
    private static final long serialVersionUID = -7965400327305809232L;
    final CompletableSubscriber actual;
    int index;
    final SequentialSubscription sd = new SequentialSubscription();
    final Completable[] sources;

    public CompletableOnSubscribeConcatArray$ConcatInnerSubscriber(CompletableSubscriber completableSubscriber, Completable[] completableArr) {
        this.actual = completableSubscriber;
        this.sources = completableArr;
    }

    @Override // rx.CompletableSubscriber
    public void onSubscribe(Subscription subscription) {
        this.sd.replace(subscription);
    }

    @Override // rx.CompletableSubscriber
    public void onError(Throwable th) {
        this.actual.onError(th);
    }

    @Override // rx.CompletableSubscriber
    public void onCompleted() {
        next();
    }

    void next() {
        if (!this.sd.isUnsubscribed() && getAndIncrement() == 0) {
            Completable[] completableArr = this.sources;
            while (!this.sd.isUnsubscribed()) {
                int i2 = this.index;
                this.index = i2 + 1;
                if (i2 == completableArr.length) {
                    this.actual.onCompleted();
                    return;
                } else {
                    completableArr[i2].unsafeSubscribe(this);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }
    }
}
