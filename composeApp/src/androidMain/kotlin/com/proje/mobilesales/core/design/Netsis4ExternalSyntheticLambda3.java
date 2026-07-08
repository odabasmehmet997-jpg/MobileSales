package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import io.reactivex.functions.Consumer;


public record Netsis4ExternalSyntheticLambda3(ResponseListener f0) implements Consumer {


    public void accept(Object obj) {
        Netsis.AnonymousClass4.lambdaonResponse3(this.f0, (Throwable) obj);
    }
}
