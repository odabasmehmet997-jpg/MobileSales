package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FicheMenuRights implements Parcelable {
    public static final Parcelable.Creator<FicheMenuRights> CREATOR = new Parcelable.Creator<FicheMenuRights>() {
        public FicheMenuRights createFromParcel(final Parcel parcel) {
            return new FicheMenuRights(parcel);
        }

        public FicheMenuRights[] newArray(final int i2) {
            return new FicheMenuRights[i2];
        }
    };
    private boolean mCancel;
    private boolean mCopy;
    private boolean mDelete;
    private boolean mEdit;

    public int describeContents() {
        return 0;
    }

    public FicheMenuRights(final boolean z, final boolean z2, final boolean z3) {
        mEdit = z;
        mDelete = z2;
        mCopy = z3;
    }

    public void setCancel(final boolean z) {
        mCancel = z;
    }

    public boolean isCancel() {
        return mCancel;
    }

    public boolean isEdit() {
        return mEdit;
    }

    public void setEdit(final boolean z) {
        mEdit = z;
    }

    public boolean isDelete() {
        return mDelete;
    }

    public void setDelete(final boolean z) {
        mDelete = z;
    }

    public boolean isCopy() {
        return mCopy;
    }

    public void setCopy(final boolean z) {
        mCopy = z;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeByte(mEdit ? (byte) 1 : (byte) 0);
        parcel.writeByte(mDelete ? (byte) 1 : (byte) 0);
        parcel.writeByte(mCopy ? (byte) 1 : (byte) 0);
        parcel.writeByte(mCancel ? (byte) 1 : (byte) 0);
    }

    protected FicheMenuRights(final Parcel parcel) {
        mEdit = 0 != parcel.readByte();
        mDelete = 0 != parcel.readByte();
        mCopy = 0 != parcel.readByte();
        mCancel = 0 != parcel.readByte();
    }
}
