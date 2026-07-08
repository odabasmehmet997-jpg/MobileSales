package com.proje.mobilesales.core.netsis.sendmodel.print;

public class PrintSlipParams {
    private String mClCode;
    private String mKey;
    private String mShipmentClCode;
    private String mSlipNo;
    private int mSlipType;

    public String getClCode() {
        return this.mClCode;
    }

    public void setClCode(String str) {
        this.mClCode = str;
    }

    public String getShipmentClCode() {
        return this.mShipmentClCode;
    }

    public void setShipmentClCode(String str) {
        this.mShipmentClCode = str;
    }

    public String getSlipNo() {
        return this.mSlipNo;
    }

    public void setSlipNo(String str) {
        this.mSlipNo = str;
    }

    public String getKey() {
        return this.mKey;
    }

    public void setKey(String str) {
        this.mKey = str;
    }

    public int getSlipType() {
        return this.mSlipType;
    }

    public void setSlipType(int i2) {
        this.mSlipType = i2;
    }
}
