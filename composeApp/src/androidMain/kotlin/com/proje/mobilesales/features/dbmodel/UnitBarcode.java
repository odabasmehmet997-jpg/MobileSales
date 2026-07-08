package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnIndex;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.TableIndex;
import com.proje.mobilesales.core.annotation.Tables;


public class UnitBarcode {


    private String barcode;
    private int barcodeShift;
    private double cmdate;
    private int f1248id;
    private String itemCode;
    private int itemRef;
    private int typ;
    private String unitCode;
    private int unitRef;
    private String variantCode; 
    private int variantRef;

    public int getId() {
        return this.f1248id;
    }

    public void setId(int i2) {
        this.f1248id = i2;
    }

    public int getUnitRef() {
        return this.unitRef;
    }

    public void setUnitRef(int i2) {
        this.unitRef = i2;
    }

    public int getItemRef() {
        return this.itemRef;
    }

    public void setItemRef(int i2) {
        this.itemRef = i2;
    }

    public String getBarcode() {
        return this.barcode;
    }

    public void setBarcode(String str) {
        this.barcode = str;
    }

    public int getTyp() {
        return this.typ;
    }

    public void setTyp(int i2) {
        this.typ = i2;
    }

    public int getBarcodeShift() {
        return this.barcodeShift;
    }

    public void setBarcodeShift(int i2) {
        this.barcodeShift = i2;
    }

    public int getVariantRef() {
        return this.variantRef;
    }

    public void setVariantRef(int i2) {
        this.variantRef = i2;
    }

    public double getCmdate() {
        return this.cmdate;
    }

    public void setCmdate(double d2) {
        this.cmdate = d2;
    }

    public String getUnitCode() {
        return this.unitCode;
    }

    public void setUnitCode(String str) {
        this.unitCode = str;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(String str) {
        this.itemCode = str;
    }

    public String getVariantCode() {
        return this.variantCode;
    }

    public void setVariantCode(String str) {
        this.variantCode = str;
    }
}
