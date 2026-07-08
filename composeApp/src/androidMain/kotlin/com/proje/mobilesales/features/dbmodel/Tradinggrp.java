package com.proje.mobilesales.features.dbmodel;
 
public class Tradinggrp {
    private int active;
    private String code;
    private int gattrib;
    private int logicalRef;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String str) {
        this.code = str;
    }
    public int getGattrib() {
        return this.gattrib;
    }
    public void setGattrib(int i2) {
        this.gattrib = i2;
    }
    public int getActive() {
        return this.active;
    }
    public void setActive(int i2) {
        this.active = i2;
    }
}
