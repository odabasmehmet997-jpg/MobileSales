package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class NotifyType {
    private static final  EnumEntries ENTRIES;
    private static final  NotifyType[] VALUES;
    public static final NotifyType _RESUME = new NotifyType("_RESUME", 0);
    public static final NotifyType _STOP = new NotifyType("_STOP", 1);
    public static final NotifyType _ERROR = new NotifyType("_ERROR", 2);
    public static final NotifyType _START = new NotifyType("_START", 3);
    public static final NotifyType _UPDATE_ADAPTER = new NotifyType("_UPDATE_ADAPTER", 4);
    public static final NotifyType _READY = new NotifyType("_READY", 5);

    private static NotifyType[] values() {
        return new NotifyType[]{_RESUME, _STOP, _ERROR, _START, _UPDATE_ADAPTER, _READY};
    }

    public static EnumEntries<NotifyType> getEntries() {
        return ENTRIES;
    }

    public static NotifyType valueOf(String str) {
        return Enum.valueOf(NotifyType.class, str);
    }

    public static NotifyType[] values() {
        return VALUES.clone();
    }

    private NotifyType(String str, int i2) {
    }

    static {
        NotifyType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
