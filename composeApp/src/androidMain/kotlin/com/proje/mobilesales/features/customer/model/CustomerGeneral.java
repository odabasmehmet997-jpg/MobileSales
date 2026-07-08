package com.proje.mobilesales.features.customer.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parceler;

public final class CustomerGeneral implements Parcelable {
    public static final Parcelable.Creator<CustomerGeneral> CREATOR = new Creator();
    private CustomerProperty propertyLeft;
    private CustomerProperty propertyRight;

    public static final class Creator implements Parcelable.Creator<CustomerGeneral> {

        public CustomerGeneral createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CustomerGeneral(parcel.readInt() == 0 ? null : CustomerProperty.CREATOR.createFromParcel(parcel), parcel.readInt() != 0 ? CustomerProperty.CREATOR.createFromParcel(parcel) : null);
        }

        public CustomerGeneral[] newArray(int i2) {
            return  Parceler.DefaultImpls.newArray(this, i2);
        }
    }


    public static   CustomerGeneral copy (CustomerGeneral customerGeneral, CustomerProperty customerProperty, CustomerProperty customerProperty2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            customerProperty = customerGeneral.propertyLeft;
        }
        if ((i2 & 2) != 0) {
            customerProperty2 = customerGeneral.propertyRight;
        }
        return customerGeneral.copy(customerProperty, customerProperty2);
    }

    public CustomerProperty component1() {
        return this.propertyLeft;
    }

    public CustomerProperty component2() {
        return this.propertyRight;
    }

    public CustomerGeneral copy(CustomerProperty customerProperty, CustomerProperty customerProperty2) {
        return new CustomerGeneral(customerProperty, customerProperty2);
    }
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerGeneral customerGeneral)) {
            return false;
        }
        return Intrinsics.areEqual(this.propertyLeft, customerGeneral.propertyLeft) && Intrinsics.areEqual(this.propertyRight, customerGeneral.propertyRight);
    }

    public int hashCode() {
        CustomerProperty customerProperty = this.propertyLeft;
        int hashCode = (customerProperty == null ? 0 : customerProperty.hashCode()) * 31;
        CustomerProperty customerProperty2 = this.propertyRight;
        return hashCode + (customerProperty2 != null ? customerProperty2.hashCode() : 0);
    }

    public String toString() {
        return "CustomerGeneral(propertyLeft=" + this.propertyLeft + ", propertyRight=" + this.propertyRight + ')';
    }
    public void writeToParcel(Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        CustomerProperty customerProperty = this.propertyLeft;
        if (customerProperty == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            customerProperty.writeToParcel(out, i2);
        }
        CustomerProperty customerProperty2 = this.propertyRight;
        if (customerProperty2 == null) {
            out.writeInt(0);
        } else {
            out.writeInt(1);
            customerProperty2.writeToParcel(out, i2);
        }
    }

    public CustomerGeneral(CustomerProperty customerProperty, CustomerProperty customerProperty2) {
        this.propertyLeft = customerProperty;
        this.propertyRight = customerProperty2;
    }

    public  CustomerGeneral(CustomerProperty customerProperty, CustomerProperty customerProperty2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : customerProperty, (i2 & 2) != 0 ? null : customerProperty2);
    }

    public   CustomerProperty getPropertyLeft() {
        return this.propertyLeft;
    }

    public  void setPropertyLeft(CustomerProperty customerProperty) {
        this.propertyLeft = customerProperty;
    }

    public   CustomerProperty getPropertyRight() {
        return this.propertyRight;
    }

    public   void setPropertyRight(CustomerProperty customerProperty) {
        this.propertyRight = customerProperty;
    }
}
