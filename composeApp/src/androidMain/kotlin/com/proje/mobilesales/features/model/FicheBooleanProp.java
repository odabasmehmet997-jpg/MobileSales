package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FicheBooleanProp extends FicheStringProp {
    public static final Parcelable.Creator<FicheBooleanProp> CREATOR = new Parcelable.Creator<FicheBooleanProp>() {
        public FicheBooleanProp createFromParcel(final Parcel parcel) {
            return new FicheBooleanProp(parcel);
        }

        public FicheBooleanProp[] newArray(final int i2) {
            return new FicheBooleanProp[i2];
        }
    };
    boolean select;
    public int describeContents() {
        return 0;
    }

    public FicheBooleanProp() {
    }

    public FicheBooleanProp(final boolean z) {
        select = z;
    }

    public void setSelect(final boolean z) {
        select = z;
    }

    public boolean isSelect() {
        return select;
    }
    public void reset() {
        super.reset();
        select = false;
    }
    public FicheBooleanProp mo1410clone() throws CloneNotSupportedException {
        return (FicheBooleanProp) super.mo1410clone();
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeByte(select ? (byte) 1 : (byte) 0);
    }

    protected FicheBooleanProp(final Parcel parcel) {
        super(parcel);
        select = 0 != parcel.readByte();
    }
}
