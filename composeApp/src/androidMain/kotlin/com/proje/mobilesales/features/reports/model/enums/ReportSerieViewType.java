package com.proje.mobilesales.features.reports.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public enum ReportSerieViewType {
    Bar,
    Pie,
    Point,
    Line;
    private static final EnumEntries<ReportSerieViewType> ENTRIES;
    static {
        ReportSerieViewType[] values = null;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
    public static EnumEntries<ReportSerieViewType> getEntries() {
        return ENTRIES;
    }
}
