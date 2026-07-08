package com.proje.mobilesales.core.netsis.sendmodel.sales;

import android.text.TextUtils;
import com.google.common.base.Strings;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proje.mobilesales.core.netsis.sendmodel.recipt.MixedReceipt;
import java.util.List;
public final class ItemSlip {

    @SerializedName("MuhasebelesmisBelge")
    @Expose
    private boolean accountingDocument;

    @SerializedName("YedekKalems")
    @Expose
    private List<ItemSlipLine> alternativeLines;

    @SerializedName("BaglantiKontrol")
    @Expose
    private boolean connectControl;

    @SerializedName("MaliyetTipineGoreHesapla")
    @Expose
    private boolean costTypeCalculate;

    @SerializedName("KosulluHesapla")
    @Expose
    private boolean discountCalculate;

    @SerializedName("EIrsEkBilgi")
    @Expose
    private EWaybillInfo eWaybillInfo;

    @SerializedName("InternalObjectAddress")
    @Expose
    private int internalObjectAddress;

    @SerializedName("SonNumaraYazilsin")
    @Expose
    private boolean lastNumberWrite;

    @SerializedName("KalemAdedi")
    @Expose
    private int lineCount;

    @SerializedName("Kalems")
    @Expose
    private List<ItemSlipLine> lines;

    @SerializedName("Siralama")
    @Expose
    private String lining;

    @SerializedName("AcikBelgeTahsilat")
    @Expose
    private boolean openDocumentReceipt;

    @SerializedName("OtoVadeGunGetir")
    @Expose
    private boolean otoAverageDay;

    @SerializedName("OtomatikCevrimYapilsin")
    @Expose
    private boolean otoConvert;

    @SerializedName("OtoIskontoGetir")
    @Expose
    private boolean otoDiscountGet;

    @SerializedName("OtoBolgeFarkIskGetir")
    @Expose
    private boolean otoDistinctAverage;

    @SerializedName("OtomatikOdemeKoduGetir")
    @Expose
    private boolean otoPaymentCode;

    @SerializedName("OtomatikIslemTipiGetir")
    @Expose
    private boolean otoProcessType;

    @SerializedName("FiyatSistemineGoreHesapla")
    @Expose
    private boolean priceSystemCalculate;

    @SerializedName("TahsilatKalemAdedi")
    @Expose
    private int receiptLineCount;

    @SerializedName("Tahsilats")
    @Expose
    private List<MixedReceipt> receipts;

    @SerializedName("KayitliNumaraOtomatikGuncellensin")
    @Expose
    private boolean recordNumberUpdate;

    @SerializedName("RiskKontrol")
    @Expose
    private boolean riskControl;

    @SerializedName("EPostaGonderilsin")
    @Expose
    private boolean sendEposta;

    @SerializedName("Seri")
    @Expose
    private String serial;

    @SerializedName("SeriliHesapla")
    @Expose
    private boolean serialCalculate;

    @SerializedName("FatUst")
    @Expose
    private ItemSlipHeader slipHeader;

    @SerializedName("FaturaTip")
    @Expose
    private NetsisSlipType slipType;

    @SerializedName("StokKartinaGoreHesapla")
    @Expose
    private boolean stockCardCalculate;

    @SerializedName("TransactSupport")
    @Expose
    private boolean transactionSupport;

    @SerializedName("OtoNakliyeKatSayisiGetir")
    @Expose
    private boolean transportCount;

    @SerializedName("TahsilatKayitKullan")
    @Expose
    private boolean useReceiptRecord;

