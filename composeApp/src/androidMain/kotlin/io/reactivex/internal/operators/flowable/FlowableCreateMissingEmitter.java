package io.reactivex.internal.operators.flowable;

import com.proje.mobilesales.features.model.Resource;
import org.reactivestreams.Subscriber;

final class FlowableCreateMissingEmitter<T> extends FlowableCreateBaseEmitter<T> {
    private static final long serialVersionUID = 3776720187248809713L;
    FlowableCreateMissingEmitter(Subscriber<? super T> subscriber) {
        super(subscriber);
    }
    public void onComplete() {
    }
    public void onNext(Resource.Success t) {
        long j2;
        if (isCancelled()) {
            return;
        }
        if (null != t) {
            this.downstream.onNext(t);
            do {
                j2 = get();
                if (0 == j2) {
                    return;
                }
            } while (!compareAndSet(j2, j2 - 1));
            return;
        }
        onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
    }
}
