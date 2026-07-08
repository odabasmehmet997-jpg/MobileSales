package com.proje.mobilesales.features.collections.receipt.model.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
 
public final class ReceiptFicheShortType extends Enum<ReceiptFicheShortType> {
    private static final EnumEntries ENTRIES;
    private static final ReceiptFicheShortType[] VALUES;
    private int dbType;
    private int fType;
    public static final ReceiptFicheShortType CASHFTYPE = new ReceiptFicheShortType("CASHFTYPE", 0, 0, 0);
    public static final ReceiptFicheShortType CREDITFTYPE = new ReceiptFicheShortType("CREDITFTYPE", 1, 1, 1);
    public static final ReceiptFicheShortType CASEFTYPE = new ReceiptFicheShortType("CASEFTYPE", 2, 2, -1);
    public static final ReceiptFicheShortType CHEQUEFTYPE = new ReceiptFicheShortType("CHEQUEFTYPE", 3, 3, 0);
    public static final ReceiptFicheShortType DEEDFTYPE = new ReceiptFicheShortType("DEEDFTYPE", 4, 4, 1);

    public static   ReceiptFicheShortType[] values() {
        return new ReceiptFicheShortType[]{ReceiptFicheShortType.CASHFTYPE, ReceiptFicheShortType.CREDITFTYPE, ReceiptFicheShortType.CASEFTYPE, ReceiptFicheShortType.CHEQUEFTYPE, ReceiptFicheShortType.DEEDFTYPE};
    }

    public static EnumEntries<ReceiptFicheShortType> getEntries() {
        return ReceiptFicheShortType.ENTRIES;
    }

    public static ReceiptFicheShortType valueOf(final String str) {
        return valueOf(ReceiptFicheShortType.class, str);
    }



    private ReceiptFicheShortType(final String str, final int i, final int i2, final int i3) {
        super(str,i);
        fType = i2;
        dbType = i3;
    }

    public   int getDbType() {
        return dbType;
    }

    public   void setDbType(final int i) {
        dbType = i;
    }

    static {
        final ReceiptFicheShortType[] values = ReceiptFicheShortType.values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    public   int getFType() {
        return fType;
    }

    public   void setFType(final int i) {
        fType = i;
    }
}