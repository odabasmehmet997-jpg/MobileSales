package com.proje.mobilesales.features.dbmodel;

import android.os.Parcel;
import android.os.Parcelable;

public class LocationStockTracking implements Cloneable, Parcelable {
    public static final Parcelable.Creator<LocationStockTracking> CREATOR = new Parcelable.Creator<LocationStockTracking>() {
        public LocationStockTracking createFromParcel(Parcel parcel) {
            return new LocationStockTracking(parcel);
        }
        public LocationStockTracking[] newArray(int i2) {
            return new LocationStockTracking[i2];
        }
    };
    private String expDate;
    private String locCode;
    private int locRef;
    private int logicalRef;
    private double remAmount;
    private int stTransRef;
    private String unit;
    private int uomRef;
    public int describeContents() {
        return 0;
    }
    protected LocationStockTracking(Parcel parcel) {
        this.logicalRef = parcel.readInt();
        this.unit = parcel.readString();
        this.remAmount = parcel.readDouble();
        this.expDate = parcel.readString();
        this.locRef = parcel.readInt();
        this.locCode = parcel.readString();
        this.uomRef = parcel.readInt();
        this.stTransRef = parcel.readInt();
    }
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.logicalRef);
        parcel.writeString(this.unit);
        parcel.writeDouble(this.remAmount);
        parcel.writeString(this.expDate);
        parcel.writeInt(this.locRef);
        parcel.writeString(this.locCode);
        parcel.writeInt(this.uomRef);
        parcel.writeInt(this.stTransRef);
    }
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String str) {
        this.unit = str;
    }
    public double getRemAmount() {
        return this.remAmount;
    }
    public void setRemAmount(double d2) {
        this.remAmount = d2;
    }
    public String getExpDate() {
        return this.expDate;
    }
    public void setExpDate(String str) {
        this.expDate = str;
    }
    public int getLocRef() {
        return this.locRef;
    }
    public void setLocRef(int i2) {
        this.locRef = i2;
    }
    public String getLocCode() {
        return this.locCode;
    }
    public void setLocCode(String str) {
        this.locCode = str;
    }
    public int getUomRef() {
        return this.uomRef;
    }
    public void setUomRef(int i2) {
        this.uomRef = i2;
    }
    public int getStTransRef() {
        return this.stTransRef;
    }
    public void setStTransRef(int i2) {
        this.stTransRef = i2;
    }
}
