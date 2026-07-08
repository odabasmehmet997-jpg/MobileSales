package com.proje.mobilesales.features.dbmodel;

import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(alterVersion = 140, name = "WORROUTE")

public class WorRoute {

    @Column(name = "CREATEDDATE", netsisName = "CREATEDDATE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    private String createdDate;

    @Column(name = "LOGICALREF", netsisName = "LOGICALREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int logicalRef;

    @Column(name = "PERIODSTARTDATE", netsisName = "PERIODSTARTDATE", shared = @ColumnProperty(alterVersion = 141, type = Column.ColumnValueTypes.TEXT))
    private String periodStartDate;

    @Column(name = "ROUTECODE", netsisName = "ROUTECODE", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    private String routeCode;

    @Column(name = "ROUTEDEFINITION", netsisName = "ROUTEDEFINITION", shared = @ColumnProperty(type = Column.ColumnValueTypes.TEXT))
    private String routeDefinition;

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

    public String getRouteCode() {
        return this.routeCode;
    }

    public void setRouteCode(String str) {
        this.routeCode = str;
    }

    public String getRouteDefinition() {
        return this.routeDefinition;
    }

    public void setRouteDefinition(String str) {
        this.routeDefinition = str;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String str) {
        this.createdDate = str;
    }

    public String getPeriodStartDate() {
        return this.periodStartDate;
    }

    public void setPeriodStartDate(String str) {
        this.periodStartDate = str;
    }
}
