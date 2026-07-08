package com.proje.mobilesales.features.customer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.base.BaseDbSalesFiche;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parceler;
 
public final class CustomerBeforeBalance implements Parcelable { 
    private double balance;
 
    private double beforeBalance;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<CustomerBeforeBalance> CREATOR = new Creator();
 
    public static final class Creator implements Parcelable.Creator<CustomerBeforeBalance> {
 
        public   CustomerBeforeBalance createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return CustomerBeforeBalance.Companion.m1357create(parcel);
        } 
        public   CustomerBeforeBalance[] newArray(int i2) {
            return new CustomerBeforeBalance[i2];
        }
    }

    public CustomerBeforeBalance() {
        this(0.0d, 0.0d, 3, null);
    }

    public static  CustomerBeforeBalance copydefault(CustomerBeforeBalance customerBeforeBalance, double d2, double d3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            d2 = customerBeforeBalance.beforeBalance;
        }
        if ((i2 & 2) != 0) {
            d3 = customerBeforeBalance.balance;
        }
        return customerBeforeBalance.copy(d2, d3);
    }

    public   double component1() {
        return this.beforeBalance;
    }

    public   double component2() {
        return this.balance;
    }

    public   CustomerBeforeBalance copy(double d2, double d3) {
        return new CustomerBeforeBalance(d2, d3);
    }
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerBeforeBalance customerBeforeBalance)) {
            return false;
        }
        return Double.compare(this.beforeBalance, customerBeforeBalance.beforeBalance) == 0 && Double.compare(this.balance, customerBeforeBalance.balance) == 0;
    }

    public int hashCode() {
        return (Double.hashCode(this.beforeBalance) * 31) + Double.hashCode(this.balance);
    }

    public String toString() {
        return "CustomerBeforeBalance(beforeBalance=" + this.beforeBalance + ", balance=" + this.balance + ')';
    } 
    public void writeToParcel(Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        Companion.write(this, out, i2);
    }

    public CustomerBeforeBalance(double d2, double d3) {
        this.beforeBalance = d2;
        this.balance = d3;
    }

    public  CustomerBeforeBalance(double d2, double d3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0.0d : d2, (i2 & 2) != 0 ? 0.0d : d3);
    }

    public double getBeforeBalance() {
        return this.beforeBalance;
    }

    public void setBeforeBalance(double d2) {
        this.beforeBalance = d2;
    }

    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double d2) {
        this.balance = d2;
    } 
    public CustomerBeforeBalance(Parcel parcel) {
        this(0.0d, 0.0d, 3, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.beforeBalance = parcel.readDouble();
        this.balance = parcel.readDouble();
    } 
    public static final class Companion implements Parceler<CustomerBeforeBalance> {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        } 
        public BaseDbSalesFiche[] newArray(int i2) {
            return  Parceler.DefaultImpls.newArray(this, i2);
        }

        public void write(CustomerBeforeBalance customerBeforeBalance, Parcel parcel, int i2) {
            Intrinsics.checkNotNullParameter(customerBeforeBalance, "<this>");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.writeDouble(customerBeforeBalance.getBeforeBalance());
            parcel.writeDouble(customerBeforeBalance.getBalance());
        } 
        public CustomerBeforeBalance m1357create(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CustomerBeforeBalance(parcel);
        }
    }
}
