package com.proje.mobilesales.features.reports.view.activity;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
 
 class ReportDesignListActivitygetReportDesigns4 extends FunctionReferenceImpl implements Function1<Throwable, Unit> {
    ReportDesignListActivitygetReportDesigns4(final Object obj) {
        super(1, obj, ReportDesignListActivity.class, "handleError", "handleError(Ljava/lang/Throwable;)V", 0);
    } 
    public Unit invoke(final Object th) {
        this.invoke2((Throwable) th);
        return Unit.INSTANCE;
    }
 
    public final void invoke2(final Throwable p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((ReportDesignListActivity) receiver).handleError(p0);
    }
}
