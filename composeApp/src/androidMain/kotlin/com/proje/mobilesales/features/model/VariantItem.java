package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;

public class VariantItem implements Parcelable, Cloneable {
    public static final Parcelable.Creator<VariantItem> CREATOR = new Parcelable.Creator<VariantItem>() {

        public VariantItem createFromParcel(final Parcel parcel) {
            return new VariantItem(parcel);
        }

        public VariantItem[] newArray(final int i2) {
            return new VariantItem[i2];
        }
    };
    private double amount;
    private String code;
    private String itemCode;
    private int itemRef;
    private int logicalRef;
    private String name;
    public int describeContents() {
        return 0;
    }
    public VariantItem() {
    }
    protected VariantItem(final Parcel parcel) {
        logicalRef = parcel.readInt();
        itemRef = parcel.readInt();
        itemCode = parcel.readString();
        code = parcel.readString();
        name = parcel.readString();
        amount = parcel.readDouble();
    }
    public void writeToParcel(final Parcel parcel, final int i2) {
        parcel.writeInt(logicalRef);
        parcel.writeInt(itemRef);
        parcel.writeString(itemCode);
        parcel.writeString(code);
        parcel.writeString(name);
        parcel.writeDouble(amount);
    }
    public VariantItem clone() throws CloneNotSupportedException {
        final VariantItem variantItem = (VariantItem) super.clone();
        final String str = code;
        variantItem.code = null != str ? str : "";
        final String str2 = name;
        variantItem.name = null != str2 ? str2 : "";
        final String str3 = itemCode;
        variantItem.itemCode = null != str3 ? str3 : "";
        variantItem.itemRef = itemRef;
        variantItem.logicalRef = logicalRef;
        variantItem.amount = amount;
        return variantItem;
    }
    public int getLogicalRef() {
        return logicalRef;
    }
    public void setLogicalRef(final int i2) {
        logicalRef = i2;
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
    public double getAmount() {
        return amount;
    }
    public void setAmount(final double d2) {
        amount = d2;
    }
}
