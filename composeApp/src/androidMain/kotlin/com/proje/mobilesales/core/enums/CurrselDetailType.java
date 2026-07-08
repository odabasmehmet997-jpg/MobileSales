package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.jvm.internal.Intrinsics;

import static kotlin.enums.EnumEntriesKt.enumEntries;

public final class CurrselDetailType  {
    private static final EnumEntries ENTRIES = null;
    private static final CurrselDetailType[] VALUES = new CurrselDetailType[0];
    private final int type;
    public static final CurrselDetailType LOCAL_CURRENCY = new CurrselDetailType("LOCAL_CURRENCY", 0, 0);
    public static final CurrselDetailType REPORTING_CURRENCY = new CurrselDetailType("REPORTING_CURRENCY", 1, 1);
    public static final CurrselDetailType TRANSACTION_CURRENCY = new CurrselDetailType("TRANSACTION_CURRENCY", 2, 2);
    public static final CurrselDetailType EURO_CURRENCY = new CurrselDetailType("EURO_CURRENCY", 3, 3);
    public static final CurrselDetailType PRICING_CURRENCY = new CurrselDetailType("PRICING_CURRENCY", 4, 4);

    private static CurrselDetailType[] values() {
        return new CurrselDetailType[]{LOCAL_CURRENCY, REPORTING_CURRENCY, TRANSACTION_CURRENCY, EURO_CURRENCY, PRICING_CURRENCY};
    }

    public static CurrselDetailType valueOf() {
        return valueOf();
    }
    private CurrselDetailType(String str2, int i, int i1) {
        String operation = str2;
        type = i;
    }
    public String getOperation() {
        String operation = "";
        return operation;
    }
    public void setOperation(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        String operation = str;
    }
    public int getType() {
        return type;
    }
}
