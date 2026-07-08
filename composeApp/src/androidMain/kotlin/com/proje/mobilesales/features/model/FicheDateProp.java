package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FicheDateProp extends FicheStringProp {
    public static final Parcelable.Creator<FicheDateProp> CREATOR = new Parcelable.Creator<FicheDateProp>() {
        public FicheDateProp createFromParcel(final Parcel parcel) {
            return new FicheDateProp(parcel);
        }

        public FicheDateProp[] newArray(final int i2) {
            return new FicheDateProp[i2];
        }
    };
    public int describeContents() {
        return 0;
    }

    public FicheDateProp() {
    }

    public FicheDateProp(final String str) {
        setDefinition(str);
    }

    protected FicheDateProp(final Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        super.writeToParcel(parcel, i2);
    }

    public FicheDateProp mo1410clone() throws CloneNotSupportedException {
        return (FicheDateProp) super.mo1410clone();
    }
}
