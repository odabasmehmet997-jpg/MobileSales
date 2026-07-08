package io.reactivex.internal.operators.flowable;

import androidx.core.location.LocationRequestCompat;
import io.reactivex.functions.Consumer;
import org.reactivestreams.Subscription;



public enum FlowableInternalHelperRequestMax implements Consumer<Subscription> {
    INSTANCE;

    @Override // io.reactivex.functions.Consumer
    public void accept(Subscription subscription) throws Exception {
        subscription.request(LocationRequestCompat.PASSIVE_INTERVAL);
    }
}
