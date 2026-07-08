package com.proje.mobilesales.features.dbmodel;

public class Factory {

    private String code;
    private int divisNr;
    private String fabrika;
    private int firmRef;
    private int logicalRef;
    private int f1239nr;
    private int parentRef;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public int getNr() {
        return this.f1239nr;
    }
    public void setNr(int i2) {
        this.f1239nr = i2;
    }
    public String getFabrika() {
        return this.fabrika;
    }
    public void setFabrika(String str) {
        this.fabrika = str;
    }
    public int getDivisNr() {
        return this.divisNr;
    }
    public void setDivisNr(int i2) {
        this.divisNr = i2;
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
