package com.github.mikephil.charting.data;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;

public class Entry implements Parcelable {
    public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator<Entry> () {
        public Entry createFromParcel(final Parcel parcel) {
            return new Entry (parcel);
        }

        public Entry[] newArray(final int i) {
            return new Entry[i];
        }
    };
    private Object mData;
    private float mVal;
    private int mXIndex;
    public Entry(final float f, final int i) {
        mData = null;
        mVal = f;
        mXIndex = i;
    }
    public Entry(final float f, final int i, final Object obj) {
        this (f, i);
        mData = obj;
    }
    protected Entry(final Parcel parcel) {
        mVal = 0.0f;
        mXIndex = 0;
        mData = null;
        mVal = parcel.readFloat ();
        mXIndex = parcel.readInt ();
        if (1 == parcel.readInt ()) {
            mData = parcel.readParcelable (Object.class.getClassLoader ());
        }
    }
    public int describeContents() {
        return 0;
    }
    public int getXIndex() {
        return mXIndex;
    }
    public void setXIndex(final int i) {
        mXIndex = i;
    }
    public float getVal() {
        return mVal;
    }
    public void setVal(final float f) {
        mVal = f;
    }
    public Object getData() {
        return mData;
    }
    public void setData(final Object obj) {
        mData = obj;
    }
    public Entry copy() {
        return new Entry (mVal, mXIndex, mData);
    }
    public boolean equalTo(final Entry entry) {
        return null != entry && entry.mData == mData && entry.mXIndex == mXIndex && 1.0E-5f >= Math.abs (entry.mVal - this.mVal);
    }
    @Override
    public String toString() {
        return "Entry, xIndex: " + mXIndex + " val (sum): " + this.mVal;
    }
    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeFloat (mVal);
        parcel.writeInt (mXIndex);
        final Object obj = mData;
        if (null == obj) {
            parcel.writeInt (0);
        } else if (obj instanceof Parcelable) {
            parcel.writeInt (1);
            parcel.writeParcelable ((Parcelable) mData, i);
        } else {
            throw new ParcelFormatException ("Cannot parcel an Entry with non-parcelable data");
        }
    }
}
