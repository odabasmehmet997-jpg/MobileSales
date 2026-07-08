package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.edocument.ShowEDocumentResponse;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.DataResponse;
import io.reactivex.functions.Consumer;


public record Netsis22ExternalSyntheticLambda1(ResponseListener f0, ShowEDocumentResponse f1) implements Consumer {


    public void accept(Object obj) {
        DataResponse dataResponse = (DataResponse) obj;
        this.f0.onResponse(this.f1);
    }
}
