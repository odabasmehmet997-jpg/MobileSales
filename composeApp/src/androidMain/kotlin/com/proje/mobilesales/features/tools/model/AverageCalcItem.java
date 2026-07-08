package com.proje.mobilesales.features.tools.model;

import kotlin.jvm.internal.Intrinsics;

public final class AverageCalcItem {
    private double amount;
    private String date;
    public AverageCalcItem(final String date, final double d2) {
        Intrinsics.checkNotNullParameter(date, "date");
        this.date = date;
        amount = d2;
    }
    public double getAmount() {
        return amount;
    }
    public String getDate() {
        return date;
    }
    public void setAmount(final double d2) {
        amount = d2;
    }
    public void setDate(final String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        date = str;
    }
}
