package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class GlobalLineType {
    private static final  EnumEntries ENTRIES;
    private static final  GlobalLineType[] VALUES;
    public static final Companion Companion;
    private final int value;
    public static final GlobalLineType UNKNOWN = new GlobalLineType("UNKNOWN", 0, -1);
    public static final GlobalLineType DETAIL = new GlobalLineType("DETAIL", 1, 0);
    public static final GlobalLineType GENERAL = new GlobalLineType("GENERAL", 2, 1);

    private static GlobalLineType[] values() {
        return new GlobalLineType[]{UNKNOWN, DETAIL, GENERAL};
    }

    public static EnumEntries<GlobalLineType> getEntries() {
        return ENTRIES;
    }

    public static GlobalLineType valueOf(String str) {
        return Enum.valueOf(GlobalLineType.class, str);
    }

    public static GlobalLineType[] values() {
        return VALUES.clone();
    }

    private GlobalLineType(String str, int i2, int i3) {
        this.value = i3;
    }

    public int getValue() {
        return this.value;
    }

    static {
        GlobalLineType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    /* compiled from: GlobalLineType.kt */
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public GlobalLineType fromInt(int i2) {
            if (i2 == -1) {
                return GlobalLineType.UNKNOWN;
            }
            if (i2 == 0) {
                return GlobalLineType.DETAIL;
            }
            if (i2 == 1) {
                return GlobalLineType.GENERAL;
            }
            return GlobalLineType.UNKNOWN;
        }
    }
}
