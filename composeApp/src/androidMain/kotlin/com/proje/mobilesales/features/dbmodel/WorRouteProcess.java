package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(alterVersion = 141, name = "WORROUTEPROCESS")

public class WorRouteProcess {

    @Column(name = "CREATEDDATE")
    private String createdDate;

    @Column(name = "FICHEREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int ficheRef;

    @Column(name = "LOGICALREF", shared = @ColumnProperty(isAutoIncrement = EmbeddingCompat.DEBUG, isPrimaryKey = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    private int logicalRef;

    @Column(name = "PLANNEDSEQUENCE", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int plannedSequence;

    @Column(name = "REALSEQUENCE", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int realSequence;

    @Column(name = "ROUTEDAYREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int routeDayRef;

    @Column(name = "ROUTEPLANREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int routePlanRef;

    @Column(name = "ROUTEUSERCUSTOMERREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int routeUserCustomerRef;

    @Column(name = "TYPE", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int type;

    public WorRouteProcess() {
    }

    public WorRouteProcess(int i2, int i3, int i4, int i5, int i6, int i7, int i8, String str) {
        this.routePlanRef = i2;
        this.routeDayRef = i3;
        this.routeUserCustomerRef = i4;
        this.type = i5;
        this.ficheRef = i6;
        this.plannedSequence = i7;
        this.realSequence = i8;
        this.createdDate = str;
    }

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }

    public int getRoutePlanRef() {
        return this.routePlanRef;
    }

    public void setRoutePlanRef(int i2) {
        this.routePlanRef = i2;
    }

    public int getRouteDayRef() {
        return this.routeDayRef;
    }

    public void setRouteDayRef(int i2) {
        this.routeDayRef = i2;
    }

    public int getRouteUserCustomerRef() {
        return this.routeUserCustomerRef;
    }

    public void setRouteUserCustomerRef(int i2) {
        this.routeUserCustomerRef = i2;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public int getFicheRef() {
        return this.ficheRef;
    }

    public void setFicheRef(int i2) {
        this.ficheRef = i2;
    }

    public int getPlannedSequence() {
        return this.plannedSequence;
    }

    public void setPlannedSequence(int i2) {
        this.plannedSequence = i2;
    }

    public int getRealSequence() {
        return this.realSequence;
    }

    public void setRealSequence(int i2) {
        this.realSequence = i2;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String str) {
        this.createdDate = str;
    }
}
