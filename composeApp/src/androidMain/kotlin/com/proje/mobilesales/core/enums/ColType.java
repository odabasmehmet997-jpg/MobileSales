package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;

public final class ColType   {
    private static final  EnumEntries ENTRIES;
    private static final  ColType[] VALUES;
    public static final ColType sayi = new ColType("sayi", 0);
    public static final ColType metin = new ColType("metin", 1);
    public static final ColType virgullu = new ColType("virgullu", 2);
    public static final ColType tarih = new ColType("tarih", 3);
    public static final ColType blob = new ColType("blob", 4);

    private static ColType[] values() {
        return new ColType[]{sayi, metin, virgullu, tarih, blob};
    }
    private ColType(String str, int r2) {
        super();
    }
    static {
        VALUES = values();
        ENTRIES = enumEntries();
    }
    private static EnumEntries enumEntries() {
        return null;
    }
}
