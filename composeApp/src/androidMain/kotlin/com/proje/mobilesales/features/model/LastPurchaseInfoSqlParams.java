package com.proje.mobilesales.features.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class LastPurchaseInfoSqlParams {
    private final String itemCode;
    private final int itemRef;
    private final String variantCode;
    private final Integer variantRef;
    private final Integer whNr;

    public static  LastPurchaseInfoSqlParams copydefault(final LastPurchaseInfoSqlParams lastPurchaseInfoSqlParams, int i2, String str, Integer num, String str2, Integer num2, final int i3, final Object obj) {
        if (0 != (i3 & 1)) {
            i2 = lastPurchaseInfoSqlParams.itemRef;
        }
        if (0 != (i3 & 2)) {
            str = lastPurchaseInfoSqlParams.itemCode;
        }
        final String str3 = str;
        if (0 != (i3 & 4)) {
            num = lastPurchaseInfoSqlParams.variantRef;
        }
        final Integer num3 = num;
        if (0 != (i3 & 8)) {
            str2 = lastPurchaseInfoSqlParams.variantCode;
        }
        final String str4 = str2;
        if (0 != (i3 & 16)) {
            num2 = lastPurchaseInfoSqlParams.whNr;
        }
        return lastPurchaseInfoSqlParams.copy(i2, str3, num3, str4, num2);
    }

    public int component1() {
        return itemRef;
    }

    public String component2() {
        return itemCode;
    }

    public Integer component3() {
        return variantRef;
    }

    public String component4() {
        return variantCode;
    }

    public Integer component5() {
        return whNr;
    }

    public LastPurchaseInfoSqlParams copy(final int i2, final String itemCode, final Integer num, final String str, final Integer num2) {
        Intrinsics.checkNotNullParameter(itemCode, "itemCode");
        return new LastPurchaseInfoSqlParams(i2, itemCode, num, str, num2);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LastPurchaseInfoSqlParams lastPurchaseInfoSqlParams)) {
            return false;
        }
        return itemRef == lastPurchaseInfoSqlParams.itemRef && Intrinsics.areEqual(itemCode, lastPurchaseInfoSqlParams.itemCode) && Intrinsics.areEqual(variantRef, lastPurchaseInfoSqlParams.variantRef) && Intrinsics.areEqual(variantCode, lastPurchaseInfoSqlParams.variantCode) && Intrinsics.areEqual(whNr, lastPurchaseInfoSqlParams.whNr);
    }

    public int hashCode() {
        final int hashCode = ((Integer.hashCode(itemRef) * 31) + itemCode.hashCode()) * 31;
        final Integer num = variantRef;
        final int hashCode2 = (hashCode + (null == num ? 0 : num.hashCode())) * 31;
        final String str = variantCode;
        final int hashCode3 = (hashCode2 + (null == str ? 0 : str.hashCode())) * 31;
        final Integer num2 = whNr;
        return hashCode3 + (null != num2 ? num2.hashCode() : 0);
    }

    public String toString() {
        return "LastPurchaseInfoSqlParams(itemRef=" + itemRef + ", itemCode=" + itemCode + ", variantRef=" + variantRef + ", variantCode=" + variantCode + ", whNr=" + whNr + ')';
    }

    public LastPurchaseInfoSqlParams(final int i2, final String itemCode, final Integer num, final String str, final Integer num2) {
        Intrinsics.checkNotNullParameter(itemCode, "itemCode");
        itemRef = i2;
        this.itemCode = itemCode;
        variantRef = num;
        variantCode = str;
        whNr = num2;
    }

    public int getItemRef() {
        return itemRef;
    }

    public String getItemCode() {
        return itemCode;
    }

    public  LastPurchaseInfoSqlParams(final int i2, final String str, final Integer num, final String str2, final Integer num2, final int i3, final DefaultConstructorMarker defaultConstructorMarker) {
        this(i2, str, 0 != (i3 & 4) ? 0 : num, 0 != (i3 & 8) ? "" : str2, 0 != (i3 & 16) ? 0 : num2);
    }

    public Integer getVariantRef() {
        return variantRef;
    }

    public String getVariantCode() {
        return variantCode;
    }

    public Integer getWhNr() {
        return whNr;
    }
}
