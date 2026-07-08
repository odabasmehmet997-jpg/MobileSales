package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BarcodeCustomer implements Parcelable {
    public static final Parcelable.Creator<BarcodeCustomer> CREATOR = new Parcelable.Creator<BarcodeCustomer>() {
        public BarcodeCustomer createFromParcel(final Parcel parcel) {
            return new BarcodeCustomer(parcel);
        }
        public BarcodeCustomer[] newArray(final int i2) {
            return new BarcodeCustomer[i2];
        }
    };
    private String cabinBarcode;
    private String cabinCode;
    private int cabinRef;
    private String code;
    private int logicalRef;
    public int describeContents() {
        return 0;
    }
    protected BarcodeCustomer(final Parcel parcel) {
        logicalRef = parcel.readInt();
        code = parcel.readString();
        cabinRef = parcel.readInt();
        cabinBarcode = parcel.readString();
        cabinCode = parcel.readString();
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(logicalRef);
        parcel.writeString(code);
        parcel.writeInt(cabinRef);
        parcel.writeString(cabinBarcode);
        parcel.writeString(cabinCode);
    }
    public int getLogicalRef() {
        return logicalRef;
    }
    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }
    public String getCode() {
        return code;
    }
    public void setCode(final String str) {
        code = str;
    }
    public String getCabinBarcode() {
        return cabinBarcode;
    }
    public void setCabinBarcode(final String str) {
        cabinBarcode = str;
    }
    public int getCabinRef() {
        return cabinRef;
    }
    public void setCabinRef(final int i2) {
        cabinRef = i2;
    }
    public String getCabinCode() {
        return cabinCode;
    }
    public void setCabinCode(final String str) {
        cabinCode = str;
    }
}
