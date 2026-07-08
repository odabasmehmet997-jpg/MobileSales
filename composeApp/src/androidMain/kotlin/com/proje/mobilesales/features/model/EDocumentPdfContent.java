package com.proje.mobilesales.features.model;

import com.proje.mobilesales.features.dbmodel.Firm;
import java.util.List;

public class EDocumentPdfContent {
    private EDespatchPdfHeader mEDespatchPdfHeader;
    private List<EDocumentPdfDetail> mEDocumentPdfDetailList;
    private List<EDocumentPdfDetailNetsis> mEDocumentPdfDetailNetsisList;
    private EDocumentPdfHeaderNetsis mEDocumentPdfHeaderNetsis;
    private EInvoicePdfHeader mEInvoicePdfHeader;
    private String mErrorDesc;
    private Firm mFirm;
    private String mLocalCurrencyCode;

    public EDocumentPdfContent(final EDespatchPdfHeader eDespatchPdfHeader, final List<EDocumentPdfDetail> list, final String str) {
        mEDespatchPdfHeader = eDespatchPdfHeader;
        mEDocumentPdfDetailList = list;
        mErrorDesc = str;
    }

    public EDocumentPdfContent(final EInvoicePdfHeader eInvoicePdfHeader, final List<EDocumentPdfDetail> list, final String str) {
        mEInvoicePdfHeader = eInvoicePdfHeader;
        mEDocumentPdfDetailList = list;
        mErrorDesc = str;
    }

    public EDocumentPdfContent(final EDocumentPdfHeaderNetsis eDocumentPdfHeaderNetsis, final List<EDocumentPdfDetailNetsis> list, final String str) {
        mEDocumentPdfHeaderNetsis = eDocumentPdfHeaderNetsis;
        mEDocumentPdfDetailNetsisList = list;
        mErrorDesc = str;
    }

    public EDespatchPdfHeader getEDespatchPdfHeader() {
        return mEDespatchPdfHeader;
    }

    public void setEDespatchPdfHeader(final EDespatchPdfHeader eDespatchPdfHeader) {
        mEDespatchPdfHeader = eDespatchPdfHeader;
    }

    public EInvoicePdfHeader getEInvoicePdfHeader() {
        return mEInvoicePdfHeader;
    }

    public void setEInvoicePdfHeader(final EInvoicePdfHeader eInvoicePdfHeader) {
        mEInvoicePdfHeader = eInvoicePdfHeader;
    }

    public EDocumentPdfHeaderNetsis getEDocumentPdfHeaderNetsis() {
        return mEDocumentPdfHeaderNetsis;
    }

    public void setEDocumentPdfHeaderNetsis(final EDocumentPdfHeaderNetsis eDocumentPdfHeaderNetsis) {
        mEDocumentPdfHeaderNetsis = eDocumentPdfHeaderNetsis;
    }

    public List<EDocumentPdfDetailNetsis> getmEDocumentPdfDetailNetsisList() {
        return mEDocumentPdfDetailNetsisList;
    }

    public void setmEDocumentPdfDetailNetsisList(final List<EDocumentPdfDetailNetsis> list) {
        mEDocumentPdfDetailNetsisList = list;
    }

    public String getErrorDesc() {
        return mErrorDesc;
    }

    public void setErrorDesc(final String str) {
        mErrorDesc = str;
    }

    public Firm getFirm() {
        return mFirm;
    }

    public void setFirm(final Firm firm) {
        mFirm = firm;
    }

    public List<EDocumentPdfDetail> getEDocumentPdfDetailList() {
        return mEDocumentPdfDetailList;
    }

    public void setEDocumentPdfDetailList(final List<EDocumentPdfDetail> list) {
        mEDocumentPdfDetailList = list;
    }

    public String getLocalCurrencyCode() {
        return mLocalCurrencyCode;
    }

    public void setLocalCurrencyCode(final String str) {
        mLocalCurrencyCode = str;
    }
}
