package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class RiskResult implements Parcelable {
    public static final Parcelable.Creator<RiskResult> CREATOR = new Parcelable.Creator<RiskResult>() {
        public RiskResult createFromParcel(final Parcel parcel) {
            return new RiskResult(parcel);
        }

        public RiskResult[] newArray(final int i2) {
            return new RiskResult[i2];
        }
    };
    double riskClosed;
    double riskLimit;
    double riskTotal;
    public int describeContents() {
        return 0;
    }

    public double getRiskLimit() {
        return riskLimit;
    }

    public void setRiskLimit(final double d2) {
        riskLimit = d2;
    }

    public double getRiskClosed() {
        return riskClosed;
    }

    public void setRiskClosed(final double d2) {
        riskClosed = d2;
    }

    public double getRiskTotal() {
        return riskTotal;
    }

    public void setRiskTotal(final double d2) {
        riskTotal = d2;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeDouble(riskLimit);
        parcel.writeDouble(riskClosed);
        parcel.writeDouble(riskTotal);
    }

    public RiskResult() {
    }

    protected RiskResult(final Parcel parcel) {
        riskLimit = parcel.readDouble();
        riskClosed = parcel.readDouble();
        riskTotal = parcel.readDouble();
    }
}
