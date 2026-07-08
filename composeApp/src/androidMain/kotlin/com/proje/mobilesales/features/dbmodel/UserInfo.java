package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;
import org.kxml2.wap.Wbxml;


@Tables(name = "USERINFO")

public class UserInfo {

    @Column(name = "CODE")
    private String code;

    @Column(name = "DOSHIP", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int doShip;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SALESMANNAME")
    private String salesmanName;

    @Column(name = "USERID", shared = @ColumnProperty(isNotNull = EmbeddingCompat.DEBUG, isUnique = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    private int userId;

    @Column(name = "DISTVEHICLE", shared = @ColumnProperty(alterVersion = Wbxml.STR_T, type = Column.ColumnValueTypes.INTEGER))
    private int vehicleRef;

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int i2) {
        this.userId = i2;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getDoShip() {
        return this.doShip;
    }

    public void setDoShip(int i2) {
        this.doShip = i2;
    }

    public String getSalesmanName() {
        return this.salesmanName;
    }

    public void setSalesmanName(String str) {
        this.salesmanName = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public int getVehicleRef() {
        return this.vehicleRef;
    }

    public void setVehicleRef(int i2) {
        this.vehicleRef = i2;
    }
}
