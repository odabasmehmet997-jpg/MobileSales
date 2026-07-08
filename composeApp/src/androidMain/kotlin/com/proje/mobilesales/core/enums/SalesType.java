package com.proje.mobilesales.core.enums;

import androidx.annotation.StringRes;
import com.proje.mobilesales.R;
import com.proje.mobilesales.features.dailyinformation.model.DailyInfo;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

import java.io.Serializable;

public final class SalesType implements Serializable {
    private static final EnumEntries ENTRIES;
    private static final SalesType[] VALUES;
    public static final Companion Companion;
    private int mResId;
    private int mValue;
    public int mfType;
    public static final SalesType FREE = new SalesType("FREE", 0, 0, R.string.empty_text, -1);
    public static final SalesType ORDER = new SalesType(DailyInfo.ORDER, 1, 1, R.string.str_sales_order, 0);
    public static final SalesType INVOICE = new SalesType("INVOICE", 2, 2, R.string.str_sales_invoice, 2);
    public static final SalesType RETURN_INVOICE = new SalesType("RETURN_INVOICE", 3, 3, R.string.str_sales_return_invoice, 2);
    public static final SalesType DISPATCH = new SalesType("DISPATCH", 4, 4, R.string.str_sales_dispatch, 1);
    public static final SalesType RETURN_DISPATCH = new SalesType("RETURN_DISPATCH", 5, 5, R.string.str_sales_return_dispatch, 1);
    public static final SalesType ONE_TO_ONE_CHANGE = new SalesType("ONE_TO_ONE_CHANGE", 6, 6, R.string.str_sales_one_to_one_change, 13);
    public static final SalesType DEMAND = new SalesType(DailyInfo.DEMAND, 7, 7, R.string.str_sales_demand, 17);
    public static final SalesType RETAIL_INVOICE = new SalesType("RETAIL_INVOICE", 8, 8, R.string.str_sales_retail_invoice, 19);
    public static final SalesType RETAIL_RETURN_INVOICE = new SalesType("RETAIL_RETURN_INVOICE", 9, 9, R.string.str_sales_retail_return_invoice, 19);
    public static final SalesType WHTRANSFER = new SalesType(DailyInfo.WHTRANSFER, 10, 10, R.string.str_sales_transfer, 21);

    private static SalesType[] values() {
        return new SalesType[]{FREE, ORDER, INVOICE, RETURN_INVOICE, DISPATCH, RETURN_DISPATCH, ONE_TO_ONE_CHANGE, DEMAND, RETAIL_INVOICE, RETAIL_RETURN_INVOICE, WHTRANSFER};
    }

    public static SalesType fromSalesType(int i2) {
        return Companion.fromSalesType(i2);
    }

    public static EnumEntries<SalesType> getEntries() {
        return ENTRIES;
    }

    public static SalesType valueOf(String str) {
        return Enum.valueOf(SalesType.class, str);
    }

    public static SalesType[] values() {
        return VALUES.clone();
    }

    private SalesType(String str, @StringRes int i2, int i3, int i4, int i5) {
        this.mValue = i3;
        this.mResId = i4;
        this.mfType = i5;
    }

    public int getMResId() {
        return this.mResId;
    }

    public int getMValue() {
        return this.mValue;
    }

    public void setMResId(int i2) {
        this.mResId = i2;
    }

    public void setMValue(int i2) {
        this.mValue = i2;
    }

    static {
        SalesType[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    public int getmResId() {
        return this.mResId;
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

        public SalesType fromSalesType(int i2) {
            for (SalesType salesType : SalesType.values()) {
                if (salesType.getMValue() == i2) {
                    return salesType;
                }
            }
            return SalesType.FREE;
        }
    }
}
