package com.proje.mobilesales.features.reports.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

import static com.proje.mobilesales.features.reports.model.enums.ReportCurrencyUnit.ENTRIES;


public enum ReportDisplayType {
    Form (0),
    Grid (1),
    Chart (2);

    public static Companion Companion;

    static {
        final ReportDisplayType[] values = new ReportDisplayType[0];
        ENTRIES = EnumEntriesKt.enumEntries (values);
        ReportDisplayType.Companion = new Companion (null);
    }

    private int mValue;

    ReportDisplayType(final int i2) {
        mValue = i2;
    }

    public static final ReportDisplayType fromDisplayType(final String str) {
        return ReportDisplayType.Companion.fromDisplayType (str);
    }

    public static EnumEntries<ReportDisplayType> getEntries() {
        return ENTRIES;
    }

    public final int getmValue() {
        return mValue;
    }

    public final void setmValue(final int i2) {
        mValue = i2;
    }

    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this ();
        }

        private Companion() {
        }

        public ReportDisplayType fromDisplayType(final String str) {
            for (final ReportDisplayType reportDisplayType : values()) {
                if (StringsKt.equals (reportDisplayType.name (), str, true)) {
                    return reportDisplayType;
                }
            }
            return Grid;
        }
    }
}
