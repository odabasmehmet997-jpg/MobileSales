package com.proje.mobilesales.features.model;

public class FicheShort {
    private String explanation;
    private String ficheDate;
    private String ficheRef;
    private int logicalRef;
    private double total;
    private boolean transfer;
    private int type;

    public int getLogicalRef() {
        return logicalRef;
    }

    public void setLogicalRef(final int i2) {
        logicalRef = i2;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(final double d2) {
        total = d2;
    }

    public String getFicheDate() {
        return ficheDate;
    }

    public void setFicheDate(final String str) {
        ficheDate = str;
    }

    public boolean isTransfer() {
        return transfer;
    }

    public void setTransfer(final boolean z) {
        transfer = z;
    }

    public String getFicheRef() {
        return ficheRef;
    }

    public void setFicheRef(final String str) {
        ficheRef = str;
    }

    public int getType() {
        return type;
    }

    public void setType(final int i2) {
        type = i2;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(final String str) {
        explanation = str;
    }
}
