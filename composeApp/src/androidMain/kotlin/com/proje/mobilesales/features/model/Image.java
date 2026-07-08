package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;

public class Image implements Parcelable {
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        public Image createFromParcel(final Parcel parcel) {
            return new Image(parcel);
        }
        public Image[] newArray(final int i2) {
            return new Image[i2];
        }
    };
    String image;
    public int describeContents() {
        return 0;
    }
    public String getImage() {
        return image;
    }
    public void setImage(final String str) {
        image = str;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeString(image);
    }
    public Image() {
    }

    protected Image(final Parcel parcel) {
        image = parcel.readString();
    }
}
