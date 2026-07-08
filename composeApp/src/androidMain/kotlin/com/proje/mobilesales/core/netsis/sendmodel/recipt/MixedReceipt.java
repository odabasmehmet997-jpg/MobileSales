package com.proje.mobilesales.core.netsis.sendmodel.recipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Exclude;

public class MixedReceipt {

    @SerializedName("CRapKod1")
    @Expose
    private String cRapKod1;

    @SerializedName("CRapKod2")
    @Expose
    private String cRapKod2;

    @SerializedName("KartNo")
    @Expose
    private String cardNo;

    @SerializedName("KasaKod")
    @Expose
    private String caseCode;

    @SerializedName("SozKodu")
    @Expose
    private String contractCode;

    @SerializedName("Kur")
    @Expose
    private double currency;

    @SerializedName("DovTutar")
    @Expose
    private double currencyTotal;

    @Exclude
    private String docNo;

    @SerializedName("Aciklama")
    @Expose
    private String explanation;

    @SerializedName("TaksitSay")
    @Expose
    private int instalmentCount;

    @SerializedName("Entegrefkey")
    @Expose
    private String integrationKey;

    @SerializedName("PLA_KODU")
    @Expose
    private String plasiyerCode;

    @SerializedName("Tutar")
    @Expose
    private double price;

    @SerializedName("Proje_Kodu")
    @Expose
    private String projectCode;

    @SerializedName("Referans_Kodu")
    @Expose
    private String refCode;

    public String getContractCode() {
        return this.contractCode;
    }

    public void setContractCode(String str) {
        this.contractCode = str;
    }

    public int getInstalmentCount() {
        return this.instalmentCount;
    }

    public void setInstalmentCount(int i2) {
        this.instalmentCount = i2;
    }

    public double getCurrencyTotal() {
        return this.currencyTotal;
    }

    public void setCurrencyTotal(double d2) {
        this.currencyTotal = d2;
    }

    public double getCurrency() {
        return this.currency;
    }

    public void setCurrency(double d2) {
        this.currency = d2;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double d2) {
        this.price = d2;
    }

    public String getCardNo() {
        return this.cardNo;
    }

    public void setCardNo(String str) {
        this.cardNo = str;
    }

    public String getcRapKod1() {
        return this.cRapKod1;
    }

    public void setcRapKod1(String str) {
        this.cRapKod1 = str;
    }

    public String getcRapKod2() {
        return this.cRapKod2;
    }

    public void setcRapKod2(String str) {
        this.cRapKod2 = str;
    }

    public String getPlasiyerCode() {
        return this.plasiyerCode;
    }

    public void setPlasiyerCode(String str) {
        this.plasiyerCode = str;
    }

    public String getProjectCode() {
        return this.projectCode;
    }

    public void setProjectCode(String str) {
        this.projectCode = str;
    }

    public String getRefCode() {
        return this.refCode;
    }

    public void setRefCode(String str) {
        this.refCode = str;
    }

    public String getIntegrationKey() {
        return this.integrationKey;
    }

    public void setIntegrationKey(String str) {
        this.integrationKey = str;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String str) {
        this.explanation = str;
    }

    public String getCaseCode() {
        return this.caseCode;
    }

    public void setCaseCode(String str) {
        this.caseCode = str;
    }

    public String getDocNo() {
        return this.docNo;
    }

    public void setDocNo(String str) {
        this.docNo = str;
    }
}
