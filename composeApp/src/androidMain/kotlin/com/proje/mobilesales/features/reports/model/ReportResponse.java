package com.proje.mobilesales.features.reports.model;

import com.proje.mobilesales.core.service.PrinterServiceBaseResponse;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReportResponse extends PrinterServiceBaseResponse {

    private List<ReportItem> result;



    public static   ReportResponse copydefault(final ReportResponse reportResponse, List list, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            list = reportResponse.result;
        }
        return reportResponse.copy(list);
    }

    public List<ReportItem> component1() {
        return result;
    }

    public ReportResponse copy(final List<ReportItem> list) {
        return new ReportResponse(list);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ReportResponse) && Intrinsics.areEqual(result, ((ReportResponse) obj).result);
    }

    public int hashCode() {
        final List<ReportItem> list = result;
        if (null == list) {
            return 0;
        }
        return list.hashCode();
    }

    public String toString() {
        return "ReportResponse(result=" + result + ')';
    }

    public  ReportResponse(final List list, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i2 & 1) ? null : list);
    }

    public List<ReportItem> getResult() {
        return result;
    }

    public void setResult(final List<ReportItem> list) {
        result = list;
    }

    public ReportResponse(final List<ReportItem> list) {
        result = list;
    }
}
