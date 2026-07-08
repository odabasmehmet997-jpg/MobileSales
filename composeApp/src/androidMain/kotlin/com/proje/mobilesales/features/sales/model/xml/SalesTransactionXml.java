package com.proje.mobilesales.features.sales.model.xml;

import kotlin.jvm.internal.Intrinsics;

public record SalesTransactionXml(String guid, double quantity, int type) {

    public String component1() {
        return this.guid;
    }

    public double component2() {
        return this.quantity;
    }

    public int component3() {
        return this.type;
    }

    public SalesTransactionXml copy(String str, double d2, int i2) {
        return new SalesTransactionXml(str, d2, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SalesTransactionXml salesTransactionXml)) {
            return false;
        }
        return Intrinsics.areEqual(this.guid, salesTransactionXml.guid) && Double.compare(this.quantity, salesTransactionXml.quantity) == 0 && this.type == salesTransactionXml.type;
    }

    public int hashCode() {
        String str = this.guid;
        return ((((str == null ? 0 : str.hashCode()) * 31) + Double.hashCode(this.quantity)) * 31) + Integer.hashCode(this.type);
    }

    public String toString() {
        return "SalesTransactionXml(guid=" + this.guid + ", quantity=" + this.quantity + ", type=" + this.type + ')';
    }

    public SalesTransactionXml(String str, double d2, int i2, int i3) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? 0.0d : d2, (i3 & 4) != 0 ? 0 : i2);
    }
}
