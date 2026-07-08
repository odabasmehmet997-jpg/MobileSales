package com.proje.mobilesales.features.sales.utils;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class PurchasePriceUtilsgetLastPurchasePrice1 extends ContinuationImpl {
    Object L0;
    int label;
    Object result;
    final  PurchasePriceUtils this0;
    PurchasePriceUtilsgetLastPurchasePrice1(PurchasePriceUtils purchasePriceUtils, Continuation<? super PurchasePriceUtilsgetLastPurchasePrice1> continuation) {
        super((Continuation<Object>) continuation);
        this.this0 = purchasePriceUtils;
    } 
    public Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        try {
            return this.this0.getLastPurchasePrice(null, this);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
