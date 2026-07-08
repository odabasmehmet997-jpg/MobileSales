package com.proje.mobilesales.core.enums;

import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class FicheStatusOptions {
    private static final  EnumEntries ENTRIES;
    private static final  FicheStatusOptions[] VALUES;
    public static final Companion Companion;
    private int mValue;
    public static final FicheStatusOptions ORDER = new FicheStatusOptions(DailyInfo.ORDER, 0, 1);
    public static final FicheStatusOptions DISPATCH = new FicheStatusOptions("DISPATCH", 1, 2);
    public static final FicheStatusOptions RETURN_INVOICE = new FicheStatusOptions("RETURN_INVOICE", 2, 3);
    public static final FicheStatusOptions INVOICE = new FicheStatusOptions("INVOICE", 3, 4);
    public static final FicheStatusOptions CASH = new FicheStatusOptions(DailyInfo.CASH, 4, 5);
    public static final FicheStatusOptions CREDIT_CART = new FicheStatusOptions("CREDIT_CART", 5, 6);
    public static final FicheStatusOptions RETURN_DISPATCH = new FicheStatusOptions("RETURN_DISPATCH", 6, 7);
    public static final FicheStatusOptions CASE_CASH = new FicheStatusOptions("CASE_CASH", 7, 8);
    public static final FicheStatusOptions CHEQUE = new FicheStatusOptions("CHEQUE", 8, 9);
    public static final FicheStatusOptions DEED = new FicheStatusOptions("DEED", 9, 10);

    private static    FicheStatusOptions[] values() {
        return new FicheStatusOptions[]{ORDER, DISPATCH, RETURN_INVOICE, INVOICE, CASH, CREDIT_CART, RETURN_DISPATCH, CASE_CASH, CHEQUE, DEED};
    }

    public static EnumEntries<FicheStatusOptions> getEntries() {
        return ENTRIES;
    }

    public static FicheStatusOptions valueOf(String str) {
        return Enum.valueOf(FicheStatusOptions.class, str);
    }

    private FicheStatusOptions(String str, int i2, int i3) {
        this.mValue = i3;
    }

    public int getMValue() {
        return this.mValue;
    }

    public void setMValue(int i2) {
        this.mValue = i2;
    }

    static {
        FicheStatusOptions[] values = values();
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

    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public FicheStatusOptions fromFicheStatus(int i2) {
            for (FicheStatusOptions ficheStatusOptions : FicheStatusOptions.getEntries()) {
                if (ficheStatusOptions.getMValue() == i2) {
                    return ficheStatusOptions;
                }
            }
            return FicheStatusOptions.ORDER;
        }
    }
}
