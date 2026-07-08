package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class MonthlyProductSales implements Parcelable {
    public static final Parcelable.Creator<MonthlyProductSales> CREATOR = new Parcelable.Creator<MonthlyProductSales>() {
        public MonthlyProductSales createFromParcel(final Parcel parcel) {
            return new MonthlyProductSales(parcel);
        }

        public MonthlyProductSales[] newArray(final int i2) {
            return new MonthlyProductSales[i2];
        }
    };
    String month;
    String salesAmount;
    String salesCashAmount;
    public int describeContents() {
        return 0;
    }
    public String getMonth() {
        return month;
    }
    public void setMonth(final String str) {
        month = str;
    }
    public String getSalesAmount() {
        return salesAmount;
    }
    public void setSalesAmount(final String str) {
        salesAmount = str;
    }
    public String getSalesCashAmount() {
        return salesCashAmount;
    }
    public void setSalesCashAmount(final String str) {
        salesCashAmount = str;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeString(month);
        parcel.writeString(salesAmount);
        parcel.writeString(salesCashAmount);
    }
    public MonthlyProductSales() {
    }
    protected MonthlyProductSales(final Parcel parcel) {
        month = parcel.readString();
        salesAmount = parcel.readString();
        salesCashAmount = parcel.readString();
    }
}
