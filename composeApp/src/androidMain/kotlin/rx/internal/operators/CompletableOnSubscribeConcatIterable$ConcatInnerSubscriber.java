package rx.internal.operators;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.internal.subscriptions.SequentialSubscription;


final class CompletableOnSubscribeConcatIterable$ConcatInnerSubscriber extends AtomicInteger implements CompletableSubscriber {
    private static final long serialVersionUID = -7965400327305809232L;
    final CompletableSubscriber actual;
    final SequentialSubscription sd = new SequentialSubscription();
    final Iterator<? extends Completable> sources;

    public CompletableOnSubscribeConcatIterable$ConcatInnerSubscriber(CompletableSubscriber completableSubscriber, Iterator<? extends Completable> it) {
        this.actual = completableSubscriber;
        this.sources = it;
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
            Iterator<? extends Completable> it = this.sources;
            while (!this.sd.isUnsubscribed()) {
                try {
                    if (!it.hasNext()) {
                        this.actual.onCompleted();
                        return;
                    }
                    try {
                        Completable next = it.next();
                        if (next == null) {
                            this.actual.onError(new NullPointerException("The completable returned is null"));
                            return;
                        } else {
                            next.unsafeSubscribe(this);
                            if (decrementAndGet() == 0) {
                                return;
                            }
                        }
                    } catch (Throwable th) {
                        this.actual.onError(th);
                        return;
                    }
                } catch (Throwable th2) {
                    this.actual.onError(th2);
                    return;
                }
            }
        }
    }
}
