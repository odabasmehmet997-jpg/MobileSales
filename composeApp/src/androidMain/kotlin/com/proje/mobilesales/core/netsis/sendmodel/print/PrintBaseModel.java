package com.proje.mobilesales.core.netsis.sendmodel.print;

import com.proje.mobilesales.core.enums.SalesType;
import com.proje.mobilesales.core.netsis.sendmodel.cari.Cari;
import java.util.List;

public abstract class PrintBaseModel {
    private Cari mCustomer;
    private String mErrorDesc;
    private List<PrintExtraInfo> mPrintExtraInfoList;
    public abstract boolean hasData();
    public Cari getCustomer() {
        return this.mCustomer;
    }
    public void setCustomer(Cari cari) {
        this.mCustomer = cari;
    }
    public String getErrorDesc() {
        return this.mErrorDesc;
    }
    public void setErrorDesc(String str) {
        this.mErrorDesc = str;
    }
    public List<PrintExtraInfo> getPrintExtraInfo() {
        return this.mPrintExtraInfoList;
    }
    public void setPrintExtraInfo(List<PrintExtraInfo> list) {
        this.mPrintExtraInfoList = list;
    }

    public SalesType getmSalesType() {
        return null;
    }

    public void setSuccess(boolean b) {

    }
}
