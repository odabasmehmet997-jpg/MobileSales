package com.proje.mobilesales.core.enums;

import androidx.annotation.StringRes;
import com.proje.mobilesales.R;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class InvoiceStatus {
    private static final  EnumEntries ENTRIES;
    private static final  InvoiceStatus[] VALUES;
    public static final Companion Companion;

    @StringRes
    private int mResId;
    private int status;
    public static final InvoiceStatus ACTUAL = new InvoiceStatus("ACTUAL", 0, 0, R.string.str_real);
    public static final InvoiceStatus OFFER = new InvoiceStatus("OFFER", 1, 1, R.string.str_suggestion);

    private static InvoiceStatus[] values() {
        return new InvoiceStatus[]{ACTUAL, OFFER};
    }

    public static EnumEntries<InvoiceStatus> getEntries() {
        return ENTRIES;
    }

    public static InvoiceStatus valueOf(String str) {
        return Enum.valueOf(InvoiceStatus.class, str);
    }

    public static InvoiceStatus[] values() {
        return VALUES.clone();
    }

    static {
        InvoiceStatus[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i2) {
        this.status = i2;
    }

    public int getMResId() {
        return this.mResId;
    }

    public void setMResId(int i2) {
        this.mResId = i2;
    }

    private InvoiceStatus(String str, @StringRes int i2, int i3, int i4) {
        this.status = i3;
        this.mResId = i4;
    }

    private InvoiceStatus(String str, int i2, int i3) {
        this.status = i3;
        this.mResId = i3 == 1 ? R.string.str_suggestion : R.string.str_real;
    }

    public int getmResId() {
        return this.mResId;
    }

    /* compiled from: InvoiceStatus.kt */
    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public InvoiceStatus fromInvoiceStatus(int i2) {
            for (InvoiceStatus invoiceStatus : InvoiceStatus.getEntries()) {
                if (invoiceStatus.getStatus() == i2) {
                    return invoiceStatus;
                }
            }
            return InvoiceStatus.OFFER;
        }
    }
}
