package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class TransferGetOptionType extends Enum<TransferGetOptionType> {
    private static final EnumEntries ENTRIES;
    private static final TransferGetOptionType[] VALUES;
    public static final TransferGetOptionType DELETE_AND_TRANSFER = new TransferGetOptionType("DELETE_AND_TRANSFER", 0, 0);
    public static final TransferGetOptionType GET_ONLY_CHANGED = new TransferGetOptionType("GET_ONLY_CHANGED", 1, 1);
    private int type;

    private static TransferGetOptionType[] values() {
        return new TransferGetOptionType[]{DELETE_AND_TRANSFER, GET_ONLY_CHANGED};
    }

    public static EnumEntries<TransferGetOptionType> getEntries() {
        return ENTRIES;
    }

    public static TransferGetOptionType valueOf(String str) {
        return Enum.valueOf(TransferGetOptionType.class, str);
    }

    
    private TransferGetOptionType(String str, int i2, int i3) {
        super(str,i2);
        this.type = i3;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    static {
        TransferGetOptionType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
