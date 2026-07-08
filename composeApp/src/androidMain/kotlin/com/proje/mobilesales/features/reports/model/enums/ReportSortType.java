package com.proje.mobilesales.features.reports.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

public enum ReportSortType {
    PRODUCT_CODE ("CODE", "X"),
    PRODUCT_NAME ("NAME", "Y");

 

    static {
        final ReportSortType[] values = new ReportSortType[0];
        final EnumEntries<ReportSortType> ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    private final String type;
    private String value;

    ReportSortType(final String str, final String str2) {
        type = str;
        value = str2;
    }
 
    public final String getType() {
        return type;
    }

    public final String getValue() {
        return value;
    }

    public final void setValue(final String str) {
        Intrinsics.checkNotNullParameter (str, "<set-?>");
        value = str;
    }
}
