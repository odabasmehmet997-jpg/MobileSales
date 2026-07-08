package com.proje.mobilesales.core.netsis.sendmodel.cari;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.design.ErpCreator;
import com.proje.mobilesales.core.netsis.sendmodel.print.PrintSlipModel;
import com.proje.mobilesales.core.preferences.Preferences;
import com.proje.mobilesales.core.utils.DateAndTimeUtils;
import com.proje.mobilesales.core.utils.StringUtils;
import com.proje.mobilesales.features.customer.model.CustomerNew;

public class CariTemelBilgi {

    private String CM_RAP_TARIH;
    private String DETAY_KODU;
    private String ISKONTO_ORANI;
    private String NAKLIYE_KATSAYISI;
    private String RISK_SINIRI;
    private String acik1;
    private String acik2;
    private String acik3;
    private String agirlik_isk;
    private String bYedek1;
    private int branchCode;
    private String cYedek1;
    private String cYedek2;
    private String cardType;
    private String cari_adres;
    private String cari_ilce;
    private String carisk;
    private String ccrisk;
    private String city;
    private String code;
    private double credit;
    private int dOVIZTURU;
    private int dOVTIP;
    private double debit;
    private String definition;
    private String discountCode;
    private boolean dovizlimi;
    private String duzeltmeTarihi;
    private String duzeltmeYapanKul;
    private String emailAddr;
    private String fYedek1;
    private String fYedek2;
    private String fax2;
    private String faxNr;
    private String fiyatGrubu;
    private String grup_kodu;
    private String gsm2;
    private String gsm21;
    private String hesapTutmaSekli;
    private String iYedek1;
    private String isletme_Kodu;
    private String kayitTarihi;
    private String kayitYapanKul;
    private String kurFarkiAlac;
    private String kurFarkiBorc;
    private String lYedek1;
    private String liste_fiati;
    private String lokalDepo;
    private String mkod;
    private String musteriBaziKdv;
    private String odemeTipi;
    private int onayNum;
    private String onayTipi;
    private String plasiyerKodu;
    private String postCode;
    private String sAlternate1;
    private String sYedek2;
    private String sarisk;
    private String scrisk;
    private String specode;
    private String specode2;
    private String specode3;
    private String specode4;
    private String specode5;
    private String taxOffCode;
    private String taxOffice;
    private String telNrs2;
    private String telNrs3;
    private String telnrs1;
    private String teminati;
    private String ulke_kodu;
    private String updateKodu;
    private int vade_gunu;
    private String web;

    public CariTemelBilgi(PrintSlipModel customerNew2) {
    }

    public int getBranchCode() {
        return this.branchCode;
    }
    public void setBranchCode(int i2) {
        this.branchCode = i2;
    }
    public String getIsletme_Kodu() {
        return this.isletme_Kodu;
    }
    public void setIsletme_Kodu(String str) {
        this.isletme_Kodu = str;
    }
    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getTelnrs1() {
        return this.telnrs1;
    }

