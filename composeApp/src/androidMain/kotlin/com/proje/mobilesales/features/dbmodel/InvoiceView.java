package com.proje.mobilesales.features.dbmodel;


public class InvoiceView {
    private String amount;
    private String code;
    private int f1241id;
    private String name;
    private String netTotal;
    private String price;
    private String unit;
    private String vat;
    public int getId() {
        return this.f1241id;
    }
    public void setId(int i2) {
        this.f1241id = i2;
    }
    public String getCode() {
        return this.code;
    }
    public void setCode(String str) {
        this.code = str;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String str) {
        this.name = str;
    }
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String str) {
        this.unit = str;
    }
    public String getAmount() {
        return this.amount;
    }
    public void setAmount(String str) {
        this.amount = str;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String str) {
        this.price = str;
    }
    public String getVat() {
        return this.vat;
    }
    public void setVat(String str) {
        this.vat = str;
    }
    public String getNetTotal() {
        return this.netTotal;
    }
    public void setNetTotal(String str) {
        this.netTotal = str;
    }
}
