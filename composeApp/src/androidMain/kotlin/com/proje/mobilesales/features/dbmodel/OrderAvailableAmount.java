package com.proje.mobilesales.features.dbmodel;

public class OrderAvailableAmount {
    private double availableAmount;
    private String code;
    private int ditributionLineRef;
    private int ditributionRef;
    private double orderAmount;
    private int orderLineLogicalRef;
    private int orderLogicalRef;
    public int getOrderLogicalRef() {
        return this.orderLogicalRef;
    }
    public void setOrderLogicalRef(int i2) {
        this.orderLogicalRef = i2;
    }
    public int getOrderLineLogicalRef() {
        return this.orderLineLogicalRef;
    }
    public void setOrderLineLogicalRef(int i2) {
        this.orderLineLogicalRef = i2;
    }
    public double getAvailableAmount() {
        return this.availableAmount;
    }
    public void setAvailableAmount(double d2) {
        this.availableAmount = d2;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String str) {
        this.code = str;
    }
    public double getOrderAmount() {
        return this.orderAmount;
    }
    public void setOrderAmount(double d2) {
        this.orderAmount = d2;
    }
    public int getDitributionRef() {
        return this.ditributionRef;
    }
    public void setDitributionRef(int i2) {
        this.ditributionRef = i2;
    }
    public int getDitributionLineRef() {
        return this.ditributionLineRef;
    }
    public void setDitributionLineRef(int i2) {
        this.ditributionLineRef = i2;
    }
}
