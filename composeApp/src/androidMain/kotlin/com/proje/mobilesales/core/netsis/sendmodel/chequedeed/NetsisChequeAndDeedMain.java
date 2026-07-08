package com.proje.mobilesales.core.netsis.sendmodel.chequedeed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NetsisChequeAndDeedMain {

    @SerializedName("BordroNo")
    @Expose
    private String bordroNo;

    @SerializedName("CariMuhKayitYapilsin")
    @Expose
    private boolean cariMuhKayitYapilsin;

    @SerializedName("CekSenEvrakKaydetmeTip")
    @Expose
    private NetsisChequeAndDeedSaveType cekSenEvrakKaydetmeTip;

    @SerializedName("EvrakAdedi")
    @Expose
    private int evrakAdedi;

    @SerializedName("Evraklar")
    @Expose
    private List<NetsisChequeAndDeed> evraklar;

    @SerializedName("MuhasebelesmisBelge")
    @Expose
    private boolean muhasebelesmisBelge;

    @SerializedName("OkunanCS")
    @Expose
    private NetsisChequeAndDeed okunanCS;

    @SerializedName("OtoNumaraGetir")
    @Expose
    private boolean otoNumaraGetir;

    @SerializedName("ProgNo")
    @Expose
    private int progNo;

    @SerializedName("Tip")
    @Expose
    private NetsisChequeAndDeedType tip;

    @SerializedName("TransactSupport")
    @Expose
    private boolean transactSupport;

    private NetsisChequeAndDeedMain(Builder builder) {
        this.evraklar = null;
        setEvraklar(builder.evraklar);
        setCekSenEvrakKaydetmeTip(builder.cekSenEvrakKaydetmeTip);
        setTransactSupport(builder.transactSupport);
        setMuhasebelesmisBelge(builder.muhasebelesmisBelge);
        setEvrakAdedi(builder.evrakAdedi);
        setOkunanCS(builder.okunanCS);
        setTip(builder.tip);
        setOtoNumaraGetir(builder.otoNumaraGetir);
        setCariMuhKayitYapilsin(builder.cariMuhKayitYapilsin);
        setProgNo(builder.progNo);
        setBordroNo(builder.bordroNo);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public List<NetsisChequeAndDeed> getEvraklar() {
        return this.evraklar;
    }

    public void setEvraklar(List<NetsisChequeAndDeed> list) {
        this.evraklar = list;
    }

    public NetsisChequeAndDeedSaveType getCekSenEvrakKaydetmeTip() {
        return this.cekSenEvrakKaydetmeTip;
    }

    public void setCekSenEvrakKaydetmeTip(NetsisChequeAndDeedSaveType netsisChequeAndDeedSaveType) {
        this.cekSenEvrakKaydetmeTip = netsisChequeAndDeedSaveType;
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

    public int getEvrakAdedi() {
        return this.evrakAdedi;
    }

    public void setEvrakAdedi(int i2) {
        this.evrakAdedi = i2;
    }

    public NetsisChequeAndDeed getOkunanCS() {
        return this.okunanCS;
    }

    public void setOkunanCS(NetsisChequeAndDeed netsisChequeAndDeed) {
        this.okunanCS = netsisChequeAndDeed;
    }

    public NetsisChequeAndDeedType getTip() {
        return this.tip;
    }

    public void setTip(NetsisChequeAndDeedType netsisChequeAndDeedType) {
        this.tip = netsisChequeAndDeedType;
    }

    public boolean isOtoNumaraGetir() {
        return this.otoNumaraGetir;
    }

    public void setOtoNumaraGetir(boolean z) {
        this.otoNumaraGetir = z;
    }

    public boolean isCariMuhKayitYapilsin() {
        return this.cariMuhKayitYapilsin;
    }

    public void setCariMuhKayitYapilsin(boolean z) {
        this.cariMuhKayitYapilsin = z;
    }

    public int getProgNo() {
        return this.progNo;
    }

    public void setProgNo(int i2) {
        this.progNo = i2;
    }

    public String getBordroNo() {
        return this.bordroNo;
    }

    public void setBordroNo(String str) {
        this.bordroNo = str;
    }

    public static final class Builder {
        private String bordroNo;
        private boolean cariMuhKayitYapilsin;
        private NetsisChequeAndDeedSaveType cekSenEvrakKaydetmeTip;
        private int evrakAdedi;
        private List<NetsisChequeAndDeed> evraklar;
        private boolean muhasebelesmisBelge;
        private NetsisChequeAndDeed okunanCS;
        private boolean otoNumaraGetir;
        private int progNo;
        private NetsisChequeAndDeedType tip;
        private boolean transactSupport;

        private Builder() {
        }

        public Builder withEvraklar(List<NetsisChequeAndDeed> list) {
            this.evraklar = list;
            return this;
        }

        public Builder withCekSenEvrakKaydetmeTip(NetsisChequeAndDeedSaveType netsisChequeAndDeedSaveType) {
            this.cekSenEvrakKaydetmeTip = netsisChequeAndDeedSaveType;
            return this;
        }

        public Builder withTransactSupport(boolean z) {
            this.transactSupport = z;
            return this;
        }

        public Builder withMuhasebelesmisBelge(boolean z) {
            this.muhasebelesmisBelge = z;
            return this;
        }

        public Builder withEvrakAdedi(int i2) {
            this.evrakAdedi = i2;
            return this;
        }

        public Builder withOkunanCS(NetsisChequeAndDeed netsisChequeAndDeed) {
            this.okunanCS = netsisChequeAndDeed;
            return this;
        }

        public Builder withTip(NetsisChequeAndDeedType netsisChequeAndDeedType) {
            this.tip = netsisChequeAndDeedType;
            return this;
        }

        public Builder withOtoNumaraGetir(boolean z) {
            this.otoNumaraGetir = z;
            return this;
        }

        public Builder withCariMuhKayitYapilsin(boolean z) {
            this.cariMuhKayitYapilsin = z;
            return this;
        }

        public Builder withProgNo(int i2) {
            this.progNo = i2;
            return this;
        }

        public Builder withBordroNo(String str) {
            this.bordroNo = str;
            return this;
        }

        public NetsisChequeAndDeedMain build() {
            return new NetsisChequeAndDeedMain(this);
        }
    }
}
