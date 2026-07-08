package com.proje.mobilesales.features.dbmodel;


public class BankAccount {
    private String bankCode;
    private int bankRef;
    private double cmDate;
    private String code;
    private String definition;
    private int logicalRef;

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }

    public int getBankRef() {
        return this.bankRef;
    }

    public void setBankRef(int i2) {
        this.bankRef = i2;
    }

    public String getDefinition() {
        return this.definition;
    }

    public void setDefinition(String str) {
        this.definition = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public double getCmDate() {
        return this.cmDate;
    }

    public void setCmDate(double d2) {
        this.cmDate = d2;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public void setBankCode(String str) {
        this.bankCode = str;
    }
}
