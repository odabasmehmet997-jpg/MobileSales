package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;

public final class CommunicationType  {
    private static final  EnumEntries ENTRIES;
    private static final  CommunicationType[] VALUES;
    private final String communationName;
    public static final CommunicationType WCF = new CommunicationType("WCF", 0, "WCF");
    public static final CommunicationType REST = new CommunicationType("REST", 1, "REST");
    public static final CommunicationType DEMO = new CommunicationType("DEMO", 2, "DEMO");

    private CommunicationType(String str, int i2, String str2) {
        this.communationName = str2;
    }
    public static CommunicationType[] values() {
        return VALUES.clone();
    }
    static {
        CommunicationType[] values = values();
        VALUES = values;
        ENTRIES = enumEntries();
    }

    private static EnumEntries enumEntries() {
        return null;
    }

}
