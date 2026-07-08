package com.proje.mobilesales.features.reports.view.activity;

import com.proje.mobilesales.features.reports.model.ReportItem;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
 
 class ReportDesignListActivitygetReportDesigns3 extends FunctionReferenceImpl implements Function1<List<ReportItem>, Unit> {
    ReportDesignListActivitygetReportDesigns3(final Object obj) {
        super(1, obj, ReportDesignListActivity.class, "handleResults", "handleResults(Ljava/util/List;)V", 0);
    }
 
    public Unit invoke(final Object list) {
        this.invoke2((List<ReportItem>) list);
        return Unit.INSTANCE;
    } 
    public final void invoke2(final List<ReportItem> p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        ((ReportDesignListActivity) receiver).handleResults(p0);
    }
}
