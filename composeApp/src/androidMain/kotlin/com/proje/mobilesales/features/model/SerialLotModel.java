package com.proje.mobilesales.features.model;

import kotlin.jvm.internal.Intrinsics;

public final class SerialLotModel {


    private final String itemCode;
    private final int logicalRef;
    private final double remainingAmount;
    private final String serialNr;
    public static   SerialLotModel copydefault(final SerialLotModel serialLotModel, String str, String str2, int i2, double d2, final int i3, final Object obj) {
        if (0 != (i3 & 1)) {
            str = serialLotModel.serialNr;
        }
        if (0 != (i3 & 2)) {
            str2 = serialLotModel.itemCode;
        }
        final String str3 = str2;
        if (0 != (i3 & 4)) {
            i2 = serialLotModel.logicalRef;
        }
        final int i4 = i2;
        if (0 != (i3 & 8)) {
            d2 = serialLotModel.remainingAmount;
        }
        return serialLotModel.copy(str, str3, i4, d2);
    }

    public String component1() {
        return serialNr;
    }

    public String component2() {
        return itemCode;
    }

    public int component3() {
        return logicalRef;
    }

    public double component4() {
        return remainingAmount;
    }

    public SerialLotModel copy(final String serialNr, final String itemCode, final int i2, final double d2) {
        Intrinsics.checkNotNullParameter(serialNr, "serialNr");
        Intrinsics.checkNotNullParameter(itemCode, "itemCode");
        return new SerialLotModel(serialNr, itemCode, i2, d2);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SerialLotModel serialLotModel)) {
            return false;
        }
        return Intrinsics.areEqual(serialNr, serialLotModel.serialNr) && Intrinsics.areEqual(itemCode, serialLotModel.itemCode) && logicalRef == serialLotModel.logicalRef && 0 == Double.compare(this.remainingAmount, serialLotModel.remainingAmount);
    }

    public int hashCode() {
        return (((((serialNr.hashCode() * 31) + itemCode.hashCode()) * 31) + Integer.hashCode(logicalRef)) * 31) + Double.hashCode(remainingAmount);
    }

    public String toString() {
        return "SerialLotModel(serialNr=" + serialNr + ", itemCode=" + itemCode + ", logicalRef=" + logicalRef + ", remainingAmount=" + remainingAmount + ')';
    }

    public SerialLotModel(final String serialNr, final String itemCode, final int i2, final double d2) {
        Intrinsics.checkNotNullParameter(serialNr, "serialNr");
        Intrinsics.checkNotNullParameter(itemCode, "itemCode");
        this.serialNr = serialNr;
        this.itemCode = itemCode;
        logicalRef = i2;
        remainingAmount = d2;
    }

    public String getSerialNr() {
        return serialNr;
    }

    public String getItemCode() {
        return itemCode;
    }

    public int getLogicalRef() {
        return logicalRef;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public SerialLotModel() {
        this("", "", 0, 0.0d);
    }
}
