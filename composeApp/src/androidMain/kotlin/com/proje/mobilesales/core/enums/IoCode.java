package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class IoCode {
    private static final  EnumEntries ENTRIES;
    private static final  IoCode[] VALUES;
    private int type;
    public static final IoCode INPUT = new IoCode("INPUT", 0, 1);
    public static final IoCode WARE_HOUSE_INPUT = new IoCode("WARE_HOUSE_INPUT", 1, 2);
    public static final IoCode WARE_HOUSE_OUTPUT = new IoCode("WARE_HOUSE_OUTPUT", 2, 3);
    public static final IoCode OUTPUT = new IoCode("OUTPUT", 3, 4);

    private static IoCode[] values() {
        return new IoCode[]{INPUT, WARE_HOUSE_INPUT, WARE_HOUSE_OUTPUT, OUTPUT};
    }

    public static EnumEntries<IoCode> getEntries() {
        return ENTRIES;
    }

    public static IoCode valueOf(String str) {
        return Enum.valueOf(IoCode.class, str);
    }

    public static IoCode[] values() {
        return VALUES.clone();
    }

    private IoCode(String str, int i2, int i3) {
        this.type = i3;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    static {
        IoCode[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
