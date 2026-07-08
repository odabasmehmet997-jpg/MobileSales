package com.proje.mobilesales.features.product.model.database;

public final class Item {
   private int addTaxRef;
   public int candeduct;
    public int cardType;
    private double cmDate;
    public String code;
    public int compKdvUse;
    public int currType;
    public String deductcode;
    private int gtipCode;
    private int imageInc;
    private int isActive;
    private int isVaryant;
    private int locTracking;
    public int logicalRef;
    private String mark;
    private String markCode;
    private int markRef;
    public String name;
    private String name2;
    public int paymentRef;
    private String producerCode;
    private String retailReturnVat;
    private String retailVat;
    private String returnVat;
    public int salesDeductPart1;
    public int salesDeductPart2;
    private float salesDispRateTot;
    public String specode;
    private String specode2;
    private String specode3;
    private String specode4;
    private String specode5;
    public String stgrpCode;
    private String stgrpName;
    public int trackType;
    private String vat;
    public String getVat() {
        return vat;
    }
    public void setVat(final String str) {
        vat = str;
    }
    public String getReturnVat() {
        return returnVat;
    }
    public void setReturnVat(final String str) {
        returnVat = str;
    }
    public int getMarkRef() {
        return markRef;
    }
    public void setMarkRef(final int i) {
        markRef = i;
    }
    public String getName2() {
        return name2;
    }
    public void setName2(final String str) {
        name2 = str;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(final String str) {
        mark = str;
    }
    public int getGtipCode() {
        return gtipCode;
    }
    public void setGtipCode(final int i) {
        gtipCode = i;
    }
    public double getCmDate() {
        return cmDate;
    }
    public void setCmDate(final double d) {
        cmDate = d;
    }
    public String getSpecode2() {
        return specode2;
    }
    public void setSpecode2(final String str) {
        specode2 = str;
    }
    public String getSpecode3() {
        return specode3;
    }
    public void setSpecode3(final String str) {
        specode3 = str;
    }
    public String getSpecode4() {
        return specode4;
    }
    public void setSpecode4(final String str) {
        specode4 = str;
    }
    public String getSpecode5() {
        return specode5;
    }
    public void setSpecode5(final String str) {
        specode5 = str;
    }
    public int isVaryant() {
        return isVaryant;
    }
    public void setVaryant(final int i) {
        isVaryant = i;
    }
    public int isActive() {
        return isActive;
    }
    public void setActive(final int i) {
        isActive = i;
    }
    public int getImageInc() {
        return imageInc;
    }
    public void setImageInc(final int i) {
        imageInc = i;
    }
    public int getAddTaxRef() {
        return addTaxRef;
    }
    public void setAddTaxRef(final int i) {
        addTaxRef = i;
    }
    public String getRetailVat() {
        return retailVat;
    }
    public void setRetailVat(final String str) {
        retailVat = str;
    }
    public String getRetailReturnVat() {
        return retailReturnVat;
    }
    public void setRetailReturnVat(final String str) {
        retailReturnVat = str;
    }
    public String getMarkCode() {
        return markCode;
    }
    public void setMarkCode(final String str) {
        markCode = str;
    }
    public String getStgrpName() {
        return stgrpName;
    }
    public void setStgrpName(final String str) {
        stgrpName = str;
    }
    public String getProducerCode() {
        return producerCode;
    }
    public void setProducerCode(final String str) {
        producerCode = str;
    }
    public float getSalesDispRateTot() {
        return salesDispRateTot;
    }
    public void setSalesDispRateTot(final int i) {
        salesDispRateTot = i;
    }
}
