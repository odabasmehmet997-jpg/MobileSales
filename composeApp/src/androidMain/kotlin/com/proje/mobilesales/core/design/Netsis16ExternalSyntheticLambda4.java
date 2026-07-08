package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import io.reactivex.functions.Consumer;


public record Netsis16ExternalSyntheticLambda4(ResponseListener f0) implements Consumer {


    public void accept(Object obj) {
        Netsis.AnonymousClass16.lambdaonResponse4(this.f0, (Throwable) obj);
    }
}
