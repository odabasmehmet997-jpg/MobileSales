package com.proje.mobilesales.core.netsis.sendmodel.recipt;

import android.text.TextUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MixedReceiptsMainParam {

    @SerializedName("KasaKod")
    @Expose
    private String caseCode;

    @SerializedName("CariKod")
    @Expose
    private String customerCode;

    @SerializedName("IslemTarihi")
    @Expose
    private String date;

    @SerializedName("BelgeNo")
    @Expose
    private String documentNumber;

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

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getCaseCode() {
        return this.caseCode;
    }

    public void setCaseCode(String str) {
        this.caseCode = str;
    }

    public String getKey() {
        String sb = (TextUtils.isEmpty(this.caseCode) ? "" : this.caseCode) +
                ";" +
                (TextUtils.isEmpty(this.documentNumber) ? "" : this.documentNumber) +
                ";" +
                (TextUtils.isEmpty(this.customerCode) ? "" : this.customerCode) +
                ";" +
                (TextUtils.isEmpty(this.date) ? "" : this.date) +
                ";";
        return sb;
    }
}
