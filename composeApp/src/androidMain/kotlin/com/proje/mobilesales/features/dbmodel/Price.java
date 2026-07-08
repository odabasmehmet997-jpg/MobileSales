package com.proje.mobilesales.features.dbmodel;

import com.proje.mobilesales.core.interfaces.CharSequenceGet;


public class Price implements CharSequenceGet {
    private String cPrice;
    private double convFact1;
    private double convFact2;
    private String curCode;
    private String curType;
    private int invcat;
    private String price;
    private int priceRef;
    private int unitConvert;
    private String variantCode;
    public int getPriceRef() {
        return this.priceRef;
    }
    public void setPriceRef(int i2) {
        this.priceRef = i2;
    }
    public String getPrice() {
        return this.price;
    }
    public void setPrice(String str) {
        this.price = str;
    }
    public int getInvcat() {
        return this.invcat;
    }
    public void setInvcat(int i2) {
        this.invcat = i2;
    }
    public String getcPrice() {
        return this.cPrice;
    }
    public void setcPrice(String str) {
        this.cPrice = str;
    }
    public String getCurType() {
        return this.curType;
    }
    public void setCurType(String str) {
        this.curType = str;
    }
    public String getCurCode() {
        return this.curCode;
    }
    public void setCurCode(String str) {
        this.curCode = str;
    }
    public CharSequence getCharSequence() {
        return getcPrice();
    }
    public int getUnitConvert() {
        return this.unitConvert;
    }
    public void setUnitConvert(int i2) {
        this.unitConvert = i2;
    }
    public double getConvFact1() {
        return this.convFact1;
    }
    public void setConvFact1(double d2) {
        this.convFact1 = d2;
    }
    public double getConvFact2() {
        return this.convFact2;
    }
    public void setConvFact2(double d2) {
        this.convFact2 = d2;
    }
    public String getVariantCode() {
        return this.variantCode;
    }
    public void setVariantCode(String str) {
        this.variantCode = str;
    }
}
