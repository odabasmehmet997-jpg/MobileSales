package com.proje.mobilesales.core.netsis.sendmodel.sales;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.netsis.sendmodel.NetsisOutputPlaceType;

public class ItemSlipHeader {

    @SerializedName("AMBHARTUR")
    @Expose
    private NetsisWarehouseTransType aMBHARTUR;

    @SerializedName("FiiliTarih")
    @Expose
    private String actualDate;

    @SerializedName("YEDEK")
    @Expose
    private String alternate;

    @SerializedName("EKACK1")
    @Expose
    private String appendixExplanation1;

    @SerializedName("EKACK10")
    @Expose
    private String appendixExplanation10;

    @SerializedName("EKACK11")
    @Expose
    private String appendixExplanation11;

    @SerializedName("EKACK12")
    @Expose
    private String appendixExplanation12;

    @SerializedName("EKACK13")
    @Expose
    private String appendixExplanation13;

    @SerializedName("EKACK14")
    @Expose
    private String appendixExplanation14;

    @SerializedName("EKACK15")
    @Expose
    private String appendixExplanation15;

    @SerializedName("EKACK16")
    @Expose
    private String appendixExplanation16;

    @SerializedName("EKACK2")
    @Expose
    private String appendixExplanation2;

    @SerializedName("EKACK3")
    @Expose
    private String appendixExplanation3;

    @SerializedName("EKACK4")
    @Expose
    private String appendixExplanation4;

    @SerializedName("EKACK5")
    @Expose
    private String appendixExplanation5;

    @SerializedName("EKACK6")
    @Expose
    private String appendixExplanation6;

    @SerializedName("EKACK7")
    @Expose
    private String appendixExplanation7;

    @SerializedName("EKACK8")
    @Expose
    private String appendixExplanation8;

    @SerializedName("EKACK9")
    @Expose
    private String appendixExplanation9;

    @SerializedName("BFORM")
    @Expose
    private String bForm;

    @SerializedName("Sube_Kodu")
    @Expose
    private int branchCode;

    @SerializedName("CikisYeri")
    @Expose
    private NetsisOutputPlaceType cikisYeri;

    @SerializedName("KOD1")
    @Expose
    private String code1;

    @SerializedName("KOD2")
    @Expose
    private String code2;

    @SerializedName("OnayNum")
    @Expose
    private int confirmationNumber;

    @SerializedName("OnayTipi")
    @Expose
    private String confirmationType;

    @SerializedName("BaglantiNo")
    @Expose
    private int connectionNo;

    @SerializedName("DovizTut")
    @Expose
    private double currencyTotal;

    @SerializedName("DOVIZTIP")
    @Expose
    private int currencyType;

    @SerializedName("CariKod")
    @Expose
    private String customerCode;

    @SerializedName("CARI_KOD2")
    @Expose
    private String customerCode2;

    @SerializedName("Tarih")
    @Expose
    private String date;

    @SerializedName("Degissin")
    @Expose
    private NetsisInvoicePriceType degissin;

    @SerializedName("SAT_ISKT")
    @Expose
    private double detailDiscount;

    @SerializedName("KOSULKODU")
    @Expose
    private String discountCode;

    @SerializedName("KOSULTARIHI")
    @Expose
    private String discountDate;

    @SerializedName("BOLGE_FARKI_ISK")
    @Expose
    private double distinctExpDisc;

    @SerializedName("EIrsaliye")
    @Expose
    private boolean eDespatch;

    @SerializedName("EfaturaCarisiMi")
    @Expose
    private boolean eInvoiceCustomer;

    @SerializedName("EFatOzelKod")
    @Expose
    private int eInvoiceSpeCode;

    @SerializedName("EXFIILITARIH")
    @Expose
    private String exActualDate;

    @SerializedName("EXGUMTARIH")
    @Expose
    private String exDutyDate;

    @SerializedName("EXGUMRUKNO")
    @Expose
    private String exDutyNo;

