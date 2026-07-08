package com.proje.mobilesales.features.reports.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

public enum ReportLayoutItemType {
    Column (0),
    TabbedGroup (1),
    LayoutGroup (2),
    Label (3),
    Row (4);
    public static Companion Companion;
    static {
        final ReportLayoutItemType[] values = new ReportLayoutItemType[0];
        ENTRIES = EnumEntriesKt.enumEntries (values);
        ReportLayoutItemType.Companion = new Companion (null);
    }
    private int mValue;
    ReportLayoutItemType(final int i2) {
        mValue = i2;
    }
    public static EnumEntries<ReportLayoutItemType> getEntries() {
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

        public ReportLayoutItemType fromItemType(final String str) {
            for (final ReportLayoutItemType reportLayoutItemType : values()) {
                if (StringsKt.equals (reportLayoutItemType.name (), str, true)) {
                    return reportLayoutItemType;
                }
            }
            return Label;
        }
    }
}
