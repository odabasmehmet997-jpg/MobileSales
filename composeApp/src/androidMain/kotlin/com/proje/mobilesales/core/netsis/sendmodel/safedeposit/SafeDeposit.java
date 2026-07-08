package com.proje.mobilesales.core.netsis.sendmodel.safedeposit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class SafeDeposit {

    @SerializedName("Aciklama")
    @Expose
    private String aciklama;

    @SerializedName("BFormCariKodu")
    @Expose
    private String bFormCariKodu;

    @SerializedName("BankaAciklama")
    @Expose
    private String bankaAciklama;

    @SerializedName("BankaEfektifTarih")
    @Expose
    private String bankaEfektifTarih;

    @SerializedName("BankaYevmiyeAciklama")
    @Expose
    private String bankaYevmiyeAciklama;

    @SerializedName("BelgeTuru")
    @Expose
    private String belgeTuru;

    @SerializedName("CariEfektifTarih")
    @Expose
    private String cariEfektifTarih;

    @SerializedName("CariHareketAciklama")
    @Expose
    private String cariHareketAciklama;

    @SerializedName("Cari_Muh")
    @Expose
    private String cariMuh;

    @SerializedName("DovizTipi")
    @Expose
    private int dovizTipi;

    @SerializedName("DovizTut")
    @Expose
    private double dovizTut;

    @SerializedName("DuzeltmeTarihi")
    @Expose
    private String duzeltmeTarihi;

    @SerializedName("DuzeltmeYapanKul")
    @Expose
    private String duzeltmeYapanKul;

    @SerializedName("Entegrefkey")
    @Expose
    private String entegrefkey;

    @SerializedName("Fisno")
    @Expose
    private String fisno;

    @SerializedName("Gecerli")
    @Expose
    private String gecerli;

    /* renamed from: iO */
    @SerializedName("IO")
    @Expose
    private String f1186iO;

    @SerializedName("KayitTarihi")
    @Expose
    private String kayitTarihi;

    @SerializedName("KayitYapanKul")
    @Expose
    private String kayitYapanKul;

    @SerializedName("Kaynak")
    @Expose
    private int kaynak;

    @SerializedName("Kdv_Dahil")
    @Expose
    private String kdvDahil;

    @SerializedName("KdvKalemAdedi")
    @Expose
    private int kdvKalemAdedi;

    @SerializedName("KdvKalems")
    @Expose
    private List<SafeDepositKdvLine> kdvKalems = null;

    @SerializedName("Kdv_Oran")
    @Expose
    private double kdvOran;

    @SerializedName("Kod")
    @Expose
    private String kod;

    @SerializedName("Kredi_Kart_No")
    @Expose
    private String krediKartNo;

    @SerializedName("KrtSozMasInckeyNo")
    @Expose
    private int krtSozMasInckeyNo;

    @SerializedName("KsMas_Kod")
    @Expose
    private String ksMasKod;

    @SerializedName("Kull_ID")
    @Expose
    private int kullID;

    @SerializedName("Kur")
    @Expose
    private double kur;

    @SerializedName("MuhasebelesmisBelge")
    @Expose
    private boolean muhasebelesmisBelge;

    @SerializedName("OdemeTuru")
    @Expose
    private int odemeTuru;

    @SerializedName("OnayNum")
    @Expose
    private int onayNum;

    @SerializedName("OnayTipi")
    @Expose
    private String onayTipi;

    @SerializedName("Plasiyer_Kodu")
    @Expose
    private String plasiyerKodu;

    @SerializedName("Proje_Kodu")
    @Expose
    private String projeKodu;

    @SerializedName("Rapor_Kodu2")
    @Expose
    private String raporKodu2;

    @SerializedName("ReferansKodu")
    @Expose
    private String referansKodu;

    @SerializedName("Sira")
    @Expose
    private int sira;

    @SerializedName("Sube_Kodu")
    @Expose
    private int subeKodu;

    @SerializedName("Taksit")
    @Expose
    private int taksit;

    @SerializedName("Tarih")
    @Expose
    private String tarih;

    @SerializedName("Tip")
    @Expose
    private String tip;

    @SerializedName("TransactSupport")
    @Expose
    private boolean transactSupport;

    @SerializedName("Tutar")
    @Expose
    private double tutar;

    @SerializedName("Update_Kodu")
    @Expose
    private String updateKodu;

    @SerializedName("Yedek1")
    @Expose
    private String yedek1;

    @SerializedName("Yedek10")
    @Expose
    private String yedek10;

    @SerializedName("Yedek2")
    @Expose
    private String yedek2;

    @SerializedName("Yedek3")
    @Expose
    private double yedek3;

    @SerializedName("Yedek4")
    @Expose
    private double yedek4;

    @SerializedName("Yedek5")
    @Expose
    private double yedek5;

    @SerializedName("Yedek6")
    @Expose
    private String yedek6;

    @SerializedName("Yedek7")
    @Expose
    private int yedek7;

    @SerializedName("Yedek8")
    @Expose
    private int yedek8;

    @SerializedName("Yedek9")
    @Expose
    private int yedek9;

    @SerializedName("Zaman")
    @Expose
    private String zaman;

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

    public String getKsMasKod() {
        return this.ksMasKod;
    }

    public void setKsMasKod(String str) {
        this.ksMasKod = str;
    }

    public String getTarih() {
        return this.tarih;
    }

    public void setTarih(String str) {
        this.tarih = str;
    }

    public String getFisno() {
        return this.fisno;
    }

    public void setFisno(String str) {
        this.fisno = str;
    }

    public String getiO() {
        return this.f1186iO;
    }

    public void setiO(String str) {
        this.f1186iO = str;
    }

    public String getAciklama() {
        return this.aciklama;
    }

    public void setAciklama(String str) {
        this.aciklama = str;
    }

    public String getTip() {
        return this.tip;
    }

    public void setTip(String str) {
        this.tip = str;
    }

    public double getTutar() {
        return this.tutar;
    }

    public void setTutar(double d2) {
        this.tutar = d2;
    }

    public String getCariMuh() {
        return this.cariMuh;
    }

    public void setCariMuh(String str) {
        this.cariMuh = str;
    }

    public String getKod() {
        return this.kod;
    }

    public void setKod(String str) {
        this.kod = str;
    }

    public double getDovizTut() {
        return this.dovizTut;
    }

    public void setDovizTut(double d2) {
        this.dovizTut = d2;
    }

    public double getKur() {
        return this.kur;
    }

    public void setKur(double d2) {
        this.kur = d2;
    }

    public String getPlasiyerKodu() {
        return this.plasiyerKodu;
    }

    public void setPlasiyerKodu(String str) {
        this.plasiyerKodu = str;
    }

    public int getOdemeTuru() {
        return this.odemeTuru;
    }

    public void setOdemeTuru(int i2) {
        this.odemeTuru = i2;
    }

    public String getUpdateKodu() {
        return this.updateKodu;
    }

    public void setUpdateKodu(String str) {
        this.updateKodu = str;
    }

    public String getZaman() {
        return this.zaman;
    }

    public void setZaman(String str) {
        this.zaman = str;
    }

    public int getKullID() {
        return this.kullID;
    }

    public void setKullID(int i2) {
        this.kullID = i2;
    }

    public int getSubeKodu() {
        return this.subeKodu;
    }

    public void setSubeKodu(int i2) {
        this.subeKodu = i2;
    }

    public String getKrediKartNo() {
        return this.krediKartNo;
    }

    public void setKrediKartNo(String str) {
        this.krediKartNo = str;
    }

    public double getKdvOran() {
        return this.kdvOran;
    }

    public void setKdvOran(double d2) {
        this.kdvOran = d2;
    }

    public String getKdvDahil() {
        return this.kdvDahil;
    }

    public void setKdvDahil(String str) {
        this.kdvDahil = str;
    }

    public String getYedek1() {
        return this.yedek1;
    }

    public void setYedek1(String str) {
        this.yedek1 = str;
    }

    public String getYedek2() {
        return this.yedek2;
    }

    public void setYedek2(String str) {
        this.yedek2 = str;
    }

    public double getYedek3() {
        return this.yedek3;
    }

    public void setYedek3(double d2) {
        this.yedek3 = d2;
    }

    public double getYedek4() {
        return this.yedek4;
    }

    public void setYedek4(double d2) {
        this.yedek4 = d2;
    }

    public double getYedek5() {
        return this.yedek5;
    }

    public void setYedek5(double d2) {
        this.yedek5 = d2;
    }

    public String getYedek6() {
        return this.yedek6;
    }

    public void setYedek6(String str) {
        this.yedek6 = str;
    }

    public int getYedek7() {
        return this.yedek7;
    }

    public void setYedek7(int i2) {
        this.yedek7 = i2;
    }

    public int getYedek8() {
        return this.yedek8;
    }

    public void setYedek8(int i2) {
        this.yedek8 = i2;
    }

    public int getYedek9() {
        return this.yedek9;
    }

    public void setYedek9(int i2) {
        this.yedek9 = i2;
    }

    public String getYedek10() {
        return this.yedek10;
    }

    public void setYedek10(String str) {
        this.yedek10 = str;
    }

    public String getProjeKodu() {
        return this.projeKodu;
    }

    public void setProjeKodu(String str) {
        this.projeKodu = str;
    }

    public String getKayitYapanKul() {
        return this.kayitYapanKul;
    }

    public void setKayitYapanKul(String str) {
        this.kayitYapanKul = str;
    }

    public String getKayitTarihi() {
        return this.kayitTarihi;
    }

    public void setKayitTarihi(String str) {
        this.kayitTarihi = str;
    }

    public String getDuzeltmeYapanKul() {
        return this.duzeltmeYapanKul;
    }

    public void setDuzeltmeYapanKul(String str) {
        this.duzeltmeYapanKul = str;
    }

    public String getDuzeltmeTarihi() {
        return this.duzeltmeTarihi;
    }

    public void setDuzeltmeTarihi(String str) {
        this.duzeltmeTarihi = str;
    }

    public String getOnayTipi() {
        return this.onayTipi;
    }

    public void setOnayTipi(String str) {
        this.onayTipi = str;
    }

    public int getOnayNum() {
        return this.onayNum;
    }

    public void setOnayNum(int i2) {
        this.onayNum = i2;
    }

    public int getSira() {
        return this.sira;
    }

    public void setSira(int i2) {
        this.sira = i2;
    }

    public int getKaynak() {
        return this.kaynak;
    }

    public void setKaynak(int i2) {
        this.kaynak = i2;
    }

    public String getEntegrefkey() {
        return this.entegrefkey;
    }

    public void setEntegrefkey(String str) {
        this.entegrefkey = str;
    }

    public int getKrtSozMasInckeyNo() {
        return this.krtSozMasInckeyNo;
    }

    public void setKrtSozMasInckeyNo(int i2) {
        this.krtSozMasInckeyNo = i2;
    }

    public int getTaksit() {
        return this.taksit;
    }

    public void setTaksit(int i2) {
        this.taksit = i2;
    }

    public String getGecerli() {
        return this.gecerli;
    }

    public void setGecerli(String str) {
        this.gecerli = str;
    }

    public String getReferansKodu() {
        return this.referansKodu;
    }

    public void setReferansKodu(String str) {
        this.referansKodu = str;
    }

    public String getBankaEfektifTarih() {
        return this.bankaEfektifTarih;
    }

    public void setBankaEfektifTarih(String str) {
        this.bankaEfektifTarih = str;
    }

    public String getBankaYevmiyeAciklama() {
        return this.bankaYevmiyeAciklama;
    }

    public void setBankaYevmiyeAciklama(String str) {
        this.bankaYevmiyeAciklama = str;
    }

    public String getBankaAciklama() {
        return this.bankaAciklama;
    }

    public void setBankaAciklama(String str) {
        this.bankaAciklama = str;
    }

    public String getCariHareketAciklama() {
        return this.cariHareketAciklama;
    }

    public void setCariHareketAciklama(String str) {
        this.cariHareketAciklama = str;
    }

    public String getCariEfektifTarih() {
        return this.cariEfektifTarih;
    }

    public void setCariEfektifTarih(String str) {
        this.cariEfektifTarih = str;
    }

    public int getDovizTipi() {
        return this.dovizTipi;
    }

    public void setDovizTipi(int i2) {
        this.dovizTipi = i2;
    }

    public String getbFormCariKodu() {
        return this.bFormCariKodu;
    }

    public void setbFormCariKodu(String str) {
        this.bFormCariKodu = str;
    }

    public String getRaporKodu2() {
        return this.raporKodu2;
    }

    public void setRaporKodu2(String str) {
        this.raporKodu2 = str;
    }

    public int getKdvKalemAdedi() {
        return this.kdvKalemAdedi;
    }

    public void setKdvKalemAdedi(int i2) {
        this.kdvKalemAdedi = i2;
    }

    public String getBelgeTuru() {
        return this.belgeTuru;
    }

    public void setBelgeTuru(String str) {
        this.belgeTuru = str;
    }

    public List<SafeDepositKdvLine> getKdvKalems() {
        return this.kdvKalems;
    }

    public void setKdvKalems(List<SafeDepositKdvLine> list) {
        this.kdvKalems = list;
    }
}
