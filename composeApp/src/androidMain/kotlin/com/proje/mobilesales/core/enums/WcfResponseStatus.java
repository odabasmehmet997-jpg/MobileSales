package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class WcfResponseStatus extends Enum<WcfResponseStatus> {
    private static final  EnumEntries ENTRIES;
    private static final  WcfResponseStatus[] VALUES;
    private int value;
    public static final WcfResponseStatus BEING_CALLED = new WcfResponseStatus("BEING_CALLED", 0, 1);
    public static final WcfResponseStatus PROCESSING = new WcfResponseStatus("PROCESSING", 1, 2);
    public static final WcfResponseStatus SUCCESS = new WcfResponseStatus("SUCCESS", 2, 3);
    public static final WcfResponseStatus FAILED = new WcfResponseStatus("FAILED", 3, 4);

    private static WcfResponseStatus[] values() {
        return new WcfResponseStatus[]{BEING_CALLED, PROCESSING, SUCCESS, FAILED};
    }

    public static EnumEntries<WcfResponseStatus> getEntries() {
        return ENTRIES;
    }

    public static WcfResponseStatus valueOf(String str) {
        return Enum.valueOf(WcfResponseStatus.class, str);
    }


    private WcfResponseStatus(String str, int i, int i2) {
        super(str,i2);
        this.value = i2;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    static {
        WcfResponseStatus[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
