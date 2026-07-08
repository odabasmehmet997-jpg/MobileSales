package com.proje.mobilesales.core.enums;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

public final class CustomerSalesAuthorization extends Enum<CustomerSalesAuthorization> {
    private static final  EnumEntries ENTRIES;
    private static final  CustomerSalesAuthorization[] VALUES;
    private int mValue;
    public static final CustomerSalesAuthorization NO_AUTHORITY = new CustomerSalesAuthorization("NO_AUTHORITY", 0, 0);
    public static final CustomerSalesAuthorization AUTHORIZED_FOR_SALES = new CustomerSalesAuthorization("AUTHORIZED_FOR_SALES", 1, 1);

    private static CustomerSalesAuthorization[] values() {
        return new CustomerSalesAuthorization[]{NO_AUTHORITY, AUTHORIZED_FOR_SALES};
    }

    public static EnumEntries<CustomerSalesAuthorization> getEntries() {
        return ENTRIES;
    }

    public static CustomerSalesAuthorization valueOf(String str) {
        return Enum.valueOf(CustomerSalesAuthorization.class, str);
    }

    private CustomerSalesAuthorization(String str, int i2, int i3) {
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
        CustomerSalesAuthorization[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
    }
}
