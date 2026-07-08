package com.proje.mobilesales.features.customer.model.database;

import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import com.proje.mobilesales.core.enums.CustomerRestriction;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class CustomerDetailedRestriction {

    private String clCode;
    private int modulNo;
    private int programNo;
    public CustomerDetailedRestriction() {
        this(null, 0, 0, 7, null);
    }
    public static   CustomerDetailedRestriction copydefault(CustomerDetailedRestriction customerDetailedRestriction, String str, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = customerDetailedRestriction.clCode;
        }
        if ((i4 & 2) != 0) {
            i2 = customerDetailedRestriction.modulNo;
        }
        if ((i4 & 4) != 0) {
            i3 = customerDetailedRestriction.programNo;
        }
        return customerDetailedRestriction.copy(str, i2, i3);
    }

    public String component1() {
        return this.clCode;
    }

    public int component2() {
        return this.modulNo;
    }

    public int component3() {
        return this.programNo;
    }

    public CustomerDetailedRestriction copy(String clCode, int i2, int i3) {
        Intrinsics.checkNotNullParameter(clCode, "clCode");
        return new CustomerDetailedRestriction(clCode, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerDetailedRestriction customerDetailedRestriction)) {
            return false;
        }
        return Intrinsics.areEqual(this.clCode, customerDetailedRestriction.clCode) && this.modulNo == customerDetailedRestriction.modulNo && this.programNo == customerDetailedRestriction.programNo;
    }

    public int hashCode() {
        return (((this.clCode.hashCode() * 31) + Integer.hashCode(this.modulNo)) * 31) + Integer.hashCode(this.programNo);
    }

    public String toString() {
        return "CustomerDetailedRestriction(clCode=" + this.clCode + ", modulNo=" + this.modulNo + ", programNo=" + this.programNo + ')';
    }

    public CustomerDetailedRestriction(String clCode, int i2, int i3) {
        Intrinsics.checkNotNullParameter(clCode, "clCode");
        this.clCode = clCode;
        this.modulNo = i2;
        this.programNo = i3;
    }

    public  CustomerDetailedRestriction(String str, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? "" : str, (i4 & 2) != 0 ? 0 : i2, (i4 & 4) != 0 ? 0 : i3);
    }

    public String getClCode() {
        return this.clCode;
    }

    public void setClCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.clCode = str;
    }

    public int getModulNo() {
        return this.modulNo;
    }

    public void setModulNo(int i2) {
        this.modulNo = i2;
    }

    public int getProgramNo() {
        return this.programNo;
    }

    public void setProgramNo(int i2) {
        this.programNo = i2;
    }

    public CustomerRestriction getCustomerDetailRestriction() {
        if (this.modulNo == 1) {
            return CustomerRestriction.Companion.getCustomerRestriction(String.valueOf(this.programNo));
        }
        return CustomerRestriction.OPEN;
    }
}