    @SerializedName("Aciklama")
    @Expose
    private String explanation;

    @SerializedName("EXPORTREFNO")
    @Expose
    private String exportRefNo;

    @SerializedName("EXPORTTYPE")
    @Expose
    private int exportType;

    @SerializedName("MFAZ_ISKT")
    @Expose
    private double fazDiscount;

    @SerializedName("GCKOD_GIRIS")
    @Expose
    private int gcCodeInput;

    @SerializedName("GCKOD_CIKIS")
    @Expose
    private int gcCodeOutput;

    @SerializedName("GEN_ISK1T")
    @Expose
    private double generalDiscount;

    @SerializedName("GEN_ISK2T")
    @Expose
    private double generalDiscount2;

    @SerializedName("GEN_ISK3T")
    @Expose
    private double generalDiscount3;

    @SerializedName("GEN_ISK1O")
    @Expose
    private double generalDiscountRatio;

    @SerializedName("GEN_ISK2O")
    @Expose
    private double generalDiscountRatio2;

    @SerializedName("GEN_ISK3O")
    @Expose
    private double generalDiscountRatio3;

    @SerializedName("GENISK1TIP")
    @Expose
    private int generalDiscountType;

    @SerializedName("GENISK2TIP")
    @Expose
    private int generalDiscountType2;

    @SerializedName("GENISK3TIP")
    @Expose
    private int generalDiscountType3;

    @SerializedName("GENELTOPLAM")
    @Expose
    private double generalTotal;

    @SerializedName("GIB_FATIRS_NO")
    @Expose
    private String gibInvoiceNo;

    @SerializedName("BRUTTUTAR")
    @Expose
    private double grossTotal;

    @SerializedName("KDV_DAHILMI")
    @Expose
    private boolean inclueVat;

    @SerializedName("ENTEGRE_TRH")
    @Expose
    private String integrationDate;

    @SerializedName("FAT_ALTM1")
    @Expose
    private double invDown1;

    @SerializedName("FAT_ALTM2")
    @Expose
    private double invDown2;

    @SerializedName("KOSVADEGUNU")
    @Expose
    private int kosAvarageDate;

    @SerializedName("KS_KODU")
    @Expose
    private String ksCode;

    @SerializedName("SIRANO")
    @Expose
    private int lineNo;

    @SerializedName("TIPI")
    @Expose
    private NetsisInvoiceType netsisInvoiceType;

    @SerializedName("SIPARIS_NUMARASI")
    @Expose
    private String orderNumber;

    @SerializedName("SIPARIS_TEST")
    @Expose
    private String orderTest;

    @SerializedName("OTV")
    @Expose
    private double otv;

    @SerializedName("OdemeKodu")
    @Expose
    private String paymentCode;

    @SerializedName("ODEMETARIHI")
    @Expose
    private String paymentDate;

    @SerializedName("ODEMEGUNU")
    @Expose
    private int paymentDay;

    @SerializedName("PLA_KODU")
    @Expose
    private String plaCode;

    @SerializedName("FIYATTARIHI")
    @Expose
    private String priceDate;

    @SerializedName("Proje_Kodu")
    @Expose
    private String projectCode;

    @SerializedName("YUVARLAMA")
    @Expose
    private double rounding;

    @SerializedName("S_Yedek1")
    @Expose
    private String sAlternate1;

    @SerializedName("FATIRS_NO")
    @Expose
    private String slipNo;

    @SerializedName("Tip")
    @Expose
    private NetsisSlipType slipType;

    @SerializedName("TopGirDepo")
    @Expose
    private int totalInputWarehouse;

    @SerializedName("TopDepo")
    @Expose
    private int totalWarehouse;

    @SerializedName("BrMaliyet")
    @Expose
    private double unitCost;

    @SerializedName("KDV")
    @Expose
    private double vat;

    @SerializedName("KDV1O")
    @Expose
    private double vat10;

    @SerializedName("KDV2O")
    @Expose
    private double vat2O;

