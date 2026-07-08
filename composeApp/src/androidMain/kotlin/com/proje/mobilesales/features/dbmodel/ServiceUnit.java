package com.proje.mobilesales.features.dbmodel;

public class ServiceUnit {
    private double cmdate;
    private String code;
    private double convfact1;
    private double convfact2;
    private int itemRef;
    private int linenr;
    private int logicalRef;
    private int priority;
    private int unitSetRef;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public int getItemRef() {
        return this.itemRef;
    }
    public void setItemRef(int i2) {
        this.itemRef = i2;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String str) {
        this.code = str;
    }
    public double getConvfact1() {
        return this.convfact1;
    }
    public void setConvfact1(double d2) {
        this.convfact1 = d2;
    }
    public double getConvfact2() {
        return this.convfact2;
    }
    public void setConvfact2(double d2) {
        this.convfact2 = d2;
    }
    public int getUnitSetRef() {
        return this.unitSetRef;
    }
    public void setUnitSetRef(int i2) {
        this.unitSetRef = i2;
    }
    public int getLinenr() {
        return this.linenr;
    }
    public void setLinenr(int i2) {
        this.linenr = i2;
    }
    public double getCmdate() {
        return this.cmdate;
    }
    public void setCmdate(double d2) {
        this.cmdate = d2;
    }
    public int getPriority() {
        return this.priority;
    }
    public void setPriority(int i2) {
        this.priority = i2;
    }
}
