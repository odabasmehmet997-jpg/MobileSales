package com.proje.mobilesales.features.dbmodel;

public class ShipType {
    private String code;
    private int logicalRef;
    private String mDefinition;
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
    public String getDefinition() {
        return this.mDefinition;
    }
    public void setDefinition(String str) {
        this.mDefinition = str;
    }
}
