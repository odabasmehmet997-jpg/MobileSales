package com.proje.mobilesales.core.netsis.sendmodel.safedeposit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SafeDepositKdvLine {

    @SerializedName("Aciklama")
    @Expose
    private String aciklama;

    @SerializedName("DOVTUT")
    @Expose
    private double dOVTUT;

    @SerializedName("Kdv_Dahil")
    @Expose
    private boolean kdvDahil;

    @SerializedName("KdvDovTut")
    @Expose
    private double kdvDovTut;

    @SerializedName("KdvMuhKod")
    @Expose
    private String kdvMuhKod;

    @SerializedName("Kdv_Oran")
    @Expose
    private double kdvOran;

    @SerializedName("KdvTutar")
    @Expose
    private double kdvTutar;

    @SerializedName("Kod")
    @Expose
    private String kod;

    @SerializedName("Miktar")
    @Expose
    private double miktar;

    @SerializedName("ProjeKodu")
    @Expose
    private String projeKodu;

    @SerializedName("ReferansKodu")
    @Expose
    private String referansKodu;

    @SerializedName("Tutar")
    @Expose
    private double tutar;

    public String getKod() {
        return this.kod;
    }

    public void setKod(String str) {
        this.kod = str;
    }

    public double getTutar() {
        return this.tutar;
    }

    public void setTutar(double d2) {
        this.tutar = d2;
    }

    public double getKdvOran() {
        return this.kdvOran;
    }

    public void setKdvOran(double d2) {
        this.kdvOran = d2;
    }

    public String getKdvMuhKod() {
        return this.kdvMuhKod;
    }

    public void setKdvMuhKod(String str) {
        this.kdvMuhKod = str;
    }

    public boolean isKdvDahil() {
        return this.kdvDahil;
    }

    public void setKdvDahil(boolean z) {
        this.kdvDahil = z;
    }

    public String getAciklama() {
        return this.aciklama;
    }

    public void setAciklama(String str) {
        this.aciklama = str;
    }

    public double getdOVTUT() {
        return this.dOVTUT;
    }

    public void setdOVTUT(double d2) {
        this.dOVTUT = d2;
    }

    public String getReferansKodu() {
        return this.referansKodu;
    }

    public void setReferansKodu(String str) {
        this.referansKodu = str;
    }

    public String getProjeKodu() {
        return this.projeKodu;
    }

    public void setProjeKodu(String str) {
        this.projeKodu = str;
    }

    public double getMiktar() {
        return this.miktar;
    }

    public void setMiktar(double d2) {
        this.miktar = d2;
    }

    public double getKdvTutar() {
        return this.kdvTutar;
    }

    public void setKdvTutar(double d2) {
        this.kdvTutar = d2;
    }

    public double getKdvDovTut() {
        return this.kdvDovTut;
    }

    public void setKdvDovTut(double d2) {
        this.kdvDovTut = d2;
    }
}
