package com.proje.mobilesales.core.netsis.sendmodel.safedeposit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SafeDepositParam {

    @SerializedName("Sube_Kodu")
    @Expose
    private int branchCode;

    @SerializedName("Kod")
    @Expose
    private String mCustomerCode;

    @SerializedName("Fisno")
    @Expose
    private String mFicheNo;

    public String getFicheNo() {
        return this.mFicheNo;
    }

    public void setFicheNo(String str) {
        this.mFicheNo = str;
    }

    public int getBranchCode() {
        return this.branchCode;
    }

    public void setBranchCode(int i2) {
        this.branchCode = i2;
    }

    public String getKey() {
        return "FISNO='" + this.mFicheNo + "' AND Sube_Kodu='" + this.branchCode + "' AND Kod='" + this.mCustomerCode + "'";
    }

    public String getCustomerCode() {
        return this.mCustomerCode;
    }

    public void setCustomerCode(String str) {
        this.mCustomerCode = str;
    }
}
