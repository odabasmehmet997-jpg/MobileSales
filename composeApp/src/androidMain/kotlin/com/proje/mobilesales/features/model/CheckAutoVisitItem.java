package com.proje.mobilesales.features.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CheckAutoVisitItem {
    private int autoVisitId;
    private String customerDefinition;
    private boolean differentCustomer;
    private boolean isExisting;

    public CheckAutoVisitItem() {
        this(false, false, 0, null, 15, null);
    }

    public static CheckAutoVisitItem copydefault(final CheckAutoVisitItem checkAutoVisitItem, boolean z, boolean z2, int i2, String str, final int i3, final Object obj) {
        if (0 != (i3 & 1)) {
            z = checkAutoVisitItem.isExisting;
        }
        if (0 != (i3 & 2)) {
            z2 = checkAutoVisitItem.differentCustomer;
        }
        if (0 != (i3 & 4)) {
            i2 = checkAutoVisitItem.autoVisitId;
        }
        if (0 != (i3 & 8)) {
            str = checkAutoVisitItem.customerDefinition;
        }
        return checkAutoVisitItem.copy(z, z2, i2, str);
    }

    public boolean component1() {
        return isExisting;
    }

    public boolean component2() {
        return differentCustomer;
    }

    public int component3() {
        return autoVisitId;
    }

    public String component4() {
        return customerDefinition;
    }

    public CheckAutoVisitItem copy(final boolean z, final boolean z2, final int i2, final String str) {
        return new CheckAutoVisitItem(z, z2, i2, str);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CheckAutoVisitItem checkAutoVisitItem)) {
            return false;
        }
        return isExisting == checkAutoVisitItem.isExisting && differentCustomer == checkAutoVisitItem.differentCustomer && autoVisitId == checkAutoVisitItem.autoVisitId && Intrinsics.areEqual(customerDefinition, checkAutoVisitItem.customerDefinition);
    }

    public int hashCode() {
        final int hashCode = ((((Boolean.hashCode(isExisting) * 31) + Boolean.hashCode(differentCustomer)) * 31) + Integer.hashCode(autoVisitId)) * 31;
        final String str = customerDefinition;
        return hashCode + (null == str ? 0 : str.hashCode());
    }

    public String toString() {
        return "CheckAutoVisitItem(isExisting=" + isExisting + ", differentCustomer=" + differentCustomer + ", autoVisitId=" + autoVisitId + ", customerDefinition=" + customerDefinition + ')';
    }

    public CheckAutoVisitItem(final boolean z, final boolean z2, final int i2, final String str) {
        isExisting = z;
        differentCustomer = z2;
        autoVisitId = i2;
        customerDefinition = str;
    }

    public CheckAutoVisitItem(final boolean z, final boolean z2, final int i2, final String str, final int i3, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 == (i3 & 1) && z, 0 == (i3 & 2) && z2, 0 != (i3 & 4) ? 0 : i2, 0 != (i3 & 8) ? null : str);
    }

    public boolean isExisting() {
        return isExisting;
    }

    public void setExisting(final boolean z) {
        isExisting = z;
    }

    public boolean getDifferentCustomer() {
        return differentCustomer;
    }

    public void setDifferentCustomer(final boolean z) {
        differentCustomer = z;
    }

    public int getAutoVisitId() {
        return autoVisitId;
    }

    public void setAutoVisitId(final int i2) {
        autoVisitId = i2;
    }

    public String getCustomerDefinition() {
        return customerDefinition;
    }

    public void setCustomerDefinition(final String str) {
        customerDefinition = str;
    }
}
