package com.proje.mobilesales.core.enums;

import androidx.annotation.StringRes;
import com.proje.mobilesales.R;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class FTypes {
    private static final  EnumEntries ENTRIES;
    private static final  FTypes[] VALUES;
    private final int mFType;
    private int mResId;
    public static final FTypes INVOICE = new FTypes("INVOICE", 0, 1, R.string.str_sales_invoice);
    public static final FTypes DISPATCH = new FTypes("DISPATCH", 1, 0, R.string.str_sales_dispatch);
    public static final FTypes RETAIL_INVOICE = new FTypes("RETAIL_INVOICE", 2, 3, R.string.str_sales_retail_invoice);
    public static final FTypes ONE_TO_ONE_CHANGE = new FTypes("ONE_TO_ONE_CHANGE", 3, 2, R.string.str_sales_one_to_one_change);

    private static FTypes[] values() {
        return new FTypes[]{INVOICE, DISPATCH, RETAIL_INVOICE, ONE_TO_ONE_CHANGE};
    }

    public static EnumEntries<FTypes> getEntries() {
        return ENTRIES;
    }

    public static FTypes valueOf(String str) {
        return Enum.valueOf(FTypes.class, str);
    }

    public static FTypes[] values() {
        return VALUES.clone();
    }

    private FTypes(String str, @StringRes int i2, int i3, int i4) {
        this.mFType = i3;
        this.mResId = i4;
    }

    public int getMResId() {
        return this.mResId;
    }

    public void setMResId(int i2) {
        this.mResId = i2;
    }

    static {
        FTypes[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    public int getmFType() {
        return this.mFType;
    }

    public int getmResId() {
        return this.mResId;
    }
}
