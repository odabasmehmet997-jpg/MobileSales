package com.proje.mobilesales.core.netsis.sendmodel.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ItemSlipLine {

    @SerializedName("AlisKDVOran")
    @Expose
    private double alisKDVOran;

    @SerializedName("Ambarkabulno")
    @Expose
    private String ambarkabulno;

    @SerializedName("BaglantiNo")
    @Expose
    private int baglantiNo;

    @SerializedName("BirimPuan")
    @Expose
    private int birimPuan;

    @SerializedName("BolgeFark")
    @Expose
    private double bolgeFark;

    @SerializedName("BrCevrim1")
    @Expose
    private double brCevrim1;

    @SerializedName("BrCevrim2")
    @Expose
    private double brCevrim2;

    @SerializedName("CEVRIM")
    @Expose
    private double cEVRIM;

    @SerializedName("C_Yedek6")
    @Expose
    private String cYedek6;

    @SerializedName("DEPO_KODU")
    @Expose
    private int dEPOKODU;

    @SerializedName("DOVIZ_TURU")
    @Expose
    private int dOVIZTURU;

    @SerializedName("DOVTIP")
    @Expose
    private int dOVTIP;

    @SerializedName("D_YEDEK10")
    @Expose
    private String dYEDEK10;

    @SerializedName("DovizAdi")
    @Expose
    private String dovizAdi;

    @SerializedName("Ecza_fat_tip")
    @Expose
    private int eczaFatTip;

    @SerializedName("Ekalan")
    @Expose
    private String ekalan;

    @SerializedName("Ekalan1")
    @Expose
    private String ekalan1;

    @SerializedName("Ekalanneden")
    @Expose
    private String ekalanneden;

    @SerializedName("EsnekMi")
    @Expose
    private boolean esnekMi;

    @SerializedName("Fiat_birimi")
    @Expose
    private int fiatBirimi;

    @SerializedName("Firmadovmal")
    @Expose
    private double firmadovmal;

    @SerializedName("Firmadovtip")
    @Expose
    private int firmadovtip;

    @SerializedName("Firmadovtut")
    @Expose
    private double firmadovtut;

    @SerializedName("Fiyatlar1")
    @Expose(serialize = false)
    private double fiyatlar1;

    @SerializedName("Fiyatlar2")
    @Expose(serialize = false)
    private double fiyatlar2;

    @SerializedName("Fiyatlar3")
    @Expose(serialize = false)
    private double fiyatlar3;

    @SerializedName("Fiyatlar4")
    @Expose(serialize = false)
    private double fiyatlar4;

    @SerializedName("Fiyatlar5")
    @Expose(serialize = false)
    private double fiyatlar5;

    @SerializedName("Fiyatlar6")
    @Expose(serialize = false)
    private double fiyatlar6;

    @SerializedName("Fiyatlar7")
    @Expose(serialize = false)
    private double fiyatlar7;

    @SerializedName("Gir_Depo_Kodu")
    @Expose
    private int girDepoKodu;

    @SerializedName("Genislik")
    @Expose
    private Double height;

    @SerializedName("IncKeyNo")
    @Expose
    private int incKeyNo;

    @SerializedName("IncKeyNo2")
    @Expose
    private int incKeyNo2;

    @SerializedName("Irsaliyeno")
    @Expose
    private String irsaliyeno;

    @SerializedName("Irsaliyetar")
    @Expose
    private String irsaliyetar;

    @SerializedName("Isk_Flag")
    @Expose
    private int iskFlag;

    @SerializedName("KKMalF")
    @Expose
    private double kKMalF;

    @SerializedName("KalemGenIskOran1")
    @Expose
    private double kalemGenIskOran1;

    @SerializedName("KalemGenIskOran2")
    @Expose
    private double kalemGenIskOran2;

    @SerializedName("KalemGenIskOran3")
    @Expose
    private double kalemGenIskOran3;

    @SerializedName("Kilit")
    @Expose
    private String kilit;

    @SerializedName("Koli_Inc")
    @Expose
    private int koliInc;

    @SerializedName("KoliStok")
    @Expose
    private boolean koliStok;

    @SerializedName("Kul_Mik")
    @Expose
    private double kulMik;

    @SerializedName("Kull1S")
    @Expose
    private String kull1S;

    @SerializedName("Boy")
    @Expose
    private Double length;

    @SerializedName("Listeno")
    @Expose
    private String listeno;

    @SerializedName("MALADI")
    @Expose
    private String mALADI;

    @SerializedName("Mamulmu")
    @Expose
    private String mamulmu;

    @SerializedName("MuhasebeKodu")
    @Expose
    private String muhasebeKodu;

    @SerializedName("OBR1")
    @Expose
    private String oBR1;

    @SerializedName("OBR2")
    @Expose
    private String oBR2;

    @SerializedName("OBR3")
    @Expose
    private String oBR3;

    @SerializedName("Olcubr")
    @Expose
    private int olcubr;

    @SerializedName("OtvFlag")
    @Expose
    private int otvFlag;

    @SerializedName("Otvtut")
    @Expose
    private double otvtut;

    @SerializedName("PROMASYON_KODU")
    @Expose
    private int pROMASYONKODU;

    @SerializedName("Payda_1")
    @Expose
    private double payda1;

    @SerializedName("Plasiyer_Kodu")
    @Expose
    private String plasiyerKodu;

    @SerializedName("ProjeKodu")
    @Expose
    private String projeKodu;

    @SerializedName("PuanDeger")
    @Expose
    private double puanDeger;

    @SerializedName("Redneden")
    @Expose
    private int redneden;

    @SerializedName("ReferansKodu")
    @Expose
    private String referansKodu;

    @SerializedName("SATREF_KOD")
    @Expose
    private String sATREFKOD;

    @SerializedName("SONGIRBFIAT")
    @Expose
    private double sONGIRBFIAT;

    @SerializedName("STOK_FIYATKODU")
    @Expose
    private String sTOKFIYATKODU;

    @SerializedName("STOK_GRKOD")
    @Expose
    private String sTOKGRKOD;

    @SerializedName("STra_ACIK")
    @Expose
    private String sTraACIK;

    @SerializedName("STra_BF")
    @Expose
    private double sTraBF;

    @SerializedName("STra_BGTIP")
    @Expose
    private String sTraBGTIP;

    @SerializedName("STra_CARI_KOD")
    @Expose
    private String sTraCARIKOD;

    @SerializedName("STra_DOVFIAT")
    @Expose
    private double sTraDOVFIAT;

    @SerializedName("STra_DOVTIP")
    @Expose
    private int sTraDOVTIP;

    @SerializedName("STra_DovizAdi")
    @Expose
    private String sTraDovizAdi;

    @SerializedName("STra_FATIRSNO")
    @Expose
    private String sTraFATIRSNO;

    @SerializedName("STra_FTIRSIP")
    @Expose
    private String sTraFTIRSIP;

    @SerializedName("STra_GC")
    @Expose
    private String sTraGC;

    @SerializedName("STra_GCMIK")
    @Expose
    private double sTraGCMIK;

    @SerializedName("STra_GCMIK2")
    @Expose
    private double sTraGCMIK2;

    @SerializedName("STra_HTUR")
    @Expose
    private String sTraHTUR;

    @SerializedName("STra_IAF")
    @Expose
    private double sTraIAF;

    @SerializedName("STra_KDV")
    @Expose
    private double sTraKDV;

    @SerializedName("STra_KOD1")
    @Expose
    private String sTraKOD1;

    @SerializedName("STra_KOD2")
    @Expose
    private String sTraKOD2;

    @SerializedName("STra_MALFISK")
    @Expose
    private double sTraMALFISK;

    @SerializedName("STra_NF")
    @Expose
    private double sTraNF;

    @SerializedName("STra_ODEGUN")
    @Expose
    private int sTraODEGUN;

    @SerializedName("STra_SIPKONT")
    @Expose
    private int sTraSIPKONT;

    @SerializedName("STra_SIPNUM")
    @Expose
    private String sTraSIPNUM;

    @SerializedName("STra_SIP_TURU")
    @Expose
    private String sTraSIPTURU;

    @SerializedName("STra_SatIsk")
    @Expose
    private double sTraSatIsk;

    @SerializedName("STra_SatIsk2")
    @Expose
    private double sTraSatIsk2;

    @SerializedName("STra_SatIsk3")
    @Expose
    private double sTraSatIsk3;

    @SerializedName("STra_SatIsk4")
    @Expose
    private double sTraSatIsk4;

    @SerializedName("STra_SatIsk5")
    @Expose
    private double sTraSatIsk5;

    @SerializedName("STra_SatIsk6")
    @Expose
    private double sTraSatIsk6;

    @SerializedName("STra_TAR")
    @Expose
    private String sTraTAR;

    @SerializedName("STra_testar")
    @Expose
    private String sTraTestar;

    @SerializedName("S_Yedek2")
    @Expose
    private String sYedek2;

    @SerializedName("SabitDepKod")
    @Expose
    private int sabitDepKod;

    @SerializedName("Sat_IskTipleri1")
    @Expose
    private int satIskTipleri1;

    @SerializedName("Sat_IskTipleri2")
    @Expose
    private int satIskTipleri2;

    @SerializedName("Sat_IskTipleri3")
    @Expose
    private int satIskTipleri3;

    @SerializedName("Sat_IskTipleri4")
    @Expose
    private int satIskTipleri4;

    @SerializedName("Sat_IskTipleri5")
    @Expose
    private int satIskTipleri5;

    @SerializedName("Sat_IskTipleri6")
    @Expose
    private int satIskTipleri6;

    @SerializedName("Satici_Kodu")
    @Expose
    private String saticiKodu;

    @SerializedName("SatisKDVOran")
    @Expose
    private double satisKDVOran;

    @SerializedName("SatisKilit")
    @Expose
    private String satisKilit;

    @SerializedName("SeriSayisi")
    @Expose
    private int seriSayisi;

    @SerializedName("SeriTakibi")
    @Expose
    private String seriTakibi;

    @SerializedName("SipTesKont")
    @Expose
    private int sipTesKont;

    @SerializedName("Sira")
    @Expose
    private int sira;

    @SerializedName("Stok_IsletmeKod")
    @Expose
    private int stokIsletmeKod;

    @SerializedName("StokKodu")
    @Expose
    private String stokKodu;

    @SerializedName("Stok_SubeKod")
    @Expose
    private int stokSubeKod;

    @SerializedName("Stra_Exportmik")
    @Expose
    private double straExportmik;

    @SerializedName("Stra_Exporttype")
    @Expose
    private int straExporttype;

    @SerializedName("Stra_FiiliTar")
    @Expose
    private String straFiiliTar;

    @SerializedName("Stra_FiyatBirimi")
    @Expose
    private int straFiyatBirimi;

    @SerializedName("Stra_FiyatTar")
    @Expose
    private String straFiyatTar;

    @SerializedName("Stra_IrsKont")
    @Expose
    private int straIrsKont;

    @SerializedName("Stra_KosTar")
    @Expose
    private String straKosTar;

    @SerializedName("Stra_KosulK")
    @Expose
    private String straKosulK;

    @SerializedName("Stra_OTVFiat")
    @Expose
    private double straOTVFiat;

    @SerializedName("Stra_OnayNum")
    @Expose
    private int straOnayNum;

    @SerializedName("Stra_OnayTipi")
    @Expose
    private String straOnayTipi;

    @SerializedName("Stra_Otv")
    @Expose
    private double straOtv;

    @SerializedName("Stra_SubeKodu")
    @Expose
    private int straSubeKodu;

    @SerializedName("TesMFMik")
    @Expose
    private double tesMFMik;

    @SerializedName("TesMik")
    @Expose
    private double tesMik;

    @SerializedName("Tur")
    @Expose
    private String tur;

    @SerializedName("Update_Kodu")
    @Expose
    private String updateKodu;

    @SerializedName("Vadetar")
    @Expose
    private String vadetar;

    @SerializedName("Hacim")
    @Expose
    private Double volume;

    @SerializedName("Birim_Agirlik")
    @Expose
    private Double weight;

    @SerializedName("En")
    @Expose
    private Double width;

    @SerializedName("YapKod")
    @Expose
    private String yapKod;

    @SerializedName("Yed_Bf")
    @Expose
    private double yedBf;

    @SerializedName("KalemSeri")
    @Expose
    private List<ItemSlipSerialLine> kalemSeri = null;

    @SerializedName("Asorti")
    @Expose
    private List<ItemSlipLinesAssortment> asorti = null;

    @SerializedName("SatirBaziAciks")
    @Expose
    private List<String> satirBaziAciks = null;

    @SerializedName("BRCevrims")
    @Expose
    private List<Double> bRCevrims = null;

    public Double getWeight() {
        return this.weight;
    }

    public void setWeight(Double d2) {
        this.weight = d2;
    }

    public Double getVolume() {
        return this.volume;
    }

    public void setVolume(Double d2) {
        this.volume = d2;
    }

    public Double getWidth() {
        return this.width;
    }

    public void setWidth(Double d2) {
        this.width = d2;
    }

    public Double getLength() {
        return this.length;
    }

    public void setLength(Double d2) {
        this.length = d2;
    }

    public Double getHeight() {
        return this.height;
    }

    public void setHeight(Double d2) {
        this.height = d2;
    }

    public String getDovizAdi() {
        return this.dovizAdi;
    }

    public void setDovizAdi(String str) {
        this.dovizAdi = str;
    }

    public String getSTraDovizAdi() {
        return this.sTraDovizAdi;
    }

    public void setSTraDovizAdi(String str) {
        this.sTraDovizAdi = str;
    }

    public List<ItemSlipSerialLine> getKalemSeri() {
        return this.kalemSeri;
    }

    public void setKalemSeri(List<ItemSlipSerialLine> list) {
        this.kalemSeri = list;
    }

    public List<ItemSlipLinesAssortment> getAsorti() {
        return this.asorti;
    }

    public void setAsorti(List<ItemSlipLinesAssortment> list) {
        this.asorti = list;
    }

    public String getStokKodu() {
        return this.stokKodu;
    }

    public void setStokKodu(String str) {
        this.stokKodu = str;
    }

    public int getSira() {
        return this.sira;
    }

    public void setSira(int i2) {
        this.sira = i2;
    }

    public String getSTraFATIRSNO() {
        return this.sTraFATIRSNO;
    }

    public void setSTraFATIRSNO(String str) {
        this.sTraFATIRSNO = str;
    }

    public double getSTraGCMIK() {
        return this.sTraGCMIK;
    }

    public void setSTraGCMIK(double d2) {
        this.sTraGCMIK = d2;
    }

    public double getSTraGCMIK2() {
        return this.sTraGCMIK2;
    }

    public void setSTraGCMIK2(double d2) {
        this.sTraGCMIK2 = d2;
    }

    public double getCEVRIM() {
        return this.cEVRIM;
    }

    public void setCEVRIM(double d2) {
        this.cEVRIM = d2;
    }

    public String getSTraTAR() {
        return this.sTraTAR;
    }

    public void setSTraTAR(String str) {
        this.sTraTAR = str;
    }

    public double getSTraNF() {
        return this.sTraNF;
    }

    public void setSTraNF(double d2) {
        this.sTraNF = d2;
    }

    public double getSTraBF() {
        return this.sTraBF;
    }

    public void setSTraBF(double d2) {
        this.sTraBF = d2;
    }

    public double getSTraIAF() {
        return this.sTraIAF;
    }

    public void setSTraIAF(double d2) {
        this.sTraIAF = d2;
    }

    public double getSTraKDV() {
        return this.sTraKDV;
    }

    public void setSTraKDV(double d2) {
        this.sTraKDV = d2;
    }

    public double getSTraSatIsk() {
        return this.sTraSatIsk;
    }

    public void setSTraSatIsk(double d2) {
        this.sTraSatIsk = d2;
    }

    public double getSTraSatIsk2() {
        return this.sTraSatIsk2;
    }

    public void setSTraSatIsk2(double d2) {
        this.sTraSatIsk2 = d2;
    }

    public double getSTraMALFISK() {
        return this.sTraMALFISK;
    }

    public void setSTraMALFISK(double d2) {
        this.sTraMALFISK = d2;
    }

    public String getSTraHTUR() {
        return this.sTraHTUR;
    }

    public void setSTraHTUR(String str) {
        this.sTraHTUR = str;
    }

    public int getSTraDOVTIP() {
        return this.sTraDOVTIP;
    }

    public void setSTraDOVTIP(int i2) {
        this.sTraDOVTIP = i2;
    }

    public int getPROMASYONKODU() {
        return this.pROMASYONKODU;
    }

    public void setPROMASYONKODU(int i2) {
        this.pROMASYONKODU = i2;
    }

    public double getSTraDOVFIAT() {
        return this.sTraDOVFIAT;
    }

    public void setSTraDOVFIAT(double d2) {
        this.sTraDOVFIAT = d2;
    }

    public int getSTraODEGUN() {
        return this.sTraODEGUN;
    }

    public void setSTraODEGUN(int i2) {
        this.sTraODEGUN = i2;
    }

    public String getSTraKOD1() {
        return this.sTraKOD1;
    }

    public void setSTraKOD1(String str) {
        this.sTraKOD1 = str;
    }

    public String getSTraKOD2() {
        return this.sTraKOD2;
    }

    public void setSTraKOD2(String str) {
        this.sTraKOD2 = str;
    }

    public String getSTraSIPNUM() {
        return this.sTraSIPNUM;
    }

    public void setSTraSIPNUM(String str) {
        this.sTraSIPNUM = str;
    }

    public String getSTraSIPTURU() {
        return this.sTraSIPTURU;
    }

    public void setSTraSIPTURU(String str) {
        this.sTraSIPTURU = str;
    }

    public String getPlasiyerKodu() {
        return this.plasiyerKodu;
    }

    public void setPlasiyerKodu(String str) {
        this.plasiyerKodu = str;
    }

    public String getEkalanneden() {
        return this.ekalanneden;
    }

    public void setEkalanneden(String str) {
        this.ekalanneden = str;
    }

    public String getEkalan() {
        return this.ekalan;
    }

    public void setEkalan(String str) {
        this.ekalan = str;
    }

    public String getEkalan1() {
        return this.ekalan1;
    }

    public void setEkalan1(String str) {
        this.ekalan1 = str;
    }

    public double getStraOtv() {
        return this.straOtv;
    }

    public void setStraOtv(double d2) {
        this.straOtv = d2;
    }

    public int getRedneden() {
        return this.redneden;
    }

    public void setRedneden(int i2) {
        this.redneden = i2;
    }

    public int getSTraSIPKONT() {
        return this.sTraSIPKONT;
    }

    public void setSTraSIPKONT(int i2) {
        this.sTraSIPKONT = i2;
    }

    public String getAmbarkabulno() {
        return this.ambarkabulno;
    }

    public void setAmbarkabulno(String str) {
        this.ambarkabulno = str;
    }

    public int getFirmadovtip() {
        return this.firmadovtip;
    }

    public void setFirmadovtip(int i2) {
        this.firmadovtip = i2;
    }

    public double getFirmadovtut() {
        return this.firmadovtut;
    }

    public void setFirmadovtut(double d2) {
        this.firmadovtut = d2;
    }

    public double getFirmadovmal() {
        return this.firmadovmal;
    }

    public void setFirmadovmal(double d2) {
        this.firmadovmal = d2;
    }

    public String getUpdateKodu() {
        return this.updateKodu;
    }

    public void setUpdateKodu(String str) {
        this.updateKodu = str;
    }

    public String getIrsaliyeno() {
        return this.irsaliyeno;
    }

    public void setIrsaliyeno(String str) {
        this.irsaliyeno = str;
    }

    public String getIrsaliyetar() {
        return this.irsaliyetar;
    }

    public void setIrsaliyetar(String str) {
        this.irsaliyetar = str;
    }

    public String getStraKosulK() {
        return this.straKosulK;
    }

    public void setStraKosulK(String str) {
        this.straKosulK = str;
    }

    public int getEczaFatTip() {
        return this.eczaFatTip;
    }

    public void setEczaFatTip(int i2) {
        this.eczaFatTip = i2;
    }

    public String getSTraTestar() {
        return this.sTraTestar;
    }

    public void setSTraTestar(String str) {
        this.sTraTestar = str;
    }

    public int getOlcubr() {
        return this.olcubr;
    }

    public void setOlcubr(int i2) {
        this.olcubr = i2;
    }

    public String getVadetar() {
        return this.vadetar;
    }

    public void setVadetar(String str) {
        this.vadetar = str;
    }

    public String getListeno() {
        return this.listeno;
    }

    public void setListeno(String str) {
        this.listeno = str;
    }

    public int getBaglantiNo() {
        return this.baglantiNo;
    }

    public void setBaglantiNo(int i2) {
        this.baglantiNo = i2;
    }

    public double getBrCevrim1() {
        return this.brCevrim1;
    }

    public void setBrCevrim1(double d2) {
        this.brCevrim1 = d2;
    }

    public double getBrCevrim2() {
        return this.brCevrim2;
    }

    public void setBrCevrim2(double d2) {
        this.brCevrim2 = d2;
    }

    public double getYedBf() {
        return this.yedBf;
    }

    public void setYedBf(double d2) {
        this.yedBf = d2;
    }

    public String getSTraBGTIP() {
        return this.sTraBGTIP;
    }

    public void setSTraBGTIP(String str) {
        this.sTraBGTIP = str;
    }

    public String getMuhasebeKodu() {
        return this.muhasebeKodu;
    }

    public void setMuhasebeKodu(String str) {
        this.muhasebeKodu = str;
    }

    public String getReferansKodu() {
        return this.referansKodu;
    }

    public void setReferansKodu(String str) {
        this.referansKodu = str;
    }

    public String getCYedek6() {
        return this.cYedek6;
    }

    public void setCYedek6(String str) {
        this.cYedek6 = str;
    }

    public String getProjeKodu() {
        return this.projeKodu;
    }

    public void setProjeKodu(String str) {
        this.projeKodu = str;
    }

    public String getSTraFTIRSIP() {
        return this.sTraFTIRSIP;
    }

    public void setSTraFTIRSIP(String str) {
        this.sTraFTIRSIP = str;
    }

    public String getSTraCARIKOD() {
        return this.sTraCARIKOD;
    }

    public void setSTraCARIKOD(String str) {
        this.sTraCARIKOD = str;
    }

    public String getSTraGC() {
        return this.sTraGC;
    }

    public void setSTraGC(String str) {
        this.sTraGC = str;
    }

    public int getDEPOKODU() {
        return this.dEPOKODU;
    }

    public void setDEPOKODU(int i2) {
        this.dEPOKODU = i2;
    }

    public int getGirDepoKodu() {
        return this.girDepoKodu;
    }

    public void setGirDepoKodu(int i2) {
        this.girDepoKodu = i2;
    }

    public String getSTraACIK() {
        return this.sTraACIK;
    }

    public void setSTraACIK(String str) {
        this.sTraACIK = str;
    }

    public String getStraOnayTipi() {
        return this.straOnayTipi;
    }

    public void setStraOnayTipi(String str) {
        this.straOnayTipi = str;
    }

    public int getStraOnayNum() {
        return this.straOnayNum;
    }

    public void setStraOnayNum(int i2) {
        this.straOnayNum = i2;
    }

    public int getStraSubeKodu() {
        return this.straSubeKodu;
    }

    public void setStraSubeKodu(int i2) {
        this.straSubeKodu = i2;
    }

    public int getStokIsletmeKod() {
        return this.stokIsletmeKod;
    }

    public void setStokIsletmeKod(int i2) {
        this.stokIsletmeKod = i2;
    }

    public int getStokSubeKod() {
        return this.stokSubeKod;
    }

    public void setStokSubeKod(int i2) {
        this.stokSubeKod = i2;
    }

    public int getStraExporttype() {
        return this.straExporttype;
    }

    public void setStraExporttype(int i2) {
        this.straExporttype = i2;
    }

    public int getIncKeyNo() {
        return this.incKeyNo;
    }

    public void setIncKeyNo(int i2) {
        this.incKeyNo = i2;
    }

    public int getIncKeyNo2() {
        return this.incKeyNo2;
    }

    public void setIncKeyNo2(int i2) {
        this.incKeyNo2 = i2;
    }

    public double getTesMik() {
        return this.tesMik;
    }

    public void setTesMik(double d2) {
        this.tesMik = d2;
    }

    public double getTesMFMik() {
        return this.tesMFMik;
    }

    public void setTesMFMik(double d2) {
        this.tesMFMik = d2;
    }

    public String getMALADI() {
        return this.mALADI;
    }

    public void setMALADI(String str) {
        this.mALADI = str;
    }

    public String getSATREFKOD() {
        return this.sATREFKOD;
    }

    public void setSATREFKOD(String str) {
        this.sATREFKOD = str;
    }

    public String getSTOKGRKOD() {
        return this.sTOKGRKOD;
    }

    public void setSTOKGRKOD(String str) {
        this.sTOKGRKOD = str;
    }

    public String getSTOKFIYATKODU() {
        return this.sTOKFIYATKODU;
    }

    public void setSTOKFIYATKODU(String str) {
        this.sTOKFIYATKODU = str;
    }

    public double getSONGIRBFIAT() {
        return this.sONGIRBFIAT;
    }

    public void setSONGIRBFIAT(double d2) {
        this.sONGIRBFIAT = d2;
    }

    public String getOBR1() {
        return this.oBR1;
    }

    public void setOBR1(String str) {
        this.oBR1 = str;
    }

    public String getOBR2() {
        return this.oBR2;
    }

    public void setOBR2(String str) {
        this.oBR2 = str;
    }

    public String getOBR3() {
        return this.oBR3;
    }

    public void setOBR3(String str) {
        this.oBR3 = str;
    }

    public int getSabitDepKod() {
        return this.sabitDepKod;
    }

    public void setSabitDepKod(int i2) {
        this.sabitDepKod = i2;
    }

    public int getDOVTIP() {
        return this.dOVTIP;
    }

    public void setDOVTIP(int i2) {
        this.dOVTIP = i2;
    }

    public int getDOVIZTURU() {
        return this.dOVIZTURU;
    }

    public void setDOVIZTURU(int i2) {
        this.dOVIZTURU = i2;
    }

    public double getFiyatlar1() {
        return this.fiyatlar1;
    }

    public void setFiyatlar1(double d2) {
        this.fiyatlar1 = d2;
    }

    public double getFiyatlar2() {
        return this.fiyatlar2;
    }

    public void setFiyatlar2(double d2) {
        this.fiyatlar2 = d2;
    }

    public double getFiyatlar3() {
        return this.fiyatlar3;
    }

    public void setFiyatlar3(double d2) {
        this.fiyatlar3 = d2;
    }

    public double getFiyatlar4() {
        return this.fiyatlar4;
    }

    public void setFiyatlar4(double d2) {
        this.fiyatlar4 = d2;
    }

    public double getFiyatlar5() {
        return this.fiyatlar5;
    }

    public void setFiyatlar5(double d2) {
        this.fiyatlar5 = d2;
    }

    public double getFiyatlar6() {
        return this.fiyatlar6;
    }

    public void setFiyatlar6(double d2) {
        this.fiyatlar6 = d2;
    }

    public double getFiyatlar7() {
        return this.fiyatlar7;
    }

    public void setFiyatlar7(double d2) {
        this.fiyatlar7 = d2;
    }

    public String getKilit() {
        return this.kilit;
    }

    public void setKilit(String str) {
        this.kilit = str;
    }

    public double getSatisKDVOran() {
        return this.satisKDVOran;
    }

    public void setSatisKDVOran(double d2) {
        this.satisKDVOran = d2;
    }

    public double getAlisKDVOran() {
        return this.alisKDVOran;
    }

    public void setAlisKDVOran(double d2) {
        this.alisKDVOran = d2;
    }

    public int getIskFlag() {
        return this.iskFlag;
    }

    public void setIskFlag(int i2) {
        this.iskFlag = i2;
    }

    public int getSipTesKont() {
        return this.sipTesKont;
    }

    public void setSipTesKont(int i2) {
        this.sipTesKont = i2;
    }

    public String getMamulmu() {
        return this.mamulmu;
    }

    public void setMamulmu(String str) {
        this.mamulmu = str;
    }

    public String getSeriTakibi() {
        return this.seriTakibi;
    }

    public void setSeriTakibi(String str) {
        this.seriTakibi = str;
    }

    public double getStraExportmik() {
        return this.straExportmik;
    }

    public void setStraExportmik(double d2) {
        this.straExportmik = d2;
    }

    public double getSTraSatIsk3() {
        return this.sTraSatIsk3;
    }

    public void setSTraSatIsk3(double d2) {
        this.sTraSatIsk3 = d2;
    }

    public String getStraKosTar() {
        return this.straKosTar;
    }

    public void setStraKosTar(String str) {
        this.straKosTar = str;
    }

    public String getStraFiyatTar() {
        return this.straFiyatTar;
    }

    public void setStraFiyatTar(String str) {
        this.straFiyatTar = str;
    }

    public String getSYedek2() {
        return this.sYedek2;
    }

    public void setSYedek2(String str) {
        this.sYedek2 = str;
    }

    public double getKulMik() {
        return this.kulMik;
    }

    public void setKulMik(double d2) {
        this.kulMik = d2;
    }

    public int getFiatBirimi() {
        return this.fiatBirimi;
    }

    public void setFiatBirimi(int i2) {
        this.fiatBirimi = i2;
    }

    public String getSaticiKodu() {
        return this.saticiKodu;
    }

    public void setSaticiKodu(String str) {
        this.saticiKodu = str;
    }

    public int getSatIskTipleri1() {
        return this.satIskTipleri1;
    }

    public void setSatIskTipleri1(int i2) {
        this.satIskTipleri1 = i2;
    }

    public int getSatIskTipleri2() {
        return this.satIskTipleri2;
    }

    public void setSatIskTipleri2(int i2) {
        this.satIskTipleri2 = i2;
    }

    public int getSatIskTipleri3() {
        return this.satIskTipleri3;
    }

    public void setSatIskTipleri3(int i2) {
        this.satIskTipleri3 = i2;
    }

    public int getKoliInc() {
        return this.koliInc;
    }

    public void setKoliInc(int i2) {
        this.koliInc = i2;
    }

    public boolean isKoliStok() {
        return this.koliStok;
    }

    public void setKoliStok(boolean z) {
        this.koliStok = z;
    }

    public String getTur() {
        return this.tur;
    }

    public void setTur(String str) {
        this.tur = str;
    }

    public String getStraFiiliTar() {
        return this.straFiiliTar;
    }

    public void setStraFiiliTar(String str) {
        this.straFiiliTar = str;
    }

    public int getBirimPuan() {
        return this.birimPuan;
    }

    public void setBirimPuan(int i2) {
        this.birimPuan = i2;
    }

    public double getPuanDeger() {
        return this.puanDeger;
    }

    public void setPuanDeger(double d2) {
        this.puanDeger = d2;
    }

    public double getKalemGenIskOran1() {
        return this.kalemGenIskOran1;
    }

    public void setKalemGenIskOran1(double d2) {
        this.kalemGenIskOran1 = d2;
    }

    public double getKalemGenIskOran2() {
        return this.kalemGenIskOran2;
    }

    public void setKalemGenIskOran2(double d2) {
        this.kalemGenIskOran2 = d2;
    }

    public double getKalemGenIskOran3() {
        return this.kalemGenIskOran3;
    }

    public void setKalemGenIskOran3(double d2) {
        this.kalemGenIskOran3 = d2;
    }

    public int getOtvFlag() {
        return this.otvFlag;
    }

    public void setOtvFlag(int i2) {
        this.otvFlag = i2;
    }

    public double getOtvtut() {
        return this.otvtut;
    }

    public void setOtvtut(double d2) {
        this.otvtut = d2;
    }

    public double getSTraSatIsk4() {
        return this.sTraSatIsk4;
    }

    public void setSTraSatIsk4(double d2) {
        this.sTraSatIsk4 = d2;
    }

    public double getSTraSatIsk5() {
        return this.sTraSatIsk5;
    }

    public void setSTraSatIsk5(double d2) {
        this.sTraSatIsk5 = d2;
    }

    public double getSTraSatIsk6() {
        return this.sTraSatIsk6;
    }

    public void setSTraSatIsk6(double d2) {
        this.sTraSatIsk6 = d2;
    }

    public String getKull1S() {
        return this.kull1S;
    }

    public void setKull1S(String str) {
        this.kull1S = str;
    }

    public double getKKMalF() {
        return this.kKMalF;
    }

    public void setKKMalF(double d2) {
        this.kKMalF = d2;
    }

    public int getStraFiyatBirimi() {
        return this.straFiyatBirimi;
    }

    public void setStraFiyatBirimi(int i2) {
        this.straFiyatBirimi = i2;
    }

    public int getStraIrsKont() {
        return this.straIrsKont;
    }

    public void setStraIrsKont(int i2) {
        this.straIrsKont = i2;
    }

    public String getSatisKilit() {
        return this.satisKilit;
    }

    public void setSatisKilit(String str) {
        this.satisKilit = str;
    }

    public double getPayda1() {
        return this.payda1;
    }

    public void setPayda1(double d2) {
        this.payda1 = d2;
    }

    public String getDYEDEK10() {
        return this.dYEDEK10;
    }

    public void setDYEDEK10(String str) {
        this.dYEDEK10 = str;
    }

    public int getSatIskTipleri4() {
        return this.satIskTipleri4;
    }

    public void setSatIskTipleri4(int i2) {
        this.satIskTipleri4 = i2;
    }

    public int getSatIskTipleri5() {
        return this.satIskTipleri5;
    }

    public void setSatIskTipleri5(int i2) {
        this.satIskTipleri5 = i2;
    }

    public int getSatIskTipleri6() {
        return this.satIskTipleri6;
    }

    public void setSatIskTipleri6(int i2) {
        this.satIskTipleri6 = i2;
    }

    public String getYapKod() {
        return this.yapKod;
    }

    public void setYapKod(String str) {
        this.yapKod = str;
    }

    public boolean isEsnekMi() {
        return this.esnekMi;
    }

    public void setEsnekMi(boolean z) {
        this.esnekMi = z;
    }

    public int getSeriSayisi() {
        return this.seriSayisi;
    }

    public void setSeriSayisi(int i2) {
        this.seriSayisi = i2;
    }

    public double getStraOTVFiat() {
        return this.straOTVFiat;
    }

    public void setStraOTVFiat(double d2) {
        this.straOTVFiat = d2;
    }

    public double getBolgeFark() {
        return this.bolgeFark;
    }

    public void setBolgeFark(double d2) {
        this.bolgeFark = d2;
    }

    public List<String> getSatirBaziAciks() {
        return this.satirBaziAciks;
    }

    public void setSatirBaziAciks(List<String> list) {
        this.satirBaziAciks = list;
    }

    public List<Double> getBRCevrims() {
        return this.bRCevrims;
    }

    public void setBRCevrims(List<Double> list) {
        this.bRCevrims = list;
    }
}
