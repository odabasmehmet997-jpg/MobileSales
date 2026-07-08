package com.proje.mobilesales.features.dbmodel;

public class Division {
    private String bolum;
    private String code;
    private int firmRef;
    private int logicalRef;
    private int f1236nr;
    private int parentRef;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public int getNr() {
        return this.f1236nr;
    }
    public void setNr(int i2) {
        this.f1236nr = i2;
    }
    public String getBolum() {
        return this.bolum;
    }
    public void setBolum(String str) {
        this.bolum = str;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String str) {
        this.code = str;
    }
    public int getParentRef() {
        return this.parentRef;
    }
    public void setParentRef(int i2) {
        this.parentRef = i2;
    }
    public int getFirmRef() {
        return this.firmRef;
    }
    public void setFirmRef(int i2) {
        this.firmRef = i2;
    }
}
