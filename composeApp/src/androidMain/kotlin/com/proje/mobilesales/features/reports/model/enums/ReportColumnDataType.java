package com.proje.mobilesales.features.reports.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.properties.ObservableProperty;
import kotlin.text.StringsKt;


public enum ReportColumnDataType {
    String (0),
    DateTime (1),
    Decimal (2),
    Numeric (3);
    public static Companion Companion;
    static {
        final ReportColumnDataType[] values = new ReportColumnDataType[0];
        final EnumEntries<ReportColumnDataType> ENTRIES = EnumEntriesKt.enumEntries (values);
        ReportColumnDataType.Companion = new Companion (null);
    }

    private int mValue;

    ReportColumnDataType(final int i2) {
        mValue = i2;
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

        public ReportColumnDataType fromDataType(final String str) {
            for (final ReportColumnDataType reportColumnDataType : values()) {
                if (StringsKt.equals (reportColumnDataType.name (), str, true)) {
                    return reportColumnDataType;
                }
            }
            return String;
        }
    }
}
