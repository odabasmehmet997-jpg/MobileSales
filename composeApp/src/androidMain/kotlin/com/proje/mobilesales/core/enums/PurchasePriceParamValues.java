package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class PurchasePriceParamValues extends Enum<PurchasePriceParamValues> {
    private static final  EnumEntries ENTRIES;
    private static final  PurchasePriceParamValues[] VALUES;
    private int value;
    public static final PurchasePriceParamValues PROCEED = new PurchasePriceParamValues("PROCEED", 0, 0);
    public static final PurchasePriceParamValues WARN_USER = new PurchasePriceParamValues("WARN_USER", 1, 1);
    public static final PurchasePriceParamValues BLOCK = new PurchasePriceParamValues("BLOCK", 2, 2);

    private static PurchasePriceParamValues[] values() {
        return new PurchasePriceParamValues[]{PROCEED, WARN_USER, BLOCK};
    }

    public static EnumEntries<PurchasePriceParamValues> getEntries() {
        return ENTRIES;
    }

    public static PurchasePriceParamValues valueOf(String str) {
        return Enum.valueOf(PurchasePriceParamValues.class, str);
    }

    public static PurchasePriceParamValues[] values() {
        return VALUES.clone();
    }

    private PurchasePriceParamValues(String str, int i2, int i3) {
        super(str,i2);
        this.value = i3;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i2) {
        this.value = i2;
    }

    static {
        PurchasePriceParamValues[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
