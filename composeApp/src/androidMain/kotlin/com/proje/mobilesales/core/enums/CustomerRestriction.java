package com.proje.mobilesales.core.enums;

import androidx.exifinterface.media.ExifInterface;
import com.proje.mobilesales.BuildConfig;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
public final class CustomerRestriction extends Enum<CustomerRestriction> {
    private static final  EnumEntries ENTRIES;
    private static final  CustomerRestriction[] VALUES;
    public static final Companion Companion;
    private String mValue;
    public static final CustomerRestriction OPEN = new CustomerRestriction("OPEN", 0, ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
    public static final CustomerRestriction RESTRICTION_ON_SALES = new CustomerRestriction("RESTRICTION_ON_SALES", 1, "B");
    public static final CustomerRestriction RESTRICTION_ON_ALL_FICHES = new CustomerRestriction("RESTRICTION_ON_ALL_FICHES", 2, "C");
    public static final CustomerRestriction DETAILED_RESTRICTION = new CustomerRestriction("DETAILED_RESTRICTION", 3, "D");
    public static final CustomerRestriction INVOICE_RESTRICTION = new CustomerRestriction("INVOICE_RESTRICTION", 4, BuildConfig.NETSIS_DEMO_PASSWORD);
    public static final CustomerRestriction DISPATCH_RESTRICTION = new CustomerRestriction("DISPATCH_RESTRICTION", 5, ExifInterface.GPS_MEASUREMENT_3D);
    public static final CustomerRestriction ORDER_RESTRICTION = new CustomerRestriction("ORDER_RESTRICTION", 6, "7");

    private static CustomerRestriction[] values() {
        return new CustomerRestriction[]{OPEN, RESTRICTION_ON_SALES, RESTRICTION_ON_ALL_FICHES, DETAILED_RESTRICTION, INVOICE_RESTRICTION, DISPATCH_RESTRICTION, ORDER_RESTRICTION};
    }

    public static CustomerRestriction getCustomerRestriction(String str) {
        return Companion.getCustomerRestriction(str);
    }

    public static EnumEntries<CustomerRestriction> getEntries() {
        return ENTRIES;
    }

    public static CustomerRestriction valueOf(String str) {
        return Enum.valueOf(CustomerRestriction.class, str);
    }

    private CustomerRestriction(String str, int i2, String str2) {
        super(str,i2);
        this.mValue = str2;
    }

    public String getMValue() {
        return this.mValue;
    }

    public void setMValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mValue = str;
    }

    static {
        CustomerRestriction[] values = values();
        VALUES = values;
        ENTRIES = EnumEntriesKt.enumEntries(values);
        Companion = new Companion(null);
    }

    public static final class Companion {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public CustomerRestriction getCustomerRestriction(String value) {
            Intrinsics.checkNotNullParameter(value, "value");
            for (CustomerRestriction customerRestriction : CustomerRestriction.values()) {
                if (Intrinsics.areEqual(customerRestriction.getMValue(), value)) {
                    return customerRestriction;
                }
            }
            return CustomerRestriction.OPEN;
        }
    }
}
