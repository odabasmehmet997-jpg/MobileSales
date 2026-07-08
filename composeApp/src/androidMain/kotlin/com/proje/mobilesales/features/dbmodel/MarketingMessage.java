package com.proje.mobilesales.features.dbmodel;

public class MarketingMessage {
    private String begDate;
    private String endDate;
    private int logicalRef;
    private String mDefinition;
    private String note;
    private int type;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
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
    public String getDefinition() {
        return this.mDefinition;
    }
    public void setDefinition(String str) {
        this.mDefinition = str;
    }
    public String getNote() {
        return this.note;
    }
    public void setNote(String str) {
        this.note = str;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int i2) {
        this.type = i2;
    }
}
