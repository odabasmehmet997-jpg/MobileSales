package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;

public class WorUserModel implements Parcelable {
    public static final Parcelable.Creator<WorUserModel> CREATOR = new Parcelable.Creator<WorUserModel>() {
        public WorUserModel createFromParcel(final Parcel parcel) {
            return new WorUserModel(parcel);
        }

        public WorUserModel[] newArray(final int i2) {
            return new WorUserModel[i2];
        }
    };
    private String email;
    private String name;
    private int userId;
    public int describeContents() {
        return 0;
    }
    public WorUserModel() {
    }
    protected WorUserModel(final Parcel parcel) {
        userId = parcel.readInt();
        name = parcel.readString();
        email = parcel.readString();
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(final int i2) {
        userId = i2;
    }
    public String getName() {
        return name;
    }
    public void setName(final String str) {
        name = str;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(final String str) {
        email = str;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(userId);
        parcel.writeString(name);
        parcel.writeString(email);
    }
}
