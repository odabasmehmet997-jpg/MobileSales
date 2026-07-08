package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;

public class BarcodeNew implements Serializable, Parcelable {
    public static final Parcelable.Creator<BarcodeNew> CREATOR = new Parcelable.Creator<BarcodeNew>() {
        public BarcodeNew createFromParcel(final Parcel parcel) {
            return new BarcodeNew(parcel);
        }
        public BarcodeNew[] newArray(final int i2) {
            return new BarcodeNew[i2];
        }
    };
    private static final long serialVersionUID = 1;
    private String code;
    int itemRef;
    private String name;
    public int describeContents() {
        return 0;
    }
    public BarcodeNew() {
    }
    public int getItemRef() {
        return itemRef;
    }
    public void setItemRef(final int i2) {
        itemRef = i2;
    }
    public String getCode() {
        return code;
    }
    public void setCode(final String str) {
        code = str;
    }
    public String getName() {
        return name;
    }
    public void setName(final String str) {
        name = str;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(itemRef);
        parcel.writeString(code);
        parcel.writeString(name);
    }
    protected BarcodeNew(final Parcel parcel) {
        itemRef = parcel.readInt();
        code = parcel.readString();
        name = parcel.readString();
    }
}
