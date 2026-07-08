package com.proje.mobilesales.features.dbmodel;
 
public class Project {
    private String code;
    private int logicalRef;
    private String proje;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public String getProje() {
        return this.proje;
    }
    public void setProje(String str) {
        this.proje = str;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String str) {
        this.code = str;
    }
}
