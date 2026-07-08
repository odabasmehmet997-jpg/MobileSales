package com.proje.mobilesales.features.customer.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.base.BaseDbSalesFiche;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parceler;

public final class CustomerProperty implements Parcelable {
    private boolean editable;
    private String key;
    private boolean number;
    private String value;
    public static final Companion Companion = new Companion(null);
    public static final Parcelable.Creator<CustomerProperty> CREATOR = new Creator();

    public static final class Creator implements Parcelable.Creator<CustomerProperty> {

        public CustomerProperty createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return CustomerProperty.Companion.m1363create(parcel);
        }

        public CustomerProperty[] newArray(int i2) {
            return new CustomerProperty[i2];
        }
    }

    public CustomerProperty() {
        this(null, null, false, false, 15, null);
    }

    public static  CustomerProperty copydefault(CustomerProperty customerProperty, String str, String str2, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = customerProperty.key;
        }
        if ((i2 & 2) != 0) {
            str2 = customerProperty.value;
        }
        if ((i2 & 4) != 0) {
            z = customerProperty.editable;
        }
        if ((i2 & 8) != 0) {
            z2 = customerProperty.number;
        }
        return customerProperty.copy(str, str2, z, z2);
    }

    public String component1() {
        return this.key;
    }

    public String component2() {
        return this.value;
    }

    public boolean component3() {
        return this.editable;
    }

    public boolean component4() {
        return this.number;
    }

    public CustomerProperty copy(String str, String str2, boolean z, boolean z2) {
        return new CustomerProperty(str, str2, z, z2);
    }
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CustomerProperty customerProperty)) {
            return false;
        }
        return Intrinsics.areEqual(this.key, customerProperty.key) && Intrinsics.areEqual(this.value, customerProperty.value) && this.editable == customerProperty.editable && this.number == customerProperty.number;
    }

    public int hashCode() {
        String str = this.key;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.value;
        return ((((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Boolean.hashCode(this.editable)) * 31) + Boolean.hashCode(this.number);
    }

    public String toString() {
        return "CustomerProperty(key=" + this.key + ", value=" + this.value + ", editable=" + this.editable + ", number=" + this.number + ')';
    }
    public void writeToParcel(Parcel out, int i2) {
        Intrinsics.checkNotNullParameter(out, "out");
        Companion.write(this, out, i2);
    }

    public CustomerProperty(String str, String str2, boolean z, boolean z2) {
        this.key = str;
        this.value = str2;
        this.editable = z;
        this.number = z2;
    }

    public CustomerProperty(String str, String str2, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) == 0 && z, (i2 & 8) == 0 && z2);
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public boolean getEditable() {
        return this.editable;
    }

    public void setEditable(boolean z) {
        this.editable = z;
    }

    public boolean getNumber() {
        return this.number;
    }

    public void setNumber(boolean z) {
        this.number = z;
    }

    public CustomerProperty(String str, String str2, boolean z) {
        this(str, str2, z, false);
    }

    public CustomerProperty(Parcel parcel) {
        this(null, null, false, false, 15, null);
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.key = parcel.readString();
        this.value = parcel.readString();
        this.editable = parcel.readByte() != 0;
        this.number = parcel.readByte() != 0;
    }
    public static final class Companion implements Parceler<CustomerProperty> {
        public  Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public BaseDbSalesFiche[] newArray(int i2) {
            return  Parceler.DefaultImpls.newArray(this, i2);
        }

        public void write(CustomerProperty customerProperty, Parcel parcel, int i2) {
            Intrinsics.checkNotNullParameter(customerProperty, "<this>");
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            parcel.writeString(customerProperty.getKey());
            parcel.writeString(customerProperty.getValue());
            parcel.writeByte(customerProperty.getEditable() ? (byte) 1 : (byte) 0);
            parcel.writeByte(customerProperty.getNumber() ? (byte) 1 : (byte) 0);
        }
        public CustomerProperty m1363create(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new CustomerProperty(parcel);
        }
    }
}
