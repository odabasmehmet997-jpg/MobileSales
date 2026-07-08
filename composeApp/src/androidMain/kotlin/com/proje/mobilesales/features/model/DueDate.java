package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.proje.mobilesales.core.enums.RiskAlert;

public class DueDate implements Parcelable {
    public static final Parcelable.Creator<DueDate> CREATOR = new Parcelable.Creator<DueDate>() {
        public DueDate createFromParcel(final Parcel parcel) {
            return new DueDate(parcel);
        }

        public DueDate[] newArray(final int i2) {
            return new DueDate[i2];
        }
    };
    private boolean control;
    private int dueDateCount;
    private double dueDateLimit;
    private RiskAlert mDueDateAlert;
    public int describeContents() {
        return 0;
    }
    public DueDate(final int i2, final int i3, final RiskAlert riskAlert, final boolean z) {
        dueDateCount = i2;
        dueDateLimit = i3;
        mDueDateAlert = riskAlert;
        control = z;
    }
    public int getDueDateCount() {
        return dueDateCount;
    }
    public void setDueDateCount(final int i2) {
        dueDateCount = i2;
    }
    public double getDueDateLimit() {
        return dueDateLimit;
    }
    public void setDueDateLimit(final double d2) {
        dueDateLimit = d2;
    }
    public RiskAlert getDueDateAlert() {
        return mDueDateAlert;
    }
    public void setDueDateAlert(final RiskAlert riskAlert) {
        mDueDateAlert = riskAlert;
    }
    public boolean isControl() {
        return control;
    }
    public void setControl(final boolean z) {
        control = z;
    }
    public DueDate() {
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(dueDateCount);
        parcel.writeDouble(dueDateLimit);
        final RiskAlert riskAlert = mDueDateAlert;
        parcel.writeInt(null == riskAlert ? -1 : riskAlert.ordinal());
        parcel.writeByte(control ? (byte) 1 : (byte) 0);
    }
    protected DueDate(final Parcel parcel) {
        dueDateCount = parcel.readInt();
        dueDateLimit = parcel.readDouble();
        final int readInt = parcel.readInt();
        mDueDateAlert = -1 == readInt ? null : RiskAlert.values()[readInt];
        control = 0 != parcel.readByte();
    }
}
