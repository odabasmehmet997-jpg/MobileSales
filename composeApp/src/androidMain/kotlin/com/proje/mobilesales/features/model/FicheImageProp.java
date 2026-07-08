package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.interfaces.FicheProp;

public class FicheImageProp implements FicheProp, Parcelable {
    public static final Parcelable.Creator<FicheImageProp> CREATOR = new Parcelable.Creator<FicheImageProp>() {
        public FicheImageProp createFromParcel(final Parcel parcel) {
            return new FicheImageProp(parcel);
        }

        public FicheImageProp[] newArray(final int i2) {
            return new FicheImageProp[i2];
        }
    };
    byte[] mImageArray;

    public int describeContents() {
        return 0;
    }

    public FicheImageProp() {
    }

    public FicheImageProp(final byte[] bArr) {
        mImageArray = bArr;
    }

    public void reset() {
        mImageArray = null;
    }

    public byte[] getImageArray() {
        return mImageArray;
    }

    public void setImageArray(final byte[] bArr) {
        mImageArray = bArr;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeByteArray(mImageArray);
    }

    protected FicheImageProp(final Parcel parcel) {
        mImageArray = parcel.createByteArray();
    }
}
