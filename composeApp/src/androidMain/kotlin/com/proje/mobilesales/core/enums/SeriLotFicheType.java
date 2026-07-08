package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt; 
public final class SeriLotFicheType extends Enum<SeriLotFicheType> {
    private static final   EnumEntries ENTRIES = null;
    private static final   SeriLotFicheType[] VALUES = new SeriLotFicheType[0];
    public static final SeriLotFicheType InvoiceDispatch = new SeriLotFicheType("InvoiceDispatch", 0);
    public static final SeriLotFicheType WhTransfer = new SeriLotFicheType("WhTransfer", 1);

    private static SeriLotFicheType[] values() {
        return new SeriLotFicheType[]{InvoiceDispatch, WhTransfer};
    }

    public static EnumEntries<SeriLotFicheType> getEntries() {
        return ENTRIES;
    }

    public static SeriLotFicheType valueOf(String str) {
        return   Enum.valueOf(SeriLotFicheType, str);
    }

    private SeriLotFicheType(String str, int i2) {
        super(str);
    }

    @Override
    public int ordinal() {
        return super.ordinal();
    }

}
