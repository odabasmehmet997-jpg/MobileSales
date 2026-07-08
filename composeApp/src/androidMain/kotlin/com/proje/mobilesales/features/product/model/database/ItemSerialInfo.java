package com.proje.mobilesales.features.product.model.database;
 
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ItemSerialInfo {
    public String amount;
    public int logicalRef;
    public String serialNumber1;
    public String serialNumber2;
   public ItemSerialInfo() {
        this(0, null, null, null, 15, null);
    }
    public static ItemSerialInfo copy(final ItemSerialInfo itemSerialInfo, int i, String str, String str2, String str3, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            i = itemSerialInfo.logicalRef;
        }
        if (0 != (i2 & 2)) {
            str = itemSerialInfo.serialNumber1;
        }
        if (0 != (i2 & 4)) {
            str2 = itemSerialInfo.serialNumber2;
        }
        if (0 != (i2 & 8)) {
            str3 = itemSerialInfo.amount;
        }
        return itemSerialInfo.copy(i, str, str2, str3);
    }

    public int component1() {
        return logicalRef;
    }

    public String component2() {
        return serialNumber1;
    }

    public String component3() {
        return serialNumber2;
    }

    public String component4() {
        return amount;
    }

    public ItemSerialInfo copy(final int i, final String str, final String str2, final String str3) {
        return new ItemSerialInfo(i, str, str2, str3);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ItemSerialInfo itemSerialInfo)) {
            return false;
        }
        return logicalRef == itemSerialInfo.logicalRef && Intrinsics.areEqual(serialNumber1, itemSerialInfo.serialNumber1) && Intrinsics.areEqual(serialNumber2, itemSerialInfo.serialNumber2) && Intrinsics.areEqual(amount, itemSerialInfo.amount);
    }

    public int hashCode() {
        final int hashCode = Integer.hashCode(logicalRef) * 31;
        final String str = serialNumber1;
        int i = 0;
        final int hashCode2 = (hashCode + (null == str ? 0 : str.hashCode())) * 31;
        final String str2 = serialNumber2;
        final int hashCode3 = (hashCode2 + (null == str2 ? 0 : str2.hashCode())) * 31;
        final String str3 = amount;
        if (null != str3) {
            i = str3.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "ItemSerialInfo(logicalRef=" + logicalRef + ", serialNumber1=" + serialNumber1 + ", serialNumber2=" + serialNumber2 + ", amount=" + amount + ')';
    }

    public ItemSerialInfo(final int i, final String str, final String str2, final String str3) {
        logicalRef = i;
        serialNumber1 = str;
        serialNumber2 = str2;
        amount = str3;
    }
    public  ItemSerialInfo(final int i, final String str, final String str2, final String str3, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i2 & 1) ? 0 : i, 0 != (i2 & 2) ? null : str, 0 != (i2 & 4) ? null : str2, 0 != (i2 & 8) ? null : str3);
    }
}
