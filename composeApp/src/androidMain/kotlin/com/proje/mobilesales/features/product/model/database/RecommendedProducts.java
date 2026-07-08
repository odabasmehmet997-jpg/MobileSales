package com.proje.mobilesales.features.product.model.database;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class RecommendedProducts {
    private String stockRef;

    public RecommendedProducts() {
        this(null, 1, null);
    }

    public static  RecommendedProducts copy(final RecommendedProducts recommendedProducts, String str, final int i, final Object obj) {
        if (0 != (i & 1)) {
            str = recommendedProducts.stockRef;
        }
        return recommendedProducts.copy(str);
    }

    public String component1() {
        return stockRef;
    }

    public RecommendedProducts copy(final String str) {
        Intrinsics.checkNotNullParameter(str, "stockRef");
        return new RecommendedProducts(str);
    }

    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof RecommendedProducts) && Intrinsics.areEqual(stockRef, ((RecommendedProducts) obj).stockRef);
    }

    public int hashCode() {
        return stockRef.hashCode();
    }

    public String toString() {
        return "RecommendedProducts(stockRef=" + stockRef + ')';
    }

    public RecommendedProducts(final String str) {
        Intrinsics.checkNotNullParameter(str, "stockRef");
        stockRef = str;
    }

    public  RecommendedProducts(final String str, final int i, final DefaultConstructorMarker defaultConstructorMarker) {
        this(0 != (i & 1) ? "" : str);
    }

    public String getStockRef() {
        return stockRef;
    }

    public void setStockRef(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        stockRef = str;
    }
}
