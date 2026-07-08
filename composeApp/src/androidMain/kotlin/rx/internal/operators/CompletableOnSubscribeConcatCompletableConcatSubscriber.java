package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.subscriptions.SequentialSubscription;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.plugins.RxJavaHooks;
 
final class CompletableOnSubscribeConcatCompletableConcatSubscriber extends Subscriber<Completable> {
    volatile boolean active;
    final CompletableSubscriber actual = null;
    volatile boolean done;
    final ConcatInnerSubscriber inner = null;
    final AtomicBoolean once = null;
    final SpscArrayQueue<Completable> queue = null;
    final SequentialSubscription sr = null; 
    public void onNext(Object completable) {
        if (!this.queue.offer((Completable) completable)) {
            onError(new MissingBackpressureException());
        } else {
            drain();
        }
    } 
    public void onError(Throwable th) {
        if (this.once.compareAndSet(false, true)) {
            this.actual.onError(th);
        } else {
            RxJavaHooks.onError(th);
        }
    } 
    public void onCompleted() {
        if (this.done) {
            return;
        }
        this.done = true;
        drain();
    }
    void innerError(Throwable th) {
        unsubscribe();
        onError(th);
    }
    void innerComplete() {
        this.active = false;
        drain();
    }
    void drain() {
        ConcatInnerSubscriber concatInnerSubscriber = this.inner;
        if (concatInnerSubscriber.getAndIncrement() != 0) {
            return;
        }
        while (!isUnsubscribed()) {
            if (!this.active) {
                boolean z = this.done;
                Completable completablePoll = this.queue.poll();
                boolean z2 = completablePoll == null;
                if (z && z2) {
                    this.actual.onCompleted();
                    return;
                } else if (!z2) {
                    this.active = true;
                    completablePoll.subscribe(concatInnerSubscriber);
                    request(1L);
                }
            }
            if (concatInnerSubscriber.decrementAndGet() == 0) {
                return;
            }
        }
    }
    final class ConcatInnerSubscriber extends AtomicInteger implements CompletableSubscriber {
        private static final long serialVersionUID = 7233503139645205620L;

        ConcatInnerSubscriber() {
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            CompletableOnSubscribeConcatCompletableConcatSubscriber.this.sr.set(subscription);
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            CompletableOnSubscribeConcatCompletableConcatSubscriber.this.innerError(th);
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            CompletableOnSubscribeConcatCompletableConcatSubscriber.this.innerComplete();
        }
    }
}
