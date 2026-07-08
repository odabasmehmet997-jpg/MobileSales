package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class DetailBarcodeFicheType extends Enum<DetailBarcodeFicheType> {
    private static final   EnumEntries ENTRIES;
    private static final  DetailBarcodeFicheType[] VALUES;
    public static final DetailBarcodeFicheType InvoiceDispatch = new DetailBarcodeFicheType("InvoiceDispatch", 0);
    public static final DetailBarcodeFicheType WhTransfer = new DetailBarcodeFicheType("WhTransfer", 1);
    public static final DetailBarcodeFicheType Order = new DetailBarcodeFicheType("Order", 2);

    private static DetailBarcodeFicheType[] values() {
        return new DetailBarcodeFicheType[]{InvoiceDispatch, WhTransfer, Order};
    }
    public static EnumEntries<DetailBarcodeFicheType> getEntries() {
        return ENTRIES;
    }
    public static DetailBarcodeFicheType valueOf(String str) {
        return Enum.valueOf(DetailBarcodeFicheType.class, str);
    }
    private DetailBarcodeFicheType(String str, int i2) {
        super(str,i2);
    }
    static {
        VALUES = values();
        ENTRIES = EnumEntriesKt.enumEntries(VALUES);
    }
}

