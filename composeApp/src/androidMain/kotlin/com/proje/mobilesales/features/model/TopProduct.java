package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;

public class TopProduct implements Parcelable {
    public static final Parcelable.Creator<TopProduct> CREATOR = new Parcelable.Creator<TopProduct>() {
        public TopProduct createFromParcel(final Parcel parcel) {
            return new TopProduct(parcel);
        }

        public TopProduct[] newArray(final int i2) {
            return new TopProduct[i2];
        }
    };
    private String code;
    private String name;
    private String total;
    public int describeContents() {
        return 0;
    }
    public String getName() {
        return name;
    }
    public void setName(final String str) {
        name = str;
    }
    public String getCode() {
        return code;
    }
    public void setCode(final String str) {
        code = str;
    }
    public String getTotal() {
        return total;
    }
    public void setTotal(final String str) {
        total = str;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeString(code);
        parcel.writeString(name);
        parcel.writeString(total);
    }
    public TopProduct() {
    }
    protected TopProduct(final Parcel parcel) {
        code = parcel.readString();
        name = parcel.readString();
        total = parcel.readString();
    }
}
