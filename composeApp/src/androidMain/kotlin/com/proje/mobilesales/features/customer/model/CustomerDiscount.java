package com.proje.mobilesales.features.customer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.base.BaseDbSalesFiche;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parceler;

public final class CustomerDiscount implements Parcelable {
    private double ratio;
    private int type;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<CustomerDiscount> CREATOR = new Creator();

    public static final class Creator implements Parcelable.Creator<CustomerDiscount> {

        public CustomerDiscount createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return CustomerDiscount.Companion.m1359create(parcel);
        }

        public CustomerDiscount[] newArray(int i2) {
            return new CustomerDiscount[i2];
        }
    }

    public CustomerDiscount() {
        this(0.0d, 0, 3, null);
    }

    public static  CustomerDiscount copydefault(CustomerDiscount customerDiscount, double d2, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            d2 = customerDiscount.ratio;
        }
        if ((i3 & 2) != 0) {
            i2 = customerDiscount.type;
        }
        return customerDiscount.copy(d2, i2);
    }

    public double component1() {
        return this.ratio;
    }

    public int component2() {
        return this.type;
    }

    public CustomerDiscount copy(double d2, int i2) {
        return new CustomerDiscount(d2, i2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerDiscount customerDiscount)) {
            return false;
        }
        return Double.compare(this.ratio, customerDiscount.ratio) == 0 && this.type == customerDiscount.type;
    }

    public int hashCode() {
        return (Double.hashCode(this.ratio) * 31) + Integer.hashCode(this.type);
    }

    public String toString() {
        return "CustomerDiscount(ratio=" + this.ratio + ", type=" + this.type + ')';
    }

    public void writeToParcel(Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        Companion.write(this, out, i2);
    }

    public CustomerDiscount(double d2, int i2) {
        this.ratio = d2;
        this.type = i2;
    }

    public   CustomerDiscount(double d2, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0.0d : d2, (i3 & 2) != 0 ? 0 : i2);
    }

    public double getRatio() {
        return this.ratio;
    }

    public void setRatio(double d2) {
        this.ratio = d2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i2) {
        this.type = i2;
    }
    public CustomerDiscount(Parcel parcel) {
        this(0.0d, 0, 3, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.ratio = parcel.readDouble();
        this.type = parcel.readInt();
    }

    public static final class Companion implements Parceler<CustomerDiscount> {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
        public BaseDbSalesFiche[] m1360newArray(int i2) {
            return  Parceler.DefaultImpls.newArray(this, i2);
        }

        public void write(CustomerDiscount customerDiscount, Parcel parcel, int i2) {
            Intrinsics.checkNotNullParameter(customerDiscount, "<this>");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.writeDouble(customerDiscount.getRatio());
            parcel.writeInt(customerDiscount.getType());
        }
        public CustomerDiscount m1359create(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CustomerDiscount(parcel);
        }
    }
}
