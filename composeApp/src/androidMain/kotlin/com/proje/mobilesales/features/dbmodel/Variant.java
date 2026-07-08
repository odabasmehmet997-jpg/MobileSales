package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnIndex;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.TableIndex;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(name = "VARIANTS")
@TableIndex(isUnique = EmbeddingCompat.DEBUG, name = "idx_Variant")

public class Variant {

    @Column(name = "ACTIVE", shared = @ColumnProperty(alterVersion = 185, defaultValue = "0", type = Column.ColumnValueTypes.INTEGER))
    private int active;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "CMDATE", shared = @ColumnProperty(alterVersion = 185, type = Column.ColumnValueTypes.REAL))
    private double cmDate;

    @Column(isAllSame = false, name = "CODE", netsis = @ColumnProperty(useUpdate = EmbeddingCompat.DEBUG), netsisName = "CODE")
    @ColumnIndex
    private String code;

    @Column(isAllSame = false, name = "ENDDATE", netsisName = "ENDDATE", shared = @ColumnProperty(alterVersion = 185, useProperty = false))
    private int endDate;

    @Column(isAllSame = false, name = "ITEMCODE", netsis = @ColumnProperty(useUpdate = EmbeddingCompat.DEBUG), netsisName = "STOK_KODU", shared = @ColumnProperty(useProperty = false))
    @ColumnIndex
    private String itemCode;

    @Column(isAllSame = false, name = "ITEMREF", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int itemRef;

    @Column(isAllSame = false, name = "LOGICALREF", netsis = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER, useWhereStatement = false), netsisName = "RowNum", shared = @ColumnProperty(isNotNull = EmbeddingCompat.DEBUG, isUnique = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    private int logicalRef;

    @Column(name = "NAME", netsisName = "NAME")
    private String name;

    @Column(isAllSame = false, name = "STARTDATE", netsisName = "STARTDATE", shared = @ColumnProperty(alterVersion = 185, useProperty = false))
    private int startDate;

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

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(String str) {
        this.itemCode = str;
    }

    public double getCmDate() {
        return this.cmDate;
    }

    public void setCmDate(double d2) {
        this.cmDate = d2;
    }

    public int getActive() {
        return this.active;
    }

    public void setActive(int i2) {
        this.active = i2;
    }

    public int getStartDate() {
        return this.startDate;
    }

    public void setStartDate(int i2) {
        this.startDate = i2;
    }

    public int getEndDate() {
        return this.endDate;
    }

    public void setEndDate(int i2) {
        this.endDate = i2;
    }
}
