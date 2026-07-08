package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
 
public final class PrintLanguage extends Enum<PrintLanguage> {
    private static final  EnumEntries ENTRIES;
    private static final  PrintLanguage[] VALUES;
    public static final PrintLanguage STANDARD = createPrintLanguage();
    public static final PrintLanguage ZPL = createPrintLanguage();
    public static final PrintLanguage PPLZ = createPrintLanguage();
    public static final PrintLanguage TSPL = createPrintLanguage();

    private static PrintLanguage[] values() {
        return new PrintLanguage[]{STANDARD, ZPL, PPLZ, TSPL};
    }

    public static EnumEntries<PrintLanguage> getEntries() {
        return ENTRIES;
    }

    public static PrintLanguage valueOf(String str) {
        return Enum.valueOf(PrintLanguage.class, str);
    }


    static {
        PrintLanguage[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    public static PrintLanguage createPrintLanguage() {
        return new PrintLanguage();
    }
}
