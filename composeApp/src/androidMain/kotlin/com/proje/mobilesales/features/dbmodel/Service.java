package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;

public class Service {

    private int addTaxRef;
    private int cardType;
    private double cmDate;
    private String code;
    private int f1244id;
    private int logicalRef;
    private String name;
    private int paymentRef;
    private String speCode;
    private int trackType;
    private double vat;
    public int getId() {
        return this.f1244id;
    }
    public void setId(int i2) {
        this.f1244id = i2;
    }
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
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
    public double getVat() {
        return this.vat;
    }
    public void setVat(double d2) {
        this.vat = d2;
    }
    public String getSpeCode() {
        return this.speCode;
    }
    public void setSpeCode(String str) {
        this.speCode = str;
    }
    public int getPaymentRef() {
        return this.paymentRef;
    }
    public void setPaymentRef(int i2) {
        this.paymentRef = i2;
    }
    public double getCmDate() {
        return this.cmDate;
    }
    public void setCmDate(double d2) {
        this.cmDate = d2;
    }
    public int getAddTaxRef() {
        return this.addTaxRef;
    }
    public void setAddTaxRef(int i2) {
        this.addTaxRef = i2;
    }
}
