package com.proje.mobilesales.core.netsis.sendmodel.recipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.annotation.Exclude;
import java.util.List;

public class MixedReceiptsMain {

    @SerializedName("BaglantiNo")
    @Expose
    private int baglantiNo;

    @SerializedName("BelgeNo")
    @Expose
    private String belgeNo;

    @SerializedName("CariKod")
    @Expose
    private String cariKod;

    @SerializedName("DOVTIP")
    @Expose
    private int dOVTIP;

    @SerializedName("IslemTarihi")
    @Expose
    private String islemTarihi;

    @SerializedName("KasaKod")
    @Expose
    private String kasaKod;

    @Exclude
    private String mBankCode;

    @Exclude
    private String mBankDescription;

    @Exclude
    private String mSpeCode;

    @Exclude
    private String mTime;

    @SerializedName("MuhasebelesmisBelge")
    @Expose
    private boolean muhasebelesmisBelge;

    @SerializedName("TahsilatKalemAdedi")
    @Expose
    private int tahsilatKalemAdedi;

    @SerializedName("Tahsilats")
    @Expose
    private List<MixedReceipt> tahsilats;

    @SerializedName("TransactSupport")
    @Expose
    private boolean transactSupport;

    private MixedReceiptsMain(Builder builder) {
        this.tahsilats = null;
        setTransactSupport(builder.transactSupport);
        setMuhasebelesmisBelge(builder.muhasebelesmisBelge);
        setKasaKod(builder.kasaKod);
        setIslemTarihi(builder.islemTarihi);
        setCariKod(builder.cariKod);
        setBelgeNo(builder.belgeNo);
        setdOVTIP(builder.dOVTIP);
        setTahsilatKalemAdedi(builder.tahsilatKalemAdedi);
        setBaglantiNo(builder.baglantiNo);
        setTahsilats(builder.tahsilats);
        setTime(builder.time);
        setBankCode(builder.bankCode);
        setBankDescription(builder.bankDescription);
        setSpeCode(builder.speCode);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(MixedReceiptsMain mixedReceiptsMain) {
        Builder builder = new Builder();
        builder.transactSupport = mixedReceiptsMain.transactSupport;
        builder.muhasebelesmisBelge = mixedReceiptsMain.muhasebelesmisBelge;
        builder.kasaKod = mixedReceiptsMain.kasaKod;
        builder.islemTarihi = mixedReceiptsMain.islemTarihi;
        builder.cariKod = mixedReceiptsMain.cariKod;
        builder.belgeNo = mixedReceiptsMain.belgeNo;
        builder.dOVTIP = mixedReceiptsMain.dOVTIP;
        builder.tahsilatKalemAdedi = mixedReceiptsMain.tahsilatKalemAdedi;
        builder.baglantiNo = mixedReceiptsMain.baglantiNo;
        builder.tahsilats = mixedReceiptsMain.tahsilats;
        builder.time = mixedReceiptsMain.mTime;
        builder.bankCode = mixedReceiptsMain.mBankCode;
        builder.bankDescription = mixedReceiptsMain.mBankDescription;
        builder.speCode = mixedReceiptsMain.mSpeCode;
        return builder;
    }

    public boolean isTransactSupport() {
        return this.transactSupport;
    }

    public void setTransactSupport(boolean z) {
        this.transactSupport = z;
    }

    public boolean isMuhasebelesmisBelge() {
        return this.muhasebelesmisBelge;
    }

    public void setMuhasebelesmisBelge(boolean z) {
        this.muhasebelesmisBelge = z;
    }

    public String getKasaKod() {
        return this.kasaKod;
    }

    public void setKasaKod(String str) {
        this.kasaKod = str;
    }

    public String getIslemTarihi() {
        return this.islemTarihi;
    }

    public void setIslemTarihi(String str) {
        this.islemTarihi = str;
    }

    public String getCariKod() {
        return this.cariKod;
    }

    public void setCariKod(String str) {
        this.cariKod = str;
    }

    public String getBelgeNo() {
        return this.belgeNo;
    }

    public void setBelgeNo(String str) {
        this.belgeNo = str;
    }

    public int getdOVTIP() {
        return this.dOVTIP;
    }

    public void setdOVTIP(int i2) {
        this.dOVTIP = i2;
    }

    public int getTahsilatKalemAdedi() {
        return this.tahsilatKalemAdedi;
    }

    public void setTahsilatKalemAdedi(int i2) {
        this.tahsilatKalemAdedi = i2;
    }

    public int getBaglantiNo() {
        return this.baglantiNo;
    }

    public void setBaglantiNo(int i2) {
        this.baglantiNo = i2;
    }

    public List<MixedReceipt> getTahsilats() {
        return this.tahsilats;
    }

    public void setTahsilats(List<MixedReceipt> list) {
        this.tahsilats = list;
    }

    public String getTime() {
        return this.mTime;
    }

    public void setTime(String str) {
        this.mTime = str;
    }

    public String getBankCode() {
        return this.mBankCode;
    }

    public void setBankCode(String str) {
        this.mBankCode = str;
    }

    public String getBankDescription() {
        return this.mBankDescription;
    }

    public void setBankDescription(String str) {
        this.mBankDescription = str;
    }

    public String getSpeCode() {
        return this.mSpeCode;
    }

    public void setSpeCode(String str) {
        this.mSpeCode = str;
    }

    public static final class Builder {
        private int baglantiNo;
        private String bankCode;
        private String bankDescription;
        private String belgeNo;
        private String cariKod;
        private int dOVTIP;
        private String islemTarihi;
        private String kasaKod;
        private boolean muhasebelesmisBelge;
        private String speCode;
        private int tahsilatKalemAdedi;
        private List<MixedReceipt> tahsilats;
        private String time;
        private boolean transactSupport;

        private Builder() {
        }

        public Builder withTransactSupport(boolean z) {
            this.transactSupport = z;
            return this;
        }

        public Builder withMuhasebelesmisBelge(boolean z) {
            this.muhasebelesmisBelge = z;
            return this;
        }

        public Builder withKasaKod(String str) {
            this.kasaKod = str;
            return this;
        }

        public Builder withIslemTarihi(String str) {
            this.islemTarihi = str;
            return this;
        }

        public Builder withCariKod(String str) {
            this.cariKod = str;
            return this;
        }

        public Builder withBelgeNo(String str) {
            this.belgeNo = str;
            return this;
        }

        public Builder withDOVTIP(int i2) {
            this.dOVTIP = i2;
            return this;
        }

        public Builder withTahsilatKalemAdedi(int i2) {
            this.tahsilatKalemAdedi = i2;
            return this;
        }

        public Builder withBaglantiNo(int i2) {
            this.baglantiNo = i2;
            return this;
        }

        public Builder withTahsilats(List<MixedReceipt> list) {
            this.tahsilats = list;
            return this;
        }

        public Builder withTime(String str) {
            this.time = str;
            return this;
        }

        public Builder withSpeCode(String str) {
            this.speCode = str;
            return this;
        }

        public Builder withBankCode(String str) {
            this.bankCode = str;
            return this;
        }

        public Builder withBankDescription(String str) {
            this.bankDescription = str;
            return this;
        }

        public MixedReceiptsMain build() {
            return new MixedReceiptsMain(this);
        }
    }
}
