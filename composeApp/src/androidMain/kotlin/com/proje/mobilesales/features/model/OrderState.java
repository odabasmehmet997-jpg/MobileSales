package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;

public class OrderState implements Parcelable {
    public static final Parcelable.Creator<OrderState> CREATOR = new Parcelable.Creator<OrderState>() {
        public OrderState createFromParcel(final Parcel parcel) {
            return new OrderState(parcel);
        }

        public OrderState[] newArray(final int i2) {
            return new OrderState[i2];
        }
    };
    private int logicalRef;
    private String statusName;
    public int describeContents() {
        return 0;
    }
    public int getLogicalRef() {
        return logicalRef;
    }
    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }
    public String getStatusName() {
        return statusName;
    }
    public void setStatusName(final String str) {
        statusName = str;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(logicalRef);
        parcel.writeString(statusName);
    }
    public OrderState() {
    }
    protected OrderState(final Parcel parcel) {
        logicalRef = parcel.readInt();
        statusName = parcel.readString();
    }
}
