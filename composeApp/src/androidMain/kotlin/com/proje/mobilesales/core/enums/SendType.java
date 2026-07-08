package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;


public final class SendType {
    private static final  EnumEntries ENTRIES;
    private static final  SendType[] VALUES;
    public static final SendType EXEC = new SendType("EXEC", 0);
    public static final SendType DIRECT = new SendType("DIRECT", 1);
    public static final SendType APPEND = new SendType("APPEND", 2);
    public static final SendType DELETE = new SendType("DELETE", 3);
    public static final SendType READ = new SendType("READ", 4);
    public static final SendType CALCULATE = new SendType("CALCULATE", 5);

    private static SendType[] values() {
        return new SendType[]{EXEC, DIRECT, APPEND, DELETE, READ, CALCULATE};
    }

    public static EnumEntries<SendType> getEntries() {
        return ENTRIES;
    }

    public static SendType valueOf(String str) {
        return Enum.valueOf(SendType.class, str);
    }

    public static SendType[] values() {
        return VALUES.clone();
    }

    private SendType(String str, int i2) {
    }

    static {
        SendType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
