package com.proje.mobilesales.features.dbmodel;

public class DefOrder {
    private String firmNr;
    private String formGroup;
    private String innerSql;
    private int logicalRef;
    private String mDefinition;
    private int mLineNo;
    private double minStock;
    private int penetPrice;
    private int penetType;
    private int productClassRef;
    private int stype;
    private int type;
    private int useMinStock;
    private int usePenet;
    private int whouse;
    private int workType;
    public int getLogicalRef() {
        return this.logicalRef;
    }
    public void setLogicalRef(int i2) {
        this.logicalRef = i2;
    }
    public int getLineNo() {
        return this.mLineNo;
    }
    public void setLineNo(int i2) {
        this.mLineNo = i2;
    }
    public String getDefinition() {
        return this.mDefinition;
    }
    public void setDefinition(String str) {
        this.mDefinition = str;
    }
    public int getWhouse() {
        return this.whouse;
    }
    public void setWhouse(int i2) {
        this.whouse = i2;
    }
    public int getStype() {
        return this.stype;
    }
    public void setStype(int i2) {
        this.stype = i2;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int i2) {
        this.type = i2;
    }
    public int getWorkType() {
        return this.workType;
    }
    public void setWorkType(int i2) {
        this.workType = i2;
    }
    public String getFormGroup() {
        return this.formGroup;
    }
    public void setFormGroup(String str) {
        this.formGroup = str;
    }
    public int getProductClassRef() {
        return this.productClassRef;
    }
    public void setProductClassRef(int i2) {
        this.productClassRef = i2;
    }
    public int getUseMinStock() {
        return this.useMinStock;
    }
    public void setUseMinStock(int i2) {
        this.useMinStock = i2;
    }
    public double getMinStock() {
        return this.minStock;
    }
    public void setMinStock(double d2) {
        this.minStock = d2;
    }
    public int getUsePenet() {
        return this.usePenet;
    }
    public void setUsePenet(int i2) {
        this.usePenet = i2;
    }
    public int getPenetType() {
        return this.penetType;
    }
    public void setPenetType(int i2) {
        this.penetType = i2;
    }
    public int getPenetPrice() {
        return this.penetPrice;
    }
    public void setPenetPrice(int i2) {
        this.penetPrice = i2;
    }
    public String getFirmNr() {
        return this.firmNr;
    }
    public void setFirmNr(String str) {
        this.firmNr = str;
    }
    public String getInnerSql() {
        return this.innerSql;
    }
    public void setInnerSql(String str) {
        this.innerSql = str;
    }
    public String toString() {
        return getDefinition();
    }
}
