package com.proje.mobilesales.features.dbmodel;


public class Curr {
    private String currCode;
    private int currType;
    private int mDate;
    private double rate;
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
    public double getRate() {
        return this.rate;
    }
    public void setRate(double d2) {
        this.rate = d2;
    }
    public int getDate() {
        return this.mDate;
    }
    public void setDate(int i2) {
        this.mDate = i2;
    }
}
