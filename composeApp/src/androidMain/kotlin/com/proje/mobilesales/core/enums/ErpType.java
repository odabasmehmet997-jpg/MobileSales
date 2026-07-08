package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class ErpType extends Enum<ErpType> {
    private static final EnumEntries ENTRIES;
    private static final ErpType[] VALUES;
    public final String erpName;
    public static final ErpType TIGER = new ErpType("TIGER", 0, "TIGER");
    public static final ErpType GO = new ErpType("GO", 1, "GO");
    public static final ErpType NETSIS = new ErpType("NETSIS", 2, "NETSIS");

    public static ErpType[] values() {
        return new ErpType[]{TIGER, GO, NETSIS};
    }

    public static EnumEntries<ErpType> getEntries() {
        return ENTRIES;
    }

    public static ErpType valueOf(String str) {
        return Enum.valueOf(ErpType.class, str);
    }

    private ErpType(String str, int i2, String str2) {
        super(str,i2);
        this.erpName = str2;
    }

    static {
        ErpType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

}
