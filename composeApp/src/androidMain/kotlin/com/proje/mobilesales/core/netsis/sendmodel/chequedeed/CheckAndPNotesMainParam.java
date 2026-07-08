package com.proje.mobilesales.core.netsis.sendmodel.chequedeed;

import android.text.TextUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Exclude;

public class CheckAndPNotesMainParam {

    @SerializedName("BordroNo")
    @Expose
    private String mBordroNo;

    @Exclude
    private String mCustomerCode;

    @SerializedName("Tip")
    @Expose
    private NetsisChequeAndDeedType mNetsisChequeAndDeedType;

    public String getBordroNo() {
        return this.mBordroNo;
    }

    public void setBordroNo(String str) {
        this.mBordroNo = str;
    }

    public NetsisChequeAndDeedType getNetsisChequeAndDeedType() {
        return this.mNetsisChequeAndDeedType;
    }

    public void setNetsisChequeAndDeedType(NetsisChequeAndDeedType netsisChequeAndDeedType) {
        this.mNetsisChequeAndDeedType = netsisChequeAndDeedType;
    }

    public String getKey() {
        String sb = this.mNetsisChequeAndDeedType.toString() +
                ";" +
                (TextUtils.isEmpty(this.mBordroNo) ? "" : this.mBordroNo) +
                ";";
        return sb;
    }

    public String getCustomerCode() {
        return this.mCustomerCode;
    }

    public void setCustomerCode(String str) {
        this.mCustomerCode = str;
    }
}
