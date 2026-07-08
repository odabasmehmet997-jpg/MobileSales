package com.proje.mobilesales.core.asynctask;

import io.reactivex.functions.Consumer;

import static com.proje.mobilesales.core.utils.AppUtils.handleNetworkErrorWithMessage;

public final  class NetsisRestAsyncTaskExternalSyntheticLambda5 implements Consumer {
    public void accept(Object obj) {
        handleNetworkErrorWithMessage((Throwable) obj);
    }

    @Override
    public Object invoke(Object obj) {

        return obj;
    }
}