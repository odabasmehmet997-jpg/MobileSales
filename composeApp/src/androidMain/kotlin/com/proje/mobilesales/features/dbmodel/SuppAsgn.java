package com.proje.mobilesales.features.dbmodel;


public class SuppAsgn {
    private int clientRef;
    private String iCustSupCode;
    private String iCustSupName;
    private int itemRef;
    public int getItemRef() {
        return this.itemRef;
    }
    public void setItemRef(int i2) {
        this.itemRef = i2;
    }
    public int getClientRef() {
        return this.clientRef;
    }
    public void setClientRef(int i2) {
        this.clientRef = i2;
    }
    public String getiCustSupCode() {
        return this.iCustSupCode;
    }
    public void setiCustSupCode(String str) {
        this.iCustSupCode = str;
    }
    public String getiCustSupName() {
        return this.iCustSupName;
    }
    public void setiCustSupName(String str) {
        this.iCustSupName = str;
    }
}
