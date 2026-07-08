package com.proje.mobilesales.features.reports.model;

import com.proje.mobilesales.features.reports.model.enums.ReportSortType;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ReportListParameter {
    private String createDateEnd;
    private String createDateStart;
    private ReportSortType reportSortType;
    private int salesmanRef;
    private int warehouseNo;

    public ReportListParameter() {
        this(null, null, 0, 0, null, 31, null);
    }

    public static  ReportListParameter copydefault(final ReportListParameter reportListParameter, String str, String str2, int i2, int i3, ReportSortType reportSortType, final int i4, final Object obj) {
        if (0 != (i4 & 1)) {
            str = reportListParameter.createDateStart;
        }
        if (0 != (i4 & 2)) {
            str2 = reportListParameter.createDateEnd;
        }
        final String str3 = str2;
        if (0 != (i4 & 4)) {
            i2 = reportListParameter.salesmanRef;
        }
        final int i5 = i2;
        if (0 != (i4 & 8)) {
            i3 = reportListParameter.warehouseNo;
        }
        final int i6 = i3;
        if (0 != (i4 & 16)) {
            reportSortType = reportListParameter.reportSortType;
        }
        return reportListParameter.copy(str, str3, i5, i6, reportSortType);
    }

    public String component1() {
        return createDateStart;
    }

    public String component2() {
        return createDateEnd;
    }

    public int component3() {
        return salesmanRef;
    }

    public int component4() {
        return warehouseNo;
    }

    public ReportSortType component5() {
        return reportSortType;
    }

    public ReportListParameter copy(final String str, final String str2, final int i2, final int i3, final ReportSortType reportSortType) {
        return new ReportListParameter(str, str2, i2, i3, reportSortType);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReportListParameter reportListParameter)) {
            return false;
        }
        return Intrinsics.areEqual(createDateStart, reportListParameter.createDateStart) && Intrinsics.areEqual(createDateEnd, reportListParameter.createDateEnd) && salesmanRef == reportListParameter.salesmanRef && warehouseNo == reportListParameter.warehouseNo && reportSortType == reportListParameter.reportSortType;
    }

    public int hashCode() {
        final String str = createDateStart;
        final int hashCode = (null == str ? 0 : str.hashCode()) * 31;
        final String str2 = createDateEnd;
        final int hashCode2 = (((((hashCode + (null == str2 ? 0 : str2.hashCode())) * 31) + Integer.hashCode(salesmanRef)) * 31) + Integer.hashCode(warehouseNo)) * 31;
        final ReportSortType reportSortType = this.reportSortType;
        return hashCode2 + (null != reportSortType ? reportSortType.hashCode() : 0);
    }

    public String toString() {
        return "ReportListParameter(createDateStart=" + createDateStart + ", createDateEnd=" + createDateEnd + ", salesmanRef=" + salesmanRef + ", warehouseNo=" + warehouseNo + ", reportSortType=" + reportSortType + ')';
    }

    public ReportListParameter(final String str, final String str2, final int i2, final int i3, final ReportSortType reportSortType) {
        createDateStart = str;
        createDateEnd = str2;
        salesmanRef = i2;
        warehouseNo = i3;
        this.reportSortType = reportSortType;
    }

    public  ReportListParameter(final String str, final String str2, final int i2, final int i3, final ReportSortType reportSortType, final int i4, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i4 & 1) ? null : str, 0 != (i4 & 2) ? null : str2, 0 != (i4 & 4) ? 0 : i2, 0 != (i4 & 8) ? 0 : i3, 0 != (i4 & 16) ? null : reportSortType);
    }

    public String getCreateDateStart() {
        return createDateStart;
    }

    public void setCreateDateStart(final String str) {
        createDateStart = str;
    }

    public String getCreateDateEnd() {
        return createDateEnd;
    }

    public void setCreateDateEnd(final String str) {
        createDateEnd = str;
    }

    public int getSalesmanRef() {
        return salesmanRef;
    }

    public void setSalesmanRef(final int i2) {
        salesmanRef = i2;
    }

    public int getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(final int i2) {
        warehouseNo = i2;
    }

    public ReportSortType getReportSortType() {
        return reportSortType;
    }

    public void setReportSortType(final ReportSortType reportSortType) {
        this.reportSortType = reportSortType;
    }
}
