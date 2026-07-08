package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.core.sql.SqlLiteVariable;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class PrintType {
    private static final  EnumEntries ENTRIES;
    private static final  PrintType[] VALUES;
    public static final PrintType TEXT = new PrintType(SqlLiteVariable._TEXT, 0);
    public static final PrintType IMAGE = new PrintType("IMAGE", 1);
    public static final PrintType PDF = new PrintType("PDF", 2);

    private static PrintType[] values() {
        return new PrintType[]{TEXT, IMAGE, PDF};
    }

    public static EnumEntries<PrintType> getEntries() {
        return ENTRIES;
    }

    public static PrintType valueOf(String str) {
        return Enum.valueOf(PrintType.class, str);
    }

    public static PrintType[] values() {
        return VALUES.clone();
    }

    private PrintType(String str, int i2) {
    }

    static {
        PrintType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
