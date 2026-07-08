package com.proje.mobilesales.features.sales.view.list;

import com.proje.mobilesales.core.service.PrintResponse;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
 
 class SalesListFragmentprint2 extends FunctionReferenceImpl implements Function1<PrintResponse, Unit> {
    SalesListFragmentprint2(Object obj) {
        super(1, obj, SalesListFragment.class, "handleResults", "handleResults(Lcom.proje.mobilesales/core/service/PrintResponse;)V", 0);
    }
 
    public Unit invoke(Object printResponse) {
        invoke2((PrintResponse) printResponse);
        return Unit.INSTANCE;
    }
 
    public final void invoke2(PrintResponse p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((SalesListFragment) this.receiver).handleResults(p0);
    }
}
