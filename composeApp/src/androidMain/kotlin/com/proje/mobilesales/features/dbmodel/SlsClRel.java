package com.proje.mobilesales.features.dbmodel;

public class SlsClRel {

    private int clRef;
    private int f1245id;
    private int mLineno;
    private String visitBegDate;
    private int visitDay;
    public int getId() {
        return this.f1245id;
    }
    public void setId(int i2) {
        this.f1245id = i2;
    }
    public int getLineno() {
        return this.mLineno;
    }
    public void setLineno(int i2) {
        this.mLineno = i2;
    }
    public int getClRef() {
        return this.clRef;
    }
    public void setClRef(int i2) {
        this.clRef = i2;
    }
    public int getVisitDay() {
        return this.visitDay;
    }
    public void setVisitDay(int i2) {
        this.visitDay = i2;
    }
    public String getVisitBegDate() {
        return this.visitBegDate;
    }
    public void setVisitBegDate(String str) {
        this.visitBegDate = str;
    }
}
