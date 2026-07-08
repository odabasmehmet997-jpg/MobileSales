package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
  
public final class EDocStatus extends Enum<EDocStatus> {
    private static final EnumEntries ENTRIES;
    private static final EDocStatus[] VALUES;
    public static final EDocStatus None = new EDocStatus("None", 0);
    public static final EDocStatus Draft = new EDocStatus("Draft", 1);
    public static final EDocStatus Send = new EDocStatus("Send", 2);

    public static EDocStatus[] values() {
        return new EDocStatus[]{None, Draft, Send};
    }

    public static EnumEntries<EDocStatus> getEntries() {
        return ENTRIES;
    }

    public static EDocStatus valueOf(String str) {
        return Enum.valueOf(EDocStatus.class, str);
    }


    private EDocStatus(String str, int i2) {
        super(str,i2);
    }

    static {
        EDocStatus[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    public String toString() {
        return name();
    }

    public String ordinal() {
        return Integer.toString(getIndex());
    }

    private int getIndex() {
        return 0;
    }
}
