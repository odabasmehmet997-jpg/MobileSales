package com.proje.mobilesales.core.netsis.sendmodel.print;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.SafeType;

public class PrintExtraInfo {

    @SerializedName("SUBE_KODU")
    @Expose
    private int mBranchCode;

    @SerializedName("CARI_KODU")
    @Expose
    private String mCustomerCode;

    @SerializedName("CARISTOK_KODU")
    @Expose
    private String mCustomerItemCode;

    @SerializedName("CARISTOKISMI")
    @Expose
    private String mCustomerItemName;

    @SerializedName("ISLETME_KODU")
    @Expose
    private int mEnterpriceCode;

    @SerializedName("FATIRS_NO")
    @Expose
    private String mFicheNo;

    @SerializedName("FTIRSIP")
    @Expose
    private int mFtirsip;

    @SerializedName("STOK_KODU")
    @Expose
    private String mItemCode;

    @SerializedName("PLASIYER_ACIKLAMA")
    @Expose
    private String mPlasiyerName;

    @SerializedName("UUID")
    @Expose
    private String uuid;

    public String getPlasiyerName() {
        return this.mPlasiyerName;
    }

    public void setPlasiyerName(String str) {
        this.mPlasiyerName = str;
    }

    public String getCustomerCode() {
        return this.mCustomerCode;
    }

    public void setCustomerCode(String str) {
        this.mCustomerCode = str;
    }

    public int getFtirsip() {
        return this.mFtirsip;
    }

    public void setFtirsip(int i2) {
        this.mFtirsip = i2;
    }

    public String getFicheNo() {
        return this.mFicheNo;
    }

    public void setFicheNo(String str) {
        this.mFicheNo = str;
    }

    public String getItemCode() {
        return this.mItemCode;
    }

    public void setItemCode(String str) {
        this.mItemCode = str;
    }

    public String getCustomerItemCode() {
        return this.mCustomerItemCode;
    }

    public void setCustomerItemCode(String str) {
        this.mCustomerItemCode = str;
    }

    public String getCustomerItemName() {
        return this.mCustomerItemName;
    }

    public void setCustomerItemName(String str) {
        this.mCustomerItemName = str;
    }

    public int getBranchCode() {
        return this.mBranchCode;
    }

    public void setBranchCode(int i2) {
        this.mBranchCode = i2;
    }

    public int getEnterpriceCode() {
        return this.mEnterpriceCode;
    }

    public void setEnterpriceCode(int i2) {
        this.mEnterpriceCode = i2;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String str) {
        this.uuid = str;
    }
}
