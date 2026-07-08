package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.R;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class SpecodeType {
    private static final  EnumEntries ENTRIES;
    private static final  SpecodeType[] VALUES;
    public static final SpecodeType SPECODE1 = new SpecodeType("SPECODE1", 0, R.id.fch_spe_code, R.string.str_customer_specode_1, "SPECODE");
    public static final SpecodeType SPECODE2 = new SpecodeType("SPECODE2", 1, R.id.fch_spe_code2, R.string.str_customer_specode_2, "SPECODE2");
    public static final SpecodeType SPECODE3 = new SpecodeType("SPECODE3", 2, R.id.fch_spe_code3, R.string.str_customer_specode_3, "SPECODE3");
    public static final SpecodeType SPECODE4 = new SpecodeType("SPECODE4", 3, R.id.fch_spe_code4, R.string.str_customer_specode_4, "SPECODE4");
    public static final SpecodeType SPECODE5 = new SpecodeType("SPECODE5", 4, R.id.fch_spe_code5, R.string.str_customer_specode_5, "SPECODE5");
    private final String querySuffix;
    private final int titleRes;
    private final int viewId;

    private static SpecodeType[] values() {
        return new SpecodeType[]{SPECODE1, SPECODE2, SPECODE3, SPECODE4, SPECODE5};
    }

    public static EnumEntries<SpecodeType> getEntries() {
        return ENTRIES;
    }

    public static SpecodeType valueOf(String str) {
        return Enum.valueOf(SpecodeType.class, str);
    }

    public static SpecodeType[] values() {
        return VALUES.clone();
    }

    private SpecodeType(String str, int i2, int i3, int i4, String str2) {
        this.viewId = i3;
        this.titleRes = i4;
        this.querySuffix = str2;
    }

    public String getQuerySuffix() {
        return this.querySuffix;
    }

    public int getTitleRes() {
        return this.titleRes;
    }

    public int getViewId() {
        return this.viewId;
    }

    static {
        SpecodeType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
