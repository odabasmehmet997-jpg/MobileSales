package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class FicheMode extends Enum<FicheMode> {
    private static final EnumEntries ENTRIES;
    private static final  FicheMode[] VALUES;
    public static final FicheMode NEW = new FicheMode("NEW", 0);
    public static final FicheMode EDIT = new FicheMode("EDIT", 1);
    public static final FicheMode ANALYSE = new FicheMode("ANALYSE", 2);
    public static final FicheMode COPY = new FicheMode("COPY", 3);
    public static final FicheMode SENDMAIL = new FicheMode("SENDMAIL", 4);

    private static FicheMode[] values() {
        return new FicheMode[]{NEW, EDIT, ANALYSE, COPY, SENDMAIL};
    }

    public static EnumEntries<FicheMode> getEntries() {
        return ENTRIES;
    }

    public static FicheMode valueOf(String str) {
        return Enum.valueOf(FicheMode.class, str);
    }

    public static FicheMode[] values() {
        return VALUES.clone();
    }

    private FicheMode(String str, int i2) {
        super(str,i2);
    }

    static {
        FicheMode[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

}
