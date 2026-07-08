package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class MobileSalesUpdateType {
    private static final  EnumEntries ENTRIES;
    private static final  MobileSalesUpdateType[] VALUES;
    public static final MobileSalesUpdateType TODO = new MobileSalesUpdateType("TODO", 0);
    public static final MobileSalesUpdateType CUSTOMERGPS = new MobileSalesUpdateType("CUSTOMERGPS", 1);
    public static final MobileSalesUpdateType STARTINFO = new MobileSalesUpdateType("STARTINFO", 2);

    private static MobileSalesUpdateType[] values() {
        return new MobileSalesUpdateType[]{TODO, CUSTOMERGPS, STARTINFO};
    }

    public static EnumEntries<MobileSalesUpdateType> getEntries() {
        return ENTRIES;
    }

    public static MobileSalesUpdateType valueOf(String str) {
        return Enum.valueOf(MobileSalesUpdateType.class, str);
    }

    public static MobileSalesUpdateType[] values() {
        return VALUES.clone();
    }

    private MobileSalesUpdateType(String str, int i2) {
    }

    static {
        MobileSalesUpdateType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
