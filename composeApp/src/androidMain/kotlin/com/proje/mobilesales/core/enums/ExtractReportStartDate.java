package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.core.base.BaseErp;
import com.proje.mobilesales.core.typs.ParameterTypes;
import com.proje.mobilesales.core.utils.StringUtils;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class ExtractReportStartDate extends Enum<ExtractReportStartDate> {
    private static final EnumEntries ENTRIES;
    private static final ExtractReportStartDate[] VALUES;
    public static final Companion Companion;
    public static final ExtractReportStartDate FIRST_DAY_OF_YEAR = new ExtractReportStartDate("FIRST_DAY_OF_YEAR", 0, 1);
    public static final ExtractReportStartDate PREVIOUS_DAY = new ExtractReportStartDate("PREVIOUS_DAY", 1, 2);
    private final int value;

    private static ExtractReportStartDate[] values() {
        return new ExtractReportStartDate[]{FIRST_DAY_OF_YEAR, PREVIOUS_DAY};
    }

    public static EnumEntries<ExtractReportStartDate> getEntries() {
        return ENTRIES;
    }

    public static ExtractReportStartDate valueOf(String str) {
        return Enum.valueOf(ExtractReportStartDate.class, str);
    }


    private ExtractReportStartDate(String str, int i2, int i3) {
        super(str,i2);
        this.value = i3;
    }

    public int getValue() {
        return this.value;
    }

    static {
        ExtractReportStartDate[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }
 
    public static final class Companion {
        public   Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public boolean isFirstDayOfYear(BaseErp<?> baseErp) {
            Intrinsics.checkNotNullParameter(baseErp, "baseErp");
            return StringUtils.convertStringToInt(baseErp.getLogoSqlHelper().getParamValue(ParameterTypes.ptExtractReportStartDate)) == ExtractReportStartDate.FIRST_DAY_OF_YEAR.getValue();
        }
    }
}
