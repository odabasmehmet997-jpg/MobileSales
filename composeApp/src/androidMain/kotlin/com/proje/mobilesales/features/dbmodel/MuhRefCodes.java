package com.proje.mobilesales.features.dbmodel;


public class MuhRefCodes {

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
}
