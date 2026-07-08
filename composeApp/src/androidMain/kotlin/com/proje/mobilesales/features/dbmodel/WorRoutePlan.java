package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(alterVersion = 140, name = "WORROUTEPLAN")

public class WorRoutePlan {

    @Column(name = "CLIENTREF", netsisName = "CLIENTREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    private String clientRef;

    @Column(name = "LOGICALREF", netsisName = "LOGICALREF", shared = @ColumnProperty(isNotNull = EmbeddingCompat.DEBUG, isUnique = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    private int logicalRef;

    @Column(name = "ROUTEID", netsisName = "ROUTEID", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int routeId;

    @Column(name = "SEQUENCE", netsisName = "SEQUENCE", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int sequence;

    @Column(name = "SHIPMENTREF", netsisName = "SHIPMENTREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    private String shipmentRef;

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

    public String getClientRef() {
        return this.clientRef;
    }

    public void setClientRef(String str) {
        this.clientRef = str;
    }

    public String getShipmentRef() {
        return this.shipmentRef;
    }

    public void setShipmentRef(String str) {
        this.shipmentRef = str;
    }

    public int getSequence() {
        return this.sequence;
    }

    public void setSequence(int i2) {
        this.sequence = i2;
    }
}
