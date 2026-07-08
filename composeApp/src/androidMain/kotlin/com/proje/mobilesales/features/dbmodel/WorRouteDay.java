package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(alterVersion = 140, name = "WORROUTEDAY")

public class WorRouteDay {

    @Column(name = "DAY", netsisName = "DAY", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int day;

    @Column(name = "LOGICALREF", netsisName = "LOGICALREF", shared = @ColumnProperty(isNotNull = EmbeddingCompat.DEBUG, isUnique = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    private int logicalRef;

    @Column(name = "ROUTEID", netsisName = "ROUTEID", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int routeId;

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }

    public int getRouteId() {
        return this.routeId;
    }

    public void setRouteId(int i2) {
        this.routeId = i2;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int i2) {
        this.day = i2;
    }
}
