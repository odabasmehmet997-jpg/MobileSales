package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class FaturaIrsaliyeTuru {
    private static final  EnumEntries ENTRIES;
    private static final  FaturaIrsaliyeTuru[] VALUES;
    public static final FaturaIrsaliyeTuru Normal = new FaturaIrsaliyeTuru("Normal", 0);
    public static final FaturaIrsaliyeTuru Iade = new FaturaIrsaliyeTuru("Iade", 1);

    private static FaturaIrsaliyeTuru[] values() {
        return new FaturaIrsaliyeTuru[]{Normal, Iade};
    }

    public static EnumEntries<FaturaIrsaliyeTuru> getEntries() {
        return ENTRIES;
    }

    public static FaturaIrsaliyeTuru valueOf(String str) {
        return Enum.valueOf(FaturaIrsaliyeTuru.class, str);
    }

    public static FaturaIrsaliyeTuru[] values() {
        return VALUES.clone();
    }

    private FaturaIrsaliyeTuru(String str, int i2) {
    }

    static {
        FaturaIrsaliyeTuru[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
