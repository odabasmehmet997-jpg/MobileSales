package com.proje.mobilesales.features.reports.model;

public final class AvgCalcItem {
    public String date;
    public double total;

    public AvgCalcItem(final String str, final double d2) {
        date = str;
        total = d2;
    }
    public AvgCalcItem() {
    }
}
