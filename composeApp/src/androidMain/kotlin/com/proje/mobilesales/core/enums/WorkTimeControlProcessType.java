package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class WorkTimeControlProcessType extends Enum<WorkTimeControlProcessType> {
    private static final  EnumEntries ENTRIES;
    private static final  WorkTimeControlProcessType[] VALUES;
    private int mValue;
    public static final WorkTimeControlProcessType Login = new WorkTimeControlProcessType("Login", 0, 0);
    public static final WorkTimeControlProcessType DataEntry = new WorkTimeControlProcessType("DataEntry", 1, 1);
    public static final WorkTimeControlProcessType TransferSend = new WorkTimeControlProcessType("TransferSend", 2, 2);
    public static final WorkTimeControlProcessType TransferGet = new WorkTimeControlProcessType("TransferGet", 3, 3);
    public static final WorkTimeControlProcessType Report = new WorkTimeControlProcessType("Report", 4, 4);
    public static final WorkTimeControlProcessType Visit = new WorkTimeControlProcessType("Visit", 5, 5);
    public static final WorkTimeControlProcessType Penetration = new WorkTimeControlProcessType("Penetration", 6, 6);
    public static final WorkTimeControlProcessType Receipt = new WorkTimeControlProcessType("Receipt", 7, 7);
    public static final WorkTimeControlProcessType Sales = new WorkTimeControlProcessType("Sales", 8, 8);
    public static final WorkTimeControlProcessType Order = new WorkTimeControlProcessType("Order", 9, 9);

    private static WorkTimeControlProcessType[] values() {
        return new WorkTimeControlProcessType[]{Login, DataEntry, TransferSend, TransferGet, Report, Visit, Penetration, Receipt, Sales, Order};
    }

    public static EnumEntries<WorkTimeControlProcessType> getEntries() {
        return ENTRIES;
    }

    public static WorkTimeControlProcessType valueOf(String str) {
        return Enum.valueOf(WorkTimeControlProcessType.class, str);
    }

    public static WorkTimeControlProcessType[] values() {
        return VALUES.clone();
    }

    private WorkTimeControlProcessType(String str, int i, int i2) {
        super(str,i2);
        this.mValue = i2;
    }

    static {
        WorkTimeControlProcessType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    public int getmValue() {
        return this.mValue;
    }

    public void setmValue(int i) {
        this.mValue = i;
    }
}
