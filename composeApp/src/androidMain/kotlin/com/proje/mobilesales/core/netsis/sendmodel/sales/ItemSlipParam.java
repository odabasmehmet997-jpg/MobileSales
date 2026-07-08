package com.proje.mobilesales.core.netsis.sendmodel.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemSlipParam {

    @SerializedName("CustomerCode")
    @Expose
    private String customerCode;

    @SerializedName("DocumentNumber")
    @Expose
    private String documentNumber;

    @SerializedName("DocumentType")
    @Expose
    private int documentType;

    @SerializedName("Code")
    @Expose
    private NetsisSlipType slipType;

    public NetsisSlipType getSlipType() {
        return this.slipType;
    }

    public void setSlipType(NetsisSlipType netsisSlipType) {
        this.slipType = netsisSlipType;
    }

    public int getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(int i2) {
        this.documentType = i2;
    }

    public String getDocumentNumber() {
        return this.documentNumber;
    }

    public void setDocumentNumber(String str) {
        this.documentNumber = str;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public void setCustomerCode(String str) {
        this.customerCode = str;
    }
}
