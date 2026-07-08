package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;

public class WorTablesModel implements Parcelable {
    public static final Parcelable.Creator<WorTablesModel> CREATOR = new Parcelable.Creator<WorTablesModel>() {
        public WorTablesModel createFromParcel(final Parcel parcel) {
            return new WorTablesModel(parcel);
        }

        public WorTablesModel[] newArray(final int i2) {
            return new WorTablesModel[i2];
        }
    };
    private String name;
    private int optype;
    public int describeContents() {
        return 0;
    }
    public WorTablesModel() {
    }
    protected WorTablesModel(final Parcel parcel) {
        name = parcel.readString();
        optype = parcel.readInt();
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeString(name);
        parcel.writeInt(optype);
    }
    public String getName() {
        return name;
    }
    public void setName(final String str) {
        name = str;
    }
    public int getOptype() {
        return optype;
    }
    public void setOptype(final int i2) {
        optype = i2;
    }
}
