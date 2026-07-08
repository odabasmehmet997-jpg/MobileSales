package com.proje.mobilesales.features.sales.view.list;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
 
 class SalesListFragmentprint3 extends FunctionReferenceImpl implements Function1<Throwable, Unit> {
    SalesListFragmentprint3(Object obj) {
        super(1, obj, SalesListFragment.class, "handleError", "handleError(Ljava/lang/Throwable;)V", 0);
    }
 
    public  Unit invoke(Object th) {
        invoke2((Throwable) th);
        return Unit.INSTANCE;
    }
    public final void invoke2(Throwable p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((SalesListFragment) this.receiver).handleError(p0);
    }
}
