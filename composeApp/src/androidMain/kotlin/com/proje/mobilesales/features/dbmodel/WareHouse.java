package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(name = "WAREHOUSE")

public class WareHouse {

    @Column(name = "AMBAR", netsisName = "NAME")
    private String ambar;

    @Column(name = "CODE", netsisName = "CODE", shared = @ColumnProperty(type = Column.ColumnValueTypes.VARCHAR))
    private String code;

    @Column(name = "COSTGRP", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int costGrp;

    @Column(name = "DIVISNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int divisNr;

    @Column(name = "FACTNR", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int factNr;

    @Column(name = "FIRMREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int firmRef;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "LOGICALREF", shared = @ColumnProperty(isNotNull = EmbeddingCompat.DEBUG, isUnique = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    private int logicalRef;

    @Column(name = "NEGATIVEBALANCECONTROL", netsisName = "EKSIBAKIYE", shared = @ColumnProperty(alterVersion = 240))
    private String negativeBalanceControl;

    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "NEGATIVEBALANCESHOW", netsisName = "C_YEDEK2", shared = @ColumnProperty(alterVersion = 240))
    private String negativeBalanceShow;

    /* renamed from: nr */
    @Column(isAllSameMappingName = EmbeddingCompat.DEBUG, name = "NR", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int f1249nr;

    @Column(name = "PARENTREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int parentRef;

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }

    public int getNr() {
        return this.f1249nr;
    }

    public void setNr(int i2) {
        this.f1249nr = i2;
    }

    public String getAmbar() {
        return this.ambar;
    }

    public void setAmbar(String str) {
        this.ambar = str;
    }

    public int getDivisNr() {
        return this.divisNr;
    }

    public void setDivisNr(int i2) {
        this.divisNr = i2;
    }

    public int getFactNr() {
        return this.factNr;
    }

    public void setFactNr(int i2) {
        this.factNr = i2;
    }

    public int getCostGrp() {
        return this.costGrp;
    }

    public void setCostGrp(int i2) {
        this.costGrp = i2;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public int getParentRef() {
        return this.parentRef;
    }

    public void setParentRef(int i2) {
        this.parentRef = i2;
    }

    public int getFirmRef() {
        return this.firmRef;
    }

    public void setFirmRef(int i2) {
        this.firmRef = i2;
    }

    public String getNegativeBalanceControl() {
        return this.negativeBalanceControl;
    }

    public void setNegativeBalanceControl(String str) {
        this.negativeBalanceControl = str;
    }

    public String getNegativeBalanceShow() {
        return this.negativeBalanceShow;
    }

    public void setNegativeBalanceShow(String str) {
        this.negativeBalanceShow = str;
    }
}
