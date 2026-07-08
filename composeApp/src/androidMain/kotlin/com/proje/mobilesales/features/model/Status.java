package com.proje.mobilesales.features.model;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
 
public final class Status extends Enum<Status> {
    private static final EnumEntries ENTRIES;
    private static final Status[] VALUES;
    public static final Status LOADING = new Status("LOADING", 0);
    public static final Status SUCCESS = new Status("SUCCESS", 1);
    public static final Status ERROR = new Status("ERROR", 2);

    private static   Status[] values() {
        return new Status[]{Status.LOADING, Status.SUCCESS, Status.ERROR};
    }

    public static EnumEntries<Status> getEntries() {
        return Status.ENTRIES;
    }

    public static Status valueOf(final String str) {
        return valueOf(Status.class, str);
    }


    private Status(final String str, final int i) {
        super(str,i);
    }

    static {
        final Status[] values = Status.values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}