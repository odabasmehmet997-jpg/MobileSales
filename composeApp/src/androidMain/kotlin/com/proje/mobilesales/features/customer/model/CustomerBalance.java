package com.proje.mobilesales.features.customer.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parceler;
 
public final class CustomerBalance implements Parcelable {
 
    private double total;  
    private String typ;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<CustomerBalance> CREATOR = new Creator();
    public static final class Creator implements Parcelable.Creator<CustomerBalance> {
        public CustomerBalance createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return CustomerBalance.Companion.create(parcel);
        } 
        public CustomerBalance[] newArray(int i2) {
            return new CustomerBalance[i2];
        }
    }
    public CustomerBalance() {
        this(null, 0.0d, 3, null);
    }
    public static  CustomerBalance copy (CustomerBalance customerBalance, String str, double d2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = customerBalance.typ;
        }
        if ((i2 & 2) != 0) {
            d2 = customerBalance.total;
        }
        return customerBalance.copy(str, d2);
    }

    public String component1() {
        return this.typ;
    }

    public double component2() {
        return this.total;
    }

    public CustomerBalance copy(String str, double d2) {
        return new CustomerBalance(str, d2);
    }
 
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerBalance customerBalance)) {
            return false;
        }
        return Intrinsics.areEqual(this.typ, customerBalance.typ) && Double.compare(this.total, customerBalance.total) == 0;
    }

    public int hashCode() {
        String str = this.typ;
        return ((str == null ? 0 : str.hashCode()) * 31) + Double.hashCode(this.total);
    }

    public String toString() {
        return "CustomerBalance(typ=" + this.typ + ", total=" + this.total + ')';
    }
    public void writeToParcel(Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        Companion.write(this, out, i2);
    }

    public CustomerBalance(String str, double d2) {
        this.typ = str;
        this.total = d2;
    }

    public  CustomerBalance(String str, double d2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? 0.0d : d2);
    }

    public String getTyp() {
        return this.typ;
    }

    public void setTyp(String str) {
        this.typ = str;
    }

    public double getTotal() {
        return this.total;
    }

    public void setTotal(double d2) {
        this.total = d2;
    }
    public CustomerBalance(Parcel parcel) {
        this(null, 0.0d, 3, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.typ = parcel.readString();
        this.total = parcel.readDouble();
    } 
    public static final class Companion implements Parceler<CustomerBalance> {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
        private Companion() {
        }
        public CustomerBalance[] newArray(int i2) {
            return (CustomerBalance[]) Parceler.DefaultImpls.newArray(this, i2);
        }
        public void write(CustomerBalance customerBalance, Parcel parcel, int i2) {
            Intrinsics.checkNotNullParameter(customerBalance, "<this>");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.writeString(customerBalance.getTyp());
            parcel.writeDouble(customerBalance.getTotal());
        }
        public CustomerBalance create(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CustomerBalance(parcel);
        }
    }
}
