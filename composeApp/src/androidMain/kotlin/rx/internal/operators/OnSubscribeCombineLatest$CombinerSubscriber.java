package rx.internal.operators;

import rx.Subscriber;
import rx.plugins.RxJavaHooks;


final class OnSubscribeCombineLatest$CombinerSubscriber<T, R> extends Subscriber<T> {
    boolean done;
    final int index;
    final OnSubscribeCombineLatest$LatestCoordinator<T, R> parent;

    public OnSubscribeCombineLatest$CombinerSubscriber(OnSubscribeCombineLatest$LatestCoordinator<T, R> onSubscribeCombineLatest$LatestCoordinator, int i2) {
        this.parent = onSubscribeCombineLatest$LatestCoordinator;
        this.index = i2;
        request(onSubscribeCombineLatest$LatestCoordinator.bufferSize);
    }

    @Override // rx.Observer
    public void onNext(Object t) {
        if (this.done) {
            return;
        }
        this.parent.combine(NotificationLite.next(t), this.index);
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaHooks.onError(th);
            return;
        }
        this.parent.onError(th);
        this.done = true;
        this.parent.combine(null, this.index);
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.parent.combine(null, this.index);
    }

    public void requestMore(long j2) {
        request(j2);
    }
}
