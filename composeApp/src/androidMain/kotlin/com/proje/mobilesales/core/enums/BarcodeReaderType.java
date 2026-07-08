package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;


public final class BarcodeReaderType  {
    private static final  EnumEntries ENTRIES;
    private static final  BarcodeReaderType[] VALUES;
    public static final BarcodeReaderType Camera = new BarcodeReaderType();
    public static final BarcodeReaderType Laser = new BarcodeReaderType();
    public static BarcodeReaderType[] values() {
        return VALUES.clone();
    }
    public static BarcodeReaderType valueOf() {
        return valueOf();
    }
    private BarcodeReaderType() {
        super();
    }
    static {
        final BarcodeReaderType[] values = BarcodeReaderType.values();
        VALUES = values;
        ENTRIES = enumEntries(values);
    }
    private static EnumEntries enumEntries(BarcodeReaderType[] values) {
        return null;
    }
}