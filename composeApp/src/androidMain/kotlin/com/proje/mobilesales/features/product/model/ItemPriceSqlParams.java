package com.proje.mobilesales.features.product.model;

import com.proje.mobilesales.core.utils.ContextUtils;
import kotlin.jvm.internal.Intrinsics;

public final class ItemPriceSqlParams {
    public String cmDateSql;
    public String currencyPriceDescription;
    private boolean isDeleteAndTransfer;
    private boolean isGetPriceFromList;
    private boolean isUseSalesPriceAsCurrencyPrice;
    public String localCurrency;
    public String priceDescription;
    private int priority;

    public boolean isGetPriceFromList() {
        return isGetPriceFromList;
    }

    public void setGetPriceFromList(final boolean z) {
        isGetPriceFromList = z;
    }

    public boolean isUseSalesPriceAsCurrencyPrice() {
        return isUseSalesPriceAsCurrencyPrice;
    }

    public void setUseSalesPriceAsCurrencyPrice(final boolean z) {
        isUseSalesPriceAsCurrencyPrice = z;
    }

    public boolean isDeleteAndTransfer() {
        return isDeleteAndTransfer;
    }

    public void setDeleteAndTransfer(final boolean z) {
        isDeleteAndTransfer = z;
    }

    public int getPriority() {
        return priority;
    }

    public String getNextPriority() {
        final int i = priority + 1;
        priority = i;
        return String.valueOf(i);
    }

    public String getPriorityWithGap() {
        final int i = priority + 2;
        priority = i;
        return String.valueOf(i);
    }

    public String getLastPriorityIfNotUsingSalesPriceAsCurrencyPrice() {
        final int i = priority;
        final String formatStringEnglish = ContextUtils.formatStringEnglish("(CASE WHEN SAT_DOV_TIP=0 THEN %d ELSE %d END)", Integer.valueOf(i + 1), Integer.valueOf(i - 4));
        Intrinsics.checkNotNullExpressionValue(formatStringEnglish, "formatStringEnglish(...)");
        return formatStringEnglish;
    }
}
