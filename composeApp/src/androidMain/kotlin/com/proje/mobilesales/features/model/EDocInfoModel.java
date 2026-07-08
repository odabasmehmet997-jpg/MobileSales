package com.proje.mobilesales.features.model;

public class EDocInfoModel {
    private int acceptEArchive;
    private int acceptEDespatch;
    private int acceptEInvoice;
    private String clCode;
    private int clRef;
    private String deliveryFirm;
    private int profileIdEDespatch;

    public int getClRef() {
        return clRef;
    }

    public void setClRef(final int i2) {
        clRef = i2;
    }

    public String getClCode() {
        return clCode;
    }

    public void setClCode(final String str) {
        clCode = str;
    }

    public String getDeliveryFirm() {
        return deliveryFirm;
    }

    public void setDeliveryFirm(final String str) {
        deliveryFirm = str;
    }

    public int getAcceptEDespatch() {
        return acceptEDespatch;
    }

    public void setAcceptEDespatch(final int i2) {
        acceptEDespatch = i2;
    }

    public int getProfileIdEDespatch() {
        return profileIdEDespatch;
    }

    public void setProfileIdEDespatch(final int i2) {
        profileIdEDespatch = i2;
    }

    public int getAcceptEInvoice() {
        return acceptEInvoice;
    }

    public void setAcceptEInvoice(final int i2) {
        acceptEInvoice = i2;
    }

    public boolean isEInvoiceCustomer() {
        return 1 == this.acceptEInvoice;
    }

    public boolean isEDespatchCustomer() {
        return 1 == this.acceptEDespatch;
    }

    public int getAcceptEArchive() {
        return acceptEArchive;
    }

    public void setAcceptEArchive(final int i2) {
        acceptEArchive = i2;
    }

    public boolean isAcceptEDespatch() {
        return 1 == this.acceptEDespatch;
    }

    public boolean isAcceptEInvoice() {
        return 1 == this.acceptEInvoice;
    }

    public boolean isAcceptEArchive() {
        return 1 == this.acceptEArchive;
    }
}
