package com.proje.mobilesales.features.product.model.database;


public final class ItemStock {
    private String ambar;
    public String itemCode;
    public int itemRef;
    public double onHand;
    public double realStock;
    public int wareHouseNr;
    public String getAmbar() {
        return ambar;
    }
    public void setAmbar(final String str) {
        ambar = str;
    }
    public ItemStock() {
    }
    public ItemStock(final int i, final int i2) {
        itemRef = i;
        wareHouseNr = i2;
    }
    public ItemStock(final String str, final int i) {
        itemCode = str;
        wareHouseNr = i;
    }
}
