package com.proje.mobilesales.core.netsis.sendmodel.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiskParam {

    @SerializedName("BelgeNo")
    @Expose
    private String belgeNo;

    @SerializedName("BelgeTipi")
    @Expose
    private String belgeTipi;

    @SerializedName("CariKodu")
    @Expose
    private String cariKodu;

    @SerializedName("OrtakGruplarKumule")
    @Expose
    private boolean ortakGruplarKumule;

    @SerializedName("RiskTipi")
    @Expose
    private int riskTipi;

    @SerializedName("Tarih")
    @Expose
    private String tarih;

    public boolean isOrtakGruplarKumule() {
        return this.ortakGruplarKumule;
    }

    public void setOrtakGruplarKumule(boolean z) {
        this.ortakGruplarKumule = z;
    }

    public int getRiskTipi() {
        return this.riskTipi;
    }

    public void setRiskTipi(int i2) {
        this.riskTipi = i2;
    }

    public String getCariKodu() {
        return this.cariKodu;
    }

    public void setCariKodu(String str) {
        this.cariKodu = str;
    }

    public String getTarih() {
        return this.tarih;
    }

    public void setTarih(String str) {
        this.tarih = str;
    }

    public String getBelgeTipi() {
        return this.belgeTipi;
    }

    public void setBelgeTipi(String str) {
        this.belgeTipi = str;
    }

    public String getBelgeNo() {
        return this.belgeNo;
    }

    public void setBelgeNo(String str) {
        this.belgeNo = str;
    }
}
