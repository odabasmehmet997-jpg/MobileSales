package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class ErpInvoiceType extends Enum<ErpInvoiceType> {
    private static final  EnumEntries ENTRIES;
    private static final  ErpInvoiceType[] VALUES;
    public static final Companion Companion;
    private int mValue;
    public static final ErpInvoiceType None = new ErpInvoiceType("None", 0, -1);
    public static final ErpInvoiceType PaperInvoice = new ErpInvoiceType("PaperInvoice", 1, 0);
    public static final ErpInvoiceType EInvoice = new ErpInvoiceType("EInvoice", 2, 1);
    public static final ErpInvoiceType EArchiveInvoice = new ErpInvoiceType("EArchiveInvoice", 3, 2);
    public static final ErpInvoiceType EArchiveInternetInvoice = new ErpInvoiceType("EArchiveInternetInvoice", 4, 3);

    private static ErpInvoiceType[] values() {
        return new ErpInvoiceType[]{None, PaperInvoice, EInvoice, EArchiveInvoice, EArchiveInternetInvoice};
    }

    public static ErpInvoiceType fromErpInvoiceType(int i2) {
        return Companion.fromErpInvoiceType(i2);
    }

    public static EnumEntries<ErpInvoiceType> getEntries() {
        return ENTRIES;
    }

    public static ErpInvoiceType valueOf(String str) {
        return Enum.valueOf(ErpInvoiceType.class, str);
    }

    public static ErpInvoiceType[] values() {
        return VALUES.clone();
    }

    private ErpInvoiceType(String str, int i2, int i3) {
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
        ErpInvoiceType[] values = values();
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

    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public ErpInvoiceType fromErpInvoiceType(int i2) {
            for (ErpInvoiceType erpInvoiceType : ErpInvoiceType.getEntries()) {
                if (erpInvoiceType.getMValue() == i2) {
                    return erpInvoiceType;
                }
            }
            return ErpInvoiceType.None;
        }
    }
}
