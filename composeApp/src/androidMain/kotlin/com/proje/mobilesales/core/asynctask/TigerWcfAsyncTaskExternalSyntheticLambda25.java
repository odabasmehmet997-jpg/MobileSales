package com.proje.mobilesales.core.asynctask;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import io.reactivex.functions.Consumer;

public record TigerWcfAsyncTaskExternalSyntheticLambda25(ResponseListener f0) implements Consumer {
    public void accept(Object obj) {
        this.f0.onResponse(obj);
    }
}