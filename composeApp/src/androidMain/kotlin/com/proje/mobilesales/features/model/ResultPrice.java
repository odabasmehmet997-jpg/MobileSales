package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ResultPrice implements Parcelable {
    public static final Parcelable.Creator<ResultPrice> CREATOR = new Parcelable.Creator<ResultPrice>() {
        public ResultPrice createFromParcel(final Parcel parcel) {
            return new ResultPrice(parcel);
        }

        public ResultPrice[] newArray(final int i2) {
            return new ResultPrice[i2];
        }
    };
    private String curCode;
    private int curType;
    private int incVat;
    private String masterCode;
    private double pcPrice;
    private double price;
    private String priceCode;
    private int priceRef;
    private int type;
    private String unitCode;
    private String variantCode;
    private double vat;

    public int describeContents() {
        return 0;
    }

    public int getType() {
        return type;
    }

    public void setType(final int i2) {
        type = i2;
    }

    public String getMasterCode() {
        return masterCode;
    }

    public void setMasterCode(final String str) {
        masterCode = str;
    }

    public String getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(final String str) {
        priceCode = str;
    }

    public int getPriceRef() {
        return priceRef;
    }

    public void setPriceRef(final int i2) {
        priceRef = i2;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double d2) {
        price = d2;
    }

    public double getPcPrice() {
        return pcPrice;
    }

    public void setPcPrice(final double d2) {
        pcPrice = d2;
    }

    public int getIncVat() {
        return incVat;
    }

    public void setIncVat(final int i2) {
        incVat = i2;
    }

    public int getCurType() {
        return curType;
    }

    public void setCurType(final int i2) {
        curType = i2;
    }

    public String getCurCode() {
        return curCode;
    }

    public void setCurCode(final String str) {
        curCode = str;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(final double d2) {
        vat = d2;
    }

    public void setUnitCode(final String str) {
        unitCode = str;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public ResultPrice() {
        this.variantCode = "";
        this.masterCode = "";
        this.unitCode = "";
        this.priceCode = "";
        this.curCode = "";
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(type);
        parcel.writeString(masterCode);
        parcel.writeString(priceCode);
        parcel.writeInt(priceRef);
        parcel.writeDouble(price);
        parcel.writeDouble(pcPrice);
        parcel.writeInt(incVat);
        parcel.writeInt(curType);
        parcel.writeString(curCode);
        parcel.writeDouble(vat);
        parcel.writeString(unitCode);
        parcel.writeString(variantCode);
    }

    protected ResultPrice(final Parcel parcel) {
        type = parcel.readInt();
        masterCode = parcel.readString();
        priceCode = parcel.readString();
        priceRef = parcel.readInt();
        price = parcel.readDouble();
        pcPrice = parcel.readDouble();
        incVat = parcel.readInt();
        curType = parcel.readInt();
        curCode = parcel.readString();
        vat = parcel.readDouble();
        unitCode = parcel.readString();
        variantCode = parcel.readString();
    }

    public String getVariantCode() {
        return variantCode;
    }

    public void setVariantCode(final String str) {
        variantCode = str;
    }
}
