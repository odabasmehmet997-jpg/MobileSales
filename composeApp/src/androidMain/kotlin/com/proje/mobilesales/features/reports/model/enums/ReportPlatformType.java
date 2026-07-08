package com.proje.mobilesales.features.reports.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;



public enum ReportPlatformType {
    WMS_MOBILE (1),
    WMS_WEB (2),
    MOBILE_SALES (3);

    private static final EnumEntries<ReportPlatformType> ENTRIES;

    static {
        ReportPlatformType[] values = null;
        EnumEntriesKt.enumEntries(values);
        values = new ReportPlatformType[0];
        ReportPlatformType.ENTRIES = EnumEntriesKt.enumEntries(values);
    }
    private final int value;
    ReportPlatformType(final int i2) {
        value = i2;
    }
    public static EnumEntries<ReportPlatformType> getEntries() {
        return ReportPlatformType.ENTRIES;
    }
    public final int getValue() {
        return value;
    }
}
