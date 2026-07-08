package com.proje.mobilesales.features.model;

public class BarcodeProduct {
    private boolean foundByStoredProcedure;
    private int itemRef;
    private String searchBarcode;
    private String variant;
    public int getItemRef() {
        return itemRef;
    }
    public void setItemRef(final int i2) {
        itemRef = i2;
    }
    public String getVariant() {
        return variant;
    }
    public void setVariant(final String str) {
        variant = str;
    }
    public boolean isFoundByStoredProcedure() {
        return foundByStoredProcedure;
    }
    public void setFoundByStoredProcedure(final boolean z) {
        foundByStoredProcedure = z;
    }
    public String getSearchBarcode() {
        return searchBarcode;
    }
    public void setSearchBarcode(final String str) {
        searchBarcode = str;
    }
}
