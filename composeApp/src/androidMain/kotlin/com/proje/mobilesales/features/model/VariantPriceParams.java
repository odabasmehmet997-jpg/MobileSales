package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
 
public final class VariantPriceParams implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private final int customerRef;
    private final String customerTradeGroup;
    private final int paymentRef;
    private final String tradeGroup;

    public static VariantPriceParams copydefault(final VariantPriceParams variantPriceParams, int i, int i2, String str, String str2, final int i3, final Object obj) {
        if (0 != (i3 & 1)) {
            i = variantPriceParams.customerRef;
        }
        if (0 != (i3 & 2)) {
            i2 = variantPriceParams.paymentRef;
        }
        if (0 != (i3 & 4)) {
            str = variantPriceParams.tradeGroup;
        }
        if (0 != (i3 & 8)) {
            str2 = variantPriceParams.customerTradeGroup;
        }
        return variantPriceParams.copy(i, i2, str, str2);
    }

    public int component1() {
        return customerRef;
    }

    public int component2() {
        return paymentRef;
    }

    public String component3() {
        return tradeGroup;
    }

    public String component4() {
        return customerTradeGroup;
    }

    public VariantPriceParams copy(final int i, final int i2, final String str, final String str2) {
        Intrinsics.checkNotNullParameter(str, "tradeGroup");
        Intrinsics.checkNotNullParameter(str2, "customerTradeGroup");
        return new VariantPriceParams(i, i2, str, str2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Object
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VariantPriceParams variantPriceParams)) {
            return false;
        }
        return customerRef == variantPriceParams.customerRef && paymentRef == variantPriceParams.paymentRef && Intrinsics.areEqual(tradeGroup, variantPriceParams.tradeGroup) && Intrinsics.areEqual(customerTradeGroup, variantPriceParams.customerTradeGroup);
    }

    @Override // java.lang.Object
    public int hashCode() {
        return (((((Integer.hashCode(customerRef) * 31) + Integer.hashCode(paymentRef)) * 31) + tradeGroup.hashCode()) * 31) + customerTradeGroup.hashCode();
    }

    @Override // java.lang.Object
    public String toString() {
        return "VariantPriceParams(customerRef=" + customerRef + ", paymentRef=" + paymentRef + ", tradeGroup=" + tradeGroup + ", customerTradeGroup=" + customerTradeGroup + ')';
    }

    public VariantPriceParams(final int i, final int i2, final String str, final String str2) {
        Intrinsics.checkNotNullParameter(str, "tradeGroup");
        Intrinsics.checkNotNullParameter(str2, "customerTradeGroup");
        customerRef = i;
        paymentRef = i2;
        tradeGroup = str;
        customerTradeGroup = str2;
    }

    public int getCustomerRef() {
        return customerRef;
    }

    public int getPaymentRef() {
        return paymentRef;
    }

    public String getTradeGroup() {
        return tradeGroup;
    }

    public String getCustomerTradeGroup() {
        return customerTradeGroup;
    }
 
    public VariantPriceParams(final android.os.Parcel r5) {
      
        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.model.VariantPriceParams.<init>(android.os.Parcel):void");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(final Parcel parcel, final int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(customerRef);
        parcel.writeInt(paymentRef);
        parcel.writeString(tradeGroup);
        parcel.writeString(customerTradeGroup);
    } 
    public static final class CREATOR implements Parcelable.Creator<VariantPriceParams> {
        public CREATOR(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }
 
        public VariantPriceParams createFromParcel(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new VariantPriceParams(parcel);
        }
 
        public VariantPriceParams[] newArray(final int i) {
            return new VariantPriceParams[i];
        }
    }
}