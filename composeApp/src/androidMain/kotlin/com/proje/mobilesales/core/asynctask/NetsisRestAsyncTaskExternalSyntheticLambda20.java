package com.proje.mobilesales.core.asynctask;


import io.reactivex.functions.Consumer;

import static com.proje.mobilesales.core.utils.AppUtils.handleNetworkError;

public final  class NetsisRestAsyncTaskExternalSyntheticLambda20 implements Consumer {
    public void accept(Object obj) {
        handleNetworkError((Throwable) obj);
    }
    @Override
    public Object invoke(Object obj) {

        return obj;
    }
}
