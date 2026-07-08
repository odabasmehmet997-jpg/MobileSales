package com.proje.mobilesales.features.product.model;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class ProductProcess {
    private double amount;
    private String definition;
    private double discount;
    private boolean isDiscountRatio;
    private int logicalRef;

    public ProductProcess() {
        this(0, null, 0.0d, 0.0d, false, 31, null);
    }

    public static  ProductProcess copydefault(final ProductProcess productProcess, int i, String str, double d, double d2, boolean z, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            i = productProcess.logicalRef;
        }
        if (0 != (i2 & 2)) {
            str = productProcess.definition;
        }
        if (0 != (i2 & 4)) {
            d = productProcess.amount;
        }
        if (0 != (i2 & 8)) {
            d2 = productProcess.discount;
        }
        if (0 != (i2 & 16)) {
            z = productProcess.isDiscountRatio;
        }
        return productProcess.copy(i, str, d, d2, z);
    }

    public   int component1() {
        return logicalRef;
    }

    public   String component2() {
        return definition;
    }

    public   double component3() {
        return amount;
    }

    public   double component4() {
        return discount;
    }

    public   boolean component5() {
        return isDiscountRatio;
    }

    public   ProductProcess copy(final int i, final String str, final double d, final double d2, final boolean z) {
        return new ProductProcess(i, str, d, d2, z);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProductProcess productProcess)) {
            return false;
        }
        return logicalRef == productProcess.logicalRef && Intrinsics.areEqual(definition, productProcess.definition) && 0 == Double.compare(this.amount, productProcess.amount) && 0 == Double.compare(this.discount, productProcess.discount) && isDiscountRatio == productProcess.isDiscountRatio;
    }

    public int hashCode() {
        final int hashCode = Integer.hashCode(logicalRef) * 31;
        final String str = definition;
        return ((((((hashCode + (null == str ? 0 : str.hashCode())) * 31) + Double.hashCode(amount)) * 31) + Double.hashCode(discount)) * 31) + Boolean.hashCode(isDiscountRatio);
    }

    public String toString() {
        return "ProductProcess(logicalRef=" + logicalRef + ", definition=" + definition + ", amount=" + amount + ", discount=" + discount + ", isDiscountRatio=" + isDiscountRatio + ')';
    }

    public ProductProcess(final int i, final String str, final double d, final double d2, final boolean z) {
        logicalRef = i;
        definition = str;
        amount = d;
        discount = d2;
        isDiscountRatio = z;
    }

    public   ProductProcess(final int i, final String str, final double d, final double d2, final boolean z, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i2 & 1) ? 0 : i, 0 != (i2 & 2) ? null : str, 0 != (i2 & 4) ? 0.0d : d, 0 == (i2 & 8) ? d2 : 0.0d, 0 == (i2 & 16) && z);
    }

    public   int getLogicalRef() {
        return logicalRef;
    }

    public   void setLogicalRef(final int i) {
        logicalRef = i;
    }

    public   String getDefinition() {
        return definition;
    }

    public   void setDefinition(final String str) {
        definition = str;
    }

    public   double getAmount() {
        return amount;
    }

    public   void setAmount(final double d) {
        amount = d;
    }

    public   double getDiscount() {
        return discount;
    }

    public   void setDiscount(final double d) {
        discount = d;
    }

    public   boolean isDiscountRatio() {
        return isDiscountRatio;
    }

    public   void setDiscountRatio(final boolean z) {
        isDiscountRatio = z;
    }
}
