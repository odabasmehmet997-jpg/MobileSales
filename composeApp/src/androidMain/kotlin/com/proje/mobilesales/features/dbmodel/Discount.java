package com.proje.mobilesales.features.dbmodel;
 

public class Discount {

    private String code;
    private String formula;
    private String indirim;
    private int logicalRef;
    private int type;
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
    public String getIndirim() {
        return this.indirim;
    }
    public void setIndirim(String str) {
        this.indirim = str;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int i2) {
        this.type = i2;
    }
    public String getFormula() {
        return this.formula;
    }
    public void setFormula(String str) {
        this.formula = str;
    }
}
