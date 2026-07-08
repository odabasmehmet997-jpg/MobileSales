package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnIndex;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.TableIndex;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(alterVersion = 163, name = "VARIANTSTOCK")
@TableIndex(isUnique = EmbeddingCompat.DEBUG, name = "idx_VariantStock")

public class VariantStock {

    @Column(isAllSame = false, name = "ITEMCODE", netsis = @ColumnProperty(useUpdate = EmbeddingCompat.DEBUG), netsisName = "ITEMCODE", shared = @ColumnProperty(useProperty = false))
    @ColumnIndex
    private String itemCode;

    @Column(isAllSame = false, name = "ITEMREF", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER, useUpdate = EmbeddingCompat.DEBUG))
    @ColumnIndex
    private int itemRef;

    @Column(name = "ONHAND", netsisName = "ACTUALSTOCK", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double onHand;

    @Column(name = "REALSTOCK", netsisName = "REALSTOCK", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double realStock;

    @Column(isAllSame = false, name = "VARIANTCODE", netsis = @ColumnProperty(useUpdate = EmbeddingCompat.DEBUG), netsisName = "VARIANTCODE", shared = @ColumnProperty(useProperty = false))
    @ColumnIndex
    private String variantCode;

    @Column(isAllSame = false, name = "VARIANTREF", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER, useUpdate = EmbeddingCompat.DEBUG))
    @ColumnIndex
    private int variantRef;

    @Column(name = "WAREHOUSE", netsisName = "WAREHOUSE")
    private String wareHouse;

    @Column(name = "WAREHOUSENR", netsisName = "DEPO_KODU", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER, useUpdate = EmbeddingCompat.DEBUG))
    @ColumnIndex
    private int wareHouseNr;

    public VariantStock() {
    }

    public VariantStock(int i2, int i3, int i4) {
        this.itemRef = i2;
        this.variantRef = i3;
        this.wareHouseNr = i4;
    }

    public int getWareHouseNr() {
        return this.wareHouseNr;
    }

    public void setWareHouseNr(int i2) {
        this.wareHouseNr = i2;
    }

    public String getWareHouse() {
        return this.wareHouse;
    }

    public void setWareHouse(String str) {
        this.wareHouse = str;
    }

    public int getItemRef() {
        return this.itemRef;
    }

    public void setItemRef(int i2) {
        this.itemRef = i2;
    }

    public double getRealStock() {
        return this.realStock;
    }

    public void setRealStock(double d2) {
        this.realStock = d2;
    }

    public double getOnHand() {
        return this.onHand;
    }

    public void setOnHand(double d2) {
        this.onHand = d2;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(String str) {
        this.itemCode = str;
    }

    public String getVariantCodeCode() {
        return this.variantCode;
    }

    public void setVariantCodeCode(String str) {
        this.variantCode = str;
    }

    public int getVariantRef() {
        return this.variantRef;
    }

    public void setVariantRef(int i2) {
        this.variantRef = i2;
    }
}
