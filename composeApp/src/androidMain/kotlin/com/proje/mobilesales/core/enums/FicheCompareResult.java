package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class FicheCompareResult {
    private static final  EnumEntries ENTRIES;
    private static final  FicheCompareResult[] VALUES;
    public static final FicheCompareResult COULDNOTREADLOCALFICHE = new FicheCompareResult("COULDNOTREADLOCALFICHE", 0);
    public static final FicheCompareResult COULDNOTREADREMOTEFICHE = new FicheCompareResult("COULDNOTREADREMOTEFICHE", 1);
    public static final FicheCompareResult COULDNOTPARSEREMOTEXML = new FicheCompareResult("COULDNOTPARSEREMOTEXML", 2);
    public static final FicheCompareResult REMOTEANDLOCALDIFFERENT = new FicheCompareResult("REMOTEANDLOCALDIFFERENT", 3);
    public static final FicheCompareResult REMOTEANDLOCALEQUAL = new FicheCompareResult("REMOTEANDLOCALEQUAL", 4);

    private static FicheCompareResult[] values() {
        return new FicheCompareResult[]{COULDNOTREADLOCALFICHE, COULDNOTREADREMOTEFICHE, COULDNOTPARSEREMOTEXML, REMOTEANDLOCALDIFFERENT, REMOTEANDLOCALEQUAL};
    }

    public static EnumEntries<FicheCompareResult> getEntries() {
        return ENTRIES;
    }

    public static FicheCompareResult valueOf(String str) {
        return Enum.valueOf(FicheCompareResult.class, str);
    }

    public static FicheCompareResult[] values() {
        return VALUES.clone();
    }

    private FicheCompareResult(String str, int i2) {
    }

    static {
        FicheCompareResult[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
