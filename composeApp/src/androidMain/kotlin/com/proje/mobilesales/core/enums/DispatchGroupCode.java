package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class DispatchGroupCode {
    private static final  EnumEntries ENTRIES;
    private static final  DispatchGroupCode[] VALUES;
    private int mValue;
    public static final DispatchGroupCode SALES = new DispatchGroupCode("SALES", 0, 2);
    public static final DispatchGroupCode BUYING = new DispatchGroupCode("BUYING", 1, 1);

    private static DispatchGroupCode[] values() {
        return new DispatchGroupCode[]{SALES, BUYING};
    }

    public static EnumEntries<DispatchGroupCode> getEntries() {
        return ENTRIES;
    }

    public static DispatchGroupCode valueOf(String str) {
        return Enum.valueOf(DispatchGroupCode.class, str);
    }


    private DispatchGroupCode(String str, int i2, int i3) {
        this.mValue = i3;
    }

    public int getMValue() {
        return this.mValue;
    }

    public void setMValue(int i2) {
        this.mValue = i2;
    }

    static {
        DispatchGroupCode[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    public int getmValue() {
        return this.mValue;
    }

    public void setmValue(int i2) {
        this.mValue = i2;
    }
}
