package com.proje.mobilesales.core.netsis.sendmodel.print;

public class PrintSlipValues {
    private double amountTotal;
    private int converttren;
    private int count;
    private int detailRowCount;
    private double grossWeight;
    private boolean ilkSayfa;
    private boolean isShowFooter;
    private boolean isShowHeader;
    private int lineSize;
    private double nakliyeYekun;
    private double netVolume;
    private double pageAmountTotal;
    private double pageTotal;
    private int printLineCount;
    private int sayi;
    private double[] unitTotals;
    private double weight;
    private String printvalue = "";
    private String headerStr = "";
    private String detailStr = "";
    private String footerStr = "";
    private String summaryStr = "";
    private String[] returnValues = new String[1];
    private double[] vats = new double[5];
    private double[] vatsamount = new double[5];
    private double[] totvats = new double[5];
    private double[] totvatsamount = new double[5];

    public PrintSlipValues(boolean z, boolean z2, int i2, int i3) {
        setUnitTotals(new double[]{0.0d, 0.0d, 0.0d});
        this.sayi = 1;
        this.count = 0;
        this.pageTotal = 0.0d;
        this.nakliyeYekun = 0.0d;
        this.ilkSayfa = true;
        this.isShowFooter = z2;
        this.isShowHeader = z;
        this.converttren = i2;
        setDetailRowCount(i3);
    }

    public String getPrintvalue() {
        return this.printvalue;
    }

    public void setPrintvalue(String str) {
        this.printvalue = str;
    }

    public String getHeaderStr() {
        return this.headerStr;
    }

    public void setHeaderStr(String str) {
        this.headerStr = str;
    }

    public String getDetailStr() {
        return this.detailStr;
    }

    public void setDetailStr(String str) {
        this.detailStr = str;
    }

    public String getFooterStr() {
        return this.footerStr;
    }

    public void setFooterStr(String str) {
        this.footerStr = str;
    }

    public String getSummaryStr() {
        return this.summaryStr;
    }

    public void setSummaryStr(String str) {
        this.summaryStr = str;
    }

    public String[] getReturnValues() {
        return this.returnValues;
    }

    public void setReturnValues(String[] strArr) {
        this.returnValues = strArr;
    }

    public double[] getVats() {
        return this.vats;
    }

    public void setVats(double[] dArr) {
        this.vats = dArr;
    }

    public double[] getVatsamount() {
        return this.vatsamount;
    }

    public void setVatsamount(double[] dArr) {
        this.vatsamount = dArr;
    }

    public double[] getTotvats() {
        return this.totvats;
    }

    public void setTotvats(double[] dArr) {
        this.totvats = dArr;
    }

    public double[] getTotvatsamount() {
        return this.totvatsamount;
    }

    public void setTotvatsamount(double[] dArr) {
        this.totvatsamount = dArr;
    }

    public int getSayi() {
        return this.sayi;
    }

    public void setSayi(int i2) {
        this.sayi = i2;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i2) {
        this.count = i2;
    }

    public boolean isIlkSayfa() {
        return this.ilkSayfa;
    }

    public void setIlkSayfa(boolean z) {
        this.ilkSayfa = z;
    }

    public double getPageTotal() {
        return this.pageTotal;
    }

    public void setPageTotal(double d2) {
        this.pageTotal = d2;
    }

    public double getNakliyeYekun() {
        return this.nakliyeYekun;
    }

    public void setNakliyeYekun(double d2) {
        this.nakliyeYekun = d2;
    }

    public int getLineSize() {
        return this.lineSize;
    }

    public void setLineSize(int i2) {
        this.lineSize = i2;
    }

    public int getPrintLineCount() {
        return this.printLineCount;
    }

    public void setPrintLineCount(int i2) {
        this.printLineCount = i2;
    }

    public boolean isShowHeader() {
        return this.isShowHeader;
    }

    public void setShowHeader(boolean z) {
        this.isShowHeader = z;
    }

    public boolean isShowFooter() {
        return this.isShowFooter;
    }

    public void setShowFooter(boolean z) {
        this.isShowFooter = z;
    }

    public int getConverttren() {
        return this.converttren;
    }

    public void setConverttren(int i2) {
        this.converttren = i2;
    }

    public int getDetailRowCount() {
        return this.detailRowCount;
    }

    public void setDetailRowCount(int i2) {
        this.detailRowCount = i2;
    }

    public double[] getUnitTotals() {
        return this.unitTotals;
    }

    public void setUnitTotals(double[] dArr) {
        this.unitTotals = dArr;
    }

    public double getPageAmountTotal() {
        return this.pageAmountTotal;
    }

    public void setPageAmountTotal(double d2) {
        this.pageAmountTotal = d2;
    }

    public double getAmountTotal() {
        return this.amountTotal;
    }

    public void setAmountTotal(double d2) {
        this.amountTotal = d2;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getGrossWeight() {
        return this.grossWeight;
    }

    public double getNetVolume() {
        return this.netVolume;
    }

    public void setWeight(double d2) {
        this.weight = d2;
    }

    public void setGrossWeight(double d2) {
        this.grossWeight = d2;
    }

    public void setNetVolume(double d2) {
        this.netVolume = d2;
    }
}
