package io.reactivex.internal.operators.single;

import io.reactivex.Observable;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;



enum SingleInternalHelperToObservable implements Function<SingleSource, Observable> {
    INSTANCE;

    @Override // io.reactivex.functions.Function
    public Observable apply(Object singleSource) {
        return new SingleToObservable(singleSource);
    }
}
