package com.proje.mobilesales.core.netsis.sendmodel.edocument;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.edocument.EDocumentType;
import com.proje.mobilesales.core.netsis.sendmodel.sales.NetsisSlipType;

public class SendEDocumentParam {

    @SerializedName("CustomerCode")
    @Expose
    private String customerCode;

    @SerializedName("DocumentNumber")
    @Expose
    private String documentNumber;

    @SerializedName("DocumentType")
    @Expose
    private NetsisSlipType documentType;

    @SerializedName("EDocumentType")
    @Expose
    private EDocumentType eDocumentType;

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

    public NetsisSlipType getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(NetsisSlipType netsisSlipType) {
        this.documentType = netsisSlipType;
    }

    public EDocumentType geteDocumentType() {
        return this.eDocumentType;
    }

    public void seteDocumentType(EDocumentType eDocumentType) {
        this.eDocumentType = eDocumentType;
    }
}
