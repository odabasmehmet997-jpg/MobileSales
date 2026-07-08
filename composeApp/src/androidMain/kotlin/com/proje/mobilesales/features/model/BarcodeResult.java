package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class BarcodeResult implements Parcelable {
    public static final Parcelable.Creator<BarcodeResult> CREATOR = new Parcelable.Creator<BarcodeResult>() {
        public BarcodeResult createFromParcel(final Parcel parcel) {
            return new BarcodeResult(parcel);
        }

        public BarcodeResult[] newArray(final int i2) {
            return new BarcodeResult[i2];
        }
    };
    double amount;
    String barcode;
    int itemRef;
    private FicheStringProp priceProp;
    private double secondAmount;
    private String secondUnitCode;
    private FicheDiscountRefProp selectedPriceRefProp;
    private String unitCode;
    public int describeContents() {
        return 0;
    }

    public BarcodeResult(final String str, final double d2, final int i2) {
        barcode = str;
        amount = d2;
        itemRef = i2;
        unitCode = "";
    }

    public BarcodeResult(final String str, final double d2) {
        barcode = str;
        amount = d2;
    }

    public BarcodeResult(final String str, final double d2, final int i2, final String str2) {
        barcode = str;
        amount = d2;
        itemRef = i2;
        unitCode = str2;
    }

    public BarcodeResult(final String str, final double d2, final int i2, final String str2, final FicheDiscountRefProp ficheDiscountRefProp, final FicheStringProp ficheStringProp) {
        barcode = str;
        amount = d2;
        itemRef = i2;
        unitCode = str2;
        selectedPriceRefProp = ficheDiscountRefProp;
        priceProp = ficheStringProp;
    }

    public BarcodeResult(final String str) {
        barcode = str;
        amount = 0.0d;
    }

    public BarcodeResult() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double d2) {
        amount = d2;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(final String str) {
        barcode = str;
    }

    public int getItemRef() {
        return itemRef;
    }

    public void setItemRef(final int i2) {
        itemRef = i2;
    }

    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeString(barcode);
        parcel.writeDouble(amount);
        parcel.writeInt(itemRef);
        parcel.writeString(unitCode);
        parcel.writeDouble(secondAmount);
        parcel.writeString(secondUnitCode);
        parcel.writeParcelable(selectedPriceRefProp, i2);
        parcel.writeParcelable(priceProp, i2);
    }

    protected BarcodeResult(final Parcel parcel) {
        barcode = parcel.readString();
        amount = parcel.readDouble();
        itemRef = parcel.readInt();
        unitCode = parcel.readString();
        secondAmount = parcel.readDouble();
        secondUnitCode = parcel.readString();
        selectedPriceRefProp = parcel.readParcelable(FicheDiscountRefProp.class.getClassLoader());
        priceProp = parcel.readParcelable(FicheStringProp.class.getClassLoader());
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

    public FicheDiscountRefProp getSelectedPriceRefProp() {
        return selectedPriceRefProp;
    }

    public void setSelectedPriceRefProp(final FicheDiscountRefProp ficheDiscountRefProp) {
        selectedPriceRefProp = ficheDiscountRefProp;
    }

    public FicheStringProp getPriceProp() {
        return priceProp;
    }

    public void setPriceProp(final FicheStringProp ficheStringProp) {
        priceProp = ficheStringProp;
    }
}
