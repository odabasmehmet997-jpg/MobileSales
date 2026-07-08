package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(alterVersion = 140, name = "WORUSERCUSTOMERS")

public class WorUserCustomers {

    @Column(name = "CLIENTREF", netsisName = "CLIENTREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    private String clientRef;

    @Column(name = "LOGICALREF", netsisName = "LOGICALREF", shared = @ColumnProperty(isNotNull = EmbeddingCompat.DEBUG, isUnique = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    private int logicalRef;

    @Column(name = "SHIPMENTREF", netsisName = "SHIPMENTREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    private String shipmentRef;

    @Column(name = "USERID", netsisName = "USERID", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int userId;

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int i2) {
        this.userId = i2;
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
}
