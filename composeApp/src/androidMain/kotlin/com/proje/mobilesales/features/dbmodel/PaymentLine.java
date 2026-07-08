package com.proje.mobilesales.features.dbmodel;

public class PaymentLine {
    
    private int bankAccRef;
    private int bankRef;
    private double cmDate;
    private int day;
    private String formul;
    private int logicalRef;
    private int month;
    private int payPlanRef;
    private int paymentType;
    private int year;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public int getPayPlanRef() {
        return this.payPlanRef;
    }
    public void setPayPlanRef(int i2) {
        this.payPlanRef = i2;
    }
    public int getPaymentType() {
        return this.paymentType;
    }
    public void setPaymentType(int i2) {
        this.paymentType = i2;
    }
    public String getFormul() {
        return this.formul;
    }
    public void setFormul(String str) {
        this.formul = str;
    }
    public double getCmDate() {
        return this.cmDate;
    }
    public void setCmDate(double d2) {
        this.cmDate = d2;
    }
    public int getBankRef() {
        return this.bankRef;
    }
    public void setBankRef(int i2) {
        this.bankRef = i2;
    }
    public int getBankAccRef() {
        return this.bankAccRef;
    }
    public void setBankAccRef(int i2) {
        this.bankAccRef = i2;
    }
    public int getDay() {
        return this.day;
    }
    public void setDay(int i2) {
        this.day = i2;
    }
    public int getMonth() {
        return this.month;
    }
    public void setMonth(int i2) {
        this.month = i2;
    }
    public int getYear() {
        return this.year;
    }
    public void setYear(int i2) {
        this.year = i2;
    }
}
