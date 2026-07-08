package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class SelectedVariant implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private final double amount;
    private final PriceInfo priceInfo;
    private final String productCode;
    private final String variantCode;
    private final String variantName;
    private final int variantRef;

    public static SelectedVariant copydefault(final SelectedVariant selectedVariant, String str, String str2, int i, String str3, double d, PriceInfo priceInfo, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = selectedVariant.productCode;
        }
        if (0 != (i2 & 2)) {
            str2 = selectedVariant.variantCode;
        }
        if (0 != (i2 & 4)) {
            i = selectedVariant.variantRef;
        }
        if (0 != (i2 & 8)) {
            str3 = selectedVariant.variantName;
        }
        if (0 != (i2 & 16)) {
            d = selectedVariant.amount;
        }
        if (0 != (i2 & 32)) {
            priceInfo = selectedVariant.priceInfo;
        }
        return selectedVariant.copy(str, str2, i, str3, d, priceInfo);
    }

    public String component1() {
        return productCode;
    }

    public String component2() {
        return variantCode;
    }

    public int component3() {
        return variantRef;
    }

    public String component4() {
        return variantName;
    }

    public double component5() {
        return amount;
    }

    public PriceInfo component6() {
        return priceInfo;
    }

    public SelectedVariant copy(final String str, final String str2, final int i, final String str3, final double d, final PriceInfo priceInfo) {
        Intrinsics.checkNotNullParameter(str, "productCode");
        Intrinsics.checkNotNullParameter(str2, "variantCode");
        Intrinsics.checkNotNullParameter(str3, "variantName");
        Intrinsics.checkNotNullParameter(priceInfo, "priceInfo");
        return new SelectedVariant(str, str2, i, str3, d, priceInfo);
    }
    public int describeContents() {
        return 0;
    }
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SelectedVariant selectedVariant)) {
            return false;
        }
        return Intrinsics.areEqual(productCode, selectedVariant.productCode) && Intrinsics.areEqual(variantCode, selectedVariant.variantCode) && variantRef == selectedVariant.variantRef && Intrinsics.areEqual(variantName, selectedVariant.variantName) && 0 == Double.compare(this.amount, selectedVariant.amount) && Intrinsics.areEqual(priceInfo, selectedVariant.priceInfo);
    }
    public int hashCode() {
        return (((((((((productCode.hashCode() * 31) + variantCode.hashCode()) * 31) + Integer.hashCode(variantRef)) * 31) + variantName.hashCode()) * 31) + Double.hashCode(amount)) * 31) + priceInfo.hashCode();
    }
    public String toString() {
        return "SelectedVariant(productCode=" + productCode + ", variantCode=" + variantCode + ", variantRef=" + variantRef + ", variantName=" + variantName + ", amount=" + amount + ", priceInfo=" + priceInfo + ')';
    }

    public SelectedVariant(final String str, final String str2, final int i, final String str3, final double d, final PriceInfo priceInfo) {
        Intrinsics.checkNotNullParameter(str, "productCode");
        Intrinsics.checkNotNullParameter(str2, "variantCode");
        Intrinsics.checkNotNullParameter(str3, "variantName");
        Intrinsics.checkNotNullParameter(priceInfo, "priceInfo");
        productCode = str;
        variantCode = str2;
        variantRef = i;
        variantName = str3;
        amount = d;
        this.priceInfo = priceInfo;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getVariantCode() {
        return variantCode;
    }

    public int getVariantRef() {
        return variantRef;
    }

    public String getVariantName() {
        return variantName;
    }

    public double getAmount() {
        return amount;
    }

    public PriceInfo getPriceInfo() {
        return priceInfo;
    }
    public SelectedVariant(final android.os.Parcel r22) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.model.SelectedVariant.<init>(android.os.Parcel):void");
    }

    public void writeToParcel(final Parcel parcel, final int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(productCode);
        parcel.writeString(variantCode);
        parcel.writeInt(variantRef);
        parcel.writeString(variantName);
        parcel.writeDouble(amount);
        parcel.writeParcelable(priceInfo, i);
    }
    public static final class CREATOR implements Parcelable.Creator<SelectedVariant> {
        public   CREATOR(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
        public SelectedVariant createFromParcel(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new SelectedVariant(parcel);
        }
        public SelectedVariant[] newArray(final int i) {
            return new SelectedVariant[i];
        }
    }
}