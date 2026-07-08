package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FicheRefProp extends FicheStringProp implements FicheStringProp.FicheAttributes {
    public static final Parcelable.Creator<FicheRefProp> CREATOR = new Parcelable.Creator<FicheRefProp>() {
        public FicheRefProp createFromParcel(final Parcel parcel) {
            return new FicheRefProp(parcel);
        }

        public FicheRefProp[] newArray(final int i2) {
            return new FicheRefProp[i2];
        }
    };
    private int logicalRef;
    private int which;

    public int describeContents() {
        return 0;
    }

    public FicheRefProp() {
        logicalRef = -1;
        which = -1;
    }

    public FicheRefProp(final int i2, final int i3, final String str) {
        logicalRef = i2;
        setDefinition(str);
        which = i3;
    }
    public int getLogicalRef() {
        return logicalRef;
    }
    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }
    public int getWhich() {
        return which;
    }
    public void setWhich(final int i2) {
        which = i2;
    }

    public void reset() {
        super.reset();
        logicalRef = -1;
        which = -1;
    }

    protected FicheRefProp(final Parcel parcel) {
        super(parcel);
        logicalRef = parcel.readInt();
        which = parcel.readInt();
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        super.writeToParcel(parcel, i2);
        parcel.writeInt(logicalRef);
        parcel.writeInt(which);
    }
    public FicheRefProp clone() throws CloneNotSupportedException {
        return (FicheRefProp) super.mo1410clone();
    }
}
