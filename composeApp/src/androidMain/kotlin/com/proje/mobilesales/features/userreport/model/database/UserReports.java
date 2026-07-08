package com.proje.mobilesales.features.userreport.model.database;

public final class UserReports {

    public int f1283id;
    private int rType;
    public int repIndex;
    public String reportContent;
    public String reportName;
    public int subType;
    public int getrType() {
        return this.rType;
    }
    public void setrType(int rType) {
        this.rType = rType;
    }
}
