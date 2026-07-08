package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.edocument.ShowEDocumentResponse;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.core.netsis.sendmodel.edocument.ShowEDocumentParam;
import io.reactivex.functions.Consumer;


public record Netsis22ExternalSyntheticLambda0(ShowEDocumentResponse f0, ShowEDocumentParam f1) implements Consumer {


    public void accept(Object obj) {
        Netsis.AnonymousClass22.lambdaonResponse0(this.f0, this.f1, (DataResponse) obj);
    }
}
