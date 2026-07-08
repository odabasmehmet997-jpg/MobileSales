package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;

public class PurchasePriceInfo implements Parcelable {
    public static final Parcelable.Creator<PurchasePriceInfo> CREATOR = new Parcelable.Creator<PurchasePriceInfo>() { // from class: com.proje.mobilesales.features.model.PurchasePriceInfo.1

        public PurchasePriceInfo createFromParcel(final Parcel parcel) {
            return new PurchasePriceInfo(parcel);
        }

        public PurchasePriceInfo[] newArray(final int i2) {
            return new PurchasePriceInfo[i2];
        }
    };
     private String date;
   private double diffPrice;
    private int prCurr;
    private double prPrice;
    private double price;
    private int trCurr;
    private double trRate;
    public int describeContents() {
        return 0;
    }
    public PurchasePriceInfo() {
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeString(this.date);
        parcel.writeInt(this.prCurr);
        parcel.writeDouble(this.prPrice);
        parcel.writeDouble(this.price);
        parcel.writeInt(this.trCurr);
        parcel.writeDouble(this.trRate);
        parcel.writeDouble(this.diffPrice);
    }

    protected PurchasePriceInfo(final Parcel parcel) {
        this.date = parcel.readString();
        this.prCurr = parcel.readInt();
        this.prPrice = parcel.readDouble();
        this.price = parcel.readDouble();
        this.trCurr = parcel.readInt();
        this.trRate = parcel.readDouble();
        this.diffPrice = parcel.readDouble();
    }

    public String getDate() {
        return date;
    }

    public void setDate(final String str) {
        date = str;
    }

    public int getPrCurr() {
        return prCurr;
    }

    public void setPrCurr(final int i2) {
        prCurr = i2;
    }

    public double getPrPrice() {
        return prPrice;
    }

    public void setPrPrice(final double d2) {
        prPrice = d2;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double d2) {
        price = d2;
    }

    public int getTrCurr() {
        return trCurr;
    }

    public void setTrCurr(final int i2) {
        trCurr = i2;
    }

    public double getTrRate() {
        return trRate;
    }

    public void setTrRate(final double d2) {
        trRate = d2;
    }

    public double getDiffPrice() {
        return diffPrice;
    }

    public void setDiffPrice(final double d2) {
        diffPrice = d2;
    }
}
