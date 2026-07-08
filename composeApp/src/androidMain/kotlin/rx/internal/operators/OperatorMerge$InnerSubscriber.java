package rx.internal.operators;

import rx.Subscriber;
import rx.internal.util.RxRingBuffer;


final class OperatorMerge$InnerSubscriber<T> extends Subscriber<T> {
    static final int LIMIT = RxRingBuffer.SIZE / 4;
    volatile boolean done;
    final long id;
    int outstanding;
    final OperatorMerge$MergeSubscriber<T> parent;
    volatile RxRingBuffer queue;

    public OperatorMerge$InnerSubscriber(OperatorMerge$MergeSubscriber<T> operatorMerge$MergeSubscriber, long j2) {
        this.parent = operatorMerge$MergeSubscriber;
        this.id = j2;
    }

    @Override // rx.Subscriber
    public void onStart() {
        int i2 = RxRingBuffer.SIZE;
        this.outstanding = i2;
        request(i2);
    }

    @Override // rx.Observer
    public void onNext(Object t) {
        this.parent.tryEmit(this, t);
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.parent.getOrCreateErrorQueue().offer(th);
        this.done = true;
        this.parent.emit();
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.done = true;
        this.parent.emit();
    }

    public void requestMore(long j2) {
        int i2 = this.outstanding - ((int) j2);
        if (i2 > LIMIT) {
            this.outstanding = i2;
            return;
        }
        int i3 = RxRingBuffer.SIZE;
        this.outstanding = i3;
        int i4 = i3 - i2;
        if (i4 > 0) {
            request(i4);
        }
    }
}
