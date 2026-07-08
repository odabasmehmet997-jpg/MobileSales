package com.proje.mobilesales.core.enums;

import androidx.annotation.StringRes;
import com.proje.mobilesales.R;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class CustomerPriceType extends Enum<CustomerPriceType> {
    private static final  EnumEntries ENTRIES;
    private static final  CustomerPriceType[] VALUES;
    private int mResourceId;
    public static final CustomerPriceType GET_CUSTOMER_LAST_SELL_PRICE = new CustomerPriceType("GET_CUSTOMER_LAST_SELL_PRICE", 0, R.string.str_get_customer_last_sell_price);
    public static final CustomerPriceType GET_CUSTOMER_LAST_BUY_PRICE = new CustomerPriceType("GET_CUSTOMER_LAST_BUY_PRICE", 1, R.string.str_get_customer_last_buy_price);
    public static final CustomerPriceType GET_ALL_CUSTOMER_LAST_SELL_PRICE = new CustomerPriceType("GET_ALL_CUSTOMER_LAST_SELL_PRICE", 2, R.string.str_get_all_customer_sell_price);
    public static final CustomerPriceType GET_ALL_CUSTOMER_LAST_BUY_PRICE = new CustomerPriceType("GET_ALL_CUSTOMER_LAST_BUY_PRICE", 3, R.string.str_get_all_customer_buy_price);

    private static CustomerPriceType[] values() {
        return new CustomerPriceType[]{CustomerPriceType.GET_CUSTOMER_LAST_SELL_PRICE, CustomerPriceType.GET_CUSTOMER_LAST_BUY_PRICE, CustomerPriceType.GET_ALL_CUSTOMER_LAST_SELL_PRICE, CustomerPriceType.GET_ALL_CUSTOMER_LAST_BUY_PRICE};
    }

    public static EnumEntries<CustomerPriceType> getEntries() {
        return CustomerPriceType.ENTRIES;
    }

    public static CustomerPriceType valueOf(final String str) {
        return valueOf(CustomerPriceType.class, str);
    }

    private CustomerPriceType(final String str, final int i2, final int i3) {
        super(str,i2);
        mResourceId = i3;
    }

    public int getMResourceId() {
        return mResourceId;
    }

    public void setMResourceId(final int i2) {
        mResourceId = i2;
    }

    static {
        final CustomerPriceType[] values = CustomerPriceType.values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }

    public int getmResourceId() {
        return mResourceId;
    }

    public void setmResourceId(final int i2) {
        mResourceId = i2;
    }
}
