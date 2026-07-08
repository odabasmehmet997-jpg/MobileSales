package com.proje.mobilesales.core.netsis.sendmodel.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class ItemSlipLinesAssortment {

    @SerializedName("SetMiktar")
    @Expose
    private int amount;

    @SerializedName("AozDegerKod")
    @Expose
    private String aozVariableCode;

    @SerializedName("AsortiKod")
    @Expose
    private String assortmentCode;

    @SerializedName("Fiyat")
    @Expose
    private double price;

    public String getAssortmentCode() {
        return this.assortmentCode;
    }

    public void setAssortmentCode(String str) {
        this.assortmentCode = str;
    }

    public String getAozVariableCode() {
        return this.aozVariableCode;
    }

    public void setAozVariableCode(String str) {
        this.aozVariableCode = str;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int i2) {
        this.amount = i2;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double d2) {
        this.price = d2;
    }
}
