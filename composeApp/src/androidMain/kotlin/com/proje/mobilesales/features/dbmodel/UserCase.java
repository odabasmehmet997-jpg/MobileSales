package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(alterVersion = 103, name = "USERCASE")

public class UserCase {

    @Column(name = "CCURRENCY", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(alterVersion = 139, type = Column.ColumnValueTypes.INTEGER))
    private int cCurrency;

    @Column(name = "CODE", netsisName = "CODE", shared = @ColumnProperty(type = Column.ColumnValueTypes.VARCHAR))
    private String code;

    @Column(name = "CURRATETYPE", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(alterVersion = 139, type = Column.ColumnValueTypes.INTEGER))
    private int curRateType;

    @Column(name = "FIXEDCURRTYPE", netsis = @ColumnProperty(useProperty = false), shared = @ColumnProperty(alterVersion = 139, type = Column.ColumnValueTypes.INTEGER))
    private int fixedCurrType;

    @Column(name = "KASA", netsisName = "KASA")
    private String kasa;

    @Column(isAllSame = false, name = "LOGICALREF", netsis = @ColumnProperty(isAutoIncrement = EmbeddingCompat.DEBUG, isPrimaryKey = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER), shared = @ColumnProperty(isNotNull = EmbeddingCompat.DEBUG, isUnique = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    private int logicalRef;

    @Column(isAllSame = false, name = "MUHCODE", netsisName = "MUHCODE", shared = @ColumnProperty(useProperty = false))
    private String muhCode;

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }

    public String getKasa() {
        return this.kasa;
    }

    public void setKasa(String str) {
        this.kasa = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMuhCode() {
        return this.muhCode;
    }

    public void setMuhCode(String str) {
        this.muhCode = str;
    }

    public int getcCurrency() {
        return this.cCurrency;
    }

    public void setcCurrency(int i2) {
        this.cCurrency = i2;
    }

    public int getCurRateType() {
        return this.curRateType;
    }

    public void setCurRateType(int i2) {
        this.curRateType = i2;
    }

    public int getFixedCurrType() {
        return this.fixedCurrType;
    }

    public void setFixedCurrType(int i2) {
        this.fixedCurrType = i2;
    }
}
