package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.NetsisSelectResult;
import io.reactivex.functions.Consumer;

import java.util.List;


public final class Netsis30ExternalSyntheticLambda3 implements Consumer {
    public final Netsis.AnonymousClass30 f0;
    public final NetsisSelectResult f1;
    public final String f2;
    public final String f3;

    public Netsis30ExternalSyntheticLambda3(ResponseListener<List<Object>> r1, NetsisSelectResult netsisSelectResult, String str, String str2) {
        this.f0 = r1;
        this.f1 = netsisSelectResult;
        this.f2 = str;
        this.f3 = str2;
    }

    
    public void accept(Object obj) {
        this.f0.lambdaonResponse3(this.f1, this.f2, this.f3, (NetsisSelectResult) obj);
    }
}
