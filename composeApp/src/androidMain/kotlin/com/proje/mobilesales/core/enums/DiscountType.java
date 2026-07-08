package com.proje.mobilesales.core.enums;

import androidx.annotation.StringRes;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class DiscountType {
    private static final  EnumEntries ENTRIES;
    private static final  DiscountType[] VALUES;

    @StringRes
    private int resId;
    public int value;
    public static final DiscountType UNDEFINED = new DiscountType("UNDEFINED", 0, -1, -1);
    public static final DiscountType DISCRATIO = new DiscountType("DISCRATIO", 1, 0, -1);
    public static final DiscountType DISCTOTAL = new DiscountType("DISCTOTAL", 2, 1, -1);
    public static final DiscountType DISCCARDCODE = new DiscountType("DISCCARDCODE", 3, 2, -1);

    private static DiscountType[] values() {
        return new DiscountType[]{UNDEFINED, DISCRATIO, DISCTOTAL, DISCCARDCODE};
    }

    public static EnumEntries<DiscountType> getEntries() {
        return ENTRIES;
    }

    public static DiscountType valueOf(String str) {
        return Enum.valueOf(DiscountType.class, str);
    }

    public static DiscountType[] values() {
        return VALUES.clone();
    }

    private DiscountType(String str, @StringRes int i2, int i3, int i4) {
        this.value = i3;
        this.resId = i4;
    }

    public int getResId() {
        return this.resId;
    }

    public void setResId(int i2) {
        this.resId = i2;
    }

    static {
        DiscountType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
