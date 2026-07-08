package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemVariantStock implements Parcelable, Cloneable {
    public static final Parcelable.Creator<ItemVariantStock> CREATOR = new Parcelable.Creator<ItemVariantStock>() {
        public ItemVariantStock createFromParcel(final Parcel parcel) {
            return new ItemVariantStock(parcel);
        }

        public ItemVariantStock[] newArray(final int i2) {
            return new ItemVariantStock[i2];
        }
    };
    private double amount;
    private String itemCode;
    private int itemRef;
    private double variantActualStok;
    private String variantCode;
    private String variantName;
    private double variantRealStok;
    private int variantRef;
    private int wareHouseNr;
    public int describeContents() {
        return 0;
    }
    public ItemVariantStock() {
    }
    public ItemVariantStock(final int i2, final int i3) {
        itemRef = i2;
        variantRef = i3;
    }
    protected ItemVariantStock(final Parcel parcel) {
        variantCode = parcel.readString();
        variantActualStok = parcel.readDouble();
        variantRealStok = parcel.readDouble();
        variantName = parcel.readString();
        variantRef = parcel.readInt();
        wareHouseNr = parcel.readInt();
        amount = parcel.readDouble();
        itemRef = parcel.readInt();
        itemCode = parcel.readString();
    }
    public String getVarintCode() {
        return variantCode;
    }
    public void setVarintCode(final String str) {
        variantCode = str;
    }
    public double getVariantActualStok() {
        return variantActualStok;
    }
    public void setVariantActualStok(final double d2) {
        variantActualStok = d2;
    }
    public double getVariantRealStok() {
        return variantRealStok;
    }
    public void setVariantRealStok(final double d2) {
        variantRealStok = d2;
    }
    public String getVariantName() {
        return variantName;
    }
    public void setVariantName(final String str) {
        variantName = str;
    }
    public int getVariantRef() {
        return variantRef;
    }
    public void setVariantRef(final int i2) {
        variantRef = i2;
    }
    public int getWareHouseNr() {
        return wareHouseNr;
    }
    public void setWareHouseNr(final int i2) {
        wareHouseNr = i2;
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeString(variantCode);
        parcel.writeDouble(variantActualStok);
        parcel.writeDouble(variantRealStok);
        parcel.writeString(variantName);
        parcel.writeInt(variantRef);
        parcel.writeInt(wareHouseNr);
        parcel.writeDouble(amount);
        parcel.writeInt(itemRef);
        parcel.writeString(itemCode);
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(final double d2) {
        amount = d2;
    }
    public int getItemRef() {
        return itemRef;
    }
    public void setItemRef(final int i2) {
        itemRef = i2;
    }
    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(final String str) {
        itemCode = str;
    }
}
