package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CompositeDetail implements Parcelable {
    public static final Parcelable.Creator<CompositeDetail> CREATOR = new Parcelable.Creator<CompositeDetail>() {
        public CompositeDetail createFromParcel(final Parcel parcel) {
            return new CompositeDetail(parcel);
        }
        public CompositeDetail[] newArray(final int i2) {
            return new CompositeDetail[i2];
        }
    };
    double amount;
    String code;
    double convFact1;
    double convFact2;
    String name;
    double perc;
    double price;
    int stockRef;
    String unitCode;
    int unitLineRef;
    private String variantCode;
    private int variantRef;
    double vat;
    public int describeContents() {
        return 0;
    }
    public int getStockRef() {
        return stockRef;
    }
    public void setStockRef(final int i2) {
        stockRef = i2;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(final double d2) {
        amount = d2;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(final double d2) {
        price = d2;
    }
    public double getVat() {
        return vat;
    }
    public void setVat(final double d2) {
        vat = d2;
    }
    public String getCode() {
        return code;
    }
    public void setCode(final String str) {
        code = str;
    }
    public String getName() {
        return name;
    }
    public void setName(final String str) {
        name = str;
    }
    public double getConvFact1() {
        return convFact1;
    }
    public void setConvFact1(final double d2) {
        convFact1 = d2;
    }
    public double getConvFact2() {
        return convFact2;
    }
    public void setConvFact2(final double d2) {
        convFact2 = d2;
    }
    public String getUnitCode() {
        return unitCode;
    }
    public void setUnitCode(final String str) {
        unitCode = str;
    }
    public int getUnitLineRef() {
        return unitLineRef;
    }
    public void setUnitLineRef(final int i2) {
        unitLineRef = i2;
    }
    public double getPerc() {
        return perc;
    }
    public void setPerc(final double d2) {
        perc = d2;
    }
    public CompositeDetail() {
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(stockRef);
        parcel.writeDouble(amount);
        parcel.writeDouble(price);
        parcel.writeDouble(vat);
        parcel.writeString(code);
        parcel.writeString(name);
        parcel.writeString(unitCode);
        parcel.writeInt(unitLineRef);
        parcel.writeDouble(convFact1);
        parcel.writeDouble(convFact2);
        parcel.writeDouble(perc);
        parcel.writeInt(variantRef);
        parcel.writeString(variantCode);
    }
    protected CompositeDetail(final Parcel parcel) {
        stockRef = parcel.readInt();
        amount = parcel.readDouble();
        price = parcel.readDouble();
        vat = parcel.readDouble();
        code = parcel.readString();
        name = parcel.readString();
        unitCode = parcel.readString();
        unitLineRef = parcel.readInt();
        convFact1 = parcel.readDouble();
        convFact2 = parcel.readDouble();
        perc = parcel.readDouble();
        variantRef = parcel.readInt();
        variantCode = parcel.readString();
    }
    public int getVariantRef() {
        return variantRef;
    }
    public void setVariantRef(final int i2) {
        variantRef = i2;
    }
    public String getVariantCode() {
        return variantCode;
    }
    public void setVariantCode(final String str) {
        variantCode = str;
    }
}