    public void setTelnrs1(String str) {
        this.telnrs1 = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getUlke_kodu() {
        return this.ulke_kodu;
    }

    public void setUlke_kodu(String str) {
        this.ulke_kodu = str;
    }

    public String getDefinition() {
        return this.definition;
    }

    public void setDefinition(String str) {
        this.definition = str;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String str) {
        this.cardType = str;
    }

    public String getGrup_kodu() {
        return this.grup_kodu;
    }

    public void setGrup_kodu(String str) {
        this.grup_kodu = str;
    }

    public String getSpecode() {
        return this.specode;
    }

    public void setSpecode(String str) {
        this.specode = str;
    }

    public String getSpecode2() {
        return this.specode2;
    }

    public void setSpecode2(String str) {
        this.specode2 = str;
    }

    public String getSpecode3() {
        return this.specode3;
    }

    public void setSpecode3(String str) {
        this.specode3 = str;
    }

    public String getSpecode4() {
        return this.specode4;
    }

    public void setSpecode4(String str) {
        this.specode4 = str;
    }

    public String getSpecode5() {
        return this.specode5;
    }

    public void setSpecode5(String str) {
        this.specode5 = str;
    }

    public String getCari_adres() {
        return this.cari_adres;
    }

    public void setCari_adres(String str) {
        this.cari_adres = str;
    }

    public String getCari_ilce() {
        return this.cari_ilce;
    }

    public void setCari_ilce(String str) {
        this.cari_ilce = str;
    }

    public String getTaxOffice() {
        return this.taxOffice;
    }

    public void setTaxOffice(String str) {
        this.taxOffice = str;
    }

    public String getTaxOffCode() {
        return this.taxOffCode;
    }

    public void setTaxOffCode(String str) {
        this.taxOffCode = str;
    }

    public String getFaxNr() {
        return this.faxNr;
    }

    public void setFaxNr(String str) {
        this.faxNr = str;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public void setPostCode(String str) {
        this.postCode = str;
    }

    public String getDETAY_KODU() {
        return this.DETAY_KODU;
    }

    public void setDETAY_KODU(String str) {
        this.DETAY_KODU = str;
    }

    public String getNAKLIYE_KATSAYISI() {
        return this.NAKLIYE_KATSAYISI;
    }

    public void setNAKLIYE_KATSAYISI(String str) {
        this.NAKLIYE_KATSAYISI = str;
    }

    public String getRISK_SINIRI() {
        return this.RISK_SINIRI;
    }

    public void setRISK_SINIRI(String str) {
        this.RISK_SINIRI = str;
    }

    public String getTeminati() {
        return this.teminati;
    }

    public void setTeminati(String str) {
        this.teminati = str;
    }

    public String getCarisk() {
        return this.carisk;
    }

    public void setCarisk(String str) {
        this.carisk = str;
    }

    public String getCcrisk() {
        return this.ccrisk;
    }

    public void setCcrisk(String str) {
        this.ccrisk = str;
    }

    public String getSarisk() {
        return this.sarisk;
    }

    public void setSarisk(String str) {
        this.sarisk = str;
    }

    public String getScrisk() {
        return this.scrisk;
    }

    public void setScrisk(String str) {
        this.scrisk = str;
    }

    public double getDebit() {
        return this.debit;
    }

    public void setDebit(double d2) {
        this.debit = d2;
    }

    public double getCredit() {
        return this.credit;
    }

    public void setCredit(double d2) {
        this.credit = d2;
    }

    public String getCM_RAP_TARIH() {
        return this.CM_RAP_TARIH;
    }

    public void setCM_RAP_TARIH(String str) {
        this.CM_RAP_TARIH = str;
    }

    public String getDiscountCode() {
        return this.discountCode;
    }

    public void setDiscountCode(String str) {
        this.discountCode = str;
    }

    public String getISKONTO_ORANI() {
        return this.ISKONTO_ORANI;
    }

    public void setISKONTO_ORANI(String str) {
        this.ISKONTO_ORANI = str;
    }

    public int getVade_gunu() {
        return this.vade_gunu;
    }

    public void setVade_gunu(int i2) {
        this.vade_gunu = i2;
    }

    public String getListe_fiati() {
        return this.liste_fiati;
    }

    public void setListe_fiati(String str) {
        this.liste_fiati = str;
    }

    public String getAcik1() {
        return this.acik1;
    }

    public void setAcik1(String str) {
        this.acik1 = str;
    }

    public String getAcik2() {
        return this.acik2;
    }

    public void setAcik2(String str) {
        this.acik2 = str;
    }

    public String getAcik3() {
        return this.acik3;
    }

    public void setAcik3(String str) {
        this.acik3 = str;
    }

    public String getMkod() {
        return this.mkod;
    }

    public void setMkod(String str) {
        this.mkod = str;
    }

    public int getdOVTIP() {
        return this.dOVTIP;
    }

    public void setdOVTIP(int i2) {
        this.dOVTIP = i2;
    }

    public int getdOVIZTURU() {
        return this.dOVIZTURU;
    }

    public void setdOVIZTURU(int i2) {
        this.dOVIZTURU = i2;
    }

    public String getHesapTutmaSekli() {
        return this.hesapTutmaSekli;
    }

    public void setHesapTutmaSekli(String str) {
        this.hesapTutmaSekli = str;
    }

    public boolean isDovizlimi() {
        return this.dovizlimi;
    }

    public void setDovizlimi(boolean z) {
        this.dovizlimi = z;
    }

    public String getUpdateKodu() {
        return this.updateKodu;
    }

    public void setUpdateKodu(String str) {
        this.updateKodu = str;
    }

    public String getPlasiyerKodu() {
        return this.plasiyerKodu;
    }

    public void setPlasiyerKodu(String str) {
        this.plasiyerKodu = str;
    }

    public String getLokalDepo() {
        return this.lokalDepo;
    }

    public void setLokalDepo(String str) {
        this.lokalDepo = str;
    }

    public String getEmailAddr() {
        return this.emailAddr;
    }

    public void setEmailAddr(String str) {
        this.emailAddr = str;
    }

    public String getWeb() {
        return this.web;
    }

    public void setWeb(String str) {
        this.web = str;
    }

    public String getKurFarkiBorc() {
        return this.kurFarkiBorc;
    }

    public void setKurFarkiBorc(String str) {
        this.kurFarkiBorc = str;
    }

    public String getKurFarkiAlac() {
        return this.kurFarkiAlac;
    }

    public void setKurFarkiAlac(String str) {
        this.kurFarkiAlac = str;
    }

    public String getsAlternate1() {
        return this.sAlternate1;
    }

    public void setsAlternate1(String str) {
        this.sAlternate1 = str;
    }

    public String getsYedek2() {
        return this.sYedek2;
    }

    public void setsYedek2(String str) {
        this.sYedek2 = str;
    }

    public String getfYedek1() {
        return this.fYedek1;
    }

    public void setfYedek1(String str) {
        this.fYedek1 = str;
    }

    public String getfYedek2() {
        return this.fYedek2;
    }

    public void setfYedek2(String str) {
        this.fYedek2 = str;
    }

    public String getcYedek1() {
        return this.cYedek1;
    }

    public void setcYedek1(String str) {
        this.cYedek1 = str;
    }

    public String getcYedek2() {
        return this.cYedek2;
    }

    public void setcYedek2(String str) {
        this.cYedek2 = str;
    }

    public String getbYedek1() {
        return this.bYedek1;
    }

    public void setbYedek1(String str) {
        this.bYedek1 = str;
    }

    public String getiYedek1() {
        return this.iYedek1;
    }

    public void setiYedek1(String str) {
        this.iYedek1 = str;
    }

    public String getlYedek1() {
        return this.lYedek1;
    }

    public void setlYedek1(String str) {
        this.lYedek1 = str;
    }

    public String getFiyatGrubu() {
        return this.fiyatGrubu;
    }

    public void setFiyatGrubu(String str) {
        this.fiyatGrubu = str;
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

    public String getOdemeTipi() {
        return this.odemeTipi;
    }

    public void setOdemeTipi(String str) {
        this.odemeTipi = str;
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

    public String getMusteriBaziKdv() {
        return this.musteriBaziKdv;
    }

    public void setMusteriBaziKdv(String str) {
        this.musteriBaziKdv = str;
    }

    public String getAgirlik_isk() {
        return this.agirlik_isk;
    }

    public void setAgirlik_isk(String str) {
        this.agirlik_isk = str;
    }

    public String getTelNrs2() {
        return this.telNrs2;
    }

    public void setTelNrs2(String str) {
        this.telNrs2 = str;
    }

    public String getTelNrs3() {
        return this.telNrs3;
    }

    public void setTelNrs3(String str) {
        this.telNrs3 = str;
    }

    public String getFax2() {
        return this.fax2;
    }

    public void setFax2(String str) {
        this.fax2 = str;
    }

    public String getGsm21() {
        return this.gsm21;
    }

    public void setGsm21(String str) {
        this.gsm21 = str;
    }

    public String getGsm2() {
        return this.gsm2;
    }

    public void setGsm2(String str) {
        this.gsm2 = str;
    }

    public CariTemelBilgi() {
    }

    public CariTemelBilgi(CustomerNew customerNew) {
        ((Preferences.NetsisUserSettings) ErpCreator.getInstance().getmBaseErp().getUserSettings()).getBranchCode();
        setCode(customerNew.getCode().toString());
        setDefinition(customerNew.getName().toString());
        setSpecode(customerNew.getSpeCode().toString());
        setSpecode2(customerNew.getSpeCode2().toString());
        setSpecode3(customerNew.getSpeCode3().toString());
        setSpecode4(customerNew.getSpeCode4().toString());
        setSpecode5(customerNew.getSpeCode5().toString());
        setGrup_kodu(customerNew.getGroupCode().toString());
        setTaxOffice(customerNew.getTaxOffice().toString());
        setTaxOffCode(customerNew.getTaxNo().toString());
        setEmailAddr(customerNew.getEmail().toString());
        setTelnrs1(customerNew.getTel1().toString());
        setTelNrs2(customerNew.getTel2().toString());
        setFaxNr(customerNew.getFax().toString());
        setCari_adres(customerNew.getAddress1().toString());
        setPostCode(customerNew.getZipCode().toString());
        setOdemeTipi(StringUtils.convertIntToString(customerNew.getPayType().getLogicalRef()));
        setVade_gunu(StringUtils.convertStringToInt(customerNew.getVade().toString()));
        setISKONTO_ORANI(customerNew.getIskontoOran().toString());
        setCity(customerNew.getCity().toString());
        setCari_ilce(customerNew.getTown().toString());
        setKayitTarihi(DateAndTimeUtils.getSqlDate(Boolean.FALSE));
        setKayitYapanKul(((Preferences.NetsisUserSettings) ErpCreator.getInstance().getmBaseErp().getUserSettings()).getUsername());
        setUlke_kodu(customerNew.getCountry().getCode());
    }
}
