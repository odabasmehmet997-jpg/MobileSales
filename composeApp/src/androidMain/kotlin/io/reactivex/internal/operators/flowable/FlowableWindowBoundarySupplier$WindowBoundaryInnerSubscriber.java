package io.reactivex.internal.operators.flowable;

import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subscribers.DisposableSubscriber;



final class FlowableWindowBoundarySupplierWindowBoundaryInnerSubscriber<T, B> extends DisposableSubscriber<B> {
    boolean done;
    final FlowableWindowBoundarySupplierWindowBoundaryMainSubscriber<T, B> parent;

    FlowableWindowBoundarySupplierWindowBoundaryInnerSubscriber(FlowableWindowBoundarySupplierWindowBoundaryMainSubscriber<T, B> flowableWindowBoundarySupplierWindowBoundaryMainSubscriber) {
        this.parent = flowableWindowBoundarySupplierWindowBoundaryMainSubscriber;
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(Object b2) {
        if (this.done) {
            return;
        }
        this.done = true;
        this.dispose();
        this.parent.innerNext(this);
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.done) {
            RxJavaPlugins.onError(th);
        } else {
            this.done = true;
            this.parent.innerError(th);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.done) {
            return;
        }
        this.done = true;
        this.parent.innerComplete();
    }
}
