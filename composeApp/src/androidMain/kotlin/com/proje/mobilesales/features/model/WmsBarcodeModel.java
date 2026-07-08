package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;

public class WmsBarcodeModel implements Parcelable {
    public static final Parcelable.Creator<WmsBarcodeModel> CREATOR = new Parcelable.Creator<WmsBarcodeModel>() {
        public WmsBarcodeModel createFromParcel(final Parcel parcel) {
            return new WmsBarcodeModel(parcel);
        }
        public WmsBarcodeModel[] newArray(final int i2) {
            return new WmsBarcodeModel[i2];
        }
    };
    private double amount;
    private String barcode;
    private String expireDate;
    private double secondAmount;
    private String secondUnitCode;
    private String trackingNumber;
    private String unitCode;
    public int describeContents() {
        return 0;
    }
    protected WmsBarcodeModel(final Parcel parcel) {
        barcode = parcel.readString();
        amount = parcel.readFloat();
        trackingNumber = parcel.readString();
        expireDate = parcel.readString();
        unitCode = parcel.readString();
        secondAmount = parcel.readFloat();
        secondUnitCode = parcel.readString();
    }
    public String getBarcode() {
        return barcode;
    }
    public void setBarcode(final String str) {
        barcode = str;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(final double d2) {
        amount = d2;
    }
    public String getTrackingNumber() {
        return trackingNumber;
    }
    public void setTrackingNumber(final String str) {
        trackingNumber = str;
    }
    public String getExpireDate() {
        return expireDate;
    }
    public void setExpireDate(final String str) {
        expireDate = str;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeString(barcode);
        parcel.writeDouble(amount);
        parcel.writeString(trackingNumber);
        parcel.writeString(expireDate);
        parcel.writeString(unitCode);
        parcel.writeDouble(secondAmount);
        parcel.writeString(secondUnitCode);
    }
    public String getUnitCode() {
        return unitCode;
    }
    public void setUnitCode(final String str) {
        unitCode = str;
    }
    public double getSecondAmount() {
        return secondAmount;
    }
    public void setSecondAmount(final double d2) {
        secondAmount = d2;
    }
    public String getSecondUnitCode() {
        return secondUnitCode;
    }
    public void setSecondUnitCode(final String str) {
        secondUnitCode = str;
    }
}