    @SerializedName("KDV2T")
    @Expose
    private double vat2T;

    @SerializedName("KDV3O")
    @Expose
    private double vat3O;

    @SerializedName("KDV3T")
    @Expose
    private double vat3T;

    @SerializedName("KDV4O")
    @Expose
    private double vat4O;

    @SerializedName("KDV4T")
    @Expose
    private double vat4T;

    @SerializedName("KDV5O")
    @Expose
    private double vat5O;

    @SerializedName("KDV5T")
    @Expose
    private double vat5T;

    @SerializedName("KDV1T")
    @Expose
    private double vatIt;

    public int getBranchCode() {
        return this.branchCode;
    }

    public void setBranchCode(int i2) {
        this.branchCode = i2;
    }

    public String getCustomerCode() {
        return this.customerCode;
    }

    public void setCustomerCode(String str) {
        this.customerCode = str;
    }

    public String getSlipNo() {
        return this.slipNo;
    }

    public void setSlipNo(String str) {
        this.slipNo = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public NetsisSlipType getSlipType() {
        return this.slipType;
    }

    public void setSlipType(NetsisSlipType netsisSlipType) {
        this.slipType = netsisSlipType;
    }

    public String getCode1() {
        return this.code1;
    }

    public void setCode1(String str) {
        this.code1 = str;
    }

    public String getAlternate() {
        return this.alternate;
    }

    public void setAlternate(String str) {
        this.alternate = str;
    }

    public String getCode2() {
        return this.code2;
    }

    public void setCode2(String str) {
        this.code2 = str;
    }

    public NetsisInvoiceType getNetsisInvoiceType() {
        return this.netsisInvoiceType;
    }

    public void setNetsisInvoiceType(NetsisInvoiceType netsisInvoiceType) {
        this.netsisInvoiceType = netsisInvoiceType;
    }

    public String getExplanation() {
        return this.explanation;
    }

    public void setExplanation(String str) {
        this.explanation = str;
    }

    public double getGrossTotal() {
        return this.grossTotal;
    }

    public void setGrossTotal(double d2) {
        this.grossTotal = d2;
    }

    public double getVat() {
        return this.vat;
    }

    public void setVat(double d2) {
        this.vat = d2;
    }

    public double getCurrencyTotal() {
        return this.currencyTotal;
    }

    public void setCurrencyTotal(double d2) {
        this.currencyTotal = d2;
    }

    public double getDetailDiscount() {
        return this.detailDiscount;
    }

    public void setDetailDiscount(double d2) {
        this.detailDiscount = d2;
    }

    public double getGeneralTotal() {
        return this.generalTotal;
    }

    public void setGeneralTotal(double d2) {
        this.generalTotal = d2;
    }

    public double getRounding() {
        return this.rounding;
    }

    public void setRounding(double d2) {
        this.rounding = d2;
    }

    public double getFazDiscount() {
        return this.fazDiscount;
    }

    public void setFazDiscount(double d2) {
        this.fazDiscount = d2;
    }

    public double getGeneralDiscount() {
        return this.generalDiscount;
    }

    public void setGeneralDiscount(double d2) {
        this.generalDiscount = d2;
    }

    public double getGeneralDiscount2() {
        return this.generalDiscount2;
    }

    public void setGeneralDiscount2(double d2) {
        this.generalDiscount2 = d2;
    }

    public double getGeneralDiscount3() {
        return this.generalDiscount3;
    }

    public void setGeneralDiscount3(double d2) {
        this.generalDiscount3 = d2;
    }

    public double getInvDown1() {
        return this.invDown1;
    }

    public void setInvDown1(double d2) {
        this.invDown1 = d2;
    }

    public double getInvDown2() {
        return this.invDown2;
    }

    public void setInvDown2(double d2) {
        this.invDown2 = d2;
    }

    public String getKsCode() {
        return this.ksCode;
    }

    public void setKsCode(String str) {
        this.ksCode = str;
    }

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(String str) {
        this.orderNumber = str;
    }

    public int getPaymentDay() {
        return this.paymentDay;
    }

    public void setPaymentDay(int i2) {
        this.paymentDay = i2;
    }

    public String getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(String str) {
        this.paymentDate = str;
    }

    public String getIntegrationDate() {
        return this.integrationDate;
    }

    public void setIntegrationDate(String str) {
        this.integrationDate = str;
    }

    public boolean isInclueVat() {
        return this.inclueVat;
    }

    public void setInclueVat(boolean z) {
        this.inclueVat = z;
    }

    public String getOrderTest() {
        return this.orderTest;
    }

    public void setOrderTest(String str) {
        this.orderTest = str;
    }

    public String getCustomerCode2() {
        return this.customerCode2;
    }

    public void setCustomerCode2(String str) {
        this.customerCode2 = str;
    }

    public String getPlaCode() {
        return this.plaCode;
    }

    public void setPlaCode(String str) {
        this.plaCode = str;
    }

    public int getLineNo() {
        return this.lineNo;
    }

    public void setLineNo(int i2) {
        this.lineNo = i2;
    }

    public int getCurrencyType() {
        return this.currencyType;
    }

    public void setCurrencyType(int i2) {
        this.currencyType = i2;
    }

    public String getProjectCode() {
        return this.projectCode;
    }

    public void setProjectCode(String str) {
        this.projectCode = str;
    }

    public String getDiscountCode() {
        return this.discountCode;
    }

    public void setDiscountCode(String str) {
        this.discountCode = str;
    }

    public String getPriceDate() {
        return this.priceDate;
    }

    public void setPriceDate(String str) {
        this.priceDate = str;
    }

    public String getDiscountDate() {
        return this.discountDate;
    }

    public void setDiscountDate(String str) {
        this.discountDate = str;
    }

    public int getGeneralDiscountType() {
        return this.generalDiscountType;
    }

    public void setGeneralDiscountType(int i2) {
        this.generalDiscountType = i2;
    }

    public int getGeneralDiscountType2() {
        return this.generalDiscountType2;
    }

    public void setGeneralDiscountType2(int i2) {
        this.generalDiscountType2 = i2;
    }

    public int getGeneralDiscountType3() {
        return this.generalDiscountType3;
    }

    public void setGeneralDiscountType3(int i2) {
        this.generalDiscountType3 = i2;
    }

    public int getExportType() {
        return this.exportType;
    }

    public void setExportType(int i2) {
        this.exportType = i2;
    }

    public String getExDutyNo() {
        return this.exDutyNo;
    }

    public void setExDutyNo(String str) {
        this.exDutyNo = str;
    }

    public String getExDutyDate() {
        return this.exDutyDate;
    }

    public void setExDutyDate(String str) {
        this.exDutyDate = str;
    }

    public String getExActualDate() {
        return this.exActualDate;
    }

    public void setExActualDate(String str) {
        this.exActualDate = str;
    }

    public String getExportRefNo() {
        return this.exportRefNo;
    }

    public void setExportRefNo(String str) {
        this.exportRefNo = str;
    }

    public NetsisWarehouseTransType getaMBHARTUR() {
        return this.aMBHARTUR;
    }

    public void setaMBHARTUR(NetsisWarehouseTransType netsisWarehouseTransType) {
        this.aMBHARTUR = netsisWarehouseTransType;
    }

    public String getConfirmationType() {
        return this.confirmationType;
    }

    public void setConfirmationType(String str) {
        this.confirmationType = str;
    }

    public int getConfirmationNumber() {
        return this.confirmationNumber;
    }

    public void setConfirmationNumber(int i2) {
        this.confirmationNumber = i2;
    }

    public int getGcCodeInput() {
        return this.gcCodeInput;
    }

    public void setGcCodeInput(int i2) {
        this.gcCodeInput = i2;
    }

    public int getGcCodeOutput() {
        return this.gcCodeOutput;
    }

    public void setGcCodeOutput(int i2) {
        this.gcCodeOutput = i2;
    }

    public String getAppendixExplanation1() {
        return this.appendixExplanation1;
    }

    public void setAppendixExplanation1(String str) {
        this.appendixExplanation1 = str;
    }

    public String getAppendixExplanation2() {
        return this.appendixExplanation2;
    }

    public void setAppendixExplanation2(String str) {
        this.appendixExplanation2 = str;
    }

    public String getAppendixExplanation3() {
        return this.appendixExplanation3;
    }

    public void setAppendixExplanation3(String str) {
        this.appendixExplanation3 = str;
    }

    public String getAppendixExplanation4() {
        return this.appendixExplanation4;
    }

    public void setAppendixExplanation4(String str) {
        this.appendixExplanation4 = str;
    }

    public String getAppendixExplanation5() {
        return this.appendixExplanation5;
    }

    public void setAppendixExplanation5(String str) {
        this.appendixExplanation5 = str;
    }

    public String getAppendixExplanation6() {
        return this.appendixExplanation6;
    }

    public void setAppendixExplanation6(String str) {
        this.appendixExplanation6 = str;
    }

    public String getAppendixExplanation7() {
        return this.appendixExplanation7;
    }

    public void setAppendixExplanation7(String str) {
        this.appendixExplanation7 = str;
    }

    public String getAppendixExplanation8() {
        return this.appendixExplanation8;
    }

    public void setAppendixExplanation8(String str) {
        this.appendixExplanation8 = str;
    }

    public String getAppendixExplanation9() {
        return this.appendixExplanation9;
    }

    public void setAppendixExplanation9(String str) {
        this.appendixExplanation9 = str;
    }

    public String getAppendixExplanation10() {
        return this.appendixExplanation10;
    }

    public void setAppendixExplanation10(String str) {
        this.appendixExplanation10 = str;
    }

    public String getAppendixExplanation11() {
        return this.appendixExplanation11;
    }

    public void setAppendixExplanation11(String str) {
        this.appendixExplanation11 = str;
    }

    public String getAppendixExplanation12() {
        return this.appendixExplanation12;
    }

    public void setAppendixExplanation12(String str) {
        this.appendixExplanation12 = str;
    }

    public String getAppendixExplanation13() {
        return this.appendixExplanation13;
    }

    public void setAppendixExplanation13(String str) {
        this.appendixExplanation13 = str;
    }

    public String getAppendixExplanation14() {
        return this.appendixExplanation14;
    }

    public void setAppendixExplanation14(String str) {
        this.appendixExplanation14 = str;
    }

    public String getAppendixExplanation15() {
        return this.appendixExplanation15;
    }

    public void setAppendixExplanation15(String str) {
        this.appendixExplanation15 = str;
    }

    public String getAppendixExplanation16() {
        return this.appendixExplanation16;
    }

    public void setAppendixExplanation16(String str) {
        this.appendixExplanation16 = str;
    }

    public double getGeneralDiscountRatio() {
        return this.generalDiscountRatio;
    }

    public void setGeneralDiscountRatio(double d2) {
        this.generalDiscountRatio = d2;
    }

    public double getGeneralDiscountRatio2() {
        return this.generalDiscountRatio2;
    }

    public void setGeneralDiscountRatio2(double d2) {
        this.generalDiscountRatio2 = d2;
    }

    public double getGeneralDiscountRatio3() {
        return this.generalDiscountRatio3;
    }

    public void setGeneralDiscountRatio3(double d2) {
        this.generalDiscountRatio3 = d2;
    }

    public NetsisOutputPlaceType getCikisYeri() {
        return this.cikisYeri;
    }

    public void setCikisYeri(NetsisOutputPlaceType netsisOutputPlaceType) {
        this.cikisYeri = netsisOutputPlaceType;
    }

    public NetsisInvoicePriceType getDegissin() {
        return this.degissin;
    }

    public void setDegissin(NetsisInvoicePriceType netsisInvoicePriceType) {
        this.degissin = netsisInvoicePriceType;
    }

    public int getTotalInputWarehouse() {
        return this.totalInputWarehouse;
    }

    public void setTotalInputWarehouse(int i2) {
        this.totalInputWarehouse = i2;
    }

    public int getTotalWarehouse() {
        return this.totalWarehouse;
    }

    public void setTotalWarehouse(int i2) {
        this.totalWarehouse = i2;
    }

    public String getActualDate() {
        return this.actualDate;
    }

    public void setActualDate(String str) {
        this.actualDate = str;
    }

    public String getPaymentCode() {
        return this.paymentCode;
    }

    public void setPaymentCode(String str) {
        this.paymentCode = str;
    }

    public String getsAlternate1() {
        return this.sAlternate1;
    }

    public void setsAlternate1(String str) {
        this.sAlternate1 = str;
    }

    public double getOtv() {
        return this.otv;
    }

    public void setOtv(double d2) {
        this.otv = d2;
    }

    public int getKosAvarageDate() {
        return this.kosAvarageDate;
    }

    public void setKosAvarageDate(int i2) {
        this.kosAvarageDate = i2;
    }

    public double getUnitCost() {
        return this.unitCost;
    }

    public void setUnitCost(double d2) {
        this.unitCost = d2;
    }

    public double getDistinctExpDisc() {
        return this.distinctExpDisc;
    }

    public void setDistinctExpDisc(double d2) {
        this.distinctExpDisc = d2;
    }

    public double getVatIt() {
        return this.vatIt;
    }

    public void setVatIt(double d2) {
        this.vatIt = d2;
    }

    public double getVat10() {
        return this.vat10;
    }

    public void setVat10(double d2) {
        this.vat10 = d2;
    }

    public double getVat2O() {
        return this.vat2O;
    }

    public void setVat2O(double d2) {
        this.vat2O = d2;
    }

    public double getVat2T() {
        return this.vat2T;
    }

    public void setVat2T(double d2) {
        this.vat2T = d2;
    }

    public double getVat3O() {
        return this.vat3O;
    }

    public void setVat3O(double d2) {
        this.vat3O = d2;
    }

    public double getVat3T() {
        return this.vat3T;
    }

    public void setVat3T(double d2) {
        this.vat3T = d2;
    }

    public double getVat4O() {
        return this.vat4O;
    }

    public void setVat4O(double d2) {
        this.vat4O = d2;
    }

    public double getVat4T() {
        return this.vat4T;
    }

    public void setVat4T(double d2) {
        this.vat4T = d2;
    }

    public double getVat5O() {
        return this.vat5O;
    }

    public void setVat5O(double d2) {
        this.vat5O = d2;
    }

    public double getVat5T() {
        return this.vat5T;
    }

    public void setVat5T(double d2) {
        this.vat5T = d2;
    }

    public boolean iseInvoiceCustomer() {
        return this.eInvoiceCustomer;
    }

    public void seteInvoiceCustomer(boolean z) {
        this.eInvoiceCustomer = z;
    }

    public String getGibInvoiceNo() {
        return this.gibInvoiceNo;
    }

    public void setGibInvoiceNo(String str) {
        this.gibInvoiceNo = str;
    }

    public int getConnectionNo() {
        return this.connectionNo;
    }

    public void setConnectionNo(int i2) {
        this.connectionNo = i2;
    }

    public int geteInvoiceSpeCode() {
        return this.eInvoiceSpeCode;
    }

    public void seteInvoiceSpeCode(int i2) {
        this.eInvoiceSpeCode = i2;
    }

    public boolean iseDespatch() {
        return this.eDespatch;
    }

    public void seteDespatch(boolean z) {
        this.eDespatch = z;
    }

    public String getbForm() {
        return this.bForm;
    }

    public void setbForm(String str) {
        this.bForm = str;
    }
}
