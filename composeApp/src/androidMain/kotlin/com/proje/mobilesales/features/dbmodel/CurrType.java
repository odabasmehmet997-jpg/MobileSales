package com.proje.mobilesales.features.dbmodel;


public class CurrType {
    private String currCode;
    private int currType;
    private String subName;
    public int getCurrType() {
        return this.currType;
    }
    public void setCurrType(int i2) {
        this.currType = i2;
    }
    public String getCurrCode() {
        return this.currCode;
    }
    public void setCurrCode(String str) {
        this.currCode = str;
    }
    public String getSubName() {
        return this.subName;
    }
    public void setSubName(String str) {
        this.subName = str;
    }
}
