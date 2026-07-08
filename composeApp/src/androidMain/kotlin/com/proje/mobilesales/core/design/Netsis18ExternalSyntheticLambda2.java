package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import io.reactivex.functions.Consumer;


public record Netsis18ExternalSyntheticLambda2(ResponseListener f0) implements Consumer {


    public void accept(Object obj) {
        Netsis.AnonymousClass18.lambdaonResponse2(this.f0, (Throwable) obj);
    }
}