    private ItemSlip(Builder builder) {
        this.lines = null;
        this.alternativeLines = null;
        this.receipts = null;
        this.eWaybillInfo = null;
        setSerial(builder.serial);
        setSlipHeader(builder.slipHeader);
        setTransactionSupport(builder.transactionSupport);
        setAccountingDocument(builder.accountingDocument);
        setLineCount(builder.lineCount);
        setSlipType(builder.slipType);
        setLastNumberWrite(builder.lastNumberWrite);
        setOtoDiscountGet(builder.otoDiscountGet);
        setDiscountCalculate(builder.discountCalculate);
        setInternalObjectAddress(builder.internalObjectAddress);
        setSerialCalculate(builder.serialCalculate);
        setPriceSystemCalculate(builder.priceSystemCalculate);
        setStockCardCalculate(builder.stockCardCalculate);
        setOtoAverageDay(builder.otoAverageDay);
        setOtoProcessType(builder.otoProcessType);
        setOtoPaymentCode(builder.otoPaymentCode);
        setCostTypeCalculate(builder.costTypeCalculate);
        setOtoConvert(builder.otoConvert);
        setRecordNumberUpdate(builder.recordNumberUpdate);
        setLining(builder.lining);
        setSendEposta(builder.sendEposta);
        setTransportCount(builder.transportCount);
        setOtoDistinctAverage(builder.otoDistinctAverage);
        setRiskControl(builder.riskControl);
        setReceiptLineCount(builder.receiptLineCount);
        setUseReceiptRecord(builder.useReceiptRecord);
        setOpenDocumentReceipt(builder.openDocumentReceipt);
        setConnectControl(builder.connectControl);
        setLines(builder.lines);
        setAlternativeLines(builder.alternativeLines);
        setReceipts(builder.receipts);
        seteWaybillInfo(builder.eWaybillInfo);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(ItemSlip itemSlip) {
        Builder builder = new Builder();
        builder.serial = itemSlip.serial;
        builder.slipHeader = itemSlip.slipHeader;
        builder.transactionSupport = itemSlip.transactionSupport;
        builder.accountingDocument = itemSlip.accountingDocument;
        builder.lineCount = itemSlip.lineCount;
        builder.slipType = itemSlip.slipType;
        builder.lastNumberWrite = itemSlip.lastNumberWrite;
        builder.otoDiscountGet = itemSlip.otoDiscountGet;
        builder.discountCalculate = itemSlip.discountCalculate;
        builder.internalObjectAddress = itemSlip.internalObjectAddress;
        builder.serialCalculate = itemSlip.serialCalculate;
        builder.priceSystemCalculate = itemSlip.priceSystemCalculate;
        builder.stockCardCalculate = itemSlip.stockCardCalculate;
        builder.otoAverageDay = itemSlip.otoAverageDay;
        builder.otoProcessType = itemSlip.otoProcessType;
        builder.otoPaymentCode = itemSlip.otoPaymentCode;
        builder.costTypeCalculate = itemSlip.costTypeCalculate;
        builder.otoConvert = itemSlip.otoConvert;
        builder.recordNumberUpdate = itemSlip.recordNumberUpdate;
        builder.lining = itemSlip.lining;
        builder.sendEposta = itemSlip.sendEposta;
        builder.transportCount = itemSlip.transportCount;
        builder.otoDistinctAverage = itemSlip.otoDistinctAverage;
        builder.riskControl = itemSlip.riskControl;
        builder.receiptLineCount = itemSlip.receiptLineCount;
        builder.useReceiptRecord = itemSlip.useReceiptRecord;
        builder.openDocumentReceipt = itemSlip.openDocumentReceipt;
        builder.connectControl = itemSlip.connectControl;
        builder.lines = itemSlip.lines;
        builder.alternativeLines = itemSlip.alternativeLines;
        builder.receipts = itemSlip.receipts;
        builder.eWaybillInfo = itemSlip.eWaybillInfo;
        return builder;
    }

    public String getSerial() {
        return this.serial;
    }

    public void setSerial(String str) {
        this.serial = str;
    }

    public ItemSlipHeader getSlipHeader() {
        return this.slipHeader;
    }

    public void setSlipHeader(ItemSlipHeader itemSlipHeader) {
        this.slipHeader = itemSlipHeader;
    }

    public boolean isTransactionSupport() {
        return this.transactionSupport;
    }

    public void setTransactionSupport(boolean z) {
        this.transactionSupport = z;
    }

    public boolean isAccountingDocument() {
        return this.accountingDocument;
    }

    public void setAccountingDocument(boolean z) {
        this.accountingDocument = z;
    }

    public int getLineCount() {
        return this.lineCount;
    }

    public void setLineCount(int i2) {
        this.lineCount = i2;
    }

    public NetsisSlipType getSlipType() {
        return this.slipType;
    }

    public void setSlipType(NetsisSlipType netsisSlipType) {
        this.slipType = netsisSlipType;
    }

    public boolean isLastNumberWrite() {
        return this.lastNumberWrite;
    }

    public void setLastNumberWrite(boolean z) {
        this.lastNumberWrite = z;
    }

    public boolean isOtoDiscountGet() {
        return this.otoDiscountGet;
    }

    public void setOtoDiscountGet(boolean z) {
        this.otoDiscountGet = z;
    }

    public boolean isDiscountCalculate() {
        return this.discountCalculate;
    }

    public void setDiscountCalculate(boolean z) {
        this.discountCalculate = z;
    }

    public int getInternalObjectAddress() {
        return this.internalObjectAddress;
    }

    public void setInternalObjectAddress(int i2) {
        this.internalObjectAddress = i2;
    }

    public boolean isSerialCalculate() {
        return this.serialCalculate;
    }

    public void setSerialCalculate(boolean z) {
        this.serialCalculate = z;
    }

    public boolean isPriceSystemCalculate() {
        return this.priceSystemCalculate;
    }

    public void setPriceSystemCalculate(boolean z) {
        this.priceSystemCalculate = z;
    }

    public boolean isStockCardCalculate() {
        return this.stockCardCalculate;
    }

    public void setStockCardCalculate(boolean z) {
        this.stockCardCalculate = z;
    }

    public boolean isOtoAverageDay() {
        return this.otoAverageDay;
    }

    public void setOtoAverageDay(boolean z) {
        this.otoAverageDay = z;
    }

    public boolean isOtoProcessType() {
        return this.otoProcessType;
    }

    public void setOtoProcessType(boolean z) {
        this.otoProcessType = z;
    }

    public boolean isOtoPaymentCode() {
        return this.otoPaymentCode;
    }

    public void setOtoPaymentCode(boolean z) {
        this.otoPaymentCode = z;
    }

    public boolean isCostTypeCalculate() {
        return this.costTypeCalculate;
    }

    public void setCostTypeCalculate(boolean z) {
        this.costTypeCalculate = z;
    }

    public boolean isOtoConvert() {
        return this.otoConvert;
    }

    public void setOtoConvert(boolean z) {
        this.otoConvert = z;
    }

    public boolean isRecordNumberUpdate() {
        return this.recordNumberUpdate;
    }

    public void setRecordNumberUpdate(boolean z) {
        this.recordNumberUpdate = z;
    }

    public String getLining() {
        return this.lining;
    }

    public void setLining(String str) {
        this.lining = str;
    }

    public boolean isSendEposta() {
        return this.sendEposta;
    }

    public void setSendEposta(boolean z) {
        this.sendEposta = z;
    }

    public boolean isTransportCount() {
        return this.transportCount;
    }

    public void setTransportCount(boolean z) {
        this.transportCount = z;
    }

    public boolean isOtoDistinctAverage() {
        return this.otoDistinctAverage;
    }

    public void setOtoDistinctAverage(boolean z) {
        this.otoDistinctAverage = z;
    }

    public boolean isRiskControl() {
        return this.riskControl;
    }

    public void setRiskControl(boolean z) {
        this.riskControl = z;
    }

    public int getReceiptLineCount() {
        return this.receiptLineCount;
    }

    public void setReceiptLineCount(int i2) {
        this.receiptLineCount = i2;
    }

    public boolean isUseReceiptRecord() {
        return this.useReceiptRecord;
    }

    public void setUseReceiptRecord(boolean z) {
        this.useReceiptRecord = z;
    }

    public boolean isOpenDocumentReceipt() {
        return this.openDocumentReceipt;
    }

    public void setOpenDocumentReceipt(boolean z) {
        this.openDocumentReceipt = z;
    }

    public boolean isConnectControl() {
        return this.connectControl;
    }

    public void setConnectControl(boolean z) {
        this.connectControl = z;
    }

    public List<ItemSlipLine> getLines() {
        return this.lines;
    }

    public void setLines(List<ItemSlipLine> list) {
        this.lines = list;
    }

    public List<ItemSlipLine> getAlternativeLines() {
        return this.alternativeLines;
    }

    public void setAlternativeLines(List<ItemSlipLine> list) {
        this.alternativeLines = list;
    }

    public List<MixedReceipt> getReceipts() {
        return this.receipts;
    }

    public void setReceipts(List<MixedReceipt> list) {
        this.receipts = list;
    }

    public EWaybillInfo geteWaybillInfo() {
        return this.eWaybillInfo;
    }

    public void seteWaybillInfo(EWaybillInfo eWaybillInfo) {
        this.eWaybillInfo = eWaybillInfo;
    }

    public static final class Builder {
        private boolean accountingDocument;
        private List<ItemSlipLine> alternativeLines;
        private boolean connectControl;
        private boolean costTypeCalculate;
        private boolean discountCalculate;
        private EWaybillInfo eWaybillInfo;
        private int internalObjectAddress;
        private boolean lastNumberWrite;
        private int lineCount;
        private List<ItemSlipLine> lines;
        private String lining;
        private boolean openDocumentReceipt;
        private boolean otoAverageDay;
        private boolean otoConvert;
        private boolean otoDiscountGet;
        private boolean otoDistinctAverage;
        private boolean otoPaymentCode;
        private boolean otoProcessType;
        private boolean priceSystemCalculate;
        private int receiptLineCount;
        private List<MixedReceipt> receipts;
        private boolean recordNumberUpdate;
        private boolean riskControl;
        private boolean sendEposta;
        private String serial;
        private boolean serialCalculate;
        private ItemSlipHeader slipHeader;
        private NetsisSlipType slipType;
        private boolean stockCardCalculate;
        private boolean transactionSupport;
        private boolean transportCount;
        private boolean useReceiptRecord;

        private Builder() {
        }

        public Builder withSerial(String str) {
            this.serial = str;
            return this;
        }

        public Builder withSlipHeader(ItemSlipHeader itemSlipHeader) {
            this.slipHeader = itemSlipHeader;
            return this;
        }

        public Builder withTransactionSupport(boolean z) {
            this.transactionSupport = z;
            return this;
        }

        public Builder withAccountingDocument(boolean z) {
            this.accountingDocument = z;
            return this;
        }

        public Builder withLineCount(int i2) {
            this.lineCount = i2;
            return this;
        }

        public Builder withSlipType(NetsisSlipType netsisSlipType) {
            this.slipType = netsisSlipType;
            return this;
        }

        public Builder withLastNumberWrite(boolean z) {
            this.lastNumberWrite = z;
            return this;
        }

        public Builder withOtoDiscountGet(boolean z) {
            this.otoDiscountGet = z;
            return this;
        }

        public Builder withDiscountCalculate(boolean z) {
            this.discountCalculate = z;
            return this;
        }

        public Builder withInternalObjectAddress(int i2) {
            this.internalObjectAddress = i2;
            return this;
        }

        public Builder withSerialCalculate(boolean z) {
            this.serialCalculate = z;
            return this;
        }

        public Builder withPriceSystemCalculate(boolean z) {
            this.priceSystemCalculate = z;
            return this;
        }

        public Builder withStockCardCalculate(boolean z) {
            this.stockCardCalculate = z;
            return this;
        }

        public Builder withOtoAverageDay(boolean z) {
            this.otoAverageDay = z;
            return this;
        }

        public Builder withOtoProcessType(boolean z) {
            this.otoProcessType = z;
            return this;
        }

        public Builder withOtoPaymentCode(boolean z) {
            this.otoPaymentCode = z;
            return this;
        }

        public Builder withCostTypeCalculate(boolean z) {
            this.costTypeCalculate = z;
            return this;
        }

        public Builder withOtoConvert(boolean z) {
            this.otoConvert = z;
            return this;
        }

        public Builder withRecordNumberUpdate(boolean z) {
            this.recordNumberUpdate = z;
            return this;
        }

        public Builder withLining(String str) {
            this.lining = str;
            return this;
        }

        public Builder withSendEposta(boolean z) {
            this.sendEposta = z;
            return this;
        }

        public Builder withTransportCount(boolean z) {
            this.transportCount = z;
            return this;
        }

        public Builder withOtoDistinctAverage(boolean z) {
            this.otoDistinctAverage = z;
            return this;
        }

        public Builder withRiskControl(boolean z) {
            this.riskControl = z;
            return this;
        }

        public Builder withReceiptLineCount(int i2) {
            this.receiptLineCount = i2;
            return this;
        }

        public Builder withUseReceiptRecord(boolean z) {
            this.useReceiptRecord = z;
            return this;
        }

        public Builder withOpenDocumentReceipt(boolean z) {
            this.openDocumentReceipt = z;
            return this;
        }

        public Builder withConnectControl(boolean z) {
            this.connectControl = z;
            return this;
        }

        public Builder withLines(List<ItemSlipLine> list) {
            this.lines = list;
            return this;
        }

        public Builder withAlternativeLines(List<ItemSlipLine> list) {
            this.alternativeLines = list;
            return this;
        }

        public Builder withReceipts(List<MixedReceipt> list) {
            this.receipts = list;
            return this;
        }

        public Builder withEWaybillInfo(EWaybillInfo eWaybillInfo) {
            this.eWaybillInfo = eWaybillInfo;
            return this;
        }

        public ItemSlip build() {
            return new ItemSlip(this);
        }
    }

    public String generateFicheNumberForCalculate() {
        String str;
        str = "";
        if (TextUtils.isEmpty(getSlipHeader().getSlipNo())) {
            return Strings.padEnd(TextUtils.isEmpty(getSerial()) ? "" : getSerial(), 15, '0');
        }
        if (TextUtils.isEmpty(getSerial())) {
            if (getSlipHeader().getSlipNo().length() >= 3) {
                str = getSlipHeader().getSlipNo().substring(0, 3);
            }
        } else {
            str = getSerial();
        }
        return Strings.padEnd(str, 15, '0');
    }
}
