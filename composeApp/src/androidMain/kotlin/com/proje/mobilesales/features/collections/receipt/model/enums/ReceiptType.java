package com.proje.mobilesales.features.collections.receipt.model.enums;

import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

public final class ReceiptType extends Enum<ReceiptType> {
    private static final EnumEntries ENTRIES;
    private static final ReceiptType[] VALUES;
    public int mfType;
    public static final ReceiptType CASH = new ReceiptType(DailyInfo.CASH, 0, 3);
    public static final ReceiptType CASE = new ReceiptType(DailyInfo.CASE, 1, 8);
    public static final ReceiptType CREDIT = new ReceiptType(DailyInfo.CREDIT, 2, 4);
    public static final ReceiptType CHEQUE = new ReceiptType("CHEQUE", 3, 5);
    public static final ReceiptType DEED = new ReceiptType("DEED", 4, 6);
    public static final Companion Companion = new Companion(null);

    public static ReceiptType[] values() {
        return new ReceiptType[]{ReceiptType.CASH, ReceiptType.CASE, ReceiptType.CREDIT, ReceiptType.CHEQUE, ReceiptType.DEED};
    }

    public static ReceiptType fromReceiptType(final int i) {
        return ReceiptType.Companion.fromReceiptType(i);
    }

    public static EnumEntries<ReceiptType> getEntries() {
        return ReceiptType.ENTRIES;
    }

    public static ReceiptType valueOf(final String str) {
        return valueOf(ReceiptType.class, str);
    }


    private ReceiptType(final String str, final int i, final int i2) {
        super(str,i);
        mfType = i2;
    }

    static {
        final ReceiptType[] values = ReceiptType.values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
 
    public static final class Companion {
        public Companion(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public ReceiptType fromReceiptType(final int i) {
            final ReceiptType[] values = values();
            for (final ReceiptType receiptType : values) {
                if (receiptType.mfType == i) {
                    return receiptType;
                }
            }
            return CASH;
        }
    }
}