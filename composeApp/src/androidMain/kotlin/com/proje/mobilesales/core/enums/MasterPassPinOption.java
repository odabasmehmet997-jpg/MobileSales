package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.features.sales.view.newadd.T;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class MasterPassPinOption {
    private static final  EnumEntries ENTRIES;
    private static final MasterPassPinOption[] VALUES;
    public static final MasterPassPinOption VALIDATESMSPURCHASE = new MasterPassPinOption("VALIDATESMSPURCHASE", 0);
    public static final MasterPassPinOption NONE = new MasterPassPinOption("NONE", 1);

    private static  MasterPassPinOption[] values() {
        return new MasterPassPinOption[]{VALIDATESMSPURCHASE, NONE};
    }
    public static MasterPassPinOption valueOf(String str) {
        Class<T> MasterPassPinOption = null;
        MasterPassPinOption masterPassPinOption;
        masterPassPinOption = ( MasterPassPinOption ) Enum.valueOf(MasterPassPinOption, str);
        return masterPassPinOption;
    }
    private MasterPassPinOption(String str, int r2) {
    }
    static {
        MasterPassPinOption[] masterPassPinOptionArrvalues = values();
        VALUES = masterPassPinOptionArrvalues;
        ENTRIES = enumEntries(masterPassPinOptionArrvalues);
    }

    private static EnumEntries enumEntries(MasterPassPinOption[] masterPassPinOptionArrvalues) {
        EnumEntries<? extends Enum<? extends Enum<?>>> enums;
        enums = EnumEntriesKt.enumEntries(masterPassPinOptionArrvalues);
        return enums;
    }
}