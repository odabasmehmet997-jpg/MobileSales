package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class FicheType extends Enum<FicheType> {
    private static final  EnumEntries ENTRIES;
    private static final  FicheType[] VALUES;
    public static final Companion Companion;
    private int mValue;
    public static final FicheType FREE = new FicheType("FREE", 0, -1);
    public static final FicheType INVOICE = new FicheType("INVOICE", 1, 1);
    public static final FicheType ORDER = new FicheType(DailyInfo.ORDER, 2, 2);
    public static final FicheType CASH = new FicheType(DailyInfo.CASH, 3, 3);
    public static final FicheType CREDIT_CART = new FicheType("CREDIT_CART", 4, 4);
    public static final FicheType CHEQUE = new FicheType("CHEQUE", 5, 5);
    public static final FicheType DEED = new FicheType("DEED", 6, 6);
    public static final FicheType DELIVERY_NOTE = new FicheType("DELIVERY_NOTE", 7, 7);
    public static final FicheType DISPATCH = new FicheType("DISPATCH", 8, 8);
    public static final FicheType VISIT = new FicheType(DailyInfo.VISIT, 9, 9);
    public static final FicheType CASE_CASH = new FicheType("CASE_CASH", 10, 10);
    public static final FicheType TODO = new FicheType("TODO", 11, 11);
    public static final FicheType CUSTOMER = new FicheType("CUSTOMER", 12, 12);
    public static final FicheType ONE_TO_ONE = new FicheType("ONE_TO_ONE", 13, 13);
    public static final FicheType WORK_INFO = new FicheType("WORK_INFO", 14, 14);
    public static final FicheType SURVEY = new FicheType("SURVEY", 15, 15);
    public static final FicheType PENETRATION = new FicheType("PENETRATION", 16, 16);
    public static final FicheType DEMAND = new FicheType(DailyInfo.DEMAND, 17, 17);
    public static final FicheType ALL = new FicheType("ALL", 18, 18);
    public static final FicheType RETAILINVOICE = new FicheType("RETAILINVOICE", 19, 20);
    public static final FicheType CABIN = new FicheType("CABIN", 20, 21);
    public static final FicheType WHTRANSFER = new FicheType(DailyInfo.WHTRANSFER, 21, 22);

    private static FicheType[] values() {
        return new FicheType[]{FREE, INVOICE, ORDER, CASH, CREDIT_CART, CHEQUE, DEED, DELIVERY_NOTE, DISPATCH, VISIT, CASE_CASH, TODO, CUSTOMER, ONE_TO_ONE, WORK_INFO, SURVEY, PENETRATION, DEMAND, ALL, RETAILINVOICE, CABIN, WHTRANSFER};
    }

    public static EnumEntries<FicheType> getEntries() {
        return ENTRIES;
    }

    public static FicheType valueOf(String str) {
        return Enum.valueOf(FicheType.class, str);
    }

    public static FicheType[] values() {
        return VALUES.clone();
    }

    private FicheType(String str, int i2, int i3) {
        super(str,i2);
        this.mValue = i3;
    }

    public int getMValue() {
        return this.mValue;
    }

    public void setMValue(int i2) {
        this.mValue = i2;
    }

    static {
        FicheType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    public int getmValue() {
        return this.mValue;
    }

    public void setmValue(int i2) {
        this.mValue = i2;
    }

    public int ordinal() {
        return 0;
    }

    /* compiled from: FicheType.kt */
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public FicheType fromFicheType(int i2) {
            for (FicheType ficheType : FicheType.getEntries()) {
                if (ficheType.getMValue() == i2) {
                    return ficheType;
                }
            }
            return FicheType.FREE;
        }
    }
}
