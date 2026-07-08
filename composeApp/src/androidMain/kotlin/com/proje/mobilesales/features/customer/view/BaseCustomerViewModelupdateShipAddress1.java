package com.proje.mobilesales.features.customer.view;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

final class BaseCustomerViewModelupdateShipAddress1 extends ContinuationImpl {
    Object L0;
    int label;
    Object result;
    final  BaseCustomerViewModel this0;
    BaseCustomerViewModelupdateShipAddress1(final BaseCustomerViewModel baseCustomerViewModel, final Continuation<? super BaseCustomerViewModelupdateShipAddress1> continuation) {
        super((Continuation<Object>) continuation);
        this0 = baseCustomerViewModel;
    }
    public Object invokeSuspend(final Object obj) {
        result = obj;
        label |= Integer.MIN_VALUE;
        return this0.updateShipAddress(0, this);
    }
}
