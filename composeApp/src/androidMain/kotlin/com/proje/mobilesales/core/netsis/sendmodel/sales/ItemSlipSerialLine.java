package com.proje.mobilesales.core.netsis.sendmodel.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemSlipSerialLine {

    @SerializedName("Miktar")
    @Expose
    private double amount;

    @SerializedName("Aciklama1")
    @Expose
    private String explanation1;

    @SerializedName("Aciklama2")
    @Expose
    private String explanation2;

    @SerializedName("Seri1")
    @Expose
    private String serial1;

    @SerializedName("Seri2")
    @Expose
    private String serial2;

    @SerializedName("HareketTip")
    @Expose
    private int transactionType;

    public String getSerial1() {
        return this.serial1;
    }

    public void setSerial1(String str) {
        this.serial1 = str;
    }

    public String getSerial2() {
        return this.serial2;
    }

    public void setSerial2(String str) {
        this.serial2 = str;
    }

    public String getExplanation1() {
        return this.explanation1;
    }

    public void setExplanation1(String str) {
        this.explanation1 = str;
    }

    public String getExplanation2() {
        return this.explanation2;
    }

    public void setExplanation2(String str) {
        this.explanation2 = str;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double d2) {
        this.amount = d2;
    }

    public int getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(int i2) {
        this.transactionType = i2;
    }
}
