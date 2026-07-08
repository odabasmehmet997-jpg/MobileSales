package com.proje.mobilesales.features.dbmodel;

import androidx.window.embedding.EmbeddingCompat;
import com.proje.mobilesales.core.annotation.Column;
import com.proje.mobilesales.core.annotation.ColumnProperty;
import com.proje.mobilesales.core.annotation.Tables;


@Tables(alterVersion = 81, name = "Units")

public class Units {

    @Column(name = "AREA", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double area;

    @Column(name = "AREAREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int areaRef;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CONVFACT1", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double convfact1;

    @Column(name = "CONVFACT2", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double convfact2;

    @Column(name = "DIVUNIT", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int divUnit;

    @Column(name = "GLOBALCODE")
    private String globalCode;

    @Column(name = "HEIGHT", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double height;

    @Column(name = "HEIGHTREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int heightRef;

    @Column(name = "LENGTH", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double lenght;

    @Column(name = "LENGTHREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int lengthRef;

    @Column(name = "LINENR", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int lineNr;

    @Column(name = "LOGICALREF", shared = @ColumnProperty(isPrimaryKey = EmbeddingCompat.DEBUG, type = Column.ColumnValueTypes.INTEGER))
    private int logicalRef;

    @Column(name = "MAINUNIT", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int mainUnit;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UNITSETREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int unitSetRef;

    @Column(name = "VOLUME", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double volume;

    @Column(name = "VOLUMEREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int volumeRef;

    @Column(name = "WEIGHT", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double weight;

    @Column(name = "WEIGHTREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int weightRef;

    @Column(name = "WIDTH", shared = @ColumnProperty(type = Column.ColumnValueTypes.REAL))
    private double width;

    @Column(name = "WIDTHREF", shared = @ColumnProperty(type = Column.ColumnValueTypes.INTEGER))
    private int widthRef;

    public int getLogicalRef() {
        return this.logicalRef;
    }

    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
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

    public int getUnitSetRef() {
        return this.unitSetRef;
    }

    public void setUnitSetRef(int i2) {
        this.unitSetRef = i2;
    }

    public int getLineNr() {
        return this.lineNr;
    }

    public void setLineNr(int i2) {
        this.lineNr = i2;
    }

    public int getMainUnit() {
        return this.mainUnit;
    }

    public void setMainUnit(int i2) {
        this.mainUnit = i2;
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

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double d2) {
        this.width = d2;
    }

    public double getLenght() {
        return this.lenght;
    }

    public void setLenght(double d2) {
        this.lenght = d2;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double d2) {
        this.height = d2;
    }

    public double getArea() {
        return this.area;
    }

    public void setArea(double d2) {
        this.area = d2;
    }

    public double getVolume() {
        return this.volume;
    }

    public void setVolume(double d2) {
        this.volume = d2;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double d2) {
        this.weight = d2;
    }

    public int getWidthRef() {
        return this.widthRef;
    }

    public void setWidthRef(int i2) {
        this.widthRef = i2;
    }

    public int getLengthRef() {
        return this.lengthRef;
    }

    public void setLengthRef(int i2) {
        this.lengthRef = i2;
    }

    public int getHeightRef() {
        return this.heightRef;
    }

    public void setHeightRef(int i2) {
        this.heightRef = i2;
    }

    public int getAreaRef() {
        return this.areaRef;
    }

    public void setAreaRef(int i2) {
        this.areaRef = i2;
    }

    public int getVolumeRef() {
        return this.volumeRef;
    }

    public void setVolumeRef(int i2) {
        this.volumeRef = i2;
    }

    public int getWeightRef() {
        return this.weightRef;
    }

    public void setWeightRef(int i2) {
        this.weightRef = i2;
    }

    public String getGlobalCode() {
        return this.globalCode;
    }

    public void setGlobalCode(String str) {
        this.globalCode = str;
    }

    public int getDivUnit() {
        return this.divUnit;
    }

    public void setDivUnit(int i2) {
        this.divUnit = i2;
    }
}
