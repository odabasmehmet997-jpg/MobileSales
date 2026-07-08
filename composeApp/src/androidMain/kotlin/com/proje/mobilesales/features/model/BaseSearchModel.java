package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.searchdialog.Searchable;

public class BaseSearchModel implements Searchable, Parcelable {
    public static final Parcelable.Creator<BaseSearchModel> CREATOR = new Parcelable.Creator<BaseSearchModel>() {
        public BaseSearchModel createFromParcel(final Parcel parcel) {
            return new BaseSearchModel(parcel);
        }

        public BaseSearchModel[] newArray(final int i2) {
            return new BaseSearchModel[i2];
        }
    };
    private String code;
    private final boolean isChecked;
    private int logicalRef;

    public int describeContents() {
        return 0;
    }

    public BaseSearchModel(final int i2, final String str, final boolean z) {
        logicalRef = i2;
        code = str;
        isChecked = z;
    }

    protected BaseSearchModel(final Parcel parcel) {
        logicalRef = parcel.readInt();
        code = parcel.readString();
        isChecked = 0 != parcel.readByte();
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

    public String getTitle() {
        return code;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(this.logicalRef);
        parcel.writeString(this.code);
        parcel.writeInt(isChecked ? 1 : 0);
    }
}
