package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class PriceInfo implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private String currCode;
    private int currNr;
    private String formattedPrice;
    private boolean incVat;
    private double price;
    private int priceRef;
    private String priceWithDigits;
    private double rate;
    public int component1() {
        return priceRef;
    }
    public double component2() {
        return price;
    }
    public String component3() {
        return priceWithDigits;
    }
    public boolean component4() {
        return incVat;
    }
    public String component5() {
        return formattedPrice;
    }
    public String component6() {
        return currCode;
    }
    public int component7() {
        return currNr;
    }
    public double component8() {
        return rate;
    }
    public PriceInfo copy(final int i2, final double d2, final String priceWithDigits, final boolean z, final String formattedPrice, final String currCode, final int i3, final double d3) {
        Intrinsics.checkNotNullParameter(priceWithDigits, "priceWithDigits");
        Intrinsics.checkNotNullParameter(formattedPrice, "formattedPrice");
        Intrinsics.checkNotNullParameter(currCode, "currCode");
        return new PriceInfo(i2, d2, priceWithDigits, z, formattedPrice, currCode, i3, d3);
    }
    public int describeContents() {
        return 0;
    }
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PriceInfo priceInfo)) {
            return false;
        }
        return priceRef == priceInfo.priceRef && 0 == Double.compare(this.price, priceInfo.price) && Intrinsics.areEqual(priceWithDigits, priceInfo.priceWithDigits) && incVat == priceInfo.incVat && Intrinsics.areEqual(formattedPrice, priceInfo.formattedPrice) && Intrinsics.areEqual(currCode, priceInfo.currCode) && currNr == priceInfo.currNr && 0 == Double.compare(this.rate, priceInfo.rate);
    }
    public int hashCode() {
        return (((((((((((((Integer.hashCode(priceRef) * 31) + Double.hashCode(price)) * 31) + priceWithDigits.hashCode()) * 31) + Boolean.hashCode(incVat)) * 31) + formattedPrice.hashCode()) * 31) + currCode.hashCode()) * 31) + Integer.hashCode(currNr)) * 31) + Double.hashCode(rate);
    }
    public String toString() {
        return "PriceInfo(priceRef=" + priceRef + ", price=" + price + ", priceWithDigits=" + priceWithDigits + ", incVat=" + incVat + ", formattedPrice=" + formattedPrice + ", currCode=" + currCode + ", currNr=" + currNr + ", rate=" + rate + ')';
    }
    public PriceInfo(final int i2, final double d2, final String priceWithDigits, final boolean z, final String formattedPrice, final String currCode, final int i3, final double d3) {
        Intrinsics.checkNotNullParameter(priceWithDigits, "priceWithDigits");
        Intrinsics.checkNotNullParameter(formattedPrice, "formattedPrice");
        Intrinsics.checkNotNullParameter(currCode, "currCode");
        priceRef = i2;
        price = d2;
        this.priceWithDigits = priceWithDigits;
        incVat = z;
        this.formattedPrice = formattedPrice;
        this.currCode = currCode;
        currNr = i3;
        rate = d3;
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
    public String getPriceWithDigits() {
        return priceWithDigits;
    }
    public void setPriceWithDigits(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        priceWithDigits = str;
    }
    public boolean getIncVat() {
        return incVat;
    }
    public void setIncVat(final boolean z) {
        incVat = z;
    }
    public String getFormattedPrice() {
        return formattedPrice;
    }
    public void setFormattedPrice(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        formattedPrice = str;
    }
    public String getCurrCode() {
        return currCode;
    }
    public void setCurrCode(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        currCode = str;
    }
    public int getCurrNr() {
        return currNr;
    }
    public void setCurrNr(final int i2) {
        currNr = i2;
    }
    public double getRate() {
        return rate;
    }
    public void setRate(final double d2) {
        rate = d2;
    }
    public PriceInfo(final Parcel parcel) {
        this(r2, r3, r5, r6, r7, null == r0 ? "" : r0, parcel.readInt(), parcel.readDouble());
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        final int readInt = parcel.readInt();
        final double readDouble = parcel.readDouble();
        final String readString = parcel.readString();
        final String str = null == readString ? "" : readString;
        final boolean z = 0 != parcel.readByte();
        final String readString2 = parcel.readString();
        final String str2 = null == readString2 ? "" : readString2;
        final String readString3 = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(final Parcel parcel, final int i2) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(priceRef);
        parcel.writeDouble(price);
        parcel.writeString(priceWithDigits);
        parcel.writeByte(incVat ? (byte) 1 : (byte) 0);
        parcel.writeString(formattedPrice);
        parcel.writeString(currCode);
        parcel.writeInt(currNr);
        parcel.writeDouble(rate);
    }

    public static final class CREATOR implements Parcelable.Creator<PriceInfo> {
        public  CREATOR(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
        public PriceInfo createFromParcel(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new PriceInfo(parcel);
        }
        public PriceInfo[] newArray(final int i2) {
            return new PriceInfo[i2];
        }
    }
}
