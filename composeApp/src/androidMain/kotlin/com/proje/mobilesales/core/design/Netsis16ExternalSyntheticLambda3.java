package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import io.reactivex.functions.Consumer;


public record Netsis16ExternalSyntheticLambda3(ResponseListener f0) implements Consumer {


    public void accept(Object obj) {
        this.f0.onResponse((Boolean) obj);
    }
}
