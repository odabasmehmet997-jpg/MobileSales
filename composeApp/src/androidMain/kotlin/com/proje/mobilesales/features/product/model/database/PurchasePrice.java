package com.proje.mobilesales.features.product.model.database;


public final class PurchasePrice {
    private double cmDate;
    public String code;
    public int curreny;
    public int itemRef;
    public int logicalRef;
    public double price;

    public double getCmDate() {
        return cmDate;
    }

    public void setCmDate(final double d) {
        cmDate = d;
    }
}
