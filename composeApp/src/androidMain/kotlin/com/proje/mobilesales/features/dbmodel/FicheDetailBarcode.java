package com.proje.mobilesales.features.dbmodel;

public class FicheDetailBarcode {

    private String barcode;
    private int detailRef;
    private int ficheType;
    private String itemCode;
    private int salesFicheRef;
    public int getDetailRef() {
        return this.detailRef;
    }
    public void setDetailRef(int i2) {
        this.detailRef = i2;
    }
    public String getItemCode() {
        return this.itemCode;
    }
    public void setItemCode(String str) {
        this.itemCode = str;
    }
    public int getFicheType() {
        return this.ficheType;
    }
    public void setFicheType(int i2) {
        this.ficheType = i2;
    }
    public String getBarcode() {
        return this.barcode;
    }
    public void setBarcode(String str) {
        this.barcode = str;
    }
    public int getSalesFicheRef() {
        return this.salesFicheRef;
    }
    public void setSalesFicheRef(int i2) {
        this.salesFicheRef = i2;
    }
}
