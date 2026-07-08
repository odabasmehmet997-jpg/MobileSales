package com.proje.mobilesales.features.dbmodel;

public class Branch {
    private String code;
    private int eArchiveType;
    private int firmRef;
    private String isYeri;
    private int logicalRef;
    private int f1232nr;
    private int parentRef;
    private int useEarchive;
    private int useEdespatch;
    private int useEinvoice;

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }

    public int getNr() {
        return this.f1232nr;
    }

    public void setNr(int i2) {
        this.f1232nr = i2;
    }

    public String getIsYeri() {
        return this.isYeri;
    }

    public void setIsYeri(String str) {
        this.isYeri = str;
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

    public int getUseEarchive() {
        return this.useEarchive;
    }

    public void setUseEarchive(int i2) {
        this.useEarchive = i2;
    }

    public int getUseEdespatch() {
        return this.useEdespatch;
    }

    public void setUseEdespatch(int i2) {
        this.useEdespatch = i2;
    }

    public int getUseEinvoice() {
        return this.useEinvoice;
    }

    public void setUseEinvoice(int i2) {
        this.useEinvoice = i2;
    }

    public int geteArchiveType() {
        return this.eArchiveType;
    }

    public void seteArchiveType(int i2) {
        this.eArchiveType = i2;
    }
}
