package com.proje.mobilesales.features.sales.model.fiche;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.Intrinsics;

public class SalesFicheFields implements Parcelable {
    private boolean[] discountCards;
    private boolean[] discountRatios;
    private boolean[] discountTotals;
    private boolean isDeliveryDate;
    private boolean isDoSalesDiscount;
    private boolean isPayment;
    private boolean isSpeCode;
    public static final Creator<SalesFicheFields> CREATOR = new Creator<SalesFicheFields>() {
        public SalesFicheFields createFromParcel(Parcel in) {
            return new SalesFicheFields(in);
        }
        public SalesFicheFields[] newArray(int size) {
            return new SalesFicheFields[size];
        }
    };
    public int describeContents() {
        return 0;
    }
    public final boolean isPayment() {
        return this.isPayment;
    }
    public final void setPayment(boolean z) {
        this.isPayment = z;
    }
    public final boolean isSpeCode() {
        return this.isSpeCode;
    }
    public final void setSpeCode(boolean z) {
        this.isSpeCode = z;
    }
    public final boolean isDoSalesDiscount() {
        return this.isDoSalesDiscount;
    }
    public final void setDoSalesDiscount(boolean z) {
        this.isDoSalesDiscount = z;
    }
    public final boolean isDeliveryDate() {
        return this.isDeliveryDate;
    }
    public final void setDeliveryDate(boolean z) {
        this.isDeliveryDate = z;
    }
    protected final boolean[] getDiscountRatios() {
        return this.discountRatios;
    }
    protected final void setDiscountRatios(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<set-?>");
        this.discountRatios = zArr;
    }
    protected final boolean[] getDiscountTotals() {
        return this.discountTotals;
    }
    protected final void setDiscountTotals(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<set-?>");
        this.discountTotals = zArr;
    }
    protected final boolean[] getDiscountCards() {
        return this.discountCards;
    }
    protected final void setDiscountCards(boolean[] zArr) {
        Intrinsics.checkNotNullParameter(zArr, "<set-?>");
        this.discountCards = zArr;
    }
    public SalesFicheFields() {
        this.discountRatios = new boolean[5];
        this.discountTotals = new boolean[5];
        this.discountCards = new boolean[5];
    }
    protected SalesFicheFields(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        this.discountRatios = new boolean[5];
        this.discountTotals = new boolean[5];
        this.discountCards = new boolean[5];
        this.isPayment = parcel.readByte() != 0;
        this.isSpeCode = parcel.readByte() != 0;
        this.isDoSalesDiscount = parcel.readByte() != 0;
        this.isDeliveryDate = parcel.readByte() != 0;
        boolean[] createBooleanArray = parcel.createBooleanArray();
        this.discountRatios = createBooleanArray == null ? new boolean[5] : createBooleanArray;
        boolean[] createBooleanArray2 = parcel.createBooleanArray();
        this.discountTotals = createBooleanArray2 == null ? new boolean[5] : createBooleanArray2;
        boolean[] createBooleanArray3 = parcel.createBooleanArray();
        this.discountCards = createBooleanArray3 == null ? new boolean[5] : createBooleanArray3;
    }
    public final void setDiscount(int i2, int i3, int i4) {
        if (this.isDoSalesDiscount) {
            setDiscounts(this.discountCards, i4);
            setDiscounts(this.discountTotals, i3);
            setDiscounts(this.discountRatios, i2);
        }
    }
    public final void setDiscounts(boolean[] discounts, int i2) {
        Intrinsics.checkNotNullParameter(discounts, "discounts");
        if (i2 == 0) {
            return;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            discounts[i3] = true;
        }
    }
    public final boolean isDoDiscountRatio(int i2) {
        return checkDiscount(this.discountRatios, i2);
    }
    public final boolean isDoDiscountTotal(int i2) {
        return checkDiscount(this.discountTotals, i2);
    }
    public final boolean isDoDiscountCard(int i2) {
        return checkDiscount(this.discountCards, i2);
    }
    private   boolean checkDiscount(boolean[] zArr, int i2) {
        if (i2 >= zArr.length) {
            return false;
        }
        return zArr[i2];
    }
    public void writeToParcel(Parcel dest, int i2) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeByte(this.isPayment ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isSpeCode ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDoSalesDiscount ? (byte) 1 : (byte) 0);
        dest.writeByte(this.isDeliveryDate ? (byte) 1 : (byte) 0);
        dest.writeBooleanArray(this.discountRatios);
        dest.writeBooleanArray(this.discountTotals);
        dest.writeBooleanArray(this.discountCards);
    }
}
