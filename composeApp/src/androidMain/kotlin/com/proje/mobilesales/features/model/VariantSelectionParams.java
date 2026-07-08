package com.proje.mobilesales.features.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class VariantSelectionParams implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR(null);
    private final int divisionNr;
    private final boolean isDivUnit;
    private final int position;
    private final String productCode;
    private final int productId;
    private final String productName;
    private final ArrayList<SelectedVariant> selectedVariants;
    private String unitCode;
    private int unitRef;
    private final VariantPriceParams variantPriceParams;
    private final int whNr;

    public int component1() {
        return productId;
    }

    public ArrayList<SelectedVariant> component10() {
        return selectedVariants;
    }

    public int component11() {
        return divisionNr;
    }

    public String component2() {
        return productCode;
    }

    public String component3() {
        return productName;
    }

    public boolean component4() {
        return isDivUnit;
    }

    public int component5() {
        return whNr;
    }

    public int component6() {
        return position;
    }

    public VariantPriceParams component7() {
        return variantPriceParams;
    }

    public int component8() {
        return unitRef;
    }

    public String component9() {
        return unitCode;
    }

    public VariantSelectionParams copy(final int i, final String str, final String str2, final boolean z, final int i2, final int i3, final VariantPriceParams variantPriceParams, final int i4, final String str3, final ArrayList<SelectedVariant> arrayList, final int i5) {
        Intrinsics.checkNotNullParameter(str, "productCode");
        Intrinsics.checkNotNullParameter(str2, "productName");
        Intrinsics.checkNotNullParameter(str3, "unitCode");
        Intrinsics.checkNotNullParameter(arrayList, "selectedVariants");
        return new VariantSelectionParams(i, str, str2, z, i2, i3, variantPriceParams, i4, str3, arrayList, i5);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VariantSelectionParams variantSelectionParams)) {
            return false;
        }
        return productId == variantSelectionParams.productId && Intrinsics.areEqual(productCode, variantSelectionParams.productCode) && Intrinsics.areEqual(productName, variantSelectionParams.productName) && isDivUnit == variantSelectionParams.isDivUnit && whNr == variantSelectionParams.whNr && position == variantSelectionParams.position && Intrinsics.areEqual(variantPriceParams, variantSelectionParams.variantPriceParams) && unitRef == variantSelectionParams.unitRef && Intrinsics.areEqual(unitCode, variantSelectionParams.unitCode) && Intrinsics.areEqual(selectedVariants, variantSelectionParams.selectedVariants) && divisionNr == variantSelectionParams.divisionNr;
    }

    public int hashCode() {
        final int hashCode = ((((((((((Integer.hashCode(productId) * 31) + productCode.hashCode()) * 31) + productName.hashCode()) * 31) + Boolean.hashCode(isDivUnit)) * 31) + Integer.hashCode(whNr)) * 31) + Integer.hashCode(position)) * 31;
        final VariantPriceParams variantPriceParams = this.variantPriceParams;
        return ((((((((hashCode + (null == variantPriceParams ? 0 : variantPriceParams.hashCode())) * 31) + Integer.hashCode(unitRef)) * 31) + unitCode.hashCode()) * 31) + selectedVariants.hashCode()) * 31) + Integer.hashCode(divisionNr);
    }

    public String toString() {
        return "VariantSelectionParams(productId=" + productId + ", productCode=" + productCode + ", productName=" + productName + ", isDivUnit=" + isDivUnit + ", whNr=" + whNr + ", position=" + position + ", variantPriceParams=" + variantPriceParams + ", unitRef=" + unitRef + ", unitCode=" + unitCode + ", selectedVariants=" + selectedVariants + ", divisionNr=" + divisionNr + ')';
    }

    public VariantSelectionParams(final int i, final String str, final String str2, final boolean z, final int i2, final int i3, final VariantPriceParams variantPriceParams, final int i4, final String str3, final ArrayList<SelectedVariant> arrayList, final int i5) {
        Intrinsics.checkNotNullParameter(str, "productCode");
        Intrinsics.checkNotNullParameter(str2, "productName");
        Intrinsics.checkNotNullParameter(str3, "unitCode");
        Intrinsics.checkNotNullParameter(arrayList, "selectedVariants");
        productId = i;
        productCode = str;
        productName = str2;
        isDivUnit = z;
        whNr = i2;
        position = i3;
        this.variantPriceParams = variantPriceParams;
        unitRef = i4;
        unitCode = str3;
        selectedVariants = arrayList;
        divisionNr = i5;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public boolean isDivUnit() {
        return isDivUnit;
    }

    public int getWhNr() {
        return whNr;
    }

    public int getPosition() {
        return position;
    }

    public VariantPriceParams getVariantPriceParams() {
        return variantPriceParams;
    }

    public int getUnitRef() {
        return unitRef;
    }

    public void setUnitRef(final int i) {
        unitRef = i;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        unitCode = str;
    }

    public ArrayList<SelectedVariant> getSelectedVariants() {
        return selectedVariants;
    }

    public int getDivisionNr() {
        return divisionNr;
    }

    public VariantSelectionParams(final android.os.Parcel r14) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.model.VariantSelectionParams.<init>(android.os.Parcel):void");
    }
    public void writeToParcel(final Parcel parcel, final int i) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(productId);
        parcel.writeString(productCode);
        parcel.writeString(productName);
        parcel.writeByte(isDivUnit ? (byte) 1 : 0);
        parcel.writeInt(whNr);
        parcel.writeInt(position);
        parcel.writeParcelable(variantPriceParams, i);
        parcel.writeInt(unitRef);
        parcel.writeString(unitCode);
        parcel.writeTypedList(selectedVariants);
        parcel.writeInt(divisionNr);
    }

    public static final class CREATOR implements Parcelable.Creator<VariantSelectionParams> {
        public  CREATOR(final DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private CREATOR() {
        }

        public VariantSelectionParams createFromParcel(final Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new VariantSelectionParams(parcel);
        }

        public VariantSelectionParams[] newArray(final int i) {
            return new VariantSelectionParams[i];
        }
    }
}