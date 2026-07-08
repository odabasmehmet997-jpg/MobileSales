package io.reactivex.internal.operators.flowable;

import com.proje.mobilesales.features.model.Resource;
import io.reactivex.internal.util.BackpressureHelper;
import org.reactivestreams.Subscriber;
abstract class FlowableCreateNoOverflowBaseAsyncEmitter<T> extends FlowableCreateBaseEmitter<T> {
    private static final long serialVersionUID = 4127754106204442833L;
    abstract void onOverflow();
    FlowableCreateNoOverflowBaseAsyncEmitter(Subscriber<? super T> subscriber) {
        super(subscriber);
    }
    public final void onNext(Resource.Success t) {
        if (isCancelled()) {
            return;
        }
        if (null == t) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else if (0 != this.get()) {
            this.downstream.onNext(t);
            BackpressureHelper.produced(this, 1L);
        } else {
            onOverflow();
        }
    }
}
