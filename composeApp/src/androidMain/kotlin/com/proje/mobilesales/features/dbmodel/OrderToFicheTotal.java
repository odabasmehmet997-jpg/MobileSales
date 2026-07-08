package com.proje.mobilesales.features.dbmodel;

public class OrderToFicheTotal {
    private double addDiscounts;
    private double grossTotal;
    private int logicalRef;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public double getGrossTotal() {
        return this.grossTotal;
    }
    public void setGrossTotal(double d2) {
        this.grossTotal = d2;
    }
    public double getAddDiscounts() {
        return this.addDiscounts;
    }
    public void setAddDiscounts(double d2) {
        this.addDiscounts = d2;
    }
}
