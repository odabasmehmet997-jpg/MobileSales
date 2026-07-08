package com.proje.mobilesales.features.dbmodel;

public class Payment {

    private double cmDate;
    private String code;
    private int logicalRef;
    private String mDefinition;
    private String odemePlan;
    private String speCode;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public String getOdemePlan() {
        return this.odemePlan;
    }
    public void setOdemePlan(String str) {
        this.odemePlan = str;
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
    public String getSpeCode() {
        return this.speCode;
    }
    public void setSpeCode(String str) {
        this.speCode = str;
    }
    public double getCmDate() {
        return this.cmDate;
    }
    public void setCmDate(double d2) {
        this.cmDate = d2;
    }
}
