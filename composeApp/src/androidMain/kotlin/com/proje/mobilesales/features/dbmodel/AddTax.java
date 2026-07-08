package com.proje.mobilesales.features.dbmodel;

import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;

public class AddTax {

    private int addTaxRef;
    private double amount;
    private String begDate;
    private double cmDate;
    private double discamount;
    private int effectKDV;
    private String fcspecode;
    private String globalCode;
    private int logicalRef;
    private String pType;
    private double rate;
    private String taxCode;
    private String taxDef;
    private int taxType;
    private String trspecode;
    private String trspecode2;

    public double getCmDate() {
        return this.cmDate;
    }

    public void setCmDate(double d2) {
        this.cmDate = d2;
    }

    public String getBegDate() {
        return this.begDate;
    }

    public void setBegDate(String str) {
        this.begDate = str;
    }

    public String getTaxCode() {
        return this.taxCode;
    }

    public void setTaxCode(String str) {
        this.taxCode = str;
    }

    public String getTaxDef() {
        return this.taxDef;
    }

    public void setTaxDef(String str) {
        this.taxDef = str;
    }

    public int getTaxType() {
        return this.taxType;
    }

    public void setTaxType(int i2) {
        this.taxType = i2;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double d2) {
        this.amount = d2;
    }

    public double getRate() {
        return this.rate;
    }

    public void setRate(double d2) {
        this.rate = d2;
    }

    public int getEffectKDV() {
        return this.effectKDV;
    }

    public void setEffectKDV(int i2) {
        this.effectKDV = i2;
    }

    public String getGlobalCode() {
        return this.globalCode;
    }

    public void setGlobalCode(String str) {
        this.globalCode = str;
    }

    public String getpType() {
        return this.pType;
    }

    public void setpType(String str) {
        this.pType = str;
    }

    public double getDiscamount() {
        return this.discamount;
    }

    public void setDiscamount(double d2) {
        this.discamount = d2;
    }

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public int getAddTaxRef() {
        return this.addTaxRef;
    }

    public void setAddTaxRef(int i2) {
        this.addTaxRef = i2;
    }

    public String getFcspecode() {
        return this.fcspecode;
    }

    public void setFcspecode(String str) {
        this.fcspecode = str;
    }

    public String getTrspecode() {
        return this.trspecode;
    }

    public void setTrspecode(String str) {
        this.trspecode = str;
    }

    public String getTrspecode2() {
        return this.trspecode2;
    }

    public void setTrspecode2(String str) {
        this.trspecode2 = str;
    }
}
