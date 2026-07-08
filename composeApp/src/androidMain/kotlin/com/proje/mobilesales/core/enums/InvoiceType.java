package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class InvoiceType {
    private static final  EnumEntries ENTRIES;
    private static final  InvoiceType[] VALUES;
    public static final InvoiceType NORMAL = new InvoiceType("NORMAL", 0, 8);
    public static final InvoiceType RETURN = new InvoiceType("RETURN", 1, 3);
    private final int value;

    private static InvoiceType[] values() {
        return new InvoiceType[]{NORMAL, RETURN};
    }

    public static EnumEntries<InvoiceType> getEntries() {
        return ENTRIES;
    }

    public static InvoiceType valueOf(String str) {
        return Enum.valueOf(InvoiceType.class, str);
    }

    public static InvoiceType[] values() {
        return VALUES.clone();
    }

    private InvoiceType(String str, int i2, int i3) {
        this.value = i3;
    }

    public int getValue() {
        return this.value;
    }

    static {
        InvoiceType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
