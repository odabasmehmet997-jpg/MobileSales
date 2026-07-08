package com.proje.mobilesales.features.dailyinformation.model;

public abstract class DailyInfoAbstract {
    public String customerCode;
    public String customerName;
    private String dDate;
    public int ficheRef;
    private boolean isTransfer;
    public String itemType;
    public int logicalRef;
    public final boolean isTransfer() {
        return this.isTransfer;
    }
    public final void setTransfer(boolean z) {
        this.isTransfer = z;
    }
    public final String getdDate() {
        return this.dDate;
    }
    public final void setdDate(String str) {
        this.dDate = str;
    }
}
