package com.proje.mobilesales.features.product.model;

import com.proje.mobilesales.core.enums.DispatchGroupCode;
import kotlin.jvm.internal.Intrinsics;
public final class LastItemPriceSqlParams {
    private String customerCode;
    private int customerRef;
    private DispatchGroupCode dispatchGroupCode;
    private String itemCode;
    private int itemRef;
    private String variantCode;
    private int variantRef;
    private int whNr;

    public LastItemPriceSqlParams() {
        this(0, null, 0, null, 0, null, null, 0, 255, null);
    }

    public int component1() {
        return this.itemRef;
    }

    public String component2() {
        return this.itemCode;
    }

    public int component3() {
        return this.variantRef;
    }

    public String component4() {
        return this.variantCode;
    }

    public int component5() {
        return this.customerRef;
    }

    public String component6() {
        return this.customerCode;
    }

    public DispatchGroupCode component7() {
        return this.dispatchGroupCode;
    }

    public int component8() {
        return this.whNr;
    }

    public LastItemPriceSqlParams copy(int i, String str, int i2, String str2, int i3, String str3, DispatchGroupCode dispatchGroupCode, int i4) {
        Intrinsics.checkNotNullParameter(str, "itemCode");
        Intrinsics.checkNotNullParameter(str2, "variantCode");
        Intrinsics.checkNotNullParameter(str3, "customerCode");
        Intrinsics.checkNotNullParameter(dispatchGroupCode, "dispatchGroupCode");
        return new LastItemPriceSqlParams(i, str, i2, str2, i3, str3, dispatchGroupCode, i4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LastItemPriceSqlParams lastItemPriceSqlParams)) {
            return false;
        }
        return this.itemRef == lastItemPriceSqlParams.itemRef && Intrinsics.areEqual(this.itemCode, lastItemPriceSqlParams.itemCode) && this.variantRef == lastItemPriceSqlParams.variantRef && Intrinsics.areEqual(this.variantCode, lastItemPriceSqlParams.variantCode) && this.customerRef == lastItemPriceSqlParams.customerRef && Intrinsics.areEqual(this.customerCode, lastItemPriceSqlParams.customerCode) && this.dispatchGroupCode == lastItemPriceSqlParams.dispatchGroupCode && this.whNr == lastItemPriceSqlParams.whNr;
    }

    public int hashCode() {
        return (((((((((((((Integer.hashCode(this.itemRef) * 31) + this.itemCode.hashCode()) * 31) + Integer.hashCode(this.variantRef)) * 31) + this.variantCode.hashCode()) * 31) + Integer.hashCode(this.customerRef)) * 31) + this.customerCode.hashCode()) * 31) + this.dispatchGroupCode.hashCode()) * 31) + Integer.hashCode(this.whNr);
    }

    public String toString() {
        return "LastItemPriceSqlParams(itemRef=" + this.itemRef + ", itemCode=" + this.itemCode + ", variantRef=" + this.variantRef + ", variantCode=" + this.variantCode + ", customerRef=" + this.customerRef + ", customerCode=" + this.customerCode + ", dispatchGroupCode=" + this.dispatchGroupCode + ", whNr=" + this.whNr + ')';
    }

    public LastItemPriceSqlParams(int i, String str, int i2, String str2, int i3, String str3, DispatchGroupCode dispatchGroupCode, int i4) {
        Intrinsics.checkNotNullParameter(str, "itemCode");
        Intrinsics.checkNotNullParameter(str2, "variantCode");
        Intrinsics.checkNotNullParameter(str3, "customerCode");
        Intrinsics.checkNotNullParameter(dispatchGroupCode, "dispatchGroupCode");
        this.itemRef = i;
        this.itemCode = str;
        this.variantRef = i2;
        this.variantCode = str2;
        this.customerRef = i3;
        this.customerCode = str3;
        this.dispatchGroupCode = dispatchGroupCode;
        this.whNr = i4;
    }

    public   LastItemPriceSqlParams(int r10, String r11, int r12, String r13, int r14, String r15, com.proje.mobilesales.core.enums.DispatchGroupCode r16, int r17, int r18, kotlin.jvm.internal.DefaultConstructorMarker r19) {

        throw new UnsupportedOperationException("Method not decompiled: com.proje.mobilesales.features.product.model.LastItemPriceSqlParams.<init>(int, java.lang.String, int, java.lang.String, int, java.lang.String, com.proje.mobilesales.core.enums.DispatchGroupCode, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public int getItemRef() {
        return this.itemRef;
    }

    public void setItemRef(int i) {
        this.itemRef = i;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.itemCode = str;
    }

    public int getVariantRef() {
        return this.variantRef;
    }

    public void setVariantRef(int i) {
        this.variantRef = i;
    }

    public String getVariantCode() {
        return this.variantCode;
    }

    public void setVariantCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.variantCode = str;
    }

    public int getCustomerRef() {
        return this.customerRef;
    }

    public void setCustomerRef(int i) {
        this.customerRef = i;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public void setCustomerCode(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.customerCode = str;
    }

    public DispatchGroupCode getDispatchGroupCode() {
        return this.dispatchGroupCode;
    }

    public void setDispatchGroupCode(DispatchGroupCode dispatchGroupCode) {
        Intrinsics.checkNotNullParameter(dispatchGroupCode, "<set-?>");
        this.dispatchGroupCode = dispatchGroupCode;
    }

    public int getWhNr() {
        return this.whNr;
    }

    public void setWhNr(int i) {
        this.whNr = i;
    }
}
