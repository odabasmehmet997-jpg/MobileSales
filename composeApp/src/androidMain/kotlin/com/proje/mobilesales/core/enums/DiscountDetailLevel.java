package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class DiscountDetailLevel {
    private static final  EnumEntries ENTRIES;
    private static final  DiscountDetailLevel[] VALUES;
    private int level;
    public static final DiscountDetailLevel _LINE = new DiscountDetailLevel("_LINE", 0, 0);
    public static final DiscountDetailLevel _GENERAL = new DiscountDetailLevel("_GENERAL", 1, 1);

    private static DiscountDetailLevel[] values() {
        return new DiscountDetailLevel[]{_LINE, _GENERAL};
    }

    public static EnumEntries<DiscountDetailLevel> getEntries() {
        return ENTRIES;
    }

    public static DiscountDetailLevel valueOf(String str) {
        return Enum.valueOf(DiscountDetailLevel.class, str);
    }

    public static DiscountDetailLevel[] values() {
        return VALUES.clone();
    }

    private DiscountDetailLevel(String str, int i2, int i3) {
        this.level = i3;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i2) {
        this.level = i2;
    }

    static {
        DiscountDetailLevel[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
