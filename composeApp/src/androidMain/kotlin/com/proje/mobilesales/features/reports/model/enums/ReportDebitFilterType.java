package com.proje.mobilesales.features.reports.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public enum ReportDebitFilterType {
    NOT_OPERATION (0),
    UNCOVERED_CLOSE (1),
    ALL (2);
    public static final Companion Companion;
    static {
        final ReportDebitFilterType[] values = new ReportDebitFilterType[0];
        final EnumEntries<ReportDebitFilterType> ENTRIES = EnumEntriesKt.enumEntries (values);
        Companion = new Companion ();
    }
    private final int value;
    ReportDebitFilterType(final int i2) {
        value = i2;
    }
    public static ReportDebitFilterType fromReportDebitFilterType(final int i2) {
        return Companion.fromReportDebitFilterType (i2);
    }
    public final int getValue() {
        return value;
    }
    public static final class Companion {
        private Companion() {
        }

        public ReportDebitFilterType fromReportDebitFilterType(final int i2) {
            for (final ReportDebitFilterType reportDebitFilterType : values()) {
                if (reportDebitFilterType.getValue () == i2) {
                    return reportDebitFilterType;
                }
            }
            return NOT_OPERATION;
        }
    }
}
