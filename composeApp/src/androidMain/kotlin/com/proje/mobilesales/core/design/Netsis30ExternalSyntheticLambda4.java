package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.NetsisSelectResult;
import io.reactivex.functions.Consumer;


public record Netsis30ExternalSyntheticLambda4(ResponseListener f0) implements Consumer {


    public void accept(Object obj) {
        Netsis.AnonymousClass30.lambdaonResponse4(this.f0, (NetsisSelectResult) obj);
    }
}
