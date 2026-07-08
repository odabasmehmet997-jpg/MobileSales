package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(alterVersion = 43, name = "VARIANTUNITS")

public class VariantUnit {

    @Column(name = "CODE")
    private String code;

    @Column(name = "CONVFACT1", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double convfact1;

    @Column(name = "CONVFACT2", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double convfact2;

    @Column(name = "GROSSVOLUME", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double grossVolume;

    @Column(name = "GROSSVOLREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int grossVolumeRef;

    @Column(name = "GROSSWEIGHT", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double grossWeight;

    @Column(name = "GROSSWGHTREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int grossWeightRef;

    @Column(isAllSame = false, name = "ITEMCODE", shared = @ColumnProperty(useProperty = false))
    private String itemCode;

    @Column(isAllSame = false, name = "ITEMREF", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int itemRef;

    @Column(isAllSame = false, name = "LOGICALREF", netsis = @ColumnProperty(isAutoIncrement = EmbeddingCompat.DEBUG, isPrimaryKey = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER), shared = @ColumnProperty(isNotNull = EmbeddingCompat.DEBUG, isUnique = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    private int logicalRef;

    @Column(isAllSame = false, name = "UNITSETCODE", shared = @ColumnProperty(useProperty = false))
    private String unitSetCode;

    @Column(isAllSame = false, name = "UNITSETREF", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int unitSetRef;

    @Column(isAllSame = false, name = "VARIANTCODE", shared = @ColumnProperty(useProperty = false))
    private String variantCode;

    @Column(isAllSame = false, name = "VARIANTREF", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int variantRef;

    @Column(name = "VOLUME", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double volume;

    @Column(name = "VOLUMEREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int volumeRef;

    @Column(name = "WEIGHT", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double weight;

    @Column(name = "WEIGHTREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int weightRef;

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }

    public int getItemRef() {
        return this.itemRef;
    }

    public void setItemRef(int i2) {
        this.itemRef = i2;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public double getConvfact1() {
        return this.convfact1;
    }

    public void setConvfact1(double d2) {
        this.convfact1 = d2;
    }

    public double getConvfact2() {
        return this.convfact2;
    }

    public void setConvfact2(double d2) {
        this.convfact2 = d2;
    }

    public int getUnitSetRef() {
        return this.unitSetRef;
    }

    public void setUnitSetRef(int i2) {
        this.unitSetRef = i2;
    }

    public int getVariantRef() {
        return this.variantRef;
    }

    public void setVariantRef(int i2) {
        this.variantRef = i2;
    }

    public String getVariantCode() {
        return this.variantCode;
    }

    public void setVariantCode(String str) {
        this.variantCode = str;
    }

    public String getUnitSetCode() {
        return this.unitSetCode;
    }

    public void setUnitSetCode(String str) {
        this.unitSetCode = str;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(String str) {
        this.itemCode = str;
    }
}
