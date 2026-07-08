package com.proje.mobilesales.features.customer.view;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class BaseCustomerViewModelgetOneSqlManager1 extends ContinuationImpl {
    Object L0;
    int label;
    Object result;
    final  BaseCustomerViewModel this0;
    BaseCustomerViewModelgetOneSqlManager1(final BaseCustomerViewModel baseCustomerViewModel, final Continuation<? super BaseCustomerViewModelgetOneSqlManager1> continuation) {
        super((Continuation<Object>) continuation);
        this0 = baseCustomerViewModel;
    }
    public Object invokeSuspend(final Object obj) {
        result = obj;
        label |= Integer.MIN_VALUE;
        return this0.getOneSqlManager(null, null, null, this);
    }
}
