package com.proje.mobilesales.features.model;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.Tables;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;


/* compiled from: EDocumentPdfDetailNetsis.kt */
@Tables

public final class EDocumentPdfDetailNetsis {

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "AMOUNT")
    private double amount;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "NAME")
    private String itemName;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "PRICE")
    private double price;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "UNITCODE")
    private String unitCode;

    public EDocumentPdfDetailNetsis() {
        this(null, 0.0d, 0.0d, null, 15, null);
    }

    public static EDocumentPdfDetailNetsis copydefault(final EDocumentPdfDetailNetsis eDocumentPdfDetailNetsis, String str, double d2, double d3, String str2, final int i2, final Object obj) {
        if (0 != (i2 & 1)) {
            str = eDocumentPdfDetailNetsis.itemName;
        }
        if (0 != (i2 & 2)) {
            d2 = eDocumentPdfDetailNetsis.amount;
        }
        final double d4 = d2;
        if (0 != (i2 & 4)) {
            d3 = eDocumentPdfDetailNetsis.price;
        }
        final double d5 = d3;
        if (0 != (i2 & 8)) {
            str2 = eDocumentPdfDetailNetsis.unitCode;
        }
        return eDocumentPdfDetailNetsis.copy(str, d4, d5, str2);
    }

    public String component1() {
        return itemName;
    }

    public double component2() {
        return amount;
    }

    public double component3() {
        return price;
    }

    public String component4() {
        return unitCode;
    }

    public EDocumentPdfDetailNetsis copy(final String str, final double d2, final double d3, final String str2) {
        return new EDocumentPdfDetailNetsis(str, d2, d3, str2);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EDocumentPdfDetailNetsis eDocumentPdfDetailNetsis)) {
            return false;
        }
        return Intrinsics.areEqual(itemName, eDocumentPdfDetailNetsis.itemName) && 0 == Double.compare(this.amount, eDocumentPdfDetailNetsis.amount) && 0 == Double.compare(this.price, eDocumentPdfDetailNetsis.price) && Intrinsics.areEqual(unitCode, eDocumentPdfDetailNetsis.unitCode);
    }

    public int hashCode() {
        final String str = itemName;
        final int hashCode = (((((null == str ? 0 : str.hashCode()) * 31) + Double.hashCode(amount)) * 31) + Double.hashCode(price)) * 31;
        final String str2 = unitCode;
        return hashCode + (null != str2 ? str2.hashCode() : 0);
    }

    public String toString() {
        return "EDocumentPdfDetailNetsis(itemName=" + itemName + ", amount=" + amount + ", price=" + price + ", unitCode=" + unitCode + ')';
    }

    public EDocumentPdfDetailNetsis(final String str, final double d2, final double d3, final String str2) {
        itemName = str;
        amount = d2;
        price = d3;
        unitCode = str2;
    }

    public EDocumentPdfDetailNetsis(final String str, final double d2, final double d3, final String str2, final int i2, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i2 & 1) ? null : str, 0 != (i2 & 2) ? 0.0d : d2, 0 == (i2 & 4) ? d3 : 0.0d, 0 != (i2 & 8) ? null : str2);
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(final String str) {
        itemName = str;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(final double d2) {
        amount = d2;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double d2) {
        price = d2;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(final String str) {
        unitCode = str;
    }
}
