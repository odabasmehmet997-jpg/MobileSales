package com.proje.mobilesales.features.product.model.database;

public final class ItemGroupCode {
    private double cmDate;
    private int codeType;
    private String grpCode;
    private String grpName;
    private int logicalRef;
    public int getLogicalRef() {
        return logicalRef;
    }
    public void setLogicalRef(final int i) {
        logicalRef = i;
    }
    public int getCodeType() {
        return codeType;
    }
    public void setCodeType(final int i) {
        codeType = i;
    }
    public String getGrpCode() {
        return grpCode;
    }
    public void setGrpCode(final String str) {
        grpCode = str;
    }
    public String getGrpName() {
        return grpName;
    }
    public void setGrpName(final String str) {
        grpName = str;
    }
    public double getCmDate() {
        return cmDate;
    }
    public void setCmDate(final double d) {
        cmDate = d;
    }
}
