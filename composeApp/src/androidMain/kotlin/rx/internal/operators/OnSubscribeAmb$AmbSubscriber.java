package rx.internal.operators;

import rx.Subscriber;


final class OnSubscribeAmb$AmbSubscriber<T> extends Subscriber<T> {
    private boolean chosen;
    private final OnSubscribeAmb$Selection<T> selection;
    private final Subscriber<? super T> subscriber;

    @Override // rx.Observer
    public void onNext(Object t) {
        if (isSelected()) {
            this.subscriber.onNext(t);
        }
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (isSelected()) {
            this.subscriber.onCompleted();
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        if (isSelected()) {
            this.subscriber.onError(th);
        }
    }

    private boolean isSelected() {
        if (this.chosen) {
            return true;
        }
        if (this.selection.get() == this) {
            this.chosen = true;
            return true;
        }
        if (this.selection.compareAndSet(null, this)) {
            this.selection.unsubscribeOthers(this);
            this.chosen = true;
            return true;
        }
        this.selection.unsubscribeLosers();
        return false;
    }
}
