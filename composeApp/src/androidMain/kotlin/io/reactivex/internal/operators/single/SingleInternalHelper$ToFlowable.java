package io.reactivex.internal.operators.single;

import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import org.reactivestreams.Publisher;



enum SingleInternalHelperToFlowable implements Function<SingleSource, Publisher> {
    INSTANCE;

    @Override // io.reactivex.functions.Function
    public Publisher apply(Object singleSource) {
        return new SingleToFlowable(singleSource);
    }
}
