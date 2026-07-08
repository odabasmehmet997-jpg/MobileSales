package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;

public record NetsisExternalSyntheticLambda6(ResponseListener f0) implements Consumer {

    public void accept(Object obj) {
        this.f0.onResponse((ArrayList) obj);
    }

    @Override
    public Object invoke(Object obj) {

        return obj;
    }
}
