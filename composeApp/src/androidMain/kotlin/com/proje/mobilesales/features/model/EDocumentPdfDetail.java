package com.proje.mobilesales.features.model;

import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.SafeType;

public class EDocumentPdfDetail {
    private double amount;
    private double distdisc;
    private int divUnit;
    private String guid;
    private String itemCode;
    private String itemName;
    private int itemRef;
    private String lineExp;
    private int lineType;
    private int logicalRef;
    private double price;
    private int stFicheLineNo;
    private int stFicheRef;
    private double total;
    private String unitCode;
    private int uomRef;
    private int usRef;
    private double vat;
    private double vatAmount;
    private String vatExceptReason;
    private double vatMatrah;
    public int getStFicheRef() {
        return stFicheRef;
    }
    public void setStFicheRef(final int i2) {
        stFicheRef = i2;
    }
    public int getLogicalRef() {
        return logicalRef;
    }
    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }
    public int getStFicheLineNo() {
        return stFicheLineNo;
    }
    public void setStFicheLineNo(final int i2) {
        stFicheLineNo = i2;
    }
    public int getLineType() {
        return lineType;
    }
    public void setLineType(final int i2) {
        lineType = i2;
    }
    public String getItemCode() {
        return itemCode;
    }
    public void setItemCode(final String str) {
        itemCode = str;
    }
    public String getItemName() {
        return itemName;
    }
    public void setItemName(final String str) {
        itemName = str;
    }
    public int getUomRef() {
        return uomRef;
    }
    public void setUomRef(final int i2) {
        uomRef = i2;
    }
    public int getUsRef() {
        return usRef;
    }
    public void setUsRef(final int i2) {
        usRef = i2;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(final double d2) {
        amount = d2;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(final double d2) {
        price = d2;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(final double d2) {
        total = d2;
    }
    public double getDistDisc() {
        return distdisc;
    }
    public void setDistDisc(final double d2) {
        distdisc = d2;
    }
    public double getVat() {
        return vat;
    }
    public void setVat(final double d2) {
        vat = d2;
    }
    public double getVatAmount() {
        return vatAmount;
    }
    public void setVatAmount(final double d2) {
        vatAmount = d2;
    }
    public double getVatMatrah() {
        return vatMatrah;
    }
    public void setVatMatrah(final double d2) {
        vatMatrah = d2;
    }
    public String getLineExp() {
        return lineExp;
    }
    public void setLineExp(final String str) {
        lineExp = str;
    }
    public int getItemRef() {
        return itemRef;
    }
    public void setItemRef(final int i2) {
        itemRef = i2;
    }
    public String getGuid() {
        return guid;
    }
    public void setGuid(final String str) {
        guid = str;
    }
    public String getUnitCode() {
        return unitCode;
    }
    public void setUnitCode(final String str) {
        unitCode = str;
    }
    public int getDivUnit() {
        return divUnit;
    }
    public void setDivUnit(final int i2) {
        divUnit = i2;
    }
    public String getVatExceptReason() {
        return vatExceptReason;
    }
    public void setVatExceptReason(final String str) {
        vatExceptReason = str;
    }
}
