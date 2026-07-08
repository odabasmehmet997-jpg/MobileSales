package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.enums.ObjectType;

public class UserPreferenceSettings implements Parcelable {
    public static final Parcelable.Creator<UserPreferenceSettings> CREATOR = new Parcelable.Creator<UserPreferenceSettings>() {
        public UserPreferenceSettings createFromParcel(final Parcel parcel) {
            return new UserPreferenceSettings(parcel);
        }

        public UserPreferenceSettings[] newArray(final int i2) {
            return new UserPreferenceSettings[i2];
        }
    };
    private String key;
    private ObjectType objectType;
    private String value;
    public int describeContents() {
        return 0;
    }
    public String getKey() {
        return key;
    }
    public void setKey(final String str) {
        key = str;
    }
    public String getValue() {
        return value;
    }
    public void setValue(final String str) {
        value = str;
    }
    public UserPreferenceSettings() {
    }
    public ObjectType getObjectType() {
        return objectType;
    }
    public void setObjectType(final ObjectType objectType) {
        this.objectType = objectType;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeString(key);
        parcel.writeString(value);
        final ObjectType objectType = this.objectType;
        parcel.writeInt(null == objectType ? -1 : objectType.ordinal());
    }
    protected UserPreferenceSettings(final Parcel parcel) {
        key = parcel.readString();
        value = parcel.readString();
        final int readInt = parcel.readInt();
        objectType = -1 == readInt ? null : ObjectType.values()[readInt];
    }
}
