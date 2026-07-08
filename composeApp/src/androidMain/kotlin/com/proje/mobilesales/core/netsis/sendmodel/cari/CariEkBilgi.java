package com.proje.mobilesales.core.netsis.sendmodel.cari;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CariEkBilgi {

    @SerializedName("B_Yedek1")
    @Expose
    private int b_yedek1;

    @SerializedName("C_Yedek1")
    @Expose
    private String c_yedek1;

    @SerializedName("C_Yedek2")
    @Expose
    private String c_yedek2;

    @SerializedName("CARIALIAS")
    @Expose
    private String carialias;

    @SerializedName("CIRO_TARIHI")
    @Expose
    private String ciro_tarihi;

    @SerializedName("CARI_KOD")
    @Expose
    private String code;

    @SerializedName("DuzeltmeTarihi")
    @Expose
    private String duzeltmeTarihi;

    @SerializedName("DuzeltmeYapanKul")
    @Expose
    private String duzeltmeYapanKul;

    @SerializedName("ESKI_YENI")
    @Expose
    private String eski_yeni;

    @SerializedName("F_Yedek1")
    @Expose
    private double f_yedek1;

    @SerializedName("F_Yedek2")
    @Expose
    private double f_yedek2;

    @SerializedName("I_Yedek1")
    @Expose
    private int i_yedek1;

    @SerializedName("KayitTarihi")
    @Expose
    private String kayitTarihi;

    @SerializedName("KayitYapanKul")
    @Expose
    private String kayitYapanKul;

    @SerializedName("Kull1S")
    @Expose
    private String kull1S;

    @SerializedName("Kull1N")
    @Expose
    private double kull1n;

    @SerializedName("Kull2S")
    @Expose
    private String kull2S;

    @SerializedName("Kull2N")
    @Expose
    private double kull2n;

    @SerializedName("Kull3S")
    @Expose
    private String kull3S;

    @SerializedName("Kull3N")
    @Expose
    private double kull3n;

    @SerializedName("Kull4S")
    @Expose
    private String kull4S;

    @SerializedName("Kull4N")
    @Expose
    private double kull4n;

    @SerializedName("Kull5S")
    @Expose
    private String kull5S;

    @SerializedName("Kull5N")
    @Expose
    private double kull5n;

    @SerializedName("Kull6N")
    @Expose
    private double kull6n;

    @SerializedName("Kull6S")
    @Expose
    private String kull6s;

    @SerializedName("Kull7N")
    @Expose
    private double kull7n;

    @SerializedName("Kull7S")
    @Expose
    private String kull7s;

    @SerializedName("Kull8N")
    @Expose
    private double kull8N;

    @SerializedName("Kull8S")
    @Expose
    private String kull8s;

    @SerializedName("L_Yedek1")
    @Expose
    private int l_yedek1;

    @SerializedName("ODEKOD")
    @Expose
    private String odekod;

    @SerializedName("PRIM")
    @Expose
    private double prim;

    @SerializedName("S_Yedek1")
    @Expose
    private String s_yedek1;

    @SerializedName("S_Yedek2")
    @Expose
    private String s_yedek2;

    @SerializedName("SALES_VOLUME")
    @Expose
    private double sales_volume;

    @SerializedName("TcKimlikNo")
    @Expose
    private String tckimlikno;

    public String getCode() {
        return this.code;
    }

    public CariEkBilgi setCode(String str) {
        this.code = str;
        return this;
    }

    public String getKayitTarihi() {
        return this.kayitTarihi;
    }

    public CariEkBilgi setKayitTarihi(String str) {
        this.kayitTarihi = str;
        return this;
    }

    public String getKayitYapanKul() {
        return this.kayitYapanKul;
    }

    public CariEkBilgi setKayitYapanKul(String str) {
        this.kayitYapanKul = str;
        return this;
    }

    public String getDuzeltmeTarihi() {
        return this.duzeltmeTarihi;
    }

    public CariEkBilgi setDuzeltmeTarihi(String str) {
        this.duzeltmeTarihi = str;
        return this;
    }

    public String getDuzeltmeYapanKul() {
        return this.duzeltmeYapanKul;
    }

    public CariEkBilgi setDuzeltmeYapanKul(String str) {
        this.duzeltmeYapanKul = str;
        return this;
    }

    public double getKull1n() {
        return this.kull1n;
    }

    public CariEkBilgi setKull1n(double d2) {
        this.kull1n = d2;
        return this;
    }

    public double getKull2n() {
        return this.kull2n;
    }

    public CariEkBilgi setKull2n(double d2) {
        this.kull2n = d2;
        return this;
    }

    public double getKull3n() {
        return this.kull3n;
    }

    public CariEkBilgi setKull3n(double d2) {
        this.kull3n = d2;
        return this;
    }

    public double getKull4n() {
        return this.kull4n;
    }

    public CariEkBilgi setKull4n(double d2) {
        this.kull4n = d2;
        return this;
    }

    public double getKull5n() {
        return this.kull5n;
    }

    public CariEkBilgi setKull5n(double d2) {
        this.kull5n = d2;
        return this;
    }

    public double getKull6n() {
        return this.kull6n;
    }

    public CariEkBilgi setKull6n(double d2) {
        this.kull6n = d2;
        return this;
    }

    public double getKull7n() {
        return this.kull7n;
    }

    public CariEkBilgi setKull7n(double d2) {
        this.kull7n = d2;
        return this;
    }

    public double getKull8N() {
        return this.kull8N;
    }

    public CariEkBilgi setKull8N(double d2) {
        this.kull8N = d2;
        return this;
    }

    public String getKull1S() {
        return this.kull1S;
    }

    public CariEkBilgi setKull1S(String str) {
        this.kull1S = str;
        return this;
    }

    public String getKull2S() {
        return this.kull2S;
    }

    public CariEkBilgi setKull2S(String str) {
        this.kull2S = str;
        return this;
    }

    public String getKull3S() {
        return this.kull3S;
    }

    public CariEkBilgi setKull3S(String str) {
        this.kull3S = str;
        return this;
    }

    public String getKull4S() {
        return this.kull4S;
    }

    public CariEkBilgi setKull4S(String str) {
        this.kull4S = str;
        return this;
    }

    public String getKull5S() {
        return this.kull5S;
    }

    public CariEkBilgi setKull5S(String str) {
        this.kull5S = str;
        return this;
    }

    public String getKull6s() {
        return this.kull6s;
    }

    public CariEkBilgi setKull6s(String str) {
        this.kull6s = str;
        return this;
    }

    public String getKull7s() {
        return this.kull7s;
    }

    public CariEkBilgi setKull7s(String str) {
        this.kull7s = str;
        return this;
    }

    public String getKull8s() {
        return this.kull8s;
    }

    public CariEkBilgi setKull8s(String str) {
        this.kull8s = str;
        return this;
    }

    public double getSales_volume() {
        return this.sales_volume;
    }

    public CariEkBilgi setSales_volume(double d2) {
        this.sales_volume = d2;
        return this;
    }

    public double getPrim() {
        return this.prim;
    }

    public CariEkBilgi setPrim(double d2) {
        this.prim = d2;
        return this;
    }

    public String getCiro_tarihi() {
        return this.ciro_tarihi;
    }

    public CariEkBilgi setCiro_tarihi(String str) {
        this.ciro_tarihi = str;
        return this;
    }

    public String getEski_yeni() {
        return this.eski_yeni;
    }

    public CariEkBilgi setEski_yeni(String str) {
        this.eski_yeni = str;
        return this;
    }

    public String getS_yedek1() {
        return this.s_yedek1;
    }

    public CariEkBilgi setS_yedek1(String str) {
        this.s_yedek1 = str;
        return this;
    }

    public String getS_yedek2() {
        return this.s_yedek2;
    }

    public CariEkBilgi setS_yedek2(String str) {
        this.s_yedek2 = str;
        return this;
    }

    public double getF_yedek1() {
        return this.f_yedek1;
    }

    public CariEkBilgi setF_yedek1(double d2) {
        this.f_yedek1 = d2;
        return this;
    }

    public double getF_yedek2() {
        return this.f_yedek2;
    }

    public CariEkBilgi setF_yedek2(double d2) {
        this.f_yedek2 = d2;
        return this;
    }

    public String getC_yedek1() {
        return this.c_yedek1;
    }

    public CariEkBilgi setC_yedek1(String str) {
        this.c_yedek1 = str;
        return this;
    }

    public String getC_yedek2() {
        return this.c_yedek2;
    }

    public CariEkBilgi setC_yedek2(String str) {
        this.c_yedek2 = str;
        return this;
    }

    public int getB_yedek1() {
        return this.b_yedek1;
    }

    public CariEkBilgi setB_yedek1(int i2) {
        this.b_yedek1 = i2;
        return this;
    }

    public int getI_yedek1() {
        return this.i_yedek1;
    }

    public CariEkBilgi setI_yedek1(int i2) {
        this.i_yedek1 = i2;
        return this;
    }

    public int getL_yedek1() {
        return this.l_yedek1;
    }

    public CariEkBilgi setL_yedek1(int i2) {
        this.l_yedek1 = i2;
        return this;
    }

    public String getOdekod() {
        return this.odekod;
    }

    public CariEkBilgi setOdekod(String str) {
        this.odekod = str;
        return this;
    }

    public String getTckimlikno() {
        return this.tckimlikno;
    }

    public CariEkBilgi setTckimlikno(String str) {
        this.tckimlikno = str;
        return this;
    }

    public String getCarialias() {
        return this.carialias;
    }

    public CariEkBilgi setCarialias(String str) {
        this.carialias = str;
        return this;
    }
}
