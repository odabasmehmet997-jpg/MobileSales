package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;


public final class TrackType {
    private static final  EnumEntries ENTRIES;
    private static final  TrackType[] VALUES;
    private int type;
    public static final TrackType NON_TRACK = new TrackType("NON_TRACK", 0, 0);
    public static final TrackType LOT = new TrackType("LOT", 1, 1);
    public static final TrackType SERIAL = new TrackType("SERIAL", 2, 2);
    public static final TrackType SERIAL_GROUP = new TrackType("SERIAL_GROUP", 3, 3);

    private static TrackType[] values() {
        return new TrackType[]{NON_TRACK, LOT, SERIAL, SERIAL_GROUP};
    }

    public static EnumEntries<TrackType> getEntries() {
        return ENTRIES;
    }

    public static TrackType valueOf(String str) {
        return Enum.valueOf(TrackType.class, str);
    }

    public static TrackType[] values() {
        return VALUES.clone();
    }

    private TrackType(String str, int i2, int i3) {
        this.type = i3;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    static {
        TrackType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
