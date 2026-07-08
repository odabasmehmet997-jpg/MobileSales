package com.proje.mobilesales.core.netsis.sendmodel.edocument;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.SafeType;
import com.proje.mobilesales.core.edocument.EDocumentType;

public class ShowEDocumentParam {

    @SerializedName("DocumentBoxType")
    @Expose
    private DocumentBoxType documentBoxType;

    @SerializedName("EDocumentType")
    @Expose
    private EDocumentType eDocumentType;

    @SerializedName("EnvelopeId")
    @Expose
    private String envolopeId;

    @SerializedName("ETTN")
    private String ettn;

    @SerializedName("GIBDocumentNumber")
    @Expose
    private String gibDocumentNumber;

    public String getGibDocumentNumber() {
        return this.gibDocumentNumber;
    }

    public void setGibDocumentNumber(String str) {
        this.gibDocumentNumber = str;
    }

    public DocumentBoxType getDocumentBoxType() {
        return this.documentBoxType;
    }

    public void setDocumentBoxType(DocumentBoxType documentBoxType) {
        this.documentBoxType = documentBoxType;
    }

    public String getEnvolopeId() {
        return this.envolopeId;
    }

    public void setEnvolopeId(String str) {
        this.envolopeId = str;
    }

    public EDocumentType geteDocumentType() {
        return this.eDocumentType;
    }

    public void seteDocumentType(EDocumentType eDocumentType) {
        this.eDocumentType = eDocumentType;
    }

    public String getEttn() {
        return this.ettn;
    }

    public void setEttn(String str) {
        this.ettn = str;
    }
}
