package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipParams;
import io.reactivex.functions.Consumer;

public record Netsis4ExternalSyntheticLambda1(PrintSlipModel f0, PrintSlipParams f1) implements Consumer {

    public void accept(Object obj) {
        Netsis.C261711.lambdaonResponse1(this.f0, this.f1, (DataResponse) obj);
    }
}
