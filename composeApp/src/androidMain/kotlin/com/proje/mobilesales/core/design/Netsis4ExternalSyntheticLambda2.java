package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import io.reactivex.functions.Consumer;

public record Netsis4ExternalSyntheticLambda2(ResponseListener f0, PrintSlipModel f1) implements Consumer {
    public void accept(Object obj) {
        DataResponse dataResponse = (DataResponse) obj;
        this.f0.onResponse(this.f1);
    }
}
