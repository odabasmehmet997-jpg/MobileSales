package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.netsis.NetsisDataHeader;
import com.proje.mobilesales.core.netsis.NetsisSelectResult;
import io.reactivex.functions.Function;


public record Netsis30ExternalSyntheticLambda2(NetsisSelectResult f0) implements Function {

    @Override // io.reactivex.functions.Function
    public Object apply(Object obj) {
        return Netsis.AnonymousClass30.lambdaonResponse2(this.f0, (NetsisDataHeader) obj);
    }
}
