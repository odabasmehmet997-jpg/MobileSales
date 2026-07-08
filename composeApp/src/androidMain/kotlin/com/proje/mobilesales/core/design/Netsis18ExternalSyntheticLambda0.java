package com.proje.mobilesales.core.design;

import com.proje.mobilesales.core.edocument.EDocumentResponse;
import com.proje.mobilesales.core.interfaces.ResponseListener;
import com.proje.mobilesales.core.netsis.DataResponse;
import com.proje.mobilesales.features.sales.model.Sales;
import io.reactivex.functions.Consumer;



public final class Netsis18ExternalSyntheticLambda0 implements Consumer {
    public final Netsis.AnonymousClass18 f0;
    public final EDocumentResponse f1;
    public final int f2;

    public Netsis18ExternalSyntheticLambda0(ResponseListener<Sales> r1, EDocumentResponse eDocumentResponse, int i2) {
        this.f0 = r1;
        this.f1 = eDocumentResponse;
        this.f2 = i2;
    }

    
    public void accept(Object obj) {
        this.f0.lambdaonResponse0(this.f1, this.f2, (DataResponse) obj);
    }
}
