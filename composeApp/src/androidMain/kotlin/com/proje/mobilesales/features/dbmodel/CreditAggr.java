package com.proje.mobilesales.features.dbmodel;


public class CreditAggr {
    private String aggreement;
    private String begDate;
    private String code;
    private String endDate;
    private String installmentInterval;
    private int logicalRef;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public String getAggreement() {
        return this.aggreement;
    }
    public void setAggreement(String str) {
        this.aggreement = str;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String str) {
        this.code = str;
    }
    public String getBegDate() {
        return this.begDate;
    }
    public void setBegDate(String str) {
        this.begDate = str;
    }
    public String getEndDate() {
        return this.endDate;
    }
    public void setEndDate(String str) {
        this.endDate = str;
    }
    public String getInstallmentInterval() {
        return this.installmentInterval;
    }
    public void setInstallmentInterval(String str) {
        this.installmentInterval = str;
    }
}
