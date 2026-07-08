package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class EInvoiceTyp {
    private static final  EnumEntries ENTRIES;
    private static final  EInvoiceTyp[] VALUES;
    public static final Companion Companion;
    private int mValue;
    public static final EInvoiceTyp NotSelected = new EInvoiceTyp("NotSelected", 0, 0);
    public static final EInvoiceTyp OzelMatrah = new EInvoiceTyp("OzelMatrah", 1, 1);
    public static final EInvoiceTyp Istisna = new EInvoiceTyp("Istisna", 2, 2);
    public static final EInvoiceTyp AracTescil = new EInvoiceTyp("AracTescil", 3, 3);
    public static final EInvoiceTyp Tevkifat = new EInvoiceTyp("Tevkifat", 4, 4);
    public static final EInvoiceTyp Sgk = new EInvoiceTyp("Sgk", 5, 5);

    private static EInvoiceTyp[] values() {
        return new EInvoiceTyp[]{NotSelected, OzelMatrah, Istisna, AracTescil, Tevkifat, Sgk};
    }

    public static EnumEntries<EInvoiceTyp> getEntries() {
        return ENTRIES;
    }

    public static EInvoiceTyp valueOf(String str) {
        return Enum.valueOf(EInvoiceTyp.class, str);
    }

    public static EInvoiceTyp[] values() {
        return VALUES.clone();
    }

    private EInvoiceTyp(String str, int i2, int i3) {
        this.mValue = i3;
    }

    public int getMValue() {
        return this.mValue;
    }

    public void setMValue(int i2) {
        this.mValue = i2;
    }

    static {
        EInvoiceTyp[] values = values();
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

    /* compiled from: EInvoiceTyp.kt */
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public EInvoiceTyp fromEInvoiceTyp(int i2) {
            for (EInvoiceTyp eInvoiceTyp : EInvoiceTyp.getEntries()) {
                if (eInvoiceTyp.getMValue() == i2) {
                    return eInvoiceTyp;
                }
            }
            return EInvoiceTyp.NotSelected;
        }
    }
}
