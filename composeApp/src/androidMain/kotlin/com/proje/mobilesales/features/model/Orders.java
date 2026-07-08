package com.proje.mobilesales.features.model;

public class Orders {
    private String amount;
    private String date;
    private String ficheNo;
    int logicalRef;
    private String state;
    private String status;
    private String type;
    public String getFicheNo() {
        return ficheNo;
    }
    public void setFicheNo(final String str) {
        ficheNo = str;
    }
    public String getDate() {
        return date;
    }
    public void setDate(final String str) {
        date = str;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(final String str) {
        status = str;
    }
    public String getType() {
        return type;
    }
    public void setType(final String str) {
        type = str;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(final String str) {
        amount = str;
    }
    public String getState() {
        return state;
    }
    public void setState(final String str) {
        state = str;
    }
    public int getLogicalRef() {
        return logicalRef;
    }
    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }
}
