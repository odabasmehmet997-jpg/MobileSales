package com.proje.mobilesales.features.userreport.view.activity;

import com.proje.mobilesales.core.reportparser.ReportColumn;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
 
public final class UserReportsActivitygenerateGridcolumnWidthTotal1 extends Lambda implements Function1<ReportColumn, Integer> {
    public static final UserReportsActivitygenerateGridcolumnWidthTotal1 INSTANCE = new UserReportsActivitygenerateGridcolumnWidthTotal1();

    UserReportsActivitygenerateGridcolumnWidthTotal1() {
        super(1);
    }

    public Integer invoke(ReportColumn reportColumn) {
        Intrinsics.checkNotNullParameter(reportColumn, "obj");
        return Integer.valueOf(reportColumn.getWidth());
    }
}
