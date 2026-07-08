package com.proje.mobilesales.features.reports.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum ReportType {
    SQL (1),
    DESIGN (2);

    private static EnumEntries<ReportType> ENTRIES = null;

    static {
        ReportType[] values = null;
        EnumEntriesKt.enumEntries(values);
        values = new ReportType[0];
        ReportType.ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    private final int value;

    ReportType(final int i2) {
        value = i2;
    }

    public static EnumEntries<ReportType> getEntries() {
        return ReportType.ENTRIES;
    }

    public final int getValue() {
        return value;
    }
}
