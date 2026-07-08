package com.proje.mobilesales.features.dbmodel;


public class DefOrderDetail {
    private int colorId;
    private int defOrderId;
    private int isslctUnit;
    private String itemCode;
    private int itemRef;
    private int lineType;
    private int logicalRef;
    private String mCode;
    private String mGroup;
    private int mLineNo;
    private double maxAmount;
    private double minAmount;
    private int unit;
    private String unitCode;
    private int unitRef;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public int getDefOrderId() {
        return this.defOrderId;
    }
    public void setDefOrderId(int i2) {
        this.defOrderId = i2;
    }
    public int getLineNo() {
        return this.mLineNo;
    }
    public void setLineNo(int i2) {
        this.mLineNo = i2;
    }
    public int getLineType() {
        return this.lineType;
    }
    public void setLineType(int i2) {
        this.lineType = i2;
    }
    public String getGroup() {
        return this.mGroup;
    }
    public void setGroup(String str) {
        this.mGroup = str;
    }
    public int getItemRef() {
        return this.itemRef;
    }
    public void setItemRef(int i2) {
        this.itemRef = i2;
    }
    public double getMinAmount() {
        return this.minAmount;
    }
    public void setMinAmount(double d2) {
        this.minAmount = d2;
    }
    public double getMaxAmount() {
        return this.maxAmount;
    }
    public void setMaxAmount(double d2) {
        this.maxAmount = d2;
    }
    public int getIsslctUnit() {
        return this.isslctUnit;
    }
    public void setIsslctUnit(int i2) {
        this.isslctUnit = i2;
    }
    public int getUnit() {
        return this.unit;
    }
    public void setUnit(int i2) {
        this.unit = i2;
    }
    public String getmCode() {
        return this.mCode;
    }
    public void setmCode(String str) {
        this.mCode = str;
    }
    public int getColorId() {
        return this.colorId;
    }
    public void setColorId(int i2) {
        this.colorId = i2;
    }
    public int getUnitRef() {
        return this.unitRef;
    }
    public void setUnitRef(int i2) {
        this.unitRef = i2;
    }
    public String getItemCode() {
        return this.itemCode;
    }
    public void setItemCode(String str) {
        this.itemCode = str;
    }
    public String getUnitCode() {
        return this.unitCode;
    }
    public void setUnitCode(String str) {
        this.unitCode = str;
    }
}
