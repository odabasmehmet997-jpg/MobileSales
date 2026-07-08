package com.proje.mobilesales.features.sales.model;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class SalessalesDetailListControl1 extends ContinuationImpl {
    int I0;
    Object L0;
    Object L1;
    Object L2;
    Object L3;
    Object L4;
    Object L5;
    boolean Z0;
    boolean Z1;
    int label;
     Object result;
    final  Sales this0;

    SalessalesDetailListControl1(Sales sales, Continuation<? super String> continuation) {
        super((Continuation<Object>) continuation);
        this.this0 = sales;
    }

    public Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        try {
            return this.this0.salesDetailListControl(false, null, null, this);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
