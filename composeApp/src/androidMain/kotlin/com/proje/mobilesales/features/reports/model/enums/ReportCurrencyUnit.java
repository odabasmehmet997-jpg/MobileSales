package com.proje.mobilesales.features.reports.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public enum ReportCurrencyUnit {
    LOCAL (0),
    REPORT (1),
    OPERATION (2);

    public static Companion Companion;


    static {
        final ReportCurrencyUnit[] values = new ReportCurrencyUnit[0];

        ReportCurrencyUnit.Companion = new Companion (null);
    }

    private final int value;

    ReportCurrencyUnit(final int i2) {
        value = i2;
    }

    public static final ReportCurrencyUnit fromReportCurrencyUnit(final int i2) {
        return ReportCurrencyUnit.Companion.fromReportCurrencyUnit (i2);
    }

    public static EnumEntries<ReportCurrencyUnit> getEntries() {
        return ENTRIES;
    }

    public final int getValue() {
        return value;
    }

    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this ();
        }

        private Companion() {
        }

        public ReportCurrencyUnit fromReportCurrencyUnit(final int i2) {
            for (final ReportCurrencyUnit reportCurrencyUnit : values()) {
                if (reportCurrencyUnit.getValue () == i2) {
                    return reportCurrencyUnit;
                }
            }
            return LOCAL;
        }
    }
}
